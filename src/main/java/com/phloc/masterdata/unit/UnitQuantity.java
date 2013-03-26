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

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.id.IHasIntID;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.text.IReadonlyMultiLingualText;

@Immutable
public final class UnitQuantity implements IHasIntID, IHasDisplayText
{
  private final int m_nQuantityID;
  private final IReadonlyMultiLingualText m_aName;

  public UnitQuantity (@Nonnegative final int nQuantityID, @Nonnull final IReadonlyMultiLingualText aName)
  {
    if (nQuantityID < 0)
      throw new IllegalArgumentException ("Invalid quantity number");
    if (aName == null)
      throw new NullPointerException ("name");
    m_nQuantityID = nQuantityID;
    m_aName = aName;
  }

  @Nonnegative
  public int getID ()
  {
    return m_nQuantityID;
  }

  @Nonnull
  public Integer getIDObj ()
  {
    return Integer.valueOf (m_nQuantityID);
  }

  @Nonnull
  public IReadonlyMultiLingualText getName ()
  {
    return m_aName;
  }

  @Nullable
  public String getDisplayText (final Locale aContentLocale)
  {
    return m_aName.getTextWithLocaleFallback (aContentLocale);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("quantityID", m_nQuantityID).append ("name", m_aName).toString ();
  }
}