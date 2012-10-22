/**
 * Copyright (C) 2006-2012 phloc systems
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

import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
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
 * @author philip
 */
public final class PriceGraduation implements IPriceGraduation
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
    if (eCurrency == null)
      throw new NullPointerException ("currency");
    m_eCurrency = eCurrency;
  }

  @Nonnull
  public ECurrency getCurrency ()
  {
    return m_eCurrency;
  }

  @Nullable
  public IPriceGraduationItem getSmallestMinimumQuantityItem ()
  {
    return ContainerHelper.getFirstElement (m_aItems);
  }

  @Nullable
  public IPriceGraduationItem getLargestMinimumQuantityItem ()
  {
    return ContainerHelper.getLastElement (m_aItems);
  }

  @Nonnull
  @ReturnsImmutableObject
  public List <? extends IPriceGraduationItem> getAllItems ()
  {
    return ContainerHelper.makeUnmodifiable (m_aItems);
  }

  @Nullable
  public IPriceGraduationItem getItemOfIndex (@Nonnegative final int nIndex)
  {
    return ContainerHelper.getSafe (m_aItems, nIndex);
  }

  @Nonnull
  private IPriceGraduationItem _getItemOfQuantity (@Nonnegative final int nQuantity)
  {
    if (nQuantity < 1)
      throw new IllegalArgumentException ("The quantity must be >= 1: " + nQuantity);
    IPriceGraduationItem ret = null;
    for (final IPriceGraduationItem aItem : m_aItems)
    {
      if (aItem.getMinimumQuantity () > nQuantity)
        break;
      ret = aItem;
    }
    if (ret == null)
      throw new IllegalStateException ("Failed to resolve item of quantity " + nQuantity + " in " + toString ());
    return ret;
  }

  @Nonnull
  private IPrice _createPrice (@Nonnull final BigDecimal aNetAmount, @Nonnull final IVATItem aVAT)
  {
    return new Price (m_eCurrency, aNetAmount, aVAT);
  }

  @Nonnull
  public IPrice getPrice (@Nonnull final IReadonlyPriceGraduationItem aItem, @Nonnull final IVATItem aVAT)
  {
    if (aItem == null)
      throw new NullPointerException ("item");
    if (aVAT == null)
      throw new NullPointerException ("vat");
    if (!m_aItems.contains (aItem))
      throw new IllegalArgumentException ("passed item is not contained in this price graduation!");
    return _createPrice (aItem.getUnitNetAmount (), aVAT);
  }

  @Nonnull
  public IPrice getSinglePriceOfQuantity (@Nonnegative final int nQuantity, @Nonnull final IVATItem aVAT)
  {
    if (aVAT == null)
      throw new NullPointerException ("vat");
    return _createPrice (_getItemOfQuantity (nQuantity).getUnitNetAmount (), aVAT);
  }

  @Nonnull
  public IPrice getTotalPriceOfQuantity (@Nonnegative final int nQuantity, @Nonnull final IVATItem aVAT)
  {
    return getSinglePriceOfQuantity (nQuantity, aVAT).getMultiplied (nQuantity);
  }

  @Nonnull
  public EChange addItem (@Nonnegative final int nMinimumQuantity, @Nonnull final BigDecimal aUnitNetAmount)
  {
    return addItem (new PriceGraduationItem (nMinimumQuantity, aUnitNetAmount));
  }

  @Nonnull
  public EChange addItem (@Nonnull final IPriceGraduationItem aItem)
  {
    if (aItem == null)
      throw new NullPointerException ("item");

    // Check if an item with the exact same minimum quantity is already
    // contained.
    int nInsertIndex = 0;
    final int nNewItemQuantity = aItem.getMinimumQuantity ();
    for (final IPriceGraduationItem aExistingItem : m_aItems)
    {
      final int nExistingMinQuantity = aExistingItem.getMinimumQuantity ();
      if (nExistingMinQuantity == nNewItemQuantity)
        throw new IllegalArgumentException ("Another item with the exact same quantity is already contained: " +
                                            nExistingMinQuantity);

      // Find the insertion index
      if (nNewItemQuantity > nExistingMinQuantity)
        ++nInsertIndex;
    }

    m_aItems.add (nInsertIndex, aItem);
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange clear ()
  {
    if (m_aItems.isEmpty ())
      return EChange.UNCHANGED;
    m_aItems.clear ();
    return EChange.CHANGED;
  }

  public boolean isEmpty ()
  {
    return m_aItems.isEmpty ();
  }

  @Nonnegative
  public int size ()
  {
    return m_aItems.size ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof PriceGraduation))
      return false;
    final PriceGraduation rhs = (PriceGraduation) o;
    return EqualsUtils.equals (m_eCurrency, rhs.m_eCurrency) && m_aItems.equals (rhs.m_aItems);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eCurrency).append (m_aItems).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("currency", m_eCurrency).append ("items", m_aItems).toString ();
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
