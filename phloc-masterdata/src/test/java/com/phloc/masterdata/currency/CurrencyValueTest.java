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
package com.phloc.masterdata.currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.phloc.commons.mock.AbstractPhlocTestCase;
import com.phloc.commons.mock.PhlocTestUtils;

public final class CurrencyValueTest extends AbstractPhlocTestCase
{
  @Test
  public void testGetFormatted ()
  {
    ICurrencyValue aCV = new CurrencyValue (ECurrency.EUR, new BigDecimal (5));
    assertEquals ("€ 5,00", aCV.getCurrencyFormatted ());
    aCV = new CurrencyValue (ECurrency.EUR, new BigDecimal ("5.12"));
    assertEquals ("€ 5,12", aCV.getCurrencyFormatted ());
    aCV = new CurrencyValue (ECurrency.USD, new BigDecimal ("5.12"));
    assertEquals ("$5.12", aCV.getCurrencyFormatted ());

    for (final ECurrency eCurrency : ECurrency.values ())
    {
      aCV = new CurrencyValue (eCurrency, new BigDecimal ("5.12"));
      final String sCurrencyFormatted = aCV.getCurrencyFormatted ();
      assertNotNull (sCurrencyFormatted);
      final String sValueFormatted = aCV.getValueFormatted ();
      assertNotNull (sValueFormatted);
      assertTrue (sValueFormatted, sValueFormatted.indexOf (eCurrency.getCurrencySymbol ()) < 0);
      PhlocTestUtils.testGetClone (aCV);

      // There seems to be a bug in the optimizer of 1.6.0_45 so that the output
      // values are sometimes reordered - dunno why :(
      m_aLogger.info ("[" + sCurrencyFormatted + "][" + sValueFormatted + "]");
    }
  }
}
