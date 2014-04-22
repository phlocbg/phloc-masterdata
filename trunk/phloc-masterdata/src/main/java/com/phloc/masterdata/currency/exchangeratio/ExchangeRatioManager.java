package com.phloc.masterdata.currency.exchangeratio;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.masterdata.currency.ECurrency;

/**
 * This class manages an {@link ExchangeRatioList} per currency.
 * 
 * @author Philip Helger
 */
public final class ExchangeRatioManager
{
  private final Map <ECurrency, ExchangeRatioList> m_aMap = new EnumMap <ECurrency, ExchangeRatioList> (ECurrency.class);

  public ExchangeRatioManager ()
  {}

  public void updateExchangeRatioList (@Nonnull final ExchangeRatioList aExchangeRatioList)
  {
    ValueEnforcer.notNull (aExchangeRatioList, "ExchangeRatioList");

    final ECurrency eCurrency = aExchangeRatioList.getCurrency ();
    ExchangeRatioList aList = m_aMap.get (eCurrency);
    if (aList == null)
    {
      aList = new ExchangeRatioList (eCurrency);
      m_aMap.put (eCurrency, aList);
    }
    aList.mergeWith (aExchangeRatioList);
  }

  @Nullable
  public ExchangeRatio getCurrentExchangeRatio (@Nullable final ECurrency eCurrency)
  {
    final ExchangeRatioList aList = m_aMap.get (eCurrency);
    return aList == null ? null : aList.getCurrentExchangeRatio ();
  }

  @Nullable
  public ExchangeRatio getExchangeRatioOfDate (@Nullable final ECurrency eCurrency, @Nonnull final LocalDate aDate)
  {
    final ExchangeRatioList aList = m_aMap.get (eCurrency);
    return aList == null ? null : aList.getExchangeRatioOfDate (aDate);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }
}
