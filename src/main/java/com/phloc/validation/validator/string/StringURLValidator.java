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
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.url.URLValidator;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

/**
 * Check if a URL is valid.
 * 
 * @author Philip Helger
 */
@Immutable
public final class StringURLValidator extends AbstractStringValidator
{
  public StringURLValidator ()
  {}

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (!URLValidator.isValid (sValue))
      return new ValidationResultError (EStandardValidationErrorTexts.INVALID_URL);

    return ValidationResultSuccess.getInstance ();
  }
}
