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
package com.phloc.validation.result;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.collections.ArrayHelper;
import com.phloc.commons.name.HasDisplayTextWithArgs;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link IValidationResult} interface for errors.
 * 
 * @author Philip Helger
 */
@Immutable
public class ValidationResultError extends AbstractValidationResultError
{
  private final IHasDisplayText m_aErrorText;

  public ValidationResultError (@Nonnull final IHasDisplayText aErrorText)
  {
    this (aErrorText, (Object []) null);
  }

  public ValidationResultError (@Nonnull final IHasDisplayText aErrorText, @Nullable final Object aArg)
  {
    this (aErrorText, aArg == null ? (Object []) null : new Object [] { aArg });
  }

  public ValidationResultError (@Nonnull final IHasDisplayText aErrorText, @Nullable final Object... aArgs)
  {
    if (aErrorText == null)
      throw new NullPointerException ("errorText");
    m_aErrorText = ArrayHelper.isEmpty (aArgs) ? aErrorText : new HasDisplayTextWithArgs (aErrorText, aArgs);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aErrorText.getDisplayText (aContentLocale);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("errorText", m_aErrorText).toString ();
  }
}
