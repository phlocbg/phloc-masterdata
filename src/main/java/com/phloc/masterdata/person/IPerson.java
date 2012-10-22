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
package com.phloc.masterdata.person;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.phloc.commons.state.EChange;
import com.phloc.masterdata.address.IReadonlyAddress;
import com.phloc.masterdata.email.IReadonlyExtendedEmailAddress;
import com.phloc.masterdata.telephone.IReadonlyTelephoneNumber;

public interface IPerson extends IReadonlyPerson
{

  /**
   * @return The writable name of the person. May not be <code>null</code>.
   */
  @Nonnull
  IPersonName getName ();

  /**
   * @return The writable telephone number of the person.
   */
  @Nullable
  PersonTelephoneNumber getTelephoneNumber ();

  /**
   * @return The writable email address number of the person.
   */
  @Nullable
  PersonEmailAddress getEmailAddress ();

  /**
   * @return The writable address of the person.
   */
  @Nullable
  PersonAddress getAddress ();

  /**
   * @param eGender
   *        The gender of the person. May be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setGender (@Nullable EGender eGender);

  /**
   * Set the name of the person
   * 
   * @param aName
   *        The new name to be set. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setName (@Nonnull PersonName aName);

  /**
   * Set the name of the person
   * 
   * @param aName
   *        The new name to be set. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setName (@Nonnull IReadonlyPersonName aName);

  /**
   * @param aBirthday
   *        The optional birthday of the person.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setBirthday (@Nullable LocalDate aBirthday);

  /**
   * @param aTelephoneNumber
   *        An optional telephone number.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setTelephoneNumber (@Nullable PersonTelephoneNumber aTelephoneNumber);

  /**
   * @param aTelephoneNumber
   *        An optional telephone number.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setTelephoneNumber (@Nullable IReadonlyTelephoneNumber aTelephoneNumber);

  /**
   * @param aEmailAddress
   *        An optional email address for this person.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setEmailAddress (@Nullable PersonEmailAddress aEmailAddress);

  /**
   * @param aEmailAddress
   *        An optional email address for this person.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setEmailAddress (@Nullable IReadonlyExtendedEmailAddress aEmailAddress);

  /**
   * @param aAddress
   *        An optional real address for this person.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setAddress (@Nullable PersonAddress aAddress);

  /**
   * @param aAddress
   *        An optional real address for this person.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setAddress (@Nullable IReadonlyAddress aAddress);
}
