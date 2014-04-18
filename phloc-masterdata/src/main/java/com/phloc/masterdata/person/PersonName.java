/**
 * Copyright (C) 2006-2014 phloc systems
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
    m_eSalutation = aBase.m_eSalutation;
    m_sPrefixTitle = aBase.m_sPrefixTitle;
    m_sFirstName = aBase.m_sFirstName;
    m_sMiddleName = aBase.m_sMiddleName;
    m_sLastName = aBase.m_sLastName;
    m_sSuffixTitle = aBase.m_sSuffixTitle;
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

  @Column (name = FIELD_SALUTATION, length = LENGTH_SALUTATION)
  @Nullable
  public ESalutation getSalutation ()
  {
    return m_eSalutation;
  }

  @Transient
  @Nullable
  public String getSalutationID ()
  {
    return m_eSalutation == null ? null : m_eSalutation.getID ();
  }

  @Nonnull
  public EChange setSalutation (@Nullable final ESalutation eSalutation)
  {
    if (EqualsUtils.equals (m_eSalutation, eSalutation))
      return EChange.UNCHANGED;
    m_eSalutation = eSalutation;
    return EChange.CHANGED;
  }

  @Transient
  @Nullable
  public String getSalutationDisplayName (@Nonnull final Locale aContentLocale)
  {
    return m_eSalutation == null ? "" : m_eSalutation.getDisplayText (aContentLocale);
  }

  @Transient
  @Nullable
  public String getGreeting (@Nonnull final Locale aContentLocale)
  {
    return m_eSalutation == null ? "" : m_eSalutation.getGreeting (aContentLocale);
  }

  @Transient
  @Nullable
  public String getGreetingComplete (@Nonnull final Locale aContentLocale)
  {
    if (m_eSalutation == null)
      return "";
    return m_eSalutation.getGreetingComplete (aContentLocale);
  }

  @Column (name = FIELD_PREFIXTITLE, length = LENGTH_PREFIXTITLE)
  @Nullable
  public String getPrefixTitle ()
  {
    return m_sPrefixTitle;
  }

  @Nonnull
  public EChange setPrefixTitle (@Nullable final String sPrefixTitle)
  {
    final String sRealPrefixTitle = MasterdataUtils.getEnsuredLength (sPrefixTitle, LENGTH_PREFIXTITLE);
    if (EqualsUtils.equals (m_sPrefixTitle, sRealPrefixTitle))
      return EChange.UNCHANGED;
    m_sPrefixTitle = sRealPrefixTitle;
    return EChange.CHANGED;
  }

  @Column (name = FIELD_FIRSTNAME, length = LENGTH_FIRSTNAME)
  @Nullable
  public String getFirstName ()
  {
    return m_sFirstName;
  }

  @UsedOnlyByJPA
  @Nonnull
  @Deprecated
  public EChange setFirstName (@Nullable final String sFirstName)
  {
    return setFirstName (sFirstName, SystemHelper.getSystemLocale ());
  }

  @Nonnull
  public EChange setFirstName (@Nullable final String sFirstName, @Nonnull final Locale aSortLocale)
  {
    final String sRealFirstName = MasterdataUtils.getEnsuredLength (PersonNameUtils.unifyName (sFirstName, aSortLocale),
                                                                    LENGTH_FIRSTNAME);
    if (EqualsUtils.equals (m_sFirstName, sRealFirstName))
      return EChange.UNCHANGED;
    m_sFirstName = sRealFirstName;
    return EChange.CHANGED;
  }

  @Column (name = FIELD_MIDDLENAME, length = LENGTH_MIDDLENAME)
  @Nullable
  public String getMiddleName ()
  {
    return m_sMiddleName;
  }

  @UsedOnlyByJPA
  @Nonnull
  @Deprecated
  public EChange setMiddleName (@Nullable final String sMiddleName)
  {
    return setMiddleName (sMiddleName, SystemHelper.getSystemLocale ());
  }

  @Nonnull
  public EChange setMiddleName (@Nullable final String sMiddleName, @Nonnull final Locale aSortLocale)
  {
    final String sRealMiddleName = MasterdataUtils.getEnsuredLength (PersonNameUtils.unifyName (sMiddleName,
                                                                                                aSortLocale),
                                                                     LENGTH_MIDDLENAME);
    if (EqualsUtils.equals (m_sMiddleName, sRealMiddleName))
      return EChange.UNCHANGED;
    m_sMiddleName = sRealMiddleName;
    return EChange.CHANGED;
  }

  @Column (name = FIELD_LASTNAME, length = LENGTH_LASTNAME)
  @Nullable
  public String getLastName ()
  {
    return m_sLastName;
  }

  @UsedOnlyByJPA
  @Nonnull
  @Deprecated
  public EChange setLastName (@Nullable final String sLastName)
  {
    return setLastName (sLastName, SystemHelper.getSystemLocale ());
  }

  @Nonnull
  public EChange setLastName (@Nullable final String sLastName, @Nonnull final Locale aSortLocale)
  {
    final String sRealLastName = MasterdataUtils.getEnsuredLength (PersonNameUtils.unifyName (sLastName, aSortLocale),
                                                                   LENGTH_LASTNAME);
    if (EqualsUtils.equals (m_sLastName, sRealLastName))
      return EChange.UNCHANGED;
    m_sLastName = sRealLastName;
    return EChange.CHANGED;
  }

  @Column (name = FIELD_SUFFIXTITLE, length = LENGTH_SUFFIXTITLE)
  @Nullable
  public String getSuffixTitle ()
  {
    return m_sSuffixTitle;
  }

  @Nonnull
  public EChange setSuffixTitle (@Nullable final String sSuffixTitle)
  {
    final String sRealSuffixTitle = MasterdataUtils.getEnsuredLength (sSuffixTitle, LENGTH_SUFFIXTITLE);
    if (EqualsUtils.equals (m_sSuffixTitle, sRealSuffixTitle))
      return EChange.UNCHANGED;
    m_sSuffixTitle = sRealSuffixTitle;
    return EChange.CHANGED;
  }

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
    return EqualsUtils.equals (m_eSalutation, rhs.m_eSalutation) &&
           EqualsUtils.equals (m_sPrefixTitle, rhs.m_sPrefixTitle) &&
           EqualsUtils.equals (m_sFirstName, rhs.m_sFirstName) &&
           EqualsUtils.equals (m_sMiddleName, rhs.m_sMiddleName) &&
           EqualsUtils.equals (m_sLastName, rhs.m_sLastName) &&
           EqualsUtils.equals (m_sSuffixTitle, rhs.m_sSuffixTitle);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eSalutation)
                                       .append (m_sPrefixTitle)
                                       .append (m_sFirstName)
                                       .append (m_sMiddleName)
                                       .append (m_sLastName)
                                       .append (m_sSuffixTitle)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("salutation", m_eSalutation)
                                       .appendIfNotNull ("prefixTitle", m_sPrefixTitle)
                                       .appendIfNotNull ("firstName", m_sFirstName)
                                       .appendIfNotNull ("middleName", m_sMiddleName)
                                       .appendIfNotNull ("lastName", m_sLastName)
                                       .appendIfNotNull ("suffixTitle", m_sSuffixTitle)
                                       .toString ();
  }
}
