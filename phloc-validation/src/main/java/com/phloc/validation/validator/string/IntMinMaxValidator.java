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

public final class IntMinMaxValidator extends AbstractStringValidator
{
  private final int m_nMinInclusive;
  private final int m_nMaxInclusive;

  /**
   * Ctor. Note: only one value may be infinity. If both values should be
   * infinity, use the {@link IntValidator} which imposes no limits!
   * 
   * @param nMinInclusive
   *        Minimum allowed value. Must be &le; maximum value. Pass in
   *        {@link Integer#MIN_VALUE} to indicate no lower limit.
   * @param nMaxInclusive
   *        Maximum allowed value. Must be &ge; minimum value. Pass in
   *        {@link Integer#MAX_VALUE} to indicate no upper limit.
   */
  public IntMinMaxValidator (final int nMinInclusive, final int nMaxInclusive)
  {
    final boolean bMinInfinity = nMinInclusive == Integer.MIN_VALUE;
    final boolean bMaxInfinity = nMaxInclusive == Integer.MAX_VALUE;
    if (bMinInfinity && bMaxInfinity)
      throw new IllegalArgumentException ("Both min and max are infinity!");
    if (!bMinInfinity && !bMaxInfinity && nMinInclusive > nMaxInclusive)
      throw new IllegalArgumentException ("Minimum value is greater than maximum value!");
    m_nMinInclusive = nMinInclusive;
    m_nMaxInclusive = nMaxInclusive;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    final Integer aValue = StringParser.parseIntObj (sValue);
    if (aValue == null)
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_INT);
    final int nValue = aValue.intValue ();
    if (m_nMinInclusive != Integer.MIN_VALUE && nValue < m_nMinInclusive)
    {
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_INT_RANGE,
                                        Integer.toString (m_nMinInclusive),
                                        Integer.toString (m_nMaxInclusive));
    }
    if (m_nMaxInclusive != Integer.MAX_VALUE && nValue > m_nMaxInclusive)
    {
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_INT_RANGE,
                                        Integer.toString (m_nMinInclusive),
                                        Integer.toString (m_nMaxInclusive));
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
    final IntMinMaxValidator rhs = (IntMinMaxValidator) o;
    return m_nMinInclusive == rhs.m_nMinInclusive && m_nMaxInclusive == rhs.m_nMaxInclusive;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_nMinInclusive)
                            .append (m_nMaxInclusive)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("minIncl", m_nMinInclusive)
                            .append ("maxIncl", m_nMaxInclusive)
                            .toString ();
  }
}
