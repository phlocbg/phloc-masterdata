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
package com.phloc.masterdata.currency.exchangeratio;

import javax.annotation.Nonnull;

import com.phloc.commons.compare.AbstractComparator;
import com.phloc.commons.compare.ESortOrder;

/**
 * A comparator comparing {@link ExchangeRatio} objects by their date.
 * 
 * @author Philip Helger
 */
public class ComparatorExchangeRatioDate extends AbstractComparator <ExchangeRatio>
{
  public ComparatorExchangeRatioDate ()
  {}

  public ComparatorExchangeRatioDate (@Nonnull final ESortOrder eSortOrder)
  {
    super (eSortOrder);
  }

  @Override
  protected int mainCompare (@Nonnull final ExchangeRatio aElement1, @Nonnull final ExchangeRatio aElement2)
  {
    return aElement1.getDate ().compareTo (aElement2.getDate ());
  }
}
