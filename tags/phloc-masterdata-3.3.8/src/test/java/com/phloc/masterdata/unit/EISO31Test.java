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
package com.phloc.masterdata.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

/**
 * Test class for class {@link EISO31}.
 * 
 * @author Philip Helger
 */
public final class EISO31Test
{
  @Test
  public void testAll ()
  {
    for (final EISO31 e : EISO31.values ())
    {
      assertTrue (e.getID () >= 0);
      assertNotNull (e.getDisplayText (Locale.GERMAN));
      assertSame (e, EISO31.getFromIDOrNull (e.getID ()));
    }
  }
}
