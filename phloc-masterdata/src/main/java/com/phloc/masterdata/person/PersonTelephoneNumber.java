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
package com.phloc.masterdata.person;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.masterdata.telephone.ETelephoneType;
import com.phloc.masterdata.telephone.IReadonlyTelephoneNumber;
import com.phloc.masterdata.telephone.TelephoneNumberWithID;

/**
 * Person specific implementation
 * 
 * @author Philip Helger
 */
@Entity
@Access (value = AccessType.PROPERTY)
public class PersonTelephoneNumber extends TelephoneNumberWithID
{
  private Person m_aOwner;

  public PersonTelephoneNumber ()
  {}

  public PersonTelephoneNumber (@Nonnull final Person aOwner)
  {
    setOwner (aOwner);
  }

  public PersonTelephoneNumber (@Nonnull final Person aOwner, @Nonnull final IReadonlyTelephoneNumber aBase)
  {
    super (aBase);
    setOwner (aOwner);
  }

  public PersonTelephoneNumber (@Nonnull final Person aOwner,
                                @Nullable final ETelephoneType eType,
                                @Nullable final String sCountryCode,
                                @Nullable final String sAreaCode,
                                @Nullable final String sLine,
                                @Nullable final String sDirectDial)
  {
    super (eType, sCountryCode, sAreaCode, sLine, sDirectDial);
    setOwner (aOwner);
  }

  @ManyToOne
  @PrimaryKeyJoinColumn
  @JoinColumn (name = "owner", nullable = false)
  @Nullable
  public Person getOwner ()
  {
    return m_aOwner;
  }

  @Transient
  @Nullable
  public String getOwnerID ()
  {
    return m_aOwner == null ? null : m_aOwner.getID ();
  }

  public void setOwner (@Nonnull final Person aOwner)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    m_aOwner = aOwner;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final PersonTelephoneNumber rhs = (PersonTelephoneNumber) o;
    return EqualsUtils.equals (getOwnerID (), rhs.getOwnerID ());
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (getOwnerID ()).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("ownerID", getOwnerID ()).toString ();
  }
}
