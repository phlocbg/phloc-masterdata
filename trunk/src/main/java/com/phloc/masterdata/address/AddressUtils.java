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
package com.phloc.masterdata.address;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.phloc.commons.exceptions.InitializationException;
import com.phloc.commons.string.StringHelper;

/**
 * Contains utility methods for addresses.
 * 
 * @author philip
 */
@ThreadSafe
public final class AddressUtils
{
  private static final String [] SEARCH = new String [] { "str.", "g." };
  private static final String [] REPLACE = new String [] { "straße", "gasse" };

  private static final AtomicBoolean s_aComplexAddressHandlingEnabled = new AtomicBoolean (false);

  static
  {
    if (SEARCH.length != REPLACE.length)
      throw new InitializationException ("Search and replace arrays have different length!");
  }

  private AddressUtils ()
  {}

  public static void setComplexAddressHandlingEnabled (final boolean bEnabled)
  {
    s_aComplexAddressHandlingEnabled.set (bEnabled);
  }

  public static boolean isComplexAddressHandlingEnabled ()
  {
    return s_aComplexAddressHandlingEnabled.get ();
  }

  @Nullable
  private static String _unifyPart (@Nonnull final String sPart, @Nonnull final Locale aSortLocale)
  {
    // empty name?
    String s = sPart.trim ();
    final int nLength = s.length ();
    if (nLength == 0)
      return null;

    // all upper case name?
    if (nLength == 1)
      return s.toUpperCase (aSortLocale);

    // uppercase first only
    s = s.substring (0, 1).toUpperCase (aSortLocale) + s.substring (1);

    return s;
  }

  @Nullable
  public static String getUnifiedStreet (@Nullable final String sStreet, @Nonnull final Locale aSortLocale)
  {
    if (!isComplexAddressHandlingEnabled ())
      return sStreet;

    if (sStreet == null)
      return null;

    String s = sStreet;
    for (int i = 0; i < SEARCH.length; ++i)
      s = StringHelper.replaceAll (s, SEARCH[i], REPLACE[i]);
    return _unifyPart (s, aSortLocale);
  }

  @Nullable
  public static String getUnifiedCity (@Nullable final String sCity, @Nonnull final Locale aSortLocale)
  {
    if (!isComplexAddressHandlingEnabled ())
      return sCity;

    if (sCity == null)
      return null;

    return _unifyPart (sCity, aSortLocale);
  }

  @Nullable
  public static String getUnifiedPOBox (@Nullable final String sPOBox, @Nonnull final Locale aSortLocale)
  {
    if (!isComplexAddressHandlingEnabled ())
      return sPOBox;

    if (sPOBox == null)
      return null;

    return _unifyPart (sPOBox, aSortLocale);
  }

  @Nullable
  public static String getUnifiedState (@Nullable final String sState, @Nonnull final Locale aSortLocale)
  {
    if (!isComplexAddressHandlingEnabled ())
      return sState;

    if (sState == null)
      return null;

    return _unifyPart (sState, aSortLocale);
  }

  @Nullable
  public static String getUnifiedCountry (@Nullable final String sCountry, @Nonnull final Locale aSortLocale)
  {
    if (!isComplexAddressHandlingEnabled ())
      return sCountry;

    if (sCountry == null)
      return null;

    return _unifyPart (sCountry, aSortLocale);
  }

  @Nullable
  public static String getAddressString (@Nullable final IReadonlyAddress aAddress, @Nonnull final Locale aDisplayLocale)
  {
    if (aAddress == null)
      return null;

    final StringBuilder aSB = new StringBuilder ();
    if (StringHelper.hasText (aAddress.getStreet ()))
      aSB.append (aAddress.getStreet ());

    final String sNextLine = StringHelper.getImplodedNonEmpty (' ', aAddress.getPostalCode (), aAddress.getCity ());
    if (StringHelper.hasText (sNextLine))
    {
      if (aSB.length () > 0)
        aSB.append ('\n');
      aSB.append (sNextLine);
    }

    if (StringHelper.hasText (aAddress.getPostOfficeBox ()))
    {
      if (aSB.length () > 0)
        aSB.append ('\n');
      aSB.append (aAddress.getPostOfficeBox ());
    }

    if (StringHelper.hasText (aAddress.getCountry ()))
    {
      if (aSB.length () > 0)
        aSB.append ('\n');

      aSB.append (aAddress.getCountryDisplayName (aDisplayLocale));
    }

    return aSB.toString ();
  }
}
