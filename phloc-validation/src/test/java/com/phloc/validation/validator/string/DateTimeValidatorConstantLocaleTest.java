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
package com.phloc.validation.validator.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;

/**
 * Test class for class {@link DateTimeValidatorConstantLocale}.
 * 
 * @author Philip Helger
 */
public final class DateTimeValidatorConstantLocaleTest
{
  @Test
  public void testAll ()
  {
    final DateTimeValidatorConstantLocale v = new DateTimeValidatorConstantLocale (Locale.GERMAN);
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertFalse (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("05.01.2010 14:15:00").isValid ());
    assertFalse (v.validate (" 05.01.2010 14:15:00 ").isValid ());
    assertFalse (v.validate ("30.02.2010 14:30:00").isValid ());
    assertFalse (v.validate ("28.02.2010 14:60:00").isValid ());
    assertFalse (v.validate ("28.02.2010 14:59").isValid ());
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new DateTimeValidatorConstantLocale (Locale.GERMAN),
                                                                    new DateTimeValidatorConstantLocale (Locale.GERMAN));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (new DateTimeValidatorConstantLocale (Locale.GERMAN),
                                                                        new DateTimeValidatorConstantLocale (Locale.ENGLISH));
  }
}
