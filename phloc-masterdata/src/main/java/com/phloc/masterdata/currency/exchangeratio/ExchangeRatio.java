package com.phloc.masterdata.currency.exchangeratio;

import java.math.BigDecimal;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.joda.time.LocalDate;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Represents a single currency exchange ratio compared to a base currency (e.g.
 * EUR)
 * 
 * @author Philip Helger
 */
@Immutable
public final class ExchangeRatio
{
  private final LocalDate m_aDate;
  private final BigDecimal m_aRatio;

  public ExchangeRatio (@Nonnull final LocalDate aDate, @Nonnull @Nonnegative final BigDecimal aRatio)
  {
    m_aDate = ValueEnforcer.notNull (aDate, "Date");
    m_aRatio = ValueEnforcer.isGT0 (aRatio, "Ratio");
  }

  @Nonnull
  public LocalDate getDate ()
  {
    return m_aDate;
  }

  @Nonnegative
  @Nonnull
  public BigDecimal getRatio ()
  {
    return m_aRatio;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof ExchangeRatio))
      return false;
    final ExchangeRatio rhs = (ExchangeRatio) o;
    return m_aDate.equals (rhs.m_aDate) && EqualsUtils.equals (m_aRatio, rhs.m_aRatio);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aDate).append (m_aRatio).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("date", m_aDate).append ("ratio", m_aRatio).toString ();
  }
}
