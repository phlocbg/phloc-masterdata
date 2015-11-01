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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.phloc.commons.annotations.MustImplementEqualsAndHashcode;

/**
 * Interface representing a single read only item of a price graduation.
 * 
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IReadonlyPriceGraduationItem
{
  /**
   * @return The minimum quantity. Should be &ge; 1.
   */
  @Nonnegative
  int getMinimumQuantity ();

  /**
   * @return The price amount that is to be charged for given quantity.
   */
  @Nonnull
  BigDecimal getUnitNetAmount ();
}
