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
package com.phloc.validation.validator.string;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

/**
 * Check that a string is not empty. This is a special case of the string
 * minimum length validator, having a minimum length of 1 character.
 * 
 * @author Philip Helger
 */
public final class StringNotEmptyValidator extends AbstractStringValidator
{
  private final IHasDisplayText m_aErrorText;

  public StringNotEmptyValidator ()
  {
    this (null);
  }

  /**
   * Constructor with custom error message.
   * 
   * @param aErrorText
   *        Optional error text. May be <code>null</code>.
   */
  public StringNotEmptyValidator (@Nullable final IHasDisplayText aErrorText)
  {
    m_aErrorText = aErrorText;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (StringHelper.hasText (sValue))
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
    final StringNotEmptyValidator rhs = (StringNotEmptyValidator) o;
    return EqualsUtils.equals (m_aErrorText, rhs.m_aErrorText);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aErrorText).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("errorText", m_aErrorText).toString ();
  }
}
