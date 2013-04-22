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
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.phloc.datetime.format.PDTFormatPatterns;
import com.phloc.datetime.format.PDTFromString;
import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;

/**
 * Base class for validating date values.
 * 
 * @author Philip Helger
 */
public abstract class AbstractStringDateValidator extends AbstractStringValidator
{
  @Nonnull
  protected abstract Locale getParseLocale ();

  @Nonnull
  public final IValidationResult validate (@Nullable final String sValue)
  {
    final Locale aParseLocale = getParseLocale ();
    final DateTime aDate = PDTFromString.getDefaultDateFromString (sValue, aParseLocale);
    if (aDate != null)
      return ValidationResultSuccess.getInstance ();
    return new ValidationResultError (EStandardValidationErrorTexts.INVALID_DATE,
                                      PDTFormatPatterns.getDefaultPatternDate (aParseLocale));
  }
}
