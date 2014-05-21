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
package com.phloc.validation.error;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.error.IHasErrorID;
import com.phloc.commons.error.IHasErrorLevel;
import com.phloc.commons.error.IResourceLocation;
import com.phloc.commons.error.ISeverityComparable;
import com.phloc.commons.state.IErrorIndicator;
import com.phloc.commons.state.ISuccessIndicator;

/**
 * Base interface for a single error, that has an error ID, and error level, and
 * error field name and an error text.
 * 
 * @author Philip Helger
 */
public interface IError extends IHasErrorID, IHasErrorLevel, ISuccessIndicator, IErrorIndicator, ISeverityComparable <IError>, Serializable
{
  /**
   * @return The field for which the error occurred. May be <code>null</code>.
   */
  @Nullable
  String getErrorFieldName ();

  /**
   * @return <code>true</code> if a field name is present, <code>false</code>
   *         otherwise
   */
  boolean hasErrorFieldName ();

  /**
   * @return The error field name of this object as an {@link IResourceLocation}
   *         . If no error field name is present, <code>null</code> is returned,
   *         else an {@link IResourceLocation} with the field name set is
   *         returned.
   */
  @Nullable
  IResourceLocation getResourceLocation ();

  /**
   * @return The message of this form error. The error text is always locale
   *         specific because this error is meant to be for a single form
   *         instance represented in a fixed locale. The result may neither be
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getErrorText ();
}
