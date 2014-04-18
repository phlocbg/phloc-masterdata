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
package com.phloc.masterdata.price;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import com.phloc.commons.CGlobal;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.math.MathHelper;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.masterdata.currency.CurrencyValue;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.currency.IReadonlyCurrencyValue;
import com.phloc.masterdata.currency.ReadonlyCurrencyValue;
import com.phloc.masterdata.vat.IVATItem;
import com.phloc.masterdata.vat.JPAVATItemIDConverter;

// ESCA-JAVA0116:
/**
 * Default implementation of the {@link IPrice} interface.
 * 
 * @author Philip Helger
 */
@Entity
@Embeddable
@Access (value = AccessType.PROPERTY)
@Converter (name = "vatitemid", converterClass = JPAVATItemIDConverter.class)
public final class Price implements IPrice
{
  public static final String FIELD_VATITEM = "vatitem";

  private CurrencyValue m_aNetAmount;
  private IVATItem m_aVATItem;

  public Price ()
  {}

  public Price (@Nonnull final IReadonlyPrice aPrice)
  {
    this (aPrice.getNetAmount (), aPrice.getVATItem ());
  }

  /**
   * Constructor
   * 
   * @param eCurrency
   *        Currency to use. May not be <code>null</code>.
   * @param aNetAmount
   *        The net amount to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   */
  public Price (@Nonnull final ECurrency eCurrency,
                @Nonnull final BigDecimal aNetAmount,
                @Nonnull final IVATItem aVATItem)
  {
    this (new CurrencyValue (eCurrency, aNetAmount), aVATItem);
  }

  /**
   * Constructor
   * 
   * @param aNetAmount
   *        The net amount incl. currency to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   */
  public Price (@Nonnull final IReadonlyCurrencyValue aNetAmount, @Nonnull final IVATItem aVATItem)
  {
    setNetAmount (aNetAmount);
    setVATItem (aVATItem);
  }

  @Nonnull
  @Embedded
  public CurrencyValue getNetAmount ()
  {
    return m_aNetAmount;
  }

  @Nonnull
  public EChange setNetAmount (@Nonnull final IReadonlyCurrencyValue aNetAmount)
  {
    final CurrencyValue aRealNetAmount = new CurrencyValue (aNetAmount);
    if (EqualsUtils.equals (m_aNetAmount, aRealNetAmount))
      return EChange.UNCHANGED;
    m_aNetAmount = aRealNetAmount;
    return EChange.CHANGED;
  }

  @Nonnull
  @Basic
  @Column (name = FIELD_VATITEM)
  @Convert ("vatitemid")
  public IVATItem getVATItem ()
  {
    return m_aVATItem;
  }

  @Nonnull
  public EChange setVATItem (@Nonnull final IVATItem aVATItem)
  {
    if (aVATItem == null)
      throw new NullPointerException ("VATtype");

    if (aVATItem.equals (m_aVATItem))
      return EChange.UNCHANGED;
    m_aVATItem = aVATItem;
    return EChange.CHANGED;
  }

  @Transient
  @Nonnull
  public IReadonlyCurrencyValue getTaxAmount ()
  {
    return m_aNetAmount.getMultiplied (getCurrency ().getDivided (m_aVATItem.getPercentage (), CGlobal.BIGDEC_100));
  }

  /*
   * Helper method to access the currency of the included "currencyValue".
   * Marked as @Transient to avoid EclipseLink automatically using it a s column
   * (because the corresponding setter method is also present!)
   */
  @Nonnull
  @Transient
  public ECurrency getCurrency ()
  {
    return m_aNetAmount.getCurrency ();
  }

  @Nonnull
  public EChange setCurrency (@Nonnull final ECurrency eCurrency)
  {
    return m_aNetAmount.setCurrency (eCurrency);
  }

  @Transient
  @Nonnull
  public IReadonlyCurrencyValue getGrossAmount ()
  {
    final BigDecimal aMultiplicationFactor = m_aVATItem.getMultiplicationFactorNetToGross ();
    if (MathHelper.isEqualToOne (aMultiplicationFactor))
    {
      // Shortcut for no VAT
      // Remember: BigDecimal is immutable
      return new ReadonlyCurrencyValue (m_aNetAmount.getCurrency (), m_aNetAmount.getValue ());
    }
    return new ReadonlyCurrencyValue (m_aNetAmount.getCurrency (), m_aNetAmount.getValue ()
                                                                               .multiply (aMultiplicationFactor));
  }

  @Transient
  @Nonnull
  public Price getMultiplied (@Nonnull final BigDecimal aValue)
  {
    return new Price (m_aNetAmount.getMultiplied (aValue), m_aVATItem);
  }

  @Transient
  @Nonnull
  public Price getMultiplied (final long nValue)
  {
    return new Price (m_aNetAmount.getMultiplied (nValue), m_aVATItem);
  }

  @Transient
  @Nonnull
  public Price getDivided (@Nonnull final BigDecimal aValue)
  {
    return new Price (m_aNetAmount.getDivided (aValue), m_aVATItem);
  }

  @Transient
  @Nonnull
  public Price getDivided (final long nValue)
  {
    return new Price (m_aNetAmount.getDivided (nValue), m_aVATItem);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof Price))
      return false;
    final Price rhs = (Price) o;
    return m_aNetAmount.equals (rhs.m_aNetAmount) && m_aVATItem.equals (rhs.m_aVATItem);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aNetAmount).append (m_aVATItem).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("netAmount", m_aNetAmount).append ("VATtype", m_aVATItem).toString ();
  }

  /**
   * Create a price from a net amount.
   * 
   * @param eCurrency
   *        Currency to use. May not be <code>null</code>.
   * @param aNetAmount
   *        The net amount to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   */
  @Nonnull
  public static Price createFromNetAmount (@Nonnull final ECurrency eCurrency,
                                           @Nonnull final BigDecimal aNetAmount,
                                           @Nonnull final IVATItem aVATItem)
  {
    return new Price (eCurrency, aNetAmount, aVATItem);
  }

  /**
   * Create a price from a net amount.
   * 
   * @param aNetAmount
   *        The net amount to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   */
  @Nonnull
  public static Price createFromNetAmount (@Nonnull final IReadonlyCurrencyValue aNetAmount,
                                           @Nonnull final IVATItem aVATItem)
  {
    return new Price (aNetAmount, aVATItem);
  }

  /**
   * Create a price from a gross amount using the scale and rounding mode from
   * the currency.
   * 
   * @param eCurrency
   *        Currency to use. May not be <code>null</code>.
   * @param aGrossAmount
   *        The gross amount to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   */
  @Nonnull
  public static Price createFromGrossAmount (@Nonnull final ECurrency eCurrency,
                                             @Nonnull final BigDecimal aGrossAmount,
                                             @Nonnull final IVATItem aVATItem)
  {
    return createFromGrossAmount (eCurrency,
                                  aGrossAmount,
                                  aVATItem,
                                  eCurrency.getScale (),
                                  eCurrency.getRoundingMode ());
  }

  /**
   * Create a price from a gross amount.
   * 
   * @param eCurrency
   *        Currency to use. May not be <code>null</code>.
   * @param aGrossAmount
   *        The gross amount to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   * @param nScale
   *        The scaling to be used for the resulting amount, in case
   *        <code>grossAmount / (1 + perc/100)</code> delivery an inexact
   *        result.
   * @param eRoundingMode
   *        The rounding mode to be used to create a valid result.
   */
  @Nonnull
  public static Price createFromGrossAmount (@Nonnull final ECurrency eCurrency,
                                             @Nonnull final BigDecimal aGrossAmount,
                                             @Nonnull final IVATItem aVATItem,
                                             @Nonnegative final int nScale,
                                             @Nonnull final RoundingMode eRoundingMode)
  {
    if (aVATItem == null)
      throw new NullPointerException ("VATItem");

    final BigDecimal aFactor = aVATItem.getMultiplicationFactorNetToGross ();
    if (MathHelper.isEqualToOne (aFactor))
    {
      // Shortcut for no VAT (net == gross)
      return new Price (eCurrency, aGrossAmount, aVATItem);
    }
    return new Price (eCurrency, aGrossAmount.divide (aFactor, nScale, eRoundingMode), aVATItem);
  }

  /**
   * Create a price from a gross amount using the scale and rounding mode from
   * the currency.
   * 
   * @param aGrossAmount
   *        The gross amount to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   */
  @Nonnull
  public static Price createFromGrossAmount (@Nonnull final IReadonlyCurrencyValue aGrossAmount,
                                             @Nonnull final IVATItem aVATItem)
  {
    if (aGrossAmount == null)
      throw new NullPointerException ("GrossAmount");

    final ECurrency eCurrency = aGrossAmount.getCurrency ();
    return createFromGrossAmount (aGrossAmount, aVATItem, eCurrency.getScale (), eCurrency.getRoundingMode ());
  }

  /**
   * Create a price from a gross amount.
   * 
   * @param aGrossAmount
   *        The gross amount to use. May not be <code>null</code>.
   * @param aVATItem
   *        The VAT item to use. May not be <code>null</code>.
   * @param nScale
   *        The scaling to be used for the resulting amount, in case
   *        <code>grossAmount / (1 + perc/100)</code> delivery an inexact
   *        result.
   * @param eRoundingMode
   *        The rounding mode to be used to create a valid result.
   */
  @Nonnull
  public static Price createFromGrossAmount (@Nonnull final IReadonlyCurrencyValue aGrossAmount,
                                             @Nonnull final IVATItem aVATItem,
                                             @Nonnegative final int nScale,
                                             @Nonnull final RoundingMode eRoundingMode)
  {
    if (aVATItem == null)
      throw new NullPointerException ("VATItem");

    final BigDecimal aFactor = aVATItem.getMultiplicationFactorNetToGross ();
    if (MathHelper.isEqualToOne (aFactor))
    {
      // Shortcut for no VAT (net == gross)
      return new Price (aGrossAmount, aVATItem);
    }

    return new Price (aGrossAmount.getCurrency (),
                      aGrossAmount.getValue ().divide (aFactor, nScale, eRoundingMode),
                      aVATItem);
  }
}
