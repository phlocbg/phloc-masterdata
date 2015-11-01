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
package com.phloc.masterdata.locale;

import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.filter.IFilter;

/**
 * A locale filter that checks if a locale is on the specified continent or not.
 * 
 * @author Philip Helger
 */
public class FilterLocaleCountryOnContinent implements IFilter <Locale>
{
  private final EContinent m_eContinent;

  public FilterLocaleCountryOnContinent (@Nonnull final EContinent eContinent)
  {
    m_eContinent = ValueEnforcer.notNull (eContinent, "Continent");
  }

  @Nonnull
  public EContinent getContinent ()
  {
    return m_eContinent;
  }

  public boolean matchesFilter (@Nonnull final Locale aValue)
  {
    // Get all continents
    final Set <EContinent> aContinents = ContinentUtils.getContinentsOfCountry (aValue);
    return aContinents != null && aContinents.contains (m_eContinent);
  }
}
