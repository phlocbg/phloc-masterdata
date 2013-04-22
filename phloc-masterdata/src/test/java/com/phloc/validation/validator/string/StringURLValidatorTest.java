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

import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;

/**
 * Test class for class {@link StringURLValidator}.
 * 
 * @author Philip Helger
 */
public final class StringURLValidatorTest
{
  @Test
  public void testAll ()
  {
    final StringURLValidator v = new StringURLValidator ();
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());

    assertFalse (v.validate ("www.phloc.com").isValid ());
    assertFalse (v.validate ("http://www.phloc.com#anchor").isValid ());
    assertFalse (v.validate ("http://www.phloc.com/#anchor").isValid ());
    assertFalse (v.validate ("http://www.phloc.com/path/#anchor").isValid ());
    assertFalse (v.validate ("http://www.phloc.com/file.txt#anchor").isValid ());
    assertFalse (v.validate ("http://www.phloc.com/path/file.txt#anchor").isValid ());
    assertFalse (v.validate ("http://www.phloc.com/path/?a=1&b=2#anchor").isValid ());
    assertFalse (v.validate ("http://www.phloc.com/path/and/path/and/file.txt?a=1&b=2#anchor").isValid ());

    assertTrue (v.validate ("ftp://phloc.com").isValid ());
    assertTrue (v.validate ("http://www.phloc.com").isValid ());
    assertTrue (v.validate ("http://www.phloc.com/").isValid ());
    assertTrue (v.validate ("http://www.phloc.com/path/").isValid ());
    assertTrue (v.validate ("http://www.phloc.com/file.txt").isValid ());
    assertTrue (v.validate ("http://www.phloc.com/path/file.txt").isValid ());
    assertTrue (v.validate ("http://www.phloc.com/path/?a=1&b=2").isValid ());
    assertTrue (v.validate ("http://www.phloc.com/path/and/path/and/file.txt?a=1&b=2").isValid ());
  }

  @Test
  public void testStandard ()
  {
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (new StringURLValidator (),
                                                                    new StringURLValidator ());
  }
}
