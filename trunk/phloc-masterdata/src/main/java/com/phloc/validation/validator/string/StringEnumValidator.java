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
package com.phloc.validation.validator.string;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

/**
 * Check that a string is part of an enumeration that implements
 * IHasID&lt;String&gt;.
 * 
 * @author philip
 */
public final class StringEnumValidator <ENUMTYPE extends Enum <ENUMTYPE> & IHasID <String>> extends
                                                                                            AbstractStringValidator
{
  private Class <ENUMTYPE> m_aClass;
  private final IHasDisplayText m_aErrorText;

  /**
   * Constructor with a default error message.
   * 
   * @param aClass
   *        The enum class to be used. May not be <code>null</code>.
   */
  public StringEnumValidator (@Nonnull final Class <ENUMTYPE> aClass)
  {
    this (aClass, null);
  }

  /**
   * Constructor with custom error message.
   * 
   * @param aClass
   *        The enum class to be used. May not be <code>null</code>.
   * @param aErrorText
   *        Optional error text. May be <code>null</code>.
   */
  public StringEnumValidator (@Nonnull final Class <ENUMTYPE> aClass, @Nullable final IHasDisplayText aErrorText)
  {
    if (aClass == null)
      throw new NullPointerException ("class");
    m_aClass = aClass;
    m_aErrorText = aErrorText;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (EnumHelper.getFromIDOrNull (m_aClass, sValue) != null)
      return ValidationResultSuccess.getInstance ();
    return new ValidationResultError (m_aErrorText != null ? m_aErrorText : EStandardValidationErrorTexts.INVALID_EMPTY);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final StringEnumValidator <?> rhs = (StringEnumValidator <?>) o;
    return m_aClass.equals (rhs.m_aClass) && EqualsUtils.equals (m_aErrorText, rhs.m_aErrorText);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aClass).append (m_aErrorText).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("class", m_aClass)
                            .appendIfNotNull ("errorText", m_aErrorText)
                            .toString ();
  }

  @Nonnull
  public static <ENUMTYPE extends Enum <ENUMTYPE> & IHasID <String>> StringEnumValidator <ENUMTYPE> create (@Nonnull final Class <ENUMTYPE> aClass)
  {
    return new StringEnumValidator <ENUMTYPE> (aClass);
  }

  @Nonnull
  public static <ENUMTYPE extends Enum <ENUMTYPE> & IHasID <String>> StringEnumValidator <ENUMTYPE> create (@Nonnull final Class <ENUMTYPE> aClass,
                                                                                                            @Nullable final IHasDisplayText aErrorText)
  {
    return new StringEnumValidator <ENUMTYPE> (aClass, aErrorText);
  }
}
