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
package com.phloc.validation.error;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.error.EErrorLevel;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Represents an overall form error. Default implementation of {@link IError}.
 * 
 * @author Philip Helger
 */
@Immutable
public class SingleError implements IError
{
  private final String m_sErrorID;
  private final EErrorLevel m_eErrorLevel;
  private final String m_sErrorFieldName;
  private final String m_sErrorText;

  public SingleError (@Nonnull final EErrorLevel eLevel, @Nonnull @Nonempty final String sErrorText)
  {
    this (eLevel, (String) null, sErrorText);
  }

  public SingleError (@Nonnull final EErrorLevel eLevel,
                      @Nullable final String sErrorFieldName,
                      @Nonnull @Nonempty final String sErrorText)
  {
    this ((String) null, eLevel, sErrorFieldName, sErrorText);
  }

  public SingleError (@Nullable final String sErrorID,
                      @Nonnull final EErrorLevel eErrorLevel,
                      @Nullable final String sErrorFieldName,
                      @Nonnull @Nonempty final String sErrorText)
  {
    if (eErrorLevel == null)
      throw new NullPointerException ("ErrorLevel");
    if (StringHelper.hasNoText (sErrorText))
      throw new IllegalArgumentException ("ErrorText");
    m_sErrorID = sErrorID;
    m_eErrorLevel = eErrorLevel;
    m_sErrorFieldName = sErrorFieldName;
    m_sErrorText = sErrorText;
  }

  @Nullable
  public String getErrorID ()
  {
    return m_sErrorID;
  }

  public boolean hasErrorID ()
  {
    return StringHelper.hasText (m_sErrorID);
  }

  @Nonnull
  public EErrorLevel getErrorLevel ()
  {
    return m_eErrorLevel;
  }

  @Nullable
  public String getErrorFieldName ()
  {
    return m_sErrorFieldName;
  }

  public boolean hasErrorFieldName ()
  {
    return StringHelper.hasText (m_sErrorFieldName);
  }

  @Nonnull
  public String getErrorText ()
  {
    return m_sErrorText;
  }

  public boolean isSuccess ()
  {
    return m_eErrorLevel.isSuccess ();
  }

  public boolean isFailure ()
  {
    return m_eErrorLevel.isFailure ();
  }

  public boolean isError ()
  {
    return m_eErrorLevel.isError ();
  }

  public boolean isNoError ()
  {
    return m_eErrorLevel.isNoError ();
  }

  public boolean isEqualSevereThan (@Nonnull final IError aOther)
  {
    return m_eErrorLevel.isEqualSevereThan (aOther.getErrorLevel ());
  }

  public boolean isLessSevereThan (@Nonnull final IError aOther)
  {
    return m_eErrorLevel.isLessSevereThan (aOther.getErrorLevel ());
  }

  public boolean isLessOrEqualSevereThan (@Nonnull final IError aOther)
  {
    return m_eErrorLevel.isLessOrEqualSevereThan (aOther.getErrorLevel ());
  }

  public boolean isMoreSevereThan (@Nonnull final IError aOther)
  {
    return m_eErrorLevel.isMoreSevereThan (aOther.getErrorLevel ());
  }

  public boolean isMoreOrEqualSevereThan (@Nonnull final IError aOther)
  {
    return m_eErrorLevel.isMoreOrEqualSevereThan (aOther.getErrorLevel ());
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final SingleError rhs = (SingleError) o;
    return EqualsUtils.equals (m_sErrorID, rhs.m_sErrorID) &&
           m_eErrorLevel.equals (rhs.m_eErrorLevel) &&
           EqualsUtils.equals (m_sErrorFieldName, rhs.m_sErrorFieldName) &&
           m_sErrorText.equals (rhs.m_sErrorText);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sErrorID)
                                       .append (m_eErrorLevel)
                                       .append (m_sErrorFieldName)
                                       .append (m_sErrorText)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("errorID", m_sErrorID)
                                       .append ("errorLevel", m_eErrorLevel)
                                       .appendIfNotNull ("errorFieldName", m_sErrorFieldName)
                                       .append ("errorText", m_sErrorText)
                                       .toString ();
  }

  @Nonnull
  public static SingleError createSuccess (@Nonnull @Nonempty final String sErrorText)
  {
    return createSuccess (null, null, sErrorText);
  }

  @Nonnull
  public static SingleError createSuccess (@Nullable final String sErrorFieldName,
                                           @Nonnull @Nonempty final String sErrorText)
  {
    return createSuccess (null, sErrorFieldName, sErrorText);
  }

  @Nonnull
  public static SingleError createSuccess (@Nullable final String sErrorID,
                                           @Nullable final String sErrorFieldName,
                                           @Nonnull @Nonempty final String sErrorText)
  {
    return new SingleError (sErrorID, EErrorLevel.SUCCESS, sErrorFieldName, sErrorText);
  }

  @Nonnull
  public static SingleError createInfo (@Nonnull @Nonempty final String sErrorText)
  {
    return createInfo (null, null, sErrorText);
  }

  @Nonnull
  public static SingleError createInfo (@Nullable final String sErrorFieldName,
                                        @Nonnull @Nonempty final String sErrorText)
  {
    return createInfo (null, sErrorFieldName, sErrorText);
  }

  @Nonnull
  public static SingleError createInfo (@Nullable final String sErrorID,
                                        @Nullable final String sErrorFieldName,
                                        @Nonnull @Nonempty final String sErrorText)
  {
    return new SingleError (sErrorID, EErrorLevel.INFO, sErrorFieldName, sErrorText);
  }

  @Nonnull
  public static SingleError createWarning (@Nonnull @Nonempty final String sErrorText)
  {
    return createWarning (null, null, sErrorText);
  }

  @Nonnull
  public static SingleError createWarning (@Nullable final String sErrorFieldName,
                                           @Nonnull @Nonempty final String sErrorText)
  {
    return createWarning (null, sErrorFieldName, sErrorText);
  }

  @Nonnull
  public static SingleError createWarning (@Nullable final String sErrorID,
                                           @Nullable final String sErrorFieldName,
                                           @Nonnull @Nonempty final String sErrorText)
  {
    return new SingleError (sErrorID, EErrorLevel.WARN, sErrorFieldName, sErrorText);
  }

  @Nonnull
  public static SingleError createError (@Nonnull @Nonempty final String sErrorText)
  {
    return createError (null, null, sErrorText);
  }

  @Nonnull
  public static SingleError createError (@Nullable final String sErrorFieldName,
                                         @Nonnull @Nonempty final String sErrorText)
  {
    return createError (null, sErrorFieldName, sErrorText);
  }

  @Nonnull
  public static SingleError createError (@Nullable final String sErrorID,
                                         @Nullable final String sErrorFieldName,
                                         @Nonnull @Nonempty final String sErrorText)
  {
    return new SingleError (sErrorID, EErrorLevel.ERROR, sErrorFieldName, sErrorText);
  }
}
