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
package com.phloc.masterdata.currency;

import java.math.BigDecimal;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import com.phloc.commons.ICloneable;
import com.phloc.commons.state.EChange;

/**
 * Writable version of a currency value. The reading methods are inherited from
 * {@link IReadonlyCurrencyValue}.
 * 
 * @author Philip Helger
 */
public interface ICurrencyValue extends IReadonlyCurrencyValue, ICloneable <ICurrencyValue>
{
  @Nonnull
  EChange setCurrency (@Nonnull ECurrency eCurrency);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getAdded (@Nonnull BigDecimal aValue);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getAdded (long nValue);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getSubtracted (@Nonnull BigDecimal aValue);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getSubtracted (long nValue);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getMultiplied (@Nonnull BigDecimal aValue);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getMultiplied (long nValue);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getDivided (@Nonnull BigDecimal aValue);

  @Nonnull
  @CheckReturnValue
  ICurrencyValue getDivided (long nValue);

  @Nonnull
  EChange setValue (@Nonnull BigDecimal aValue);

  void addValue (@Nonnull BigDecimal aValue);
}
