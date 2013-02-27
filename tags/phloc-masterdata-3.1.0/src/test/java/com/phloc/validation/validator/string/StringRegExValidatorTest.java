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
 * Test class for class {@link StringRegExValidator}.
 * 
 * @author philip
 */
public final class StringRegExValidatorTest
{
  @Test
  @SuppressFBWarnings (value = "NP_NONNULL_PARAM_VIOLATION")
  public void testAll ()
  {
    StringRegExValidator v = new StringRegExValidator ("\\-?[0-9]+");
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertFalse (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("-1").isValid ());
    assertFalse (v.validate ("-0.00001").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertTrue (v.validate ("1").isValid ());
    assertTrue (v.validate ("10").isValid ());

    v = new StringRegExValidator ("\\-?[0-9]+", new ConstantTextProvider ("any"));
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertFalse (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("-1").isValid ());
    assertFalse (v.validate ("-0.00001").isValid ());
    assertTrue (v.validate ("0").isValid ());
    assertTrue (v.validate ("1").isValid ());
    assertTrue (v.validate ("10").isValid ());

    try
    {
      // empty regex
      new StringRegExValidator (null);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}

    try
    {
      // empty regex
      new StringRegExValidator ("");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new StringRegExValidator ("ab.+"),
                                                                    new StringRegExValidator ("ab.+"));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new StringRegExValidator ("^any$"),
                                                                    new StringRegExValidator ("^any$"));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new StringRegExValidator ("^any$"),
                                                                        new StringRegExValidator (".*else.*"));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new StringRegExValidator ("^any$"),
                                                                        new StringRegExValidator ("^any$",
                                                                                                  new ConstantTextProvider ("any")));
  }
}
