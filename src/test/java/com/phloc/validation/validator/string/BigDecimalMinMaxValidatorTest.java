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
package com.phloc.validation.validator.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.validation.validator.string.BigDecimalMinMaxValidator;

/**
 * Test class for class {@link BigDecimalMinMaxValidator}.
 * 
 * @author philip
 */
public final class BigDecimalMinMaxValidatorTest
{
  @Test
  public void testAll ()
  {
    BigDecimalMinMaxValidator v = new BigDecimalMinMaxValidator (BigDecimal.ZERO, BigDecimal.TEN);
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertFalse (v.validate ("TRUE").isValid ());
    assertFalse (v.validate ("-1").isValid ());
    assertFalse (v.validate ("-0.00001").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertTrue (v.validate ("1").isValid ());
    assertTrue (v.validate ("10").isValid ());

    v = new BigDecimalMinMaxValidator (null, BigDecimal.ZERO);
    assertTrue (v.validate ("-1").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertFalse (v.validate ("0.001").isValid ());
    assertFalse (v.validate ("1").isValid ());

    v = new BigDecimalMinMaxValidator (BigDecimal.ZERO, null);
    assertFalse (v.validate ("-1").isValid ());
    assertFalse (v.validate ("-0.001").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertTrue (v.validate ("1").isValid ());

    try
    {
      // Both null not okay
      new BigDecimalMinMaxValidator (null, null);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}

    try
    {
      // Min > Max
      new BigDecimalMinMaxValidator (BigDecimal.ONE, BigDecimal.ZERO);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                   BigDecimal.TEN),
                                                                    new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                   BigDecimal.TEN));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new BigDecimalMinMaxValidator (null, BigDecimal.TEN),
                                                                    new BigDecimalMinMaxValidator (null, BigDecimal.TEN));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                   null),
                                                                    new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                   null));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                       BigDecimal.ONE),
                                                                        new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                       BigDecimal.TEN));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                       BigDecimal.ONE),
                                                                        new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                       null));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new BigDecimalMinMaxValidator (BigDecimal.ZERO,
                                                                                                       BigDecimal.ONE),
                                                                        new BigDecimalMinMaxValidator (null,
                                                                                                       BigDecimal.ONE));
  }
}
