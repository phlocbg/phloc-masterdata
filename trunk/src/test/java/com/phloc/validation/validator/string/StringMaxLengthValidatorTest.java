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

import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.commons.text.impl.ConstantTextProvider;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Test class for class {@link StringMaxLengthValidator}.
 * 
 * @author philip
 */
public final class StringMaxLengthValidatorTest
{
  @Test
  @SuppressFBWarnings ("TQ_NEVER_VALUE_USED_WHERE_ALWAYS_REQUIRED")
  public void testAll ()
  {
    StringMaxLengthValidator v = new StringMaxLengthValidator (5);
    assertTrue (v.validate (null).isValid ());
    assertTrue (v.validate ("").isValid ());
    assertTrue (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("-1").isValid ());
    assertFalse (v.validate ("-0.00001").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertTrue (v.validate ("1").isValid ());
    assertTrue (v.validate ("10").isValid ());

    v = new StringMaxLengthValidator (5, new ConstantTextProvider ("any"));
    assertTrue (v.validate (null).isValid ());
    assertTrue (v.validate ("").isValid ());
    assertTrue (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("-1").isValid ());
    assertFalse (v.validate ("-0.00001").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertTrue (v.validate ("1").isValid ());
    assertTrue (v.validate ("10").isValid ());

    try
    {
      // Max length invalid
      new StringMaxLengthValidator (-1);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new StringMaxLengthValidator (10),
                                                                    new StringMaxLengthValidator (10));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new StringMaxLengthValidator (1),
                                                                    new StringMaxLengthValidator (1));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new StringMaxLengthValidator (1),
                                                                        new StringMaxLengthValidator (10));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new StringMaxLengthValidator (1),
                                                                        new StringMaxLengthValidator (Integer.MAX_VALUE));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new StringMaxLengthValidator (1),
                                                                        new StringMaxLengthValidator (2));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new StringMaxLengthValidator (1),
                                                                        new StringMaxLengthValidator (1,
                                                                                                      new ConstantTextProvider ("any")));
  }
}
