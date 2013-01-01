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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import com.phloc.commons.CGlobal;
import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.io.IReadableResource;
import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.MicroTypeConverter;
import com.phloc.commons.microdom.serialize.MicroReader;
import com.phloc.commons.string.StringParser;
import com.phloc.commons.text.IReadonlyMultiLingualText;

/**
 * FIXME THIS CLASS IS NOT YET FINISHED!
 * 
 * @author philip
 */
public final class UnitManager
{
  private static final class SingletonHolder
  {
    static final UnitManager s_aInstance = new UnitManager (new ClassPathResource ("codelists/rec20_Rev7e_2010.xml"));
  }

  private final Map <Integer, UnitSector> m_aSectors = new HashMap <Integer, UnitSector> ();

  private void _readFromFile (@Nonnull final IReadableResource aRes)
  {
    final IMicroDocument aDoc = MicroReader.readMicroXML (aRes);
    if (aDoc == null)
      throw new IllegalArgumentException ("Failed to read " + aRes + " as XML document!");

    // Read all sectors
    for (final IMicroElement eSector : aDoc.getDocumentElement ()
                                           .getFirstChildElement ("sectors")
                                           .getChildElements ("sector"))
    {
      final int nGroupNum = StringParser.parseInt (eSector.getAttribute ("groupnum"), CGlobal.ILLEGAL_UINT);
      final IReadonlyMultiLingualText aName = MicroTypeConverter.convertToNative (eSector.getFirstChildElement ("name"),
                                                                                  IReadonlyMultiLingualText.class);
      final UnitSector aSector = new UnitSector (nGroupNum, aName);
      if (m_aSectors.containsKey (aSector.getIDObj ()))
        throw new IllegalStateException ("A unit sector with group number " +
                                         aSector.getID () +
                                         " is already contained!");
      m_aSectors.put (aSector.getIDObj (), aSector);
    }
  }

  public UnitManager (@Nonnull final IReadableResource aListRes)
  {
    _readFromFile (aListRes);
  }

  @Nonnull
  public static UnitManager getDefaultInstance ()
  {
    return SingletonHolder.s_aInstance;
  }

  @Nonnull
  @ReturnsImmutableObject
  public Collection <UnitSector> getAllSectors ()
  {
    return ContainerHelper.makeUnmodifiable (m_aSectors.values ());
  }
}
