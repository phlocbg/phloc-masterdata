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
package com.phloc.masterdata.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.phloc.commons.mock.AbstractPhlocTestCase;
import com.phloc.commons.mock.PhlocTestUtils;

/**
 * Test class for class {@link ReadonlyExtendedEmailAddress}
 * 
 * @author philip
 */
public final class ReadonlyExtendedEmailAddressTest extends AbstractPhlocTestCase
{
  @Test
  public void testBasic ()
  {
    ReadonlyExtendedEmailAddress a = new ReadonlyExtendedEmailAddress (null, null, null);
    assertNull (a.getType ());
    assertNull (a.getAddress ());
    assertNull (a.getPersonal ());

    a = new ReadonlyExtendedEmailAddress (EEmailAddressType.OFFICE, "test@example.org", "Ich");
    assertEquals (EEmailAddressType.OFFICE, a.getType ());
    assertEquals ("test@example.org", a.getAddress ());
    assertEquals ("Ich", a.getPersonal ());
  }

  @Test
  public void testDefaultImpl ()
  {
    final ReadonlyExtendedEmailAddress a = new ReadonlyExtendedEmailAddress (EEmailAddressType.OFFICE,
                                                                             "test@example.org",
                                                                             "Ich");
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (a,
                                                                        new ReadonlyExtendedEmailAddress (EEmailAddressType.OFFICE2,
                                                                                                          "test@example.org",
                                                                                                          "Ich"));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (a,
                                                                        new ReadonlyExtendedEmailAddress (EEmailAddressType.OFFICE,
                                                                                                          "test@example.com",
                                                                                                          "Ich"));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (a,
                                                                        new ReadonlyExtendedEmailAddress (EEmailAddressType.OFFICE,
                                                                                                          "test@example.org",
                                                                                                          "Du"));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (a, new ReadonlyExtendedEmailAddress (a));
    PhlocTestUtils.testDefaultSerialization (a);
  }
}
