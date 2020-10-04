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

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.idfactory.GlobalIDFactory;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.type.ObjectType;
import com.phloc.json.IJSONObject;
import com.phloc.json.impl.JSONObject;
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
 * @author Boris Gregorcic
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
  private String m_sAltName;
  private boolean m_bIsDeletable = DEFAULT_DELETABLE;
  private boolean m_bIsVirtualSite = DEFAULT_VIRTUALSITE;
  private IAddress m_aAddress = new Address ();
  private ITelephoneNumber m_aTelNo = new TelephoneNumber ();
  private ITelephoneNumber m_aFaxNo = new TelephoneNumber ();
  private IExtendedEmailAddress m_aEmailAddress = new ExtendedEmailAddress ();
  private ECompanySiteStatus m_eStatus = ECompanySiteStatus.VALID;
  private IJSONObject m_aProperties = new JSONObject ();

  public CompanySite (@Nonnull final ICompany aCompany)
  {
    this (GlobalIDFactory.getNewPersistentStringID (), aCompany);
  }

  public CompanySite (@Nonnull @Nonempty final String sID, @Nonnull final ICompany aCompany)
  {
    this.m_sID = ValueEnforcer.notEmpty (sID, "ID");
    this.m_aCompany = ValueEnforcer.notNull (aCompany, "Company");
  }

  @Override
  @Nonnull
  public ObjectType getTypeID ()
  {
    return TYPE_COMPANY_SITE;
  }

  @Override
  @Nonnull
  @Nonempty
  public String getID ()
  {
    return this.m_sID;
  }

  @Override
  @Nonnull
  public ICompany getCompany ()
  {
    return this.m_aCompany;
  }

  @Override
  @Nullable
  public String getDisplayName ()
  {
    return this.m_sDisplayName;
  }

  @Override
  @Nonnull
  public EChange setDisplayName (@Nullable final String sDisplayName)
  {
    if (EqualsUtils.equals (this.m_sDisplayName, sDisplayName))
      return EChange.UNCHANGED;
    this.m_sDisplayName = sDisplayName;
    return EChange.CHANGED;
  }

  @Override
  @Nullable
  public String getLongName ()
  {
    return this.m_sLongName;
  }

  @Override
  @Nullable
  public String getAltName ()
  {
    return this.m_sAltName;
  }

  @Override
  @Nonnull
  public String getAltNameWithFallback ()
  {
    return StringHelper.getNonEmpty (StringHelper.getNonEmpty (this.m_sAltName, this.m_sDisplayName), "");
  }

  @Override
  @Nonnull
  public EChange setLongName (@Nullable final String sLongName)
  {
    if (EqualsUtils.equals (this.m_sLongName, sLongName))
      return EChange.UNCHANGED;
    this.m_sLongName = sLongName;
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  public EChange setAltName (@Nullable final String sAltName)
  {
    if (EqualsUtils.equals (this.m_sAltName, sAltName))
      return EChange.UNCHANGED;
    this.m_sAltName = sAltName;
    return EChange.CHANGED;
  }

  @Override
  public boolean isDeletable ()
  {
    return this.m_bIsDeletable;
  }

  @Override
  @Nonnull
  public EChange setDeletable (final boolean bIsDeletable)
  {
    if (this.m_bIsDeletable == bIsDeletable)
      return EChange.UNCHANGED;
    this.m_bIsDeletable = bIsDeletable;
    return EChange.CHANGED;
  }

  @Override
  public boolean isVirtualSite ()
  {
    return this.m_bIsVirtualSite;
  }

  @Override
  @Nonnull
  public EChange setVirtualSite (final boolean bIsVirtualSite)
  {
    if (this.m_bIsVirtualSite == bIsVirtualSite)
      return EChange.UNCHANGED;
    this.m_bIsVirtualSite = bIsVirtualSite;
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  public IAddress getAddress ()
  {
    return this.m_aAddress;
  }

  @Override
  @Nonnull
  public EChange setAddress (@Nonnull final IAddress aAddress)
  {
    ValueEnforcer.notNull (aAddress, "Address");

    if (aAddress.getType () == null)
      aAddress.setType (EAddressType.OFFICE);

    if (this.m_aAddress.equals (aAddress))
      return EChange.UNCHANGED;
    this.m_aAddress = aAddress;
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  public ITelephoneNumber getDefaultTelNo ()
  {
    return this.m_aTelNo;
  }

  @Override
  @Nonnull
  public EChange setDefaultTelNo (@Nonnull final ITelephoneNumber aTelNo)
  {
    ValueEnforcer.notNull (aTelNo, "TelNo");

    if (aTelNo.getType () == null)
      aTelNo.setType (ETelephoneType.OFFICE);

    if (this.m_aTelNo.equals (aTelNo))
      return EChange.UNCHANGED;
    this.m_aTelNo = aTelNo;
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  public ITelephoneNumber getDefaultFaxNo ()
  {
    return this.m_aFaxNo;
  }

  @Override
  @Nonnull
  public EChange setDefaultFaxNo (@Nonnull final ITelephoneNumber aFaxNo)
  {
    ValueEnforcer.notNull (aFaxNo, "FaxNo");

    if (aFaxNo.getType () == null)
      aFaxNo.setType (ETelephoneType.OFFICE_FAX);

    if (this.m_aFaxNo.equals (aFaxNo))
      return EChange.UNCHANGED;
    this.m_aFaxNo = aFaxNo;
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  public IExtendedEmailAddress getDefaultEmailAddress ()
  {
    return this.m_aEmailAddress;
  }

  @Override
  @Nonnull
  public EChange setDefaultEmailAddress (@Nonnull final IExtendedEmailAddress aEmailAddress)
  {
    ValueEnforcer.notNull (aEmailAddress, "EmailAddress");

    if (aEmailAddress.getType () == null)
      aEmailAddress.setType (EEmailAddressType.OFFICE);

    if (this.m_aEmailAddress.equals (aEmailAddress))
      return EChange.UNCHANGED;
    this.m_aEmailAddress = aEmailAddress;
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
    return this.m_sID.equals (rhs.m_sID);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (this.m_sID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", this.m_sID)
                                       .append ("companyID", this.m_aCompany.getID ())
                                       .appendIfNotNull ("displayName", this.m_sDisplayName)
                                       .appendIfNotNull ("longName", this.m_sLongName)
                                       .append ("virtual", this.m_bIsVirtualSite)
                                       .appendIfNotNull ("address", this.m_aAddress)
                                       .appendIfNotNull ("telNo", this.m_aTelNo)
                                       .appendIfNotNull ("faxNo", this.m_aFaxNo)
                                       .appendIfNotNull ("email", this.m_aEmailAddress)
                                       .toString ();
  }

  @Override
  public ECompanySiteStatus getStatus ()
  {
    return this.m_eStatus;
  }

  @Override
  public IJSONObject getProperties ()
  {
    return this.m_aProperties.getClone ();
  }

  @Override
  public EChange setStatus (@Nonnull final ECompanySiteStatus eStatus)
  {
    ValueEnforcer.notNull (eStatus, "eStatus");
    if (eStatus == this.m_eStatus)
    {
      return EChange.UNCHANGED;
    }
    this.m_eStatus = eStatus;
    return EChange.CHANGED;
  }

  @Override
  public EChange setProperties (@Nonnull final IJSONObject aProperties)
  {
    ValueEnforcer.notNull (aProperties, "aProperties");
    if (EqualsUtils.equals (this.m_aProperties, aProperties))
    {
      return EChange.UNCHANGED;
    }
    this.m_aProperties = aProperties;
    return EChange.CHANGED;
  }
}
