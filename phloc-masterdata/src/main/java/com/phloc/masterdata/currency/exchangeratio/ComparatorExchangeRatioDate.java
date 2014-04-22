package com.phloc.masterdata.currency.exchangeratio;

import javax.annotation.Nonnull;

import com.phloc.commons.compare.AbstractComparator;
import com.phloc.commons.compare.ESortOrder;

/**
 * A comparator comparing {@link ExchangeRatio} objects by their date.
 * 
 * @author Philip Helger
 */
public class ComparatorExchangeRatioDate extends AbstractComparator <ExchangeRatio>
{
  public ComparatorExchangeRatioDate ()
  {}

  public ComparatorExchangeRatioDate (@Nonnull final ESortOrder eSortOrder)
  {
    super (eSortOrder);
  }

  @Override
  protected int mainCompare (@Nonnull final ExchangeRatio aElement1, @Nonnull final ExchangeRatio aElement2)
  {
    return aElement1.getDate ().compareTo (aElement2.getDate ());
  }
}
