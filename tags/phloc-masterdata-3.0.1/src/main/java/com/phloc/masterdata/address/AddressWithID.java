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
package com.phloc.masterdata.address;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.id.IHasSimpleIntID;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Writable implementation of the {@link IAddress} interface.
 * 
 * @author philip
 */
@MappedSuperclass
@Embeddable
@Access (value = AccessType.PROPERTY)
@NotThreadSafe
public class AddressWithID extends Address implements IHasSimpleIntID
{
  public static final String FIELD_ID = "id";

  private int m_nID;

  public AddressWithID ()
  {}

  @Deprecated
  public AddressWithID (@Nonnull final IReadonlyAddress aBase)
  {
    super (aBase);
  }

  public AddressWithID (@Nonnull final IReadonlyAddress aBase, @Nonnull final Locale aSortLocale)
  {
    super (aBase, aSortLocale);
  }

  public AddressWithID (@Nullable final EAddressType eType)
  {
    super (eType);
  }

  public AddressWithID (@Nullable final EAddressType eType,
                        @Nullable final String sCountry,
                        @Nullable final String sState,
                        @Nullable final String sPostalCode,
                        @Nullable final String sCity,
                        @Nullable final String sStreet,
                        @Nullable final String sPostOfficeBox,
                        @Nonnull final Locale aSortLocale)
  {
    super (eType, sCountry, sState, sPostalCode, sCity, sStreet, sPostOfficeBox, aSortLocale);
  }

  @Column (name = FIELD_ID)
  @Id
  @GeneratedValue (generator = "address_gen")
  public int getID ()
  {
    return m_nID;
  }

  public void setID (final int nID)
  {
    m_nID = nID;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AddressWithID rhs = (AddressWithID) o;
    return m_nID == rhs.m_nID;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_nID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("id", m_nID).toString ();
  }
}
