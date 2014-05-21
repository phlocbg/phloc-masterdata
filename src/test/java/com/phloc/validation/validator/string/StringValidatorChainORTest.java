/**
 * Copyright (C) 2006-2014 phloc systems
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
import com.phloc.validation.validator.IStringValidator;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Test class for class {@link StringValidatorChainOR}.
 * 
 * @author Philip Helger
 */
public final class StringValidatorChainORTest
{
  @Test
  @SuppressFBWarnings (value = "NP_NONNULL_PARAM_VIOLATION")
  public void testAll ()
  {
    StringValidatorChainOR v = new StringValidatorChainOR (new StringMinLengthValidator (3),
                                                           new StringMaxLengthValidator (6));
    assertTrue (v.validate (null).isValid ());
    assertTrue (v.validate ("").isValid ());
    assertTrue (v.validate ("TR").isValid ());
    assertTrue (v.validate ("TRU").isValid ());
    assertTrue (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("TRUE!").isValid ());
    assertTrue (v.validate ("TRUE!!").isValid ());
    assertTrue (v.validate ("TRUE!!!").isValid ());

    v = new StringValidatorChainOR (new StringMaxLengthValidator (5, new ConstantTextProvider ("any")));
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
      // null not allowed
      new StringValidatorChainOR ((IStringValidator []) null);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
  }

  @Test
  public void testStandard ()
  {
    final StringValidatorChainOR aBase = new StringValidatorChainOR (new StringMinLengthValidator (5),
                                                                     new StringMaxLengthValidator (10));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (aBase,
                                                                    new StringValidatorChainOR (new StringMinLengthValidator (5),
                                                                                                new StringMaxLengthValidator (10)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (aBase,
                                                                        new StringValidatorChainOR (new StringMinLengthValidator (5)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (aBase,
                                                                        new StringValidatorChainOR (new StringMinLengthValidator (5),
                                                                                                    new StringMaxLengthValidator (11)));
  }
}
