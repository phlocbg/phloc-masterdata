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
package com.phloc.validation.validator.map;

import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

/**
 * A validator that checks for the minimum length of a list.
 * 
 * @author philip
 */
public final class MinLengthMapValidator extends AbstractMapValidator
{
  private final int m_nMinLength;

  public MinLengthMapValidator (@Nonnegative final int nMinLength)
  {
    if (nMinLength < 0)
      throw new IllegalArgumentException ("Min length is too small: " + nMinLength);
    m_nMinLength = nMinLength;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final Map <String, ?> aRequestValues)
  {
    if ((aRequestValues == null && m_nMinLength > 0) ||
        (aRequestValues != null && aRequestValues.size () < m_nMinLength))
    {
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_MIN_LENGTH,
                                        Integer.toString (m_nMinLength));
    }
    return ValidationResultSuccess.getInstance ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final MinLengthMapValidator rhs = (MinLengthMapValidator) o;
    return m_nMinLength == rhs.m_nMinLength;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_nMinLength).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("minLength", m_nMinLength).toString ();
  }
}
