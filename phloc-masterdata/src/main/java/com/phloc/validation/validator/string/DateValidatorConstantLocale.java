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

import java.util.Locale;

import javax.annotation.Nonnull;

import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Validating date values with a fixed locale.
 * 
 * @author philip
 */
public class DateValidatorConstantLocale extends AbstractStringDateValidator
{
  private final Locale m_aParseLocale;

  public DateValidatorConstantLocale (@Nonnull final Locale aParseLocale)
  {
    if (aParseLocale == null)
      throw new NullPointerException ("parseLocale");
    m_aParseLocale = aParseLocale;
  }

  @Override
  @Nonnull
  protected final Locale getParseLocale ()
  {
    return m_aParseLocale;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final DateValidatorConstantLocale rhs = (DateValidatorConstantLocale) o;
    return m_aParseLocale.equals (rhs.m_aParseLocale);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aParseLocale).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("parseLocale", m_aParseLocale).toString ();
  }
}
