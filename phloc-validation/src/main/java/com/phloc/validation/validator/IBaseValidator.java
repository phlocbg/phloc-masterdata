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
package com.phloc.validation.validator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.validation.result.IValidationResult;

/**
 * Base interface for a validator.
 * 
 * @author Philip Helger
 * @param <T>
 *        The datatype to be validated
 */
public interface IBaseValidator <T>
{
  /**
   * Validate the passed value.
   * 
   * @param aValue
   *        The value to be validated. May be <code>null</code>.
   * @return The non-null validation result.
   */
  @Nonnull
  IValidationResult validate (@Nullable T aValue);
}
