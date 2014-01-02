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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation class of {@link IPriceGraduationItem} and
 * {@link IReadonlyPriceGraduationItem}.
 * 
 * @author Philip Helger
 */
public final class PriceGraduationItem implements IPriceGraduationItem
{
  private int m_nMinimumQuantity;
  private BigDecimal m_aNetAmount;

  public PriceGraduationItem (@Nonnegative final int nMinimumQuantity, @Nonnull final BigDecimal aUnitNetAmount)
  {
    setMinimumQuantity (nMinimumQuantity);
    setUnitNetAmount (aUnitNetAmount);
  }

  @Nonnegative
  public int getMinimumQuantity ()
  {
    return m_nMinimumQuantity;
  }

  @Nonnull
  public EChange setMinimumQuantity (@Nonnegative final int nMinimumQuantity)
  {
    if (nMinimumQuantity < 1)
      throw new IllegalArgumentException ("The minimum quantity must be >= 1: " + nMinimumQuantity);

    if (nMinimumQuantity == m_nMinimumQuantity)
      return EChange.UNCHANGED;
    m_nMinimumQuantity = nMinimumQuantity;
    return EChange.CHANGED;
  }

  @Nonnull
  public BigDecimal getUnitNetAmount ()
  {
    return m_aNetAmount;
  }

  @Nonnull
  public EChange setUnitNetAmount (@Nonnull final BigDecimal aNetAmount)
  {
    if (aNetAmount == null)
      throw new NullPointerException ("price amount");

    if (EqualsUtils.equals (aNetAmount, m_aNetAmount))
      return EChange.UNCHANGED;
    m_aNetAmount = aNetAmount;
    return EChange.CHANGED;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof PriceGraduationItem))
      return false;
    final PriceGraduationItem rhs = (PriceGraduationItem) o;
    return m_nMinimumQuantity == rhs.m_nMinimumQuantity && EqualsUtils.equals (m_aNetAmount, rhs.m_aNetAmount);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nMinimumQuantity).append (m_aNetAmount).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("minQuantity", m_nMinimumQuantity)
                                       .append ("priceamount", m_aNetAmount)
                                       .toString ();
  }
}
