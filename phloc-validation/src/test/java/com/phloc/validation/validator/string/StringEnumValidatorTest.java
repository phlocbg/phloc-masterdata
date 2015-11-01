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

import org.junit.Test;

import com.phloc.commons.error.EErrorLevel;
import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.commons.state.ETriState;
import com.phloc.commons.text.impl.ConstantTextProvider;
import com.phloc.validation.validator.IStringValidator;

/**
 * Test class for class {@link StringEnumValidator}.
 * 
 * @author Philip Helger
 */
public final class StringEnumValidatorTest
{
  @Test
  public void testAll ()
  {
    final IStringValidator v = StringEnumValidator.create (ETriState.class);
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertFalse (v.validate ("TRUE").isValid ());
    assertFalse (v.validate ("-1").isValid ());
    for (final ETriState eFEL : ETriState.values ())
      assertTrue (v.validate (eFEL.getID ()).isValid ());
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (StringEnumValidator.create (ETriState.class),
                                                                    StringEnumValidator.create (ETriState.class));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (StringEnumValidator.create (ETriState.class),
                                                                        StringEnumValidator.create (EErrorLevel.class));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (StringEnumValidator.create (ETriState.class),
                                                                        StringEnumValidator.create (ETriState.class,
                                                                                                    new ConstantTextProvider ("x")));
  }
}
