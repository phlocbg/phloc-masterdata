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
package com.phloc.validation.validator.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.validation.validator.string.BigDecimalUnsignedValidator;

/**
 * Test class for class {@link BigDecimalUnsignedValidator}.
 * 
 * @author philip
 */
public final class BigDecimalUnsignedValidatorTest
{
  @Test
  public void testAll ()
  {
    BigDecimalUnsignedValidator v = new BigDecimalUnsignedValidator (true);
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertFalse (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("1").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertFalse (v.validate ("-1").isValid ());

    v = new BigDecimalUnsignedValidator (false);
    assertTrue (v.validate ("1").isValid ());
    assertFalse (v.validate ("0").isValid ());
    assertFalse (v.validate ("-1").isValid ());
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new BigDecimalUnsignedValidator (true),
                                                                    new BigDecimalUnsignedValidator (true));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new BigDecimalUnsignedValidator (false),
                                                                    new BigDecimalUnsignedValidator (false));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new BigDecimalUnsignedValidator (true),
                                                                        new BigDecimalUnsignedValidator (false));
  }
}
