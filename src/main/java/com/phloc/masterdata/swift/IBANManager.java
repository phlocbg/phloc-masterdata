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
package com.phloc.masterdata.swift;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import com.phloc.commons.CGlobal;
import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.exceptions.InitializationException;
import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.serialize.MicroReader;
import com.phloc.commons.regex.RegExHelper;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.StringParser;
import com.phloc.datetime.format.PDTFormatter;
import com.phloc.datetime.format.PDTFromString;

/**
 * Contains the IBAN manager.<br>
 * All banks in Europe (except for those in the CIS) provide an IBAN identifier
 * for their accounts as well as nationally recognized identifiers. In addition,
 * Israel, Tunisia, Mauritius, Turkey, and Saudi Arabia also provide IBAN format
 * account identifiers.<br>
 * Banks in the British dependencies (except Gibraltar and the Crown
 * Dependencies) do not use the IBAN format, but this may be due to internal
 * banking regulatory issues. Banks in the Dutch West Indies don't use the IBAN
 * format either. Some banks outside Europe may not recognize IBAN, though as
 * time passes this is expected to diminish. Non-European banks typically accept
 * IBANs as bank account numbers for accounts in Europe, although they might not
 * treat IBANs differently to the way they treat other foreign bank account
 * numbers. In particular, they might choose not to check that the IBAN is valid
 * prior to sending the payment.<br>
 * In the absence of an IBAN it remains necessary to use the current ISO 9362
 * Bank Identifier Code system (BIC or SWIFT code) in conjunction with the BBAN.<br>
 * Banks in the United States do not provide IBAN format account numbers. Any
 * adoption of the IBAN standard by U.S. banks would likely be initiated by ANSI
 * ASC X9, the U.S. financial services standards development organization but to
 * date it has not done so. Hence payments to U.S. bank accounts from outside
 * the U.S. are prone to errors of routing.<br>
 * Banks in Australia and New Zealand have not adopted IBAN, and tend to use
 * Bank State Branch codes for domestic transfers and SWIFT for international.<br>
 * Source: http://en.wikipedia.org/wiki/International_Bank_Account_Number
 * 
 * @author philip
 */
public final class IBANManager
{
  private static final String ELEMENT_COUNTRY = "country";
  private static final String ATTR_VALIDFROM = "validfrom";
  private static final String ATTR_VALIDUNTIL = "validuntil";
  private static final String ATTR_LEN = "len";
  private static final String DATETIME_PATTERN = "yyyy-MM-dd";
  private static final int ILLEGAL_CHECKSUM = CGlobal.ILLEGAL_UINT;

  /** Maps country code to IBAn country data */
  private static final Map <String, IBANCountryData> s_aIBANData = new HashMap <String, IBANCountryData> ();

  static
  {
    _readIBANDataFromXML ();
  }

  private IBANManager ()
  {}

  /**
   * Read all IBAN country data from a file.
   */
  private static void _readIBANDataFromXML ()
  {
    final IMicroDocument doc = MicroReader.readMicroXML (new ClassPathResource ("codelists/iban-country-data.xml"));
    if (doc == null)
      throw new InitializationException ("Failed to read IBAN country data [1]");
    if (doc.getDocumentElement () == null)
      throw new InitializationException ("Failed to read IBAN country data [2]");

    final DateTimeFormatter aDTPattern = PDTFormatter.getForPattern (DATETIME_PATTERN, null);

    for (final IMicroElement eCountry : doc.getDocumentElement ().getChildElements (ELEMENT_COUNTRY))
    {
      // get descriptive string
      final String sDesc = eCountry.getTextContent ();
      final String sCountryCode = sDesc.substring (0, 2);

      LocalDate aValidFrom = null;
      if (eCountry.hasAttribute (ATTR_VALIDFROM))
      {
        // Constant format, conforming to XML date
        aValidFrom = PDTFromString.getLocalDateFromString (eCountry.getAttribute (ATTR_VALIDFROM), aDTPattern);
      }

      LocalDate aValidTo = null;
      if (eCountry.hasAttribute (ATTR_VALIDUNTIL))
      {
        // Constant format, conforming to XML date
        aValidTo = PDTFromString.getLocalDateFromString (eCountry.getAttribute (ATTR_VALIDUNTIL), aDTPattern);
      }

      // get expected length
      final int nExpectedLength = StringParser.parseInt (eCountry.getAttribute (ATTR_LEN), CGlobal.ILLEGAL_UINT);
      if (nExpectedLength == CGlobal.ILLEGAL_UINT)
        throw new InitializationException ("Failed to convert length to int!");

      if (s_aIBANData.containsKey (sCountryCode))
        throw new IllegalArgumentException ("Country " + sCountryCode + " is already contained!");
      s_aIBANData.put (sCountryCode, IBANCountryData.createFromString (nExpectedLength, sDesc, aValidFrom, aValidTo));
    }
  }

  /**
   * Get the country data for the given country code.
   * 
   * @param sCountryCode
   *        The country code to use. May not be <code>null</code> and needs to
   *        have exactly 2 characters to work.
   * @return <code>null</code> if the passed country has no IBAN support.
   */
  @Nullable
  public static IBANCountryData getCountryData (@Nonnull final String sCountryCode)
  {
    if (sCountryCode == null)
      throw new NullPointerException ("countryCode");
    return s_aIBANData.get (sCountryCode.toUpperCase (Locale.US));
  }

  /**
   * @return An unmodifiable, non-<code>null</code> set of all countries for
   *         which IBAN information is present.
   */
  @Nonnull
  @ReturnsImmutableObject
  public static Set <String> getAllSupportedCountries ()
  {
    return ContainerHelper.makeUnmodifiable (s_aIBANData.keySet ());
  }

  private static int _calculateChecksum (@Nonnull final String sIBAN)
  {
    final String sCalcBase = sIBAN.substring (4) + sIBAN.substring (0, 4);
    int nChecksum = 0;
    final int nLength = sCalcBase.length ();
    for (int nIndex = 0; nIndex < nLength; nIndex++)
    {
      final char c = sCalcBase.charAt (nIndex);
      int nCurChecksumValue = 0;
      if (c >= '0' && c <= '9')
        nCurChecksumValue = c - '0';
      else
        if (c >= 'A' && c <= 'Z')
          nCurChecksumValue = c - '7';
        else
          return ILLEGAL_CHECKSUM;

      if (nCurChecksumValue > 9)
        nChecksum = (100 * nChecksum + nCurChecksumValue) % 97;
      else
        nChecksum = (10 * nChecksum + nCurChecksumValue) % 97;
    }
    return nChecksum;
  }

  private static boolean _isValidChecksumChar (final char c)
  {
    return c >= '0' && c <= '9';
  }

  /**
   * Make an IBAN that can be parsed. It is converted to upper case and all
   * non-alphanumeric characters are removed.
   * 
   * @param sIBAN
   *        The IBAN to be unified.
   * @return The unified string or <code>null</code> if this is no IBAN at all.
   */
  @Nullable
  public static String unifyIBAN (@Nullable final String sIBAN)
  {
    if (sIBAN == null)
      return null;

    // to uppercase and kick all non-IBAN chars
    final String sRealIBAN = RegExHelper.stringReplacePattern ("[^0-9A-Z]", sIBAN.toUpperCase (Locale.US), "");
    if (sRealIBAN.length () < 4)
      return null;

    return sRealIBAN;
  }

  /**
   * Check if the passed IBAN is valid and the country is supported!
   * 
   * @param sIBAN
   *        The IBAN number string to check.
   * @return <code>true</code> if the IBAN is valid and supported.
   */
  public static boolean isValidIBAN (@Nullable final String sIBAN)
  {
    // kick all non-IBAN chars
    final String sRealIBAN = unifyIBAN (sIBAN);
    if (sRealIBAN == null)
      return false;

    // is the country supported?
    final IBANCountryData aData = s_aIBANData.get (sRealIBAN.substring (0, 2));
    if (aData == null)
      return false;

    // Does the length match the expected length?
    if (aData.getExpectedLength () != sRealIBAN.length ())
      return false;

    // Are the checksum characters valid?
    if (!_isValidChecksumChar (sRealIBAN.charAt (2)) || !_isValidChecksumChar (sRealIBAN.charAt (3)))
      return false;

    // Is existing checksum valid?
    if (_calculateChecksum (sRealIBAN) != 1)
      return false;

    return true;
  }

  public static int createChecksumOfNewIBAN (@Nonnull final String sCountryCode, @Nonnull final String sBBAN)
  {
    if (sCountryCode == null)
      throw new NullPointerException ("countryCode");
    if (sCountryCode.length () != 2)
      throw new IllegalArgumentException ("Country code does not have exactly 2 characters!");
    if (sBBAN == null)
      throw new NullPointerException ("bban");

    // unify aggregated IBAN
    final String sIBAN = unifyIBAN (sCountryCode + "00" + sBBAN);
    if (sIBAN == null)
      throw new IllegalArgumentException ("The passed data does not resemble an IBAN!");

    // calculate the new checksum
    final int nChecksum = _calculateChecksum (sIBAN);
    if (nChecksum == ILLEGAL_CHECKSUM)
      throw new IllegalArgumentException ("The passed data does not resemble an IBAN!");

    return 98 - (((nChecksum - 1) % 97) + 1);
  }

  @Nonnull
  public static String createIBANWithValidChecksum (@Nonnull final String sCountry, @Nonnull final String sBBAN)
  {
    // create the checksum
    final int nChecksum = createChecksumOfNewIBAN (sCountry, sBBAN);

    // convert to unified IBAN
    return unifyIBAN (sCountry + StringHelper.getLeadingZero (nChecksum, 2) + sBBAN);
  }
}
