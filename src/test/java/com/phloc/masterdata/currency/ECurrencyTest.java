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
package com.phloc.masterdata.currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Locale;

import org.junit.Test;

import com.phloc.commons.mock.AbstractPhlocTestCase;
import com.phloc.commons.string.StringHelper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public final class ECurrencyTest extends AbstractPhlocTestCase
{
  @Test
  public void testGetFromIDOrNull ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
      assertSame (eCurrency, ECurrency.getFromIDOrNull (eCurrency.getID ()));
    assertNull (ECurrency.getFromIDOrNull (null));
    assertNull (ECurrency.getFromIDOrNull ("Does not exist!"));
  }

  @Test
  public void testGetFromIDOrDefault ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
      assertSame (eCurrency, ECurrency.getFromIDOrDefault (eCurrency.getID (), ECurrency.ALL));
    assertSame (ECurrency.ALL, ECurrency.getFromIDOrDefault (null, ECurrency.ALL));
    assertSame (ECurrency.ALL, ECurrency.getFromIDOrDefault ("Does not exist!", ECurrency.ALL));
  }

  @Test
  public void testAsCurrency ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
    {
      assertNotNull (eCurrency.getAsCurrency ());
      assertEquals (eCurrency.getID (), eCurrency.getAsCurrency ().getCurrencyCode ());
    }
  }

  @Test
  public void testGetDisplayText ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
    {
      assertNotNull (eCurrency.getDisplayText (L_DE));
      assertNotNull (eCurrency.getDisplayText (L_EN));
    }
  }

  @Test
  public void testGetCurrencyFormat ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
      assertNotNull (eCurrency.getCurrencyFormat ());

    assertEquals ("EUR", ECurrency.EUR.getAsCurrency ().getCurrencyCode ());
  }

  @Test
  public void testFormatting ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
    {
      // currency format
      assertNotNull (eCurrency.getCurrencyFormat ());
      assertNotNull (eCurrency.getCurrencyFormatted (BigDecimal.TEN));
      assertNotNull (eCurrency.getCurrencyFormatted (BigDecimal.TEN, 3));
      assertEquals (BigDecimal.TEN, eCurrency.parseCurrencyFormat (null, BigDecimal.TEN));
      assertEquals (BigDecimal.TEN, eCurrency.parseCurrencyFormat ("", BigDecimal.TEN));
      assertEquals (BigDecimal.TEN, eCurrency.parseCurrencyFormat ("    ", BigDecimal.TEN));

      // value format
      assertNotNull (eCurrency.getValueFormat ());
      assertNotNull (eCurrency.getValueFormatted (BigDecimal.TEN));
      assertNotNull (eCurrency.getValueFormatted (BigDecimal.TEN, 3));
      assertEquals (BigDecimal.TEN, eCurrency.parseValueFormat (null, BigDecimal.TEN));
      assertEquals (BigDecimal.TEN, eCurrency.parseValueFormat ("", BigDecimal.TEN));
      assertEquals (BigDecimal.TEN, eCurrency.parseValueFormat ("    ", BigDecimal.TEN));

      // No decimal separator
      final BigDecimal FIVE = new BigDecimal ("5").setScale (eCurrency.getAsCurrency ().getDefaultFractionDigits ());
      assertEquals (FIVE, eCurrency.parseValueFormat ("5", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat (" 5", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat ("5 ", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat (" 5 ", BigDecimal.TEN));
      if (false)
      {
        final BigDecimal MFIVE = new BigDecimal ("-5").setScale (eCurrency.getAsCurrency ().getDefaultFractionDigits ());
        assertEquals (FIVE, eCurrency.parseValueFormat ("+5", BigDecimal.TEN));
        assertEquals (MFIVE, eCurrency.parseValueFormat ("-5", BigDecimal.TEN));
      }

      // comma as decimal separator
      assertEquals (FIVE, eCurrency.parseValueFormat ("5,0", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat (" 5,0", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat ("5,0 ", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat (" 5,0 ", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat ("5,0000", BigDecimal.TEN));

      // dot as decimal separator
      assertEquals (FIVE, eCurrency.parseValueFormat ("5.0", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat (" 5.0", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat ("5.0 ", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat (" 5.0 ", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat ("5.0000", BigDecimal.TEN));
      assertEquals (FIVE, eCurrency.parseValueFormat ("5.0009", BigDecimal.TEN));

      // symbol
      assertTrue (eCurrency.getValueFormat ().format (5).contains ("5"));
      assertFalse (eCurrency.getValueFormat ().format (5).contains (eCurrency.getCurrencySymbol ()));
      assertTrue (eCurrency.getCurrencyFormat ().format (5).contains ("5"));
      assertTrue (eCurrency.getCurrencyFormat ().format (5).contains (eCurrency.getCurrencySymbol ()));
    }
  }

  @Test
  public void testGetCurrencySymbol ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
      assertNotNull (eCurrency.getCurrencySymbol ());

    assertEquals ("€", ECurrency.EUR.getCurrencySymbol ());
  }

  @Test
  @SuppressFBWarnings ("TQ_NEVER_VALUE_USED_WHERE_ALWAYS_REQUIRED")
  public void testPLN ()
  {
    final ECurrency e = ECurrency.PLN;
    assertEquals ("PLN", e.getID ());
    assertEquals ("zł", e.getCurrencySymbol ());
    assertEquals (2, e.getAsCurrency ().getDefaultFractionDigits ());
    assertEquals ("5 zł", e.getCurrencyFormat ().format (5));
    assertEquals ("5", e.getValueFormat ().format (5));
    assertEquals ("5,1 zł", e.getCurrencyFormat ().format (5.1));
    assertEquals ("5,1", e.getValueFormat ().format (5.1));
    assertEquals ("5,12 zł", e.getCurrencyFormat ().format (5.123));
    assertEquals ("5,12", e.getValueFormat ().format (5.123));

    assertEquals (0, e.getMinimumFractionDigits ());
    e.setMinimumFractionDigits (2);
    assertEquals (2, e.getMinimumFractionDigits ());

    assertEquals ("5,00 zł", e.getCurrencyFormat ().format (5));
    assertEquals ("5,00", e.getValueFormat ().format (5));
    assertEquals ("5,10 zł", e.getCurrencyFormat ().format (5.1));
    assertEquals ("5,10", e.getValueFormat ().format (5.1));
    assertEquals ("5,12 zł", e.getCurrencyFormat ().format (5.123));
    assertEquals ("5,12", e.getValueFormat ().format (5.123));

    e.setMinimumFractionDigits (0);
    assertEquals (0, e.getMinimumFractionDigits ());

    try
    {
      e.setMinimumFractionDigits (-1);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
    assertEquals (0, e.getMinimumFractionDigits ());
  }

  @Test
  public void testForint ()
  {
    final ECurrency e = ECurrency.HUF;
    assertEquals ("HUF", e.getID ());
    assertEquals ("Ft", e.getCurrencySymbol ());
    assertEquals (2, e.getAsCurrency ().getDefaultFractionDigits ());
    assertEquals ("5 Ft", e.getCurrencyFormat ().format (5));
    assertEquals ("5", e.getValueFormat ().format (5));
    assertEquals ("5,1 Ft", e.getCurrencyFormat ().format (5.1));
    assertEquals ("5,1", e.getValueFormat ().format (5.1));
    assertEquals ("5,12 Ft", e.getCurrencyFormat ().format (5.123));
    assertEquals ("5,12", e.getValueFormat ().format (5.123));
  }

  @Test
  public void testGetAllMatchingCountries ()
  {
    for (final ECurrency eCurrency : ECurrency.values ())
    {
      assertFalse (eCurrency.getAllMatchingCountries ().isEmpty ());
      for (final Locale aCountry : eCurrency.getAllMatchingCountries ())
        assertNotNull (aCountry);
    }
  }

  @Test
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public void testGetDivided ()
  {
    final BigDecimal aBD3 = new BigDecimal (3);
    final BigDecimal aBD = new BigDecimal (2);
    try
    {
      // 2/3 == 0.666666666....
      aBD.divide (aBD3);
      fail ();
    }
    catch (final ArithmeticException ex)
    {}
    // -> uses the correct rounding mode
    assertEquals (new BigDecimal ("0.67"), ECurrency.EUR.getDivided (aBD, aBD3));
  }

  @Test
  public void testGetRounded ()
  {
    final BigDecimal aBD = new BigDecimal ("1.2355352343");
    assertEquals (new BigDecimal ("1.24"), ECurrency.EUR.getRounded (aBD));
    assertEquals (new BigDecimal ("1.24"), ECurrency.PLN.getRounded (aBD));

    assertEquals (new BigDecimal ("1.2"), ECurrency.EUR.getRounded (aBD, 1));
    assertEquals (new BigDecimal ("1.2"), ECurrency.EUR.getRounded (aBD, 1));
  }

  @Test
  public void testGetDisplayName ()
  {
    for (final ECurrencyName e : ECurrencyName.values ())
    {
      assertSame (e, ECurrencyName.valueOf (e.name ()));
      assertTrue (e.ordinal () >= 0);
    }
  }

  @Test
  public void testGetScale ()
  {
    for (final ECurrency e : ECurrency.values ())
      assertTrue (e.getScale () >= 0);
  }

  @Test
  public void testGetPatterns ()
  {
    for (final ECurrency e : ECurrency.values ())
    {
      assertTrue (StringHelper.hasText (e.getCurrencyPattern ()));
      assertTrue (StringHelper.hasText (e.getValuePattern ()));
    }
  }

  @SuppressWarnings ("deprecation")
  @Test
  public void testIsDeprecated ()
  {
    assertTrue (ECurrency.EEK.isDeprecated ());
    assertFalse (ECurrency.EUR.isDeprecated ());
  }

  @Test
  public void testGetFromCountry ()
  {
    for (final ECurrency e : ECurrency.values ())
      if (!e.isDeprecated ())
        for (final Locale aLocale : e.getAllMatchingCountries ())
          assertSame (e, ECurrency.getFromCountryOrNull (aLocale, false));
  }
}
