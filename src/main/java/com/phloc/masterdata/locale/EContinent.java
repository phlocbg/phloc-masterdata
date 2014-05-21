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
package com.phloc.masterdata.locale;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;
import com.phloc.commons.name.IHasDisplayText;

public enum EContinent implements IHasID <String>, IHasDisplayText
{
  AFRICA ("af", EContinentName.AFRICA),
  ANTARCTICA ("an", EContinentName.ANTARCTICA),
  ASIA ("as", EContinentName.ASIA),
  EUROPE ("eu", EContinentName.EUROPE),
  NORTH_AMERICA ("na", EContinentName.NORTH_AMERICA),
  OCEANIA ("oc", EContinentName.OCEANIA),
  SOUTH_AMERICA ("sa", EContinentName.SOUTH_AMERICA),
  UNDEFINED ("ud", EContinentName.UNDEFINED);

  private final String m_sID;
  private final EContinentName m_aName;

  private EContinent (@Nonnull @Nonempty final String sID, @Nonnull final EContinentName aName)
  {
    m_sID = sID;
    m_aName = aName;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aName.getDisplayText (aContentLocale);
  }

  @Nullable
  public static EContinent getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EContinent.class, sID);
  }
}
