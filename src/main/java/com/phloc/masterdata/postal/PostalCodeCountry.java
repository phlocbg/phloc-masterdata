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
package com.phloc.masterdata.postal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Contains postal code information for a single country.
 * 
 * @author philip
 */
@NotThreadSafe
public final class PostalCodeCountry implements IPostalCodeCountry
{
  private final String m_sISO;
  private final List <PostalCodeFormat> m_aFormats = new ArrayList <PostalCodeFormat> ();
  private String m_sOneCode;
  private String m_sNote;

  public PostalCodeCountry (@Nonnull @Nonempty final String sISO)
  {
    if (StringHelper.hasNoText (sISO))
      throw new IllegalArgumentException ("ISO may not be empty!");
    m_sISO = sISO;
  }

  @Nonnull
  @Nonempty
  public String getISO ()
  {
    return m_sISO;
  }

  void addFormat (@Nonnull final PostalCodeFormat aFormat)
  {
    if (aFormat == null)
      throw new NullPointerException ("format");
    m_aFormats.add (aFormat);
  }

  @Nonnegative
  public int getFormatCount ()
  {
    return m_aFormats.size ();
  }

  @Nonnull
  @ReturnsImmutableObject
  public List <PostalCodeFormat> getAllFormats ()
  {
    return ContainerHelper.makeUnmodifiable (m_aFormats);
  }

  void setExactlyOneCode (@Nonnull @Nonempty final String sOneCode)
  {
    if (StringHelper.hasNoText (sOneCode))
      throw new IllegalArgumentException ("oneCode may not be empty");
    if (!isValidPostalCode (sOneCode))
      throw new IllegalArgumentException ("The passed code '" + sOneCode + "' is not valid according to the rules!");
    m_sOneCode = sOneCode;
  }

  @Nullable
  public String getExactlyOneCode ()
  {
    return m_sOneCode;
  }

  void setNote (@Nonnull @Nonempty final String sNote)
  {
    if (StringHelper.hasNoText (sNote))
      throw new IllegalArgumentException ("note may not be empty");
    m_sNote = sNote;
  }

  @Nullable
  public String getNote ()
  {
    return m_sNote;
  }

  public boolean isValidPostalCode (@Nullable final String sPostalCode)
  {
    if (StringHelper.hasText (sPostalCode))
      for (final PostalCodeFormat aFormat : m_aFormats)
        if (aFormat.isValidPostalCode (sPostalCode))
          return true;
    return false;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllExamples ()
  {
    final List <String> ret = new ArrayList <String> ();
    for (final PostalCodeFormat aFormat : m_aFormats)
      ret.add (aFormat.getExample ());
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ISO", m_sISO)
                                       .append ("formats", m_aFormats)
                                       .appendIfNotNull ("oneCode", m_sOneCode)
                                       .appendIfNotNull ("note", m_sNote)
                                       .toString ();
  }
}