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

import javax.annotation.Nonnegative;
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
 * Check whether a string fulfils a minimum length restriction.
 * 
 * @author philip
 */
public final class StringMinLengthValidator extends AbstractStringValidator
{
  private final int m_nMinLength;
  private final IHasDisplayText m_aErrorText;

  public StringMinLengthValidator (@Nonnegative final int nMinLength)
  {
    this (nMinLength, null);
  }

  /**
   * Constructor with custom error message.
   * 
   * @param nMinLength
   *        The maximum allowed string length. Must be &ge; 0.
   * @param aErrorText
   *        Optional error text. May be <code>null</code>.
   */
  public StringMinLengthValidator (@Nonnegative final int nMinLength, @Nullable final IHasDisplayText aErrorText)
  {
    if (nMinLength < 0)
      throw new IllegalArgumentException ("min length must be >= 0! Is: " + nMinLength);
    m_nMinLength = nMinLength;
    m_aErrorText = aErrorText;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (StringHelper.getLength (sValue) >= m_nMinLength)
      return ValidationResultSuccess.getInstance ();
    return m_aErrorText != null ? new ValidationResultError (m_aErrorText)
                               : new ValidationResultError (EStandardValidationErrorTexts.INVALID_MINLENGTH,
                                                            Integer.toString (m_nMinLength));
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final StringMinLengthValidator rhs = (StringMinLengthValidator) o;
    return m_nMinLength == rhs.m_nMinLength && EqualsUtils.equals (m_aErrorText, rhs.m_aErrorText);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_nMinLength).append (m_aErrorText).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("minLength", m_nMinLength)
                            .appendIfNotNull ("errorText", m_aErrorText)
                            .toString ();
  }
}
