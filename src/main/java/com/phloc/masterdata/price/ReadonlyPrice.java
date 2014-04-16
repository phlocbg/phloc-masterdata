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

import javax.annotation.Nonnull;

import com.phloc.commons.CGlobal;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.currency.IReadonlyCurrencyValue;
import com.phloc.masterdata.currency.ReadonlyCurrencyValue;
import com.phloc.masterdata.vat.IVATItem;

/**
 * Default implementation of the {@link IReadonlyPrice} interface.
 * 
 * @author Philip Helger
 */
public final class ReadonlyPrice implements IReadonlyPrice
{
  private final IReadonlyCurrencyValue m_aNetAmount;
  private final IVATItem m_aVATItem;

  public ReadonlyPrice (@Nonnull final IReadonlyPrice aOtherPrice)
  {
    this (new ReadonlyCurrencyValue (aOtherPrice.getNetAmount ()), aOtherPrice.getVATItem ());
  }

  public ReadonlyPrice (@Nonnull final ECurrency eCurrency,
                        @Nonnull final BigDecimal aNetAmount,
                        @Nonnull final IVATItem aVATType)
  {
    this (new ReadonlyCurrencyValue (eCurrency, aNetAmount), aVATType);
  }

  public ReadonlyPrice (@Nonnull final IReadonlyCurrencyValue aNetAmount, @Nonnull final IVATItem aVATType)
  {
    if (aNetAmount == null)
      throw new NullPointerException ("netAmount");
    if (aVATType == null)
      throw new NullPointerException ("VATtype");
    m_aNetAmount = aNetAmount;
    m_aVATItem = aVATType;
  }

  @Nonnull
  public ECurrency getCurrency ()
  {
    return m_aNetAmount.getCurrency ();
  }

  @Nonnull
  public IReadonlyCurrencyValue getNetAmount ()
  {
    return m_aNetAmount;
  }

  @Nonnull
  public IReadonlyCurrencyValue getGrossAmount ()
  {
    final BigDecimal aMultiplicationFactor = m_aVATItem.getMultiplicationFactorNetToGross ();
    return m_aNetAmount.getMultiplied (aMultiplicationFactor);
  }

  @Nonnull
  public IVATItem getVATItem ()
  {
    return m_aVATItem;
  }

  @Nonnull
  public IReadonlyCurrencyValue getTaxAmount ()
  {
    return m_aNetAmount.getMultiplied (getCurrency ().getDivided (m_aVATItem.getPercentage (), CGlobal.BIGDEC_100));
  }

  @Nonnull
  public ReadonlyPrice getMultiplied (@Nonnull final BigDecimal aValue)
  {
    return new ReadonlyPrice (m_aNetAmount.getMultiplied (aValue), m_aVATItem);
  }

  @Nonnull
  public ReadonlyPrice getMultiplied (final long nValue)
  {
    return new ReadonlyPrice (m_aNetAmount.getMultiplied (nValue), m_aVATItem);
  }

  @Nonnull
  public ReadonlyPrice getDivided (@Nonnull final BigDecimal aValue)
  {
    return new ReadonlyPrice (m_aNetAmount.getDivided (aValue), m_aVATItem);
  }

  @Nonnull
  public ReadonlyPrice getDivided (final long nValue)
  {
    return new ReadonlyPrice (m_aNetAmount.getDivided (nValue), m_aVATItem);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof ReadonlyPrice))
      return false;
    final ReadonlyPrice rhs = (ReadonlyPrice) o;
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
}
