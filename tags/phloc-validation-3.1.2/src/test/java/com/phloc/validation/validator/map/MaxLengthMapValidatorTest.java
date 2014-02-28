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
package com.phloc.validation.validator.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.mock.PhlocTestUtils;

/**
 * Test class for class MaxLengthMapValidator
 * 
 * @author Philip Helger
 */
public final class MaxLengthMapValidatorTest
{
  @Test
  public void testAll ()
  {
    final MaxLengthMapValidator v = new MaxLengthMapValidator (2);
    assertFalse (v.validate (null).isValid ());
    assertTrue (v.validate (ContainerHelper.newMap ("a", "a")).isValid ());
    assertTrue (v.validate (ContainerHelper.newMap ("a", "a", "b", "b")).isValid ());
    assertFalse (v.validate (ContainerHelper.newMap ("a", "a", "b", "b", "c", "c")).isValid ());

    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (v, new MaxLengthMapValidator (2));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (v, new MaxLengthMapValidator (1));

    try
    {
      new MaxLengthMapValidator (0);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
  }
}
