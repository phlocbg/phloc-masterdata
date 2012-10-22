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
package com.phloc.masterdata.currency;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.db.jpa.annotations.UsedOnlyByJPA;

/**
 * This class represents a single currency value as the combination of a value
 * and a currency.
 * 
 * @author philip
 */
@Embeddable
@Entity
@Access (value = AccessType.PROPERTY)
@NotThreadSafe
public final class CurrencyValue extends AbstractCurrencyValue implements ICurrencyValue
{
  public static final String FIELD_CURRENCY = "currency";
  public static final String FIELD_CURRENCYVALUE = "currencyvalue";

  private ECurrency m_eCurrency;
  private BigDecimal m_aValue;

  @UsedOnlyByJPA
  public CurrencyValue ()
  {}

  public CurrencyValue (@Nonnull final IReadonlyCurrencyValue aCurrencyValue)
  {
    this (aCurrencyValue.getCurrency (), aCurrencyValue.getValue ());
  }

  public CurrencyValue (@Nonnull final ECurrency eCurrency)
  {
    this (eCurrency, BigDecimal.ZERO);
  }

  public CurrencyValue (@Nonnull final ECurrency eCurrency, @Nonnull final BigDecimal aValue)
  {
    setCurrency (eCurrency);
    setValue (aValue);
  }

  @Nonnull
  @Column (name = FIELD_CURRENCY)
  public ECurrency getCurrency ()
  {
    return m_eCurrency;
  }

  @Nonnull
  public EChange setCurrency (@Nonnull final ECurrency eCurrency)
  {
    if (eCurrency == null)
      throw new NullPointerException ("currency");

    if (eCurrency.equals (m_eCurrency))
      return EChange.UNCHANGED;
    m_eCurrency = eCurrency;
    return EChange.CHANGED;
  }

  @Nonnull
  @Column (name = FIELD_CURRENCYVALUE)
  public BigDecimal getValue ()
  {
    return m_aValue;
  }

  @Nonnull
  public EChange setValue (@Nonnull final BigDecimal aValue)
  {
    if (aValue == null)
      throw new NullPointerException ("value");

    if (EqualsUtils.equals (aValue, m_aValue))
      return EChange.UNCHANGED;
    m_aValue = aValue;
    return EChange.CHANGED;
  }

  public void addValue (@Nonnull final BigDecimal aValue)
  {
    if (aValue == null)
      throw new NullPointerException ("value");
    m_aValue = m_aValue.add (aValue);
  }

  @Nonnull
  public ICurrencyValue getAdded (@Nonnull final BigDecimal aValue)
  {
    if (EqualsUtils.equals (BigDecimal.ZERO, aValue))
      return this;
    return new CurrencyValue (getCurrency (), getValue ().add (aValue));
  }

  @Nonnull
  public ICurrencyValue getAdded (final long nValue)
  {
    if (nValue == 0)
      return this;
    return getAdded (new BigDecimal (nValue));
  }

  @Nonnull
  public ICurrencyValue getSubtracted (@Nonnull final BigDecimal aValue)
  {
    if (EqualsUtils.equals (BigDecimal.ZERO, aValue))
      return this;
    return new CurrencyValue (getCurrency (), getValue ().subtract (aValue));
  }

  @Nonnull
  public ICurrencyValue getSubtracted (final long nValue)
  {
    if (nValue == 0)
      return this;
    return getSubtracted (new BigDecimal (nValue));
  }

  @Nonnull
  public ICurrencyValue getMultiplied (@Nonnull final BigDecimal aValue)
  {
    if (EqualsUtils.equals (BigDecimal.ONE, aValue))
      return this;
    return new CurrencyValue (getCurrency (), getValue ().multiply (aValue));
  }

  @Nonnull
  public ICurrencyValue getMultiplied (@Nonnull final long nValue)
  {
    if (nValue == 1)
      return this;
    return getMultiplied (new BigDecimal (nValue));
  }

  @Nonnull
  public ICurrencyValue getDivided (@Nonnull final BigDecimal aValue)
  {
    if (EqualsUtils.equals (BigDecimal.ONE, aValue))
      return this;
    final ECurrency eCurrency = getCurrency ();
    return new CurrencyValue (eCurrency, eCurrency.getDivided (getValue (), aValue));
  }

  @Nonnull
  public ICurrencyValue getDivided (@Nonnull final long nValue)
  {
    if (nValue == 1)
      return this;
    return getDivided (new BigDecimal (nValue));
  }

  @Nonnull
  public CurrencyValue getClone ()
  {
    return new CurrencyValue (this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof CurrencyValue))
      return false;
    final CurrencyValue rhs = (CurrencyValue) o;
    return m_eCurrency.equals (rhs.m_eCurrency) && EqualsUtils.equals (m_aValue, rhs.m_aValue);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eCurrency).append (m_aValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("currency", m_eCurrency).append ("value", m_aValue).toString ();
  }

  @Nonnull
  public static ICurrencyValue fromCurrencyFormattedString (@Nonnull final String sText,
                                                            @Nonnull final ECurrency eCurrency,
                                                            @Nonnull final BigDecimal aDefaultValue)
  {
    return new CurrencyValue (eCurrency, eCurrency.parseCurrencyFormat (sText, aDefaultValue));
  }

  @Nonnull
  public static ICurrencyValue fromValueFormattedString (@Nonnull final String sText,
                                                         @Nonnull final ECurrency eCurrency,
                                                         @Nonnull final BigDecimal aDefaultValue)
  {
    return new CurrencyValue (eCurrency, eCurrency.parseValueFormat (sText, aDefaultValue));
  }
}
