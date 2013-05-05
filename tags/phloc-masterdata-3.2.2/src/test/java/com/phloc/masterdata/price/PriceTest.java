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
package com.phloc.masterdata.price;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import com.phloc.commons.mock.PhlocAssert;
import com.phloc.commons.state.EChange;
import com.phloc.masterdata.currency.CurrencyValue;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.currency.IReadonlyCurrencyValue;
import com.phloc.masterdata.currency.ReadonlyCurrencyValue;
import com.phloc.masterdata.vat.EVATType;
import com.phloc.masterdata.vat.IVATItem;
import com.phloc.masterdata.vat.VATItem;
import com.phloc.masterdata.vat.VATManager;

/**
 * Test class for class {@link Price}.
 * 
 * @author Philip Helger
 */
public final class PriceTest
{
  @SuppressWarnings ("deprecation")
  @Test
  public void testAll ()
  {
    final Price p = new Price (ECurrency.DEFAULT_CURRENCY, new BigDecimal ("9.9"), VATManager.VATTYPE_NONE);
    assertEquals (ECurrency.DEFAULT_CURRENCY, p.getCurrency ());
    PhlocAssert.assertEquals (9.9, p.getNetAmount ().getValue ().doubleValue ());
    assertEquals (VATManager.VATTYPE_NONE, p.getVATItem ());

    // Setter
    assertEquals (EChange.CHANGED, p.getNetAmount ().setCurrency (ECurrency.AMD));
    assertEquals (ECurrency.AMD, p.getCurrency ());
    assertEquals (EChange.UNCHANGED, p.getNetAmount ().setCurrency (ECurrency.AMD));
    assertEquals (ECurrency.AMD, p.getCurrency ());
    assertEquals (EChange.UNCHANGED, p.setNetAmount (new ReadonlyCurrencyValue (ECurrency.AMD, new BigDecimal ("9.9"))));
    assertEquals (EChange.CHANGED, p.getNetAmount ().setCurrency (ECurrency.DEFAULT_CURRENCY));
    assertEquals (ECurrency.DEFAULT_CURRENCY, p.getCurrency ());

    final IVATItem aVATItem = new VATItem ("vat50", EVATType.REGULAR, new BigDecimal ("50"), false);
    assertEquals (EChange.CHANGED, p.setVATItem (aVATItem));
    assertEquals (aVATItem, p.getVATItem ());
    assertEquals (EChange.UNCHANGED, p.setVATItem (aVATItem));
    PhlocAssert.assertEquals (9.9, p.getNetAmount ().getValue ().doubleValue ());
    // 14.85 = 9.9 * 1.5 (50%)
    PhlocAssert.assertEquals (14.85, p.getGrossAmount ().getValue ().doubleValue ());
    assertEquals (EChange.CHANGED, p.setVATItem (VATManager.VATTYPE_NONE));
    PhlocAssert.assertEquals (9.9, p.getNetAmount ().getValue ().doubleValue ());
    PhlocAssert.assertEquals (9.9, p.getGrossAmount ().getValue ().doubleValue ());

    assertEquals (p, p.getMultiplied (BigDecimal.ONE));

    try
    {
      // null not allowed
      p.setNetAmount ((IReadonlyCurrencyValue) null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}
    try
    {
      // null not allowed
      p.setNetAmount ((CurrencyValue) null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}

    try
    {
      // null not allowed
      p.setVATItem (null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}
  }
}
