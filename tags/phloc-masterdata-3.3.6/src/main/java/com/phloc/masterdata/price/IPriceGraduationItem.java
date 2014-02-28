/**
 * Copyright (C) 2006-2014 phloc systems
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.phloc.commons.annotations.MustImplementEqualsAndHashcode;
import com.phloc.commons.state.EChange;

/**
 * The writable version of a single price graduation item.
 * 
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IPriceGraduationItem extends IReadonlyPriceGraduationItem
{
  /**
   * Set the minimum quantity of this price graduation item.
   * 
   * @param nMinimumQuantity
   *        The minimum quantity. Must be &ge; 1.
   * @return {@link EChange#CHANGED} if the value changed,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  EChange setMinimumQuantity (@Nonnegative int nMinimumQuantity);

  /**
   * Change the price of this graduation item.
   * 
   * @param aAmount
   *        The new price amount to set. May not be <code>null</code>.
   * @return {@link EChange#CHANGED} if the value changed,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  EChange setUnitNetAmount (@Nonnull BigDecimal aAmount);
}
