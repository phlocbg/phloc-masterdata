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

import javax.annotation.Nonnull;

import com.phloc.commons.state.EChange;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.currency.ICurrencyValue;
import com.phloc.masterdata.currency.IReadonlyCurrencyValue;
import com.phloc.masterdata.vat.IVATItem;

public interface IPrice extends IReadonlyPrice
{
  @Nonnull
  ICurrencyValue getNetAmount ();

  /**
   * Change the amount of this price.
   * 
   * @param aValue
   *        The new value. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setNetAmount (@Nonnull IReadonlyCurrencyValue aValue);

  /**
   * Change the VAT type of this price.
   * 
   * @param aVATType
   *        The new VAT type. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setVATItem (@Nonnull IVATItem aVATType);

  /**
   * Set the currency of the price.
   * 
   * @param eCurrency
   *        The new currency to use. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setCurrency (@Nonnull ECurrency eCurrency);

  @Nonnull
  IPrice getMultiplied (@Nonnull BigDecimal aValue);

  @Nonnull
  IPrice getMultiplied (long nValue);
}
