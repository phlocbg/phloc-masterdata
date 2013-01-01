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
package com.phloc.masterdata.address;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.ICloneable;
import com.phloc.commons.state.EChange;

/**
 * The writable version of the address interface.
 * 
 * @author philip
 */
public interface IAddress extends IReadonlyAddress, ICloneable <IAddress>
{
  /**
   * @param eAddressType
   *        The semantic type of this address.
   */
  @Nonnull
  EChange setType (@Nullable EAddressType eAddressType);

  /**
   * @param sCountry
   *        The country the address resides in. The uppercase two-letter
   *        ISO-3166 code as used by java.util.Locale.
   */
  @Nonnull
  EChange setCountry (@Nullable String sCountry);

  /**
   * @param sState
   *        The optional state within the country. May be <code>null</code>.
   */
  @Nonnull
  EChange setState (@Nullable String sState);

  /**
   * @param sZipCode
   *        The ZIP code representing the area within a state/country.
   */
  @Nonnull
  EChange setPostalCode (@Nullable String sZipCode);

  /**
   * @param sCity
   *        The name of the city the address resides in.
   */
  @Nonnull
  EChange setCity (@Nullable String sCity);

  /**
   * @param sStreet
   *        The street (including the number) of the address.
   */
  @Nonnull
  EChange setStreet (@Nullable String sStreet);

  /**
   * @param sPOBox
   *        An optional post office box that should be used instead the street.
   *        May be <code>null</code>.
   */
  @Nonnull
  EChange setPostOfficeBox (@Nullable String sPOBox);
}
