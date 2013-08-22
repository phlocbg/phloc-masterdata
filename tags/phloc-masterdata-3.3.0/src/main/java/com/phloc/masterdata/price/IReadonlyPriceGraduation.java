/**
 * Copyright (C) 2006-2013 phloc systems
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

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.IHasSize;
import com.phloc.commons.annotations.MustImplementEqualsAndHashcode;
import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.currency.IHasCurrency;
import com.phloc.masterdata.vat.IVATItem;

/**
 * Read only interface for a single complete price graduation (German:
 * Preisstaffelung). A price graduation is assembled from a list of price
 * graduation items.<br>
 * The implementation needs to ensure, that each price graduation item's price
 * has the same currency, and that all items are sorted according to the minimum
 * quantity. The implementation should also ensure, that at least one entry for
 * the minimum quantity 1 exists. If you're selling certain items only in higher
 * quantities (e.g. balloons or other small things), you should instead use a
 * packaging unit (German: Verpackungseinheit) and define a quantity of 1.
 * 
 * @see IReadonlyPriceGraduationItem
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IReadonlyPriceGraduation extends IHasSize, IHasCurrency
{
  /**
   * @return The currency of this price graduation.
   * @throws IllegalStateException
   *         if no currency is defined
   */
  @Nonnull
  ECurrency getCurrency ();

  /**
   * @return The price graduation item with the smallest minimum quantity. May
   *         be <code>null</code> if no item is contained at all!
   */
  @Nullable
  IReadonlyPriceGraduationItem getSmallestMinimumQuantityItem ();

  /**
   * @return The price graduation item with the smallest minimum quantity. May
   *         be <code>null</code> if no item is contained at all!
   */
  @Nullable
  IReadonlyPriceGraduationItem getLargestMinimumQuantityItem ();

  /**
   * @return All contained read only items, sorted ascending by the minimum
   *         quantity. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsImmutableObject
  List <? extends IReadonlyPriceGraduationItem> getAllItems ();

  /**
   * @param nIndex
   *        the index to be retrieved
   * @return The item at the given index or <code>null</code> if no such index
   *         is present.
   */
  @Nullable
  IReadonlyPriceGraduationItem getItemOfIndex (@Nonnegative int nIndex);

  /**
   * Get the price of a single item, for the given quantity.
   * 
   * @param nQuantity
   *        The quantity to query. Must be &ge; 1.
   * @param aVAT
   *        The VAT item to use.
   * @return The price of a single item.
   */
  @Nonnull
  IReadonlyPrice getSinglePriceOfQuantity (@Nonnegative int nQuantity, @Nonnull IVATItem aVAT);

  /**
   * Get the total price of all items for the given quantity. This is a shortcut
   * for <code>getSinglePriceOfQuantity (nQuantity).multiply (nQuantity)</code>
   * 
   * @param nQuantity
   *        The quantity to query. Must be &ge; 1.
   * @param aVAT
   *        The VAT item to use.
   * @return The total price of all items.
   */
  @Nonnull
  IReadonlyPrice getTotalPriceOfQuantity (@Nonnegative int nQuantity, @Nonnull IVATItem aVAT);

  /**
   * Delivers a price object based on the net amount of the passed item and the
   * currency and VAT set for the price graduation
   * 
   * @param aItem
   *        the price graduation item for which to retrieve the price
   * @param aVAT
   *        The VAT item to use.
   * @return the resulting {@link IReadonlyPrice}
   */
  @Nonnull
  IReadonlyPrice getPrice (@Nonnull IReadonlyPriceGraduationItem aItem, @Nonnull IVATItem aVAT);

  /**
   * @return <code>true</code> if no item is contained, <code>false</code> if at
   *         least one item is present.
   */
  boolean isEmpty ();

  /**
   * @return The number of quantity-price pairs in this object. Always &ge; 0.
   */
  @Nonnegative
  int size ();
}
