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
package com.phloc.validation.validator.string;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.StringParser;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

public final class LongUnsignedValidator extends AbstractStringValidator
{
  private final boolean m_bZeroIsAllowed;

  public LongUnsignedValidator (final boolean bZeroIsAllowed)
  {
    m_bZeroIsAllowed = bZeroIsAllowed;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    final Long aValue = StringParser.parseLongObj (sValue);
    if (aValue == null)
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_INT);
    final long nValue = aValue.longValue ();
    if (m_bZeroIsAllowed)
    {
      if (nValue < 0)
        return new ValidationResultError (EStandardValidationErrorTexts.INVALID_INT_UNSIGNED_INCL0);
    }
    else
    {
      if (nValue <= 0)
        return new ValidationResultError (EStandardValidationErrorTexts.INVALID_INT_UNSIGNED_EXCL0);
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
    final LongUnsignedValidator rhs = (LongUnsignedValidator) o;
    return m_bZeroIsAllowed == rhs.m_bZeroIsAllowed;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_bZeroIsAllowed).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("zeroIsAllowed", m_bZeroIsAllowed).toString ();
  }
}
