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
import com.phloc.commons.text.impl.ConstantTextProvider;

/**
 * Test class for class {@link StringNotEmptyValidator}.
 * 
 * @author philip
 */
public final class StringNotEmptyValidatorTest
{
  @Test
  public void testAll ()
  {
    StringNotEmptyValidator v = new StringNotEmptyValidator ();
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertTrue (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("-1").isValid ());
    assertTrue (v.validate ("-0.00001").isValid ());
    assertTrue (v.validate ("0").isValid ());

    v = new StringNotEmptyValidator (new ConstantTextProvider ("any"));
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertTrue (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("-1").isValid ());
    assertTrue (v.validate ("-0.00001").isValid ());
    assertTrue (v.validate ("0").isValid ());
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new StringNotEmptyValidator (),
                                                                    new StringNotEmptyValidator ());
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new StringNotEmptyValidator (),
                                                                        new StringNotEmptyValidator (new ConstantTextProvider ("x")));
  }
}
