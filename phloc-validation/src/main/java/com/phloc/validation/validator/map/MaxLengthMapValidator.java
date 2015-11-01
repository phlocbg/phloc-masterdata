/**
 * Copyright (C) 2006-2015 phloc systems
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
 * A validator that checks for the maximum length of a map.
 * 
 * @author Philip Helger
 */
public final class MaxLengthMapValidator extends AbstractMapValidator <Object, Object>
{
  private final int m_nMaxLength;

  public MaxLengthMapValidator (@Nonnegative final int nMaxLength)
  {
    if (nMaxLength < 1)
      throw new IllegalArgumentException ("Max length is too small: " + nMaxLength);
    m_nMaxLength = nMaxLength;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final Map <? extends Object, ? extends Object> aRequestValues)
  {
    if (aRequestValues == null || aRequestValues.size () > m_nMaxLength)
    {
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_MAX_LENGTH,
                                        Integer.toString (m_nMaxLength));
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
    final MaxLengthMapValidator rhs = (MaxLengthMapValidator) o;
    return m_nMaxLength == rhs.m_nMaxLength;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_nMaxLength).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("maxLength", m_nMaxLength).toString ();
  }
}
