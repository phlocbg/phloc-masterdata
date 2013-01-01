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
package com.phloc.masterdata.swift;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.string.StringHelper;

/**
 * This class represents a single IBAN number (SEPA). An IBAN number is a
 * combination of different values that are country dependent.
 * 
 * @author philip
 */
@Immutable
public final class IBAN
{
  private final List <IBANElementValue> m_aValues;

  public IBAN (@Nonnull final List <IBANElementValue> aValues)
  {
    if (aValues == null)
      throw new NullPointerException ("values");
    m_aValues = ContainerHelper.makeUnmodifiable (aValues);
  }

  @Nonnull
  @ReturnsImmutableObject
  public List <IBANElementValue> getValues ()
  {
    return m_aValues;
  }

  @Nonnull
  private static String _extractCountryCode (@Nonnull final String sIBAN)
  {
    return sIBAN.substring (0, 2);
  }

  @Nullable
  public static IBAN createFromString (@Nullable final String sIBAN)
  {
    final String sRealIBAN = IBANManager.unifyIBAN (sIBAN);
    if (StringHelper.hasNoText (sRealIBAN))
      return null;

    // get country specific data
    final String sCountryCode = _extractCountryCode (sRealIBAN);
    final IBANCountryData aCountryData = IBANManager.getCountryData (sCountryCode);
    if (aCountryData == null)
      throw new IllegalArgumentException ("Passed IBAN country '" + sCountryCode + "' is not supported!");

    // parse IBAN to country specific list of elements
    return new IBAN (aCountryData.parseToElementValues (sRealIBAN));
  }
}
