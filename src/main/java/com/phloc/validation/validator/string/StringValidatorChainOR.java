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

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.collections.ArrayHelper;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultSuccess;
import com.phloc.validation.validator.IBaseValidator;
import com.phloc.validation.validator.IStringValidator;

/**
 * A collection of simple {@link IBaseValidator} objects that are executed
 * together. If at least one validators indicates success, the overall
 * validation succeeds. In case of only validation error, the last error is
 * returned.
 * 
 * @author Philip Helger
 */
public class StringValidatorChainOR extends AbstractStringValidator
{
  private final List <IStringValidator> m_aValidators;

  public StringValidatorChainOR (@Nonnull @Nonempty final IStringValidator... aValidators)
  {
    if (ArrayHelper.isEmpty (aValidators))
      throw new IllegalArgumentException ("validators");
    m_aValidators = ContainerHelper.newList (aValidators);
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String aValue)
  {
    IValidationResult aRes = null;
    for (final IStringValidator aValidator : m_aValidators)
    {
      aRes = aValidator.validate (aValue);
      if (aRes.isValid ())
        return aRes;
    }
    return aRes != null ? aRes : ValidationResultSuccess.getInstance ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final StringValidatorChainOR rhs = (StringValidatorChainOR) o;
    return m_aValidators.equals (rhs.m_aValidators);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aValidators).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("validators", m_aValidators).toString ();
  }
}
