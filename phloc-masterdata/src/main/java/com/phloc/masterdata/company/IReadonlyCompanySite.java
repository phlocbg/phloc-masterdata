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
package com.phloc.masterdata.company;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.name.IHasDisplayName;
import com.phloc.commons.type.ITypedObject;
import com.phloc.json.IJSONObject;
import com.phloc.masterdata.address.IReadonlyAddress;
import com.phloc.masterdata.email.IReadonlyExtendedEmailAddress;
import com.phloc.masterdata.telephone.IReadonlyTelephoneNumber;

/**
 * Represents a single location of a company.
 * 
 * @author Philip Helger
 */
public interface IReadonlyCompanySite extends IHasDisplayName, ITypedObject <String>
{
  /**
   * @return The company to which the site belongs
   */
  @Nonnull
  IReadonlyCompany getCompany ();

  /**
   * @return The long name of the company site, e.g. containing the city or
   *         similar stuff.
   */
  @Nullable
  String getLongName ();

  /**
   * @return The alternative name of the company site, e.g. containing a prefix
   *         regarding the type of subsidiary
   */
  @Nullable
  String getAltName ();

  @Nonnull
  String getAltNameWithFallback ();

  /**
   * @return <code>false</code> if this site is undeletable, because it is
   *         required by business logic!
   */
  boolean isDeletable ();

  /**
   * @return <code>true</code> if it is a virtual site (e.g. a WebShop) or
   *         <code>false</code> if it is a real site.
   */
  boolean isVirtualSite ();

  /**
   * @return The address of this company site.
   */
  @Nonnull
  IReadonlyAddress getAddress ();

  /**
   * @return The default telephone number of the company site.
   */
  @Nonnull
  IReadonlyTelephoneNumber getDefaultTelNo ();

  /**
   * @return The default fax number of the company site.
   */
  @Nonnull
  IReadonlyTelephoneNumber getDefaultFaxNo ();

  /**
   * @return The default email address of the company site.
   */
  @Nonnull
  IReadonlyExtendedEmailAddress getDefaultEmailAddress ();

  /**
   * @return The status of this location
   */
  @Nonnull
  ECompanySiteStatus getStatus ();

  /**
   * @return
   */
  @Nonnull
  IJSONObject getProperties ();
}
