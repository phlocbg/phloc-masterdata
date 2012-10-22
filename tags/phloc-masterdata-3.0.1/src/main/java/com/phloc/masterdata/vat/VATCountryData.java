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
package com.phloc.masterdata.vat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.IHasCountry;
import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Represents all the different VAT items for a single country.
 * 
 * @author philip
 */
@NotThreadSafe
public final class VATCountryData implements IHasCountry
{
  private final Locale m_aCountry;
  private final boolean m_bZeroVATAllowed;
  private final Map <String, IVATItem> m_aItems = new HashMap <String, IVATItem> ();
  private final String m_sCountryName;
  private final String m_sInternalComment;

  public VATCountryData (@Nonnull final Locale aCountry,
                         final boolean bZeroVATAllowed,
                         @Nullable final String sCountryName,
                         @Nullable final String sInternalComment)
  {
    if (aCountry == null)
      throw new NullPointerException ("country");
    m_aCountry = aCountry;
    m_bZeroVATAllowed = bZeroVATAllowed;
    m_sCountryName = sCountryName;
    m_sInternalComment = sInternalComment;
  }

  @Nonnull
  public Locale getCountry ()
  {
    return m_aCountry;
  }

  public boolean isZeroVATAllowed ()
  {
    return m_bZeroVATAllowed;
  }

  @Nullable
  public String getCountryName ()
  {
    return m_sCountryName;
  }

  @Nullable
  public String getInternalComment ()
  {
    return m_sInternalComment;
  }

  @Nonnull
  public EChange addItem (@Nonnull final VATItem aVATItem)
  {
    if (aVATItem == null)
      throw new NullPointerException ("VAT item");

    final String sID = aVATItem.getID ();
    if (m_aItems.containsKey (sID))
      return EChange.UNCHANGED;
    m_aItems.put (sID, aVATItem);
    return EChange.CHANGED;
  }

  public boolean isEmpty ()
  {
    return m_aItems.isEmpty ();
  }

  @Nonnull
  @ReturnsImmutableObject
  public Map <? extends String, ? extends IVATItem> getAllItems ()
  {
    return ContainerHelper.makeUnmodifiable (m_aItems);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("country", m_aCountry)
                                       .append ("zeroVATAllowed", m_bZeroVATAllowed)
                                       .append ("items", m_aItems)
                                       .appendIfNotNull ("countryname", m_sCountryName)
                                       .appendIfNotNull ("internalComment", m_sInternalComment)
                                       .toString ();
  }
}
