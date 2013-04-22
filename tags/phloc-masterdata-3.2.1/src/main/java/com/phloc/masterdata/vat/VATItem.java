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
package com.phloc.masterdata.vat;

import java.math.BigDecimal;
import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.LocalDate;

import com.phloc.commons.CGlobal;
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.idfactory.GlobalIDFactory;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.datetime.period.LocalDatePeriod;

@Immutable
public final class VATItem extends LocalDatePeriod implements IVATItem
{
  private final String m_sID;
  private final EVATType m_eType;
  private final BigDecimal m_aPercentage;
  private final BigDecimal m_aNetToGrossFactor;
  private final boolean m_bDeprecated;

  public VATItem (@Nonnull @Nonempty final String sID,
                  @Nonnull final EVATType eType,
                  @Nonnull @Nonnegative final BigDecimal aPercentage,
                  final boolean bDeprecated)
  {
    this (sID, eType, aPercentage, bDeprecated, null, null);
  }

  public VATItem (@Nonnull @Nonempty final String sID,
                  @Nonnull final EVATType eType,
                  @Nonnull @Nonnegative final BigDecimal aPercentage,
                  final boolean bDeprecated,
                  @Nullable final LocalDate aValidFrom,
                  @Nullable final LocalDate aValidTo)
  {
    if (StringHelper.hasNoText (sID))
      throw new IllegalArgumentException ("Passed ID may not be empty");
    if (eType == null)
      throw new NullPointerException ("type");
    if (aPercentage == null)
      throw new NullPointerException ("percentage");
    if (aPercentage.compareTo (BigDecimal.ZERO) < 0 || aPercentage.compareTo (CGlobal.BIGDEC_100) > 0)
      throw new IllegalArgumentException ("percentage must be between 0 and 100");
    if (aValidFrom != null && aValidTo != null && aValidTo.isBefore (aValidFrom))
      throw new IllegalArgumentException ("ValidFrom date must be <= validTo date");

    m_sID = sID;
    m_eType = eType;
    m_aPercentage = aPercentage;
    m_aNetToGrossFactor = BigDecimal.ONE.add (m_aPercentage.divide (CGlobal.BIGDEC_100));
    m_bDeprecated = bDeprecated;
    setStart (aValidFrom);
    setEnd (aValidTo);
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public EVATType getType ()
  {
    return m_eType;
  }

  @Nonnull
  @Nonnegative
  public BigDecimal getPercentage ()
  {
    return m_aPercentage;
  }

  @Nonnull
  @Nonnegative
  public BigDecimal getMultiplicationFactorNetToGross ()
  {
    return m_aNetToGrossFactor;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return EVATItemText.VAT_PERC.getDisplayTextWithArgs (aContentLocale, m_aPercentage);
  }

  public boolean isDeprecated ()
  {
    return m_bDeprecated;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final VATItem rhs = (VATItem) o;
    return m_sID.equals (rhs.m_sID) &&
           m_eType.equals (rhs.m_eType) &&
           EqualsUtils.equals (m_aPercentage, rhs.m_aPercentage) &&
           m_bDeprecated == rhs.m_bDeprecated;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ())
                            .append (m_sID)
                            .append (m_eType)
                            .append (m_aPercentage)
                            .append (m_bDeprecated)
                            .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("id", m_sID)
                            .append ("type", m_eType)
                            .append ("percentage", m_aPercentage)
                            .append ("netToGrossFactor", m_aNetToGrossFactor)
                            .append ("deprecated", m_bDeprecated)
                            .toString ();
  }

  @Nonnull
  public static VATItem createNewItem (@Nonnull final EVATType eType,
                                       @Nonnull @Nonnegative final BigDecimal aPercentage,
                                       @Nullable final LocalDate aValidFrom,
                                       @Nullable final LocalDate aValidTo)
  {
    return new VATItem (GlobalIDFactory.getNewPersistentStringID (), eType, aPercentage, false, aValidFrom, aValidTo);
  }
}