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
package com.phloc.masterdata.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;

import com.phloc.commons.CGlobal;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.MicroTypeConverter;
import com.phloc.commons.microdom.serialize.MicroReader;
import com.phloc.commons.regex.RegExHelper;
import com.phloc.commons.string.StringParser;
import com.phloc.commons.text.IReadonlyMultiLingualText;

/**
 * Test class for class {@link UnitManager}.
 * 
 * @author philip
 */
public final class UnitManagerTest
{
  @Test
  public void testDefault ()
  {
    final UnitManager aMgr = UnitManager.getDefaultInstance ();
    assertNotNull (aMgr);
    assertEquals (13, aMgr.getAllSectors ().size ());
    assertEquals (401, aMgr.getAllQuantities ().size ());
  }

  @Test
  public void createCodeList ()
  {
    final IMicroDocument aDoc = MicroReader.readMicroXML (UnitManager.DEFAULT_UNIT_RES);
    final IMicroElement eRoot = aDoc.getDocumentElement ();
    final StringBuilder aSB1 = new StringBuilder ();
    // Read all quantities
    for (final IMicroElement eQuantity : eRoot.getFirstChildElement ("quantities").getChildElements ("quantity"))
    {
      final int nQuantity = StringParser.parseInt (eQuantity.getAttribute ("id"), CGlobal.ILLEGAL_UINT);
      final IReadonlyMultiLingualText aName = MicroTypeConverter.convertToNative (eQuantity.getFirstChildElement ("name"),
                                                                                  IReadonlyMultiLingualText.class);

      String sEnumName = aName.getTextWithLocaleFallback (Locale.ENGLISH).trim ().toUpperCase (Locale.US);
      sEnumName = RegExHelper.getAsIdentifier (sEnumName, '_');
      while (sEnumName.startsWith ("_"))
        sEnumName = sEnumName.substring (1);
      while (sEnumName.endsWith ("_"))
        sEnumName = sEnumName.substring (0, sEnumName.length () - 1);
      sEnumName = sEnumName.replace ("__", "_");
      System.out.println (sEnumName);
    }
  }
}
