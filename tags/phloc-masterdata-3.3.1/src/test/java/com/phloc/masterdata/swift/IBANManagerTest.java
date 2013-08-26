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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.joda.time.DateTimeConstants;
import org.junit.Test;

import com.phloc.commons.string.StringParser;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link IBANManager}.
 * 
 * @author Philip Helger
 */
public final class IBANManagerTest
{
  static final String [] VALID_IBANS = new String [] { "DE89 3704 0044 0532 0130 00",
                                                      "FR14 2004 1010 0505 0001 3M02 606",
                                                      "GB29 NWBK 6016 1331 9268 19",
                                                      "GR16 0110 1250 0000 0001 2300 695",
                                                      "MT84 MALT 0110 0001 2345 MTLC AST0 01S",
                                                      "GR16 0110 1050 0000 1054 7023 795",
                                                      "GB35 MIDL 4025 3432 1446 70",
                                                      "CH51 0868 6001 2565 1500 1" };

  @Test
  public void testIBANCountries ()
  {
    for (final String sCountry : IBANManager.getAllSupportedCountries ())
    {
      final Locale aLocale = new Locale ("", sCountry);
      assertNotNull (aLocale.getDisplayCountry ());
      assertNotNull (IBANManager.getCountryData (sCountry));
    }
  }

  @Test
  public void testValidity ()
  {
    final IBANCountryData aData = IBANManager.getCountryData ("MR");
    assertNotNull (aData);
    assertEquals (PDTFactory.createLocalDate (2012, DateTimeConstants.JANUARY, 1), aData.getStart ());
    assertNull (aData.getEnd ());
  }

  @Test
  public void testIBANValidity ()
  {
    // check valid IBANs
    for (final String sIBAN : VALID_IBANS)
      assertTrue (IBANManager.isValidIBAN (sIBAN));

    // check if checksum creation works
    for (final String sIBAN : VALID_IBANS)
      assertEquals (sIBAN + " has other checksum than expected;",
                    StringParser.parseInt (sIBAN.substring (2, 4), -1),
                    IBANManager.createChecksumOfNewIBAN (sIBAN.substring (0, 2), sIBAN.substring (4)));
  }
}
