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
package com.phloc.validation.error;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.phloc.commons.error.EErrorLevel;
import com.phloc.commons.mock.PhlocTestUtils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Test class for class {@link SingleError}.
 * 
 * @author Philip Helger
 */
public final class SingleErrorTest
{
  @Test
  @SuppressFBWarnings (value = "NP_NONNULL_PARAM_VIOLATION")
  public void testBasic ()
  {
    final SingleError aFE = new SingleError (EErrorLevel.ERROR, "any");
    assertNull (aFE.getErrorID ());
    assertSame (EErrorLevel.ERROR, aFE.getErrorLevel ());
    assertNull (aFE.getErrorFieldName ());
    assertEquals ("any", aFE.getErrorText ());

    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (aFE, new SingleError (EErrorLevel.ERROR, "any"));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (aFE, new SingleError (EErrorLevel.WARN, "any"));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (aFE, new SingleError (EErrorLevel.ERROR,
                                                                                              "any other"));

    try
    {
      new SingleError (null, "xy");
      fail ();
    }
    catch (final NullPointerException ex)
    {}
    try
    {
      new SingleError (EErrorLevel.ERROR, null);
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
    try
    {
      new SingleError (EErrorLevel.ERROR, "");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}
  }
}
