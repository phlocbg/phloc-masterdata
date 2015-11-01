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

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.string.StringParser;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

/**
 * Validates {@link BigDecimal} values without constraints.
 * 
 * @author Philip Helger
 */
public final class BigDecimalValidator extends AbstractStringValidator
{
  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (StringParser.parseBigDecimal (sValue) == null)
    {
      // It's OK to reuse the DOUBLE error text!
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_DOUBLE);
    }
    return ValidationResultSuccess.getInstance ();
  }
}
