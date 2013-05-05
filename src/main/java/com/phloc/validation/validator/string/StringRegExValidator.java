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

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.regex.RegExHelper;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

/**
 * Checks string values whether they match a certain regular expression.
 * 
 * @author Philip Helger
 */
public final class StringRegExValidator extends AbstractStringValidator
{
  private final String m_sRegEx;
  private final IHasDisplayText m_aErrorText;

  public StringRegExValidator (@Nonnull @Nonempty final String sRegEx)
  {
    this (sRegEx, null);
  }

  /**
   * Constructor with custom error message.
   * 
   * @param sRegEx
   *        Regular expression to use. May neither be <code>null</code> nor
   *        empty.
   * @param aErrorText
   *        Optional error text. May be <code>null</code>.
   */
  public StringRegExValidator (@Nonnull @Nonempty final String sRegEx, @Nullable final IHasDisplayText aErrorText)
  {
    if (StringHelper.hasNoText (sRegEx))
      throw new IllegalArgumentException ("Empty reg ex");
    m_sRegEx = sRegEx;
    m_aErrorText = aErrorText;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (StringHelper.hasText (sValue) && RegExHelper.stringMatchesPattern (m_sRegEx, sValue))
      return ValidationResultSuccess.getInstance ();
    return m_aErrorText != null ? new ValidationResultError (m_aErrorText, m_sRegEx)
                               : new ValidationResultError (EStandardValidationErrorTexts.INVALID_REGEX, m_sRegEx);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final StringRegExValidator rhs = (StringRegExValidator) o;
    return m_sRegEx.equals (rhs.m_sRegEx) && EqualsUtils.equals (m_aErrorText, rhs.m_aErrorText);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sRegEx).append (m_aErrorText).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("regEx", m_sRegEx)
                            .appendIfNotNull ("errorText", m_aErrorText)
                            .toString ();
  }
}
