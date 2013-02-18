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
package com.phloc.masterdata.company;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.idfactory.GlobalIDFactory;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.type.ObjectType;
import com.phloc.masterdata.address.Address;
import com.phloc.masterdata.address.EAddressType;
import com.phloc.masterdata.address.IAddress;
import com.phloc.masterdata.email.EEmailAddressType;
import com.phloc.masterdata.email.ExtendedEmailAddress;
import com.phloc.masterdata.email.IExtendedEmailAddress;
import com.phloc.masterdata.telephone.ETelephoneType;
import com.phloc.masterdata.telephone.ITelephoneNumber;
import com.phloc.masterdata.telephone.TelephoneNumber;

// ESCA-JAVA0116:
/**
 * The default implementation of the {@link ICompanySite} interface.
 * 
 * @author philip
 */
public final class CompanySite implements ICompanySite
{
  public static final ObjectType TYPE_COMPANY_SITE = new ObjectType ("company-site");
  public static final boolean DEFAULT_DELETABLE = true;
  public static final boolean DEFAULT_VIRTUALSITE = false;

  private final String m_sID;
  private final ICompany m_aCompany;
  private String m_sDisplayName;
  private String m_sLongName;
  private boolean m_bIsDeletable = DEFAULT_DELETABLE;
  private boolean m_bIsVirtualSite = DEFAULT_VIRTUALSITE;
  private IAddress m_aAddress = new Address ();
  private ITelephoneNumber m_aTelNo = new TelephoneNumber ();
  private ITelephoneNumber m_aFaxNo = new TelephoneNumber ();
  private IExtendedEmailAddress m_aEmailAddress = new ExtendedEmailAddress ();

  public CompanySite (@Nonnull final ICompany aCompany)
  {
    this (GlobalIDFactory.getNewPersistentStringID (), aCompany);
  }

  public CompanySite (@Nonnull @Nonempty final String sID, @Nonnull final ICompany aCompany)
  {
    if (StringHelper.hasNoText (sID))
      throw new IllegalArgumentException ("Passed ID is empty");
    if (aCompany == null)
      throw new NullPointerException ("company");
    m_sID = sID;
    m_aCompany = aCompany;
  }

  @Nonnull
  public ObjectType getTypeID ()
  {
    return TYPE_COMPANY_SITE;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public ICompany getCompany ()
  {
    return m_aCompany;
  }

  @Nullable
  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  @Nonnull
  public EChange setDisplayName (@Nullable final String sDisplayName)
  {
    if (EqualsUtils.equals (m_sDisplayName, sDisplayName))
      return EChange.UNCHANGED;
    m_sDisplayName = sDisplayName;
    return EChange.CHANGED;
  }

  @Nullable
  public String getLongName ()
  {
    return m_sLongName;
  }

  @Nonnull
  public EChange setLongName (@Nullable final String sLongName)
  {
    if (EqualsUtils.equals (m_sLongName, sLongName))
      return EChange.UNCHANGED;
    m_sLongName = sLongName;
    return EChange.CHANGED;
  }

  public boolean isDeletable ()
  {
    return m_bIsDeletable;
  }

  @Nonnull
  public EChange setDeletable (final boolean bIsDeletable)
  {
    if (m_bIsDeletable == bIsDeletable)
      return EChange.UNCHANGED;
    m_bIsDeletable = bIsDeletable;
    return EChange.CHANGED;
  }

  public boolean isVirtualSite ()
  {
    return m_bIsVirtualSite;
  }

  @Nonnull
  public EChange setVirtualSite (final boolean bIsVirtualSite)
  {
    if (m_bIsVirtualSite == bIsVirtualSite)
      return EChange.UNCHANGED;
    m_bIsVirtualSite = bIsVirtualSite;
    return EChange.CHANGED;
  }

  @Nonnull
  public IAddress getAddress ()
  {
    return m_aAddress;
  }

  @Nonnull
  public EChange setAddress (@Nonnull final IAddress aAddress)
  {
    if (aAddress == null)
      throw new NullPointerException ("address");

    if (aAddress.getType () == null)
      aAddress.setType (EAddressType.OFFICE);

    if (m_aAddress.equals (aAddress))
      return EChange.UNCHANGED;
    m_aAddress = aAddress;
    return EChange.CHANGED;
  }

  @Nonnull
  public ITelephoneNumber getDefaultTelNo ()
  {
    return m_aTelNo;
  }

  @Nonnull
  public EChange setDefaultTelNo (@Nonnull final ITelephoneNumber aTelNo)
  {
    if (aTelNo == null)
      throw new NullPointerException ("telNo");

    if (aTelNo.getType () == null)
      aTelNo.setType (ETelephoneType.OFFICE);

    if (m_aTelNo.equals (aTelNo))
      return EChange.UNCHANGED;
    m_aTelNo = aTelNo;
    return EChange.CHANGED;
  }

  @Nonnull
  public ITelephoneNumber getDefaultFaxNo ()
  {
    return m_aFaxNo;
  }

  @Nonnull
  public EChange setDefaultFaxNo (@Nonnull final ITelephoneNumber aFaxNo)
  {
    if (aFaxNo == null)
      throw new NullPointerException ("faxNo");

    if (aFaxNo.getType () == null)
      aFaxNo.setType (ETelephoneType.OFFICE_FAX);

    if (m_aFaxNo.equals (aFaxNo))
      return EChange.UNCHANGED;
    m_aFaxNo = aFaxNo;
    return EChange.CHANGED;
  }

  @Nonnull
  public IExtendedEmailAddress getDefaultEmailAddress ()
  {
    return m_aEmailAddress;
  }

  @Nonnull
  public EChange setDefaultEmailAddress (@Nonnull final IExtendedEmailAddress aEmailAddress)
  {
    if (aEmailAddress == null)
      throw new NullPointerException ("emailAddress");

    if (aEmailAddress.getType () == null)
      aEmailAddress.setType (EEmailAddressType.OFFICE);

    if (m_aEmailAddress.equals (aEmailAddress))
      return EChange.UNCHANGED;
    m_aEmailAddress = aEmailAddress;
    return EChange.CHANGED;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof CompanySite))
      return false;
    final CompanySite rhs = (CompanySite) o;
    return m_sID.equals (rhs.m_sID);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .append ("companyID", m_aCompany.getID ())
                                       .appendIfNotNull ("displayName", m_sDisplayName)
                                       .appendIfNotNull ("longName", m_sLongName)
                                       .append ("virtual", m_bIsVirtualSite)
                                       .appendIfNotNull ("address", m_aAddress)
                                       .appendIfNotNull ("telNo", m_aTelNo)
                                       .appendIfNotNull ("faxNo", m_aFaxNo)
                                       .appendIfNotNull ("email", m_aEmailAddress)
                                       .toString ();
  }
}
