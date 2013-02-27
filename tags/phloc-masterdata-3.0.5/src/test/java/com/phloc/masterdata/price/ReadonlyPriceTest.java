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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import com.phloc.commons.mock.PhlocAssert;
import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.vat.EVATType;
import com.phloc.masterdata.vat.VATItem;
import com.phloc.masterdata.vat.VATManager;

/**
 * Test class for class {@link ReadonlyPrice}.
 * 
 * @author philip
 */
public final class ReadonlyPriceTest
{
  @Test
  public void testCtor ()
  {
    try
    {
      // null currency value not allowed
      new ReadonlyPrice (null, VATManager.VATTYPE_NONE);
      fail ();
    }
    catch (final NullPointerException ex)
    {}

    try
    {
      // null VAT value not allowed
      new ReadonlyPrice (ECurrency.AMD, new BigDecimal (5), null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}
  }

  @Test
  public void testBasic ()
  {
    final ReadonlyPrice p1 = new ReadonlyPrice (ECurrency.DEFAULT_CURRENCY,
                                                new BigDecimal ("9.9"),
                                                VATManager.VATTYPE_NONE);
    assertEquals (ECurrency.DEFAULT_CURRENCY, p1.getCurrency ());
    PhlocAssert.assertEquals (9.9, p1.getNetAmount ().getValue ().doubleValue ());
    PhlocAssert.assertEquals (9.9, p1.getGrossAmount ().getValue ().doubleValue ());
    assertEquals (VATManager.VATTYPE_NONE, p1.getVATItem ());
    assertEquals (p1, p1.getMultiplied (BigDecimal.ONE));

    final ReadonlyPrice p2 = new ReadonlyPrice (ECurrency.DEFAULT_CURRENCY,
                                                new BigDecimal ("9.9"),
                                                VATManager.VATTYPE_NONE);
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (p1, p2);

    final VATItem v1 = new VATItem ("vat20", EVATType.REGULAR, new BigDecimal ("20"), false);
    assertTrue (p2.equals (new ReadonlyPrice (ECurrency.DEFAULT_CURRENCY,
                                              new BigDecimal ("9.9"),
                                              VATManager.VATTYPE_NONE)));
    assertFalse (p2.equals (new ReadonlyPrice (ECurrency.AMD, new BigDecimal ("9.9"), VATManager.VATTYPE_NONE)));
    assertFalse (p2.equals (new ReadonlyPrice (ECurrency.DEFAULT_CURRENCY,
                                               new BigDecimal ("10.9"),
                                               VATManager.VATTYPE_NONE)));
    assertFalse (p2.equals (new ReadonlyPrice (ECurrency.DEFAULT_CURRENCY, new BigDecimal ("9.9"), v1)));

    final ReadonlyPrice p3 = new ReadonlyPrice (ECurrency.DEFAULT_CURRENCY, new BigDecimal ("9.9"), v1);
    PhlocAssert.assertEquals (9.9, p3.getNetAmount ().getValue ().doubleValue ());
    PhlocAssert.assertEquals (11.88, p3.getGrossAmount ().getValue ().doubleValue ());
  }
}
