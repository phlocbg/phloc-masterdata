/**
 * Copyright (C) 2006-2015 phloc systems
 * http://www.phloc.com
 * office[at]phloc[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phloc.masterdata.price;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.compare.CompareUtils;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.vat.IVATItem;

/**
 * Default implementation of the {@link IPriceGraduation} and
 * {@link IReadonlyPriceGraduation} interfaces.
 * 
 * @author Philip Helger
 */
public class PriceGraduation implements IPriceGraduation
{
  private final ECurrency m_eCurrency;

  // All items sorted ascending by the minimum quantity
  private final List <IPriceGraduationItem> m_aItems = new ArrayList <IPriceGraduationItem> ();

  /**
   * Create a new price graduation valid only for the given currency and VAT
   * type.
   * 
   * @param eCurrency
   *        The currency to use. May not be <code>null</code>.
   */
  public PriceGraduation (@Nonnull final ECurrency eCurrency)
  {
    this.m_eCurrency = ValueEnforcer.notNull (eCurrency, "Currency");
  }

  public PriceGraduation (@Nonnull final IPriceGraduation aOther)
  {
    this (aOther.getCurrency ());
    for (final IPriceGraduationItem aItem : aOther.getAllItems ())
    {
      addItem (aItem);
    }
  }

  @Override
  @Nonnull
  public ECurrency getCurrency ()
  {
    return this.m_eCurrency;
  }

  @Override
  @Nullable
  public IPriceGraduationItem getSmallestMinimumQuantityItem ()
  {
    return ContainerHelper.getFirstElement (this.m_aItems);
  }

  @Override
  @Nullable
  public IPriceGraduationItem getLargestMinimumQuantityItem ()
  {
    return ContainerHelper.getLastElement (this.m_aItems);
  }

  @Override
  @Nonnull
  @ReturnsImmutableObject
  public List <? extends IPriceGraduationItem> getAllItems ()
  {
    return ContainerHelper.makeUnmodifiable (this.m_aItems);
  }

  @Override
  @Nullable
  public IPriceGraduationItem getItemOfIndex (@Nonnegative final int nIndex)
  {
    return ContainerHelper.getSafe (this.m_aItems, nIndex);
  }

  @Nonnull
  private IPriceGraduationItem _getItemOfQuantity (@Nonnegative final int nQuantity)
  {
    ValueEnforcer.isGT0 (nQuantity, "Quantity");

    IPriceGraduationItem aBestItem = null;
    for (final IPriceGraduationItem aItem : this.m_aItems)
    {
      if (aItem.getMinimumQuantity () > nQuantity)
        break;
      if (aBestItem == null ||
          CompareUtils.nullSafeCompare (aBestItem.getUnitNetAmount (), aItem.getUnitNetAmount ()) >= 0)
      {
        aBestItem = aItem;
      }
    }
    if (aBestItem == null)
      throw new IllegalStateException ("Failed to resolve item of quantity " + nQuantity + " in " + toString ());
    return aBestItem;
  }

  @Nonnull
  private IPrice _createPrice (@Nonnull final BigDecimal aNetAmount, @Nonnull final IVATItem aVAT)
  {
    return new Price (this.m_eCurrency, aNetAmount, aVAT);
  }

  @Override
  @Nonnull
  public IPrice getPrice (@Nonnull final IReadonlyPriceGraduationItem aItem, @Nonnull final IVATItem aVATItem)
  {
    ValueEnforcer.notNull (aItem, "Item");
    ValueEnforcer.notNull (aVATItem, "VATItem");

    if (!this.m_aItems.contains (aItem))
      throw new IllegalArgumentException ("passed item is not contained in this price graduation: " + aItem);
    return _createPrice (aItem.getUnitNetAmount (), aVATItem);
  }

  @Override
  @Nonnull
  public IPrice getSinglePriceOfQuantity (@Nonnegative final int nQuantity, @Nonnull final IVATItem aVATItem)
  {
    ValueEnforcer.notNull (aVATItem, "VATItem");
    return _createPrice (_getItemOfQuantity (nQuantity).getUnitNetAmount (), aVATItem);
  }

  @Override
  @Nonnull
  public IPrice getTotalPriceOfQuantity (@Nonnegative final int nQuantity, @Nonnull final IVATItem aVAT)
  {
    return getSinglePriceOfQuantity (nQuantity, aVAT).getMultiplied (nQuantity);
  }

  @Override
  @Nonnull
  public EChange addItem (@Nonnegative final int nMinimumQuantity, @Nonnull final BigDecimal aUnitNetAmount)
  {
    return addItem (nMinimumQuantity, aUnitNetAmount, false);
  }

  @Nonnull
  public EChange addItem (@Nonnegative final int nMinimumQuantity,
                          @Nonnull final BigDecimal aUnitNetAmount,
                          final boolean bAllowUpdate)
  {
    return addItem (new PriceGraduationItem (nMinimumQuantity, aUnitNetAmount), bAllowUpdate);
  }

  @Override
  @Nonnull
  public EChange addItem (@Nonnull final IPriceGraduationItem aItem)
  {
    return addItem (aItem, false);
  }

  private EChange addItem (@Nonnull final IPriceGraduationItem aItem, final boolean bAllowUpdate)
  {
    ValueEnforcer.notNull (aItem, "Item");

    // Check if an item with the exact same minimum quantity is already
    // contained.
    int nInsertIndex = 0;
    final int nNewItemQuantity = aItem.getMinimumQuantity ();
    for (final IPriceGraduationItem aExistingItem : this.m_aItems)
    {
      final int nExistingMinQuantity = aExistingItem.getMinimumQuantity ();
      if (nExistingMinQuantity == nNewItemQuantity)
      {
        if (bAllowUpdate)
        {
          this.m_aItems.set (nInsertIndex, aItem);
          return EChange.CHANGED;
        }
        throw new IllegalArgumentException ("Another item with the exact same quantity is already contained: " +
                                            nExistingMinQuantity);
      }
      // Find the insertion index
      if (nNewItemQuantity > nExistingMinQuantity)
        ++nInsertIndex;
    }

    this.m_aItems.add (nInsertIndex, aItem);
    return EChange.CHANGED;
  }

  public EChange removeItems (@Nonnull final List <IPriceGraduationItem> aItems)
  {
    return EChange.valueOf (this.m_aItems.removeAll (aItems));
  }

  @Override
  @Nonnull
  public EChange clear ()
  {
    if (this.m_aItems.isEmpty ())
      return EChange.UNCHANGED;
    this.m_aItems.clear ();
    return EChange.CHANGED;
  }

  @Override
  public boolean isEmpty ()
  {
    return this.m_aItems.isEmpty ();
  }

  @Override
  @Nonnegative
  public int size ()
  {
    return this.m_aItems.size ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PriceGraduation rhs = (PriceGraduation) o;
    return EqualsUtils.equals (this.m_eCurrency, rhs.m_eCurrency) && this.m_aItems.equals (rhs.m_aItems);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (this.m_eCurrency).append (this.m_aItems).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("currency", this.m_eCurrency)
                                       .append ("items", this.m_aItems)
                                       .toString ();
  }

  /**
   * Create a simple price graduation that contains one item with the minimum
   * quantity of 1.
   * 
   * @param aPrice
   *        The price to use. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static IPriceGraduation createSimple (@Nonnull final IPrice aPrice)
  {
    final PriceGraduation ret = new PriceGraduation (aPrice.getCurrency ());
    ret.addItem (new PriceGraduationItem (1, aPrice.getNetAmount ().getValue ()));
    return ret;
  }
}
