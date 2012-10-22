/**
 * Copyright (C) 2006-2012 phloc systems
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
package com.phloc.masterdata.telephone;

import javax.annotation.Nonnull;

import com.phloc.commons.ICloneable;
import com.phloc.commons.state.EChange;

/**
 * Base interface for a telephone number.
 * 
 * @author philip
 */
public interface ITelephoneNumber extends IReadonlyTelephoneNumber, ICloneable <ITelephoneNumber>
{
  /**
   * @param eType
   *        The semantic type of this telephone number.
   */
  @Nonnull
  EChange setType (ETelephoneType eType);

  /**
   * @param sCountryCode
   *        The country where the number resides.
   */
  @Nonnull
  EChange setCountryCode (String sCountryCode);

  /**
   * @param sAreaCode
   *        The area code for the phone number. This is country dependent.
   */
  @Nonnull
  EChange setAreaCode (String sAreaCode);

  /**
   * @param sLine
   *        The main telephone number within an area code.
   */
  @Nonnull
  EChange setLine (String sLine);

  /**
   * @param sDirectDial
   *        The direct dial for a further specification of a line. Is optional
   *        and may be <code>null</code>.
   */
  @Nonnull
  EChange setDirectDial (String sDirectDial);
}
