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
package com.phloc.masterdata.vat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTimeConstants;
import org.junit.Test;

import com.phloc.commons.locale.country.CountryCache;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link VATManager}.
 * 
 * @author philip
 */
public final class VATManagerTest
{
  @Test
  public void testInit ()
  {
    final VATManager aVATMgr = VATManager.getDefaultInstance ();
    assertNotNull (aVATMgr);
    assertFalse (aVATMgr.getAllAvailableCountries ().isEmpty ());
    final Locale aFirstCountry = aVATMgr.getAllAvailableCountries ().iterator ().next ();
    assertNotNull (aFirstCountry);
    assertNotNull (aVATMgr.getAllVATItemsForCountry (aFirstCountry));
  }

  @Test
  public void testExport ()
  {
    final VATManager aVATMgr = VATManager.getDefaultInstance ();
    for (final Locale aCountry : aVATMgr.getAllAvailableCountries ())
    {
      for (final Map.Entry <String, IVATItem> aEntry : aVATMgr.getAllVATItemsForCountry (aCountry).entrySet ())
      {
        assertNotNull (aEntry.getKey ());
        assertNotNull (aEntry.getValue ());
      }
    }
  }

  @Test
  public void testValidity ()
  {
    final VATManager aVATMgr = VATManager.getDefaultInstance ();
    final Map <String, IVATItem> aData = aVATMgr.getAllVATItemsForCountry (CountryCache.getCountry ("hu"));
    assertNotNull (aData);

    IVATItem aItem = aData.get ("hu.v25");
    assertNotNull (aItem);
    assertEquals (BigDecimal.valueOf (25), aItem.getPercentage ());
    assertNull (aItem.getStart ());
    assertEquals (PDTFactory.createLocalDate (2011, DateTimeConstants.DECEMBER, 31), aItem.getEnd ());

    aItem = aData.get ("hu.v27");
    assertNotNull (aItem);
    assertEquals (PDTFactory.createLocalDate (2012, DateTimeConstants.JANUARY, 1), aItem.getStart ());
    assertNull (aItem.getEnd ());
  }
}
