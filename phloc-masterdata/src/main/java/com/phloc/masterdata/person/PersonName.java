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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.commons.system.SystemHelper;
import com.phloc.db.jpa.annotations.UsedOnlyByJPA;
import com.phloc.masterdata.MasterdataUtils;

// ESCA-JAVA0116:
@Entity
@Embeddable
@Access (value = AccessType.PROPERTY)
public class PersonName implements IPersonName
{
  public static final String FIELD_SALUTATION = "pnsalutation";
  public static final int LENGTH_SALUTATION = 3;
  public static final String FIELD_PREFIXTITLE = "pnprefixtitle";
  public static final int LENGTH_PREFIXTITLE = 50;
  public static final String FIELD_FIRSTNAME = "pnfirstname";
  public static final int LENGTH_FIRSTNAME = 100;
  public static final String FIELD_MIDDLENAME = "pnmiddlename";
  public static final int LENGTH_MIDDLENAME = 100;
  public static final String FIELD_LASTNAME = "pnlastname";
  public static final int LENGTH_LASTNAME = 100;
  public static final String FIELD_SUFFIXTITLE = "pnsuffixtitle";
  public static final int LENGTH_SUFFIXTITLE = 50;

  private ESalutation m_eSalutation;
  private String m_sPrefixTitle;
  private String m_sFirstName;
  private String m_sMiddleName;
  private String m_sLastName;
  private String m_sSuffixTitle;

  public PersonName ()
  {}

  public PersonName (@Nonnull final PersonName aBase)
  {
    ValueEnforcer.notNull (aBase, "Base");
    this.m_eSalutation = aBase.m_eSalutation;
    this.m_sPrefixTitle = aBase.m_sPrefixTitle;
    this.m_sFirstName = aBase.m_sFirstName;
    this.m_sMiddleName = aBase.m_sMiddleName;
    this.m_sLastName = aBase.m_sLastName;
    this.m_sSuffixTitle = aBase.m_sSuffixTitle;
  }

  public PersonName (@Nonnull final IReadonlyPersonName aBase, @Nonnull final Locale aSortLocale)
  {
    ValueEnforcer.notNull (aBase, "Base");
    setSalutation (aBase.getSalutation ());
    setPrefixTitle (aBase.getPrefixTitle ());
    setFirstName (aBase.getFirstName (), aSortLocale);
    setMiddleName (aBase.getMiddleName (), aSortLocale);
    setLastName (aBase.getLastName (), aSortLocale);
    setSuffixTitle (aBase.getSuffixTitle ());
  }

  public PersonName (@Nullable final ESalutation eSalutation,
                     @Nullable final String sPrefixTitle,
                     @Nullable final String sFirstName,
                     @Nullable final String sMiddleName,
                     @Nullable final String sLastName,
                     @Nullable final String sSuffixTitle,
                     @Nonnull final Locale aSortLocale)
  {
    setSalutation (eSalutation);
    setPrefixTitle (sPrefixTitle);
    setFirstName (sFirstName, aSortLocale);
    setMiddleName (sMiddleName, aSortLocale);
    setLastName (sLastName, aSortLocale);
    setSuffixTitle (sSuffixTitle);
  }

  @Override
  @Column (name = FIELD_SALUTATION, length = LENGTH_SALUTATION)
  @Nullable
  public ESalutation getSalutation ()
  {
    return this.m_eSalutation;
  }

  @Override
  @Transient
  @Nullable
  public String getSalutationID ()
  {
    return this.m_eSalutation == null ? null : this.m_eSalutation.getID ();
  }

  @Override
  @Nonnull
  public EChange setSalutation (@Nullable final ESalutation eSalutation)
  {
    if (EqualsUtils.equals (this.m_eSalutation, eSalutation))
      return EChange.UNCHANGED;
    this.m_eSalutation = eSalutation;
    return EChange.CHANGED;
  }

  @Override
  @Transient
  @Nullable
  public String getSalutationDisplayName (@Nonnull final Locale aContentLocale)
  {
    return this.m_eSalutation == null ? "" : this.m_eSalutation.getDisplayText (aContentLocale);
  }

  @Override
  @Transient
  @Nullable
  public String getGreeting (@Nonnull final Locale aContentLocale)
  {
    return this.m_eSalutation == null ? "" : this.m_eSalutation.getGreeting (aContentLocale);
  }

  @Override
  @Transient
  @Nullable
  public String getGreetingComplete (@Nonnull final Locale aContentLocale)
  {
    if (this.m_eSalutation == null)
      return "";
    return this.m_eSalutation.getGreetingComplete (aContentLocale);
  }

  @Transient
  @Nullable
  public String getGreetingCompletePersonal (@Nonnull final Locale aContentLocale)
  {
    if (this.m_eSalutation == null)
      return "";
    return this.m_eSalutation.getGreetingCompletePersonal (aContentLocale);
  }

  @Override
  @Column (name = FIELD_PREFIXTITLE, length = LENGTH_PREFIXTITLE)
  @Nullable
  public String getPrefixTitle ()
  {
    return this.m_sPrefixTitle;
  }

  @Override
  @Nonnull
  public EChange setPrefixTitle (@Nullable final String sPrefixTitle)
  {
    final String sRealPrefixTitle = MasterdataUtils.getEnsuredLength (sPrefixTitle, LENGTH_PREFIXTITLE);
    if (EqualsUtils.equals (this.m_sPrefixTitle, sRealPrefixTitle))
      return EChange.UNCHANGED;
    this.m_sPrefixTitle = sRealPrefixTitle;
    return EChange.CHANGED;
  }

  @Override
  @Column (name = FIELD_FIRSTNAME, length = LENGTH_FIRSTNAME)
  @Nullable
  public String getFirstName ()
  {
    return this.m_sFirstName;
  }

  @UsedOnlyByJPA
  @Nonnull
  @Deprecated
  public EChange setFirstName (@Nullable final String sFirstName)
  {
    return setFirstName (sFirstName, SystemHelper.getSystemLocale ());
  }

  @Override
  @Nonnull
  public EChange setFirstName (@Nullable final String sFirstName, @Nonnull final Locale aSortLocale)
  {
    final String sRealFirstName = MasterdataUtils.getEnsuredLength (PersonNameUtils.unifyName (sFirstName, aSortLocale),
                                                                    LENGTH_FIRSTNAME);
    if (EqualsUtils.equals (this.m_sFirstName, sRealFirstName))
      return EChange.UNCHANGED;
    this.m_sFirstName = sRealFirstName;
    return EChange.CHANGED;
  }

  @Override
  @Column (name = FIELD_MIDDLENAME, length = LENGTH_MIDDLENAME)
  @Nullable
  public String getMiddleName ()
  {
    return this.m_sMiddleName;
  }

  @UsedOnlyByJPA
  @Nonnull
  @Deprecated
  public EChange setMiddleName (@Nullable final String sMiddleName)
  {
    return setMiddleName (sMiddleName, SystemHelper.getSystemLocale ());
  }

  @Override
  @Nonnull
  public EChange setMiddleName (@Nullable final String sMiddleName, @Nonnull final Locale aSortLocale)
  {
    final String sRealMiddleName = MasterdataUtils.getEnsuredLength (PersonNameUtils.unifyName (sMiddleName,
                                                                                                aSortLocale),
                                                                     LENGTH_MIDDLENAME);
    if (EqualsUtils.equals (this.m_sMiddleName, sRealMiddleName))
      return EChange.UNCHANGED;
    this.m_sMiddleName = sRealMiddleName;
    return EChange.CHANGED;
  }

  @Override
  @Column (name = FIELD_LASTNAME, length = LENGTH_LASTNAME)
  @Nullable
  public String getLastName ()
  {
    return this.m_sLastName;
  }

  @UsedOnlyByJPA
  @Nonnull
  @Deprecated
  public EChange setLastName (@Nullable final String sLastName)
  {
    return setLastName (sLastName, SystemHelper.getSystemLocale ());
  }

  @Override
  @Nonnull
  public EChange setLastName (@Nullable final String sLastName, @Nonnull final Locale aSortLocale)
  {
    final String sRealLastName = MasterdataUtils.getEnsuredLength (PersonNameUtils.unifyName (sLastName, aSortLocale),
                                                                   LENGTH_LASTNAME);
    if (EqualsUtils.equals (this.m_sLastName, sRealLastName))
      return EChange.UNCHANGED;
    this.m_sLastName = sRealLastName;
    return EChange.CHANGED;
  }

  @Override
  @Column (name = FIELD_SUFFIXTITLE, length = LENGTH_SUFFIXTITLE)
  @Nullable
  public String getSuffixTitle ()
  {
    return this.m_sSuffixTitle;
  }

  @Override
  @Nonnull
  public EChange setSuffixTitle (@Nullable final String sSuffixTitle)
  {
    final String sRealSuffixTitle = MasterdataUtils.getEnsuredLength (sSuffixTitle, LENGTH_SUFFIXTITLE);
    if (EqualsUtils.equals (this.m_sSuffixTitle, sRealSuffixTitle))
      return EChange.UNCHANGED;
    this.m_sSuffixTitle = sRealSuffixTitle;
    return EChange.CHANGED;
  }

  @Override
  @Transient
  @Nonnull
  public PersonName getClone ()
  {
    return new PersonName (this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof PersonName))
      return false;
    final PersonName rhs = (PersonName) o;
    return EqualsUtils.equals (this.m_eSalutation, rhs.m_eSalutation) &&
           EqualsUtils.equals (this.m_sPrefixTitle, rhs.m_sPrefixTitle) &&
           EqualsUtils.equals (this.m_sFirstName, rhs.m_sFirstName) &&
           EqualsUtils.equals (this.m_sMiddleName, rhs.m_sMiddleName) &&
           EqualsUtils.equals (this.m_sLastName, rhs.m_sLastName) &&
           EqualsUtils.equals (this.m_sSuffixTitle, rhs.m_sSuffixTitle);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (this.m_eSalutation)
                                       .append (this.m_sPrefixTitle)
                                       .append (this.m_sFirstName)
                                       .append (this.m_sMiddleName)
                                       .append (this.m_sLastName)
                                       .append (this.m_sSuffixTitle)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("salutation", this.m_eSalutation)
                                       .appendIfNotNull ("prefixTitle", this.m_sPrefixTitle)
                                       .appendIfNotNull ("firstName", this.m_sFirstName)
                                       .appendIfNotNull ("middleName", this.m_sMiddleName)
                                       .appendIfNotNull ("lastName", this.m_sLastName)
                                       .appendIfNotNull ("suffixTitle", this.m_sSuffixTitle)
                                       .toString ();
  }
}
