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
package com.phloc.masterdata.postal;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.annotations.ReturnsMutableCopy;

public interface IPostalCodeCountry
{
  /**
   * @return The ISO code of this country. Never <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getISO ();

  /**
   * @return The number of formats defined for this country.
   */
  @Nonnegative
  int getFormatCount ();

  /**
   * @return All formats defined for this country. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  List <PostalCodeFormat> getAllFormats ();

  /**
   * Get the format at the specified index
   * 
   * @param nIndex
   *        The index to use.
   * @return <code>null</code> if the index is invalid
   */
  @Nullable
  PostalCodeFormat getFormatOfIndex (int nIndex);

  /**
   * @return The number of specific postal codes defined for this country.
   */
  @Nonnegative
  int getSpecificPostalCodeCount ();

  /**
   * @return All specific postal codes but never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  List <String> getAllSpecificPostalCodes ();

  /**
   * @return An optional note for this country.
   */
  @Nullable
  String getNote ();

  /**
   * Check if the passed postal code is valid for this country. A postal code is
   * considered valid, if it matches any of the formats defined for this
   * country.
   * 
   * @param sPostalCode
   *        The postal code to check. May be <code>null</code>.
   * @return <code>true</code> if the passed postal code matches at least any
   *         format defined for this country.
   */
  boolean isValidPostalCode (@Nullable String sPostalCode);

  /**
   * @return A list of all available example postal codes that show the
   *         different formats available.
   */
  @Nonnull
  @ReturnsMutableCopy
  List <String> getAllExamples ();
}
