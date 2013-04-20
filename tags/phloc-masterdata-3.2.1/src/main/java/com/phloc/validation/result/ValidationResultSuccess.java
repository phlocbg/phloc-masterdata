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
package com.phloc.validation.result;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link IValidationResult} interface.
 * 
 * @author philip
 */
@Immutable
public final class ValidationResultSuccess implements IValidationResult
{
  private static final ValidationResultSuccess s_aSuccess = new ValidationResultSuccess ();

  private ValidationResultSuccess ()
  {}

  /**
   * @return The singleton for success. Never <code>null</code>.
   */
  @Nonnull
  public static IValidationResult getInstance ()
  {
    return s_aSuccess;
  }

  public boolean isValid ()
  {
    return true;
  }

  public boolean isInvalid ()
  {
    return false;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return null;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
