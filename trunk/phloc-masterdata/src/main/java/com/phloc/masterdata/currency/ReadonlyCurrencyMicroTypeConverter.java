package com.phloc.masterdata.currency;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import com.phloc.commons.microdom.IMicroElement;

public final class ReadonlyCurrencyMicroTypeConverter extends AbstractCurrencyMicroTypeConverter
{
  @Nonnull
  public final ReadonlyCurrencyValue convertToNative (@Nonnull final IMicroElement ePrice)
  {
    final ECurrency eCurrency = ECurrency.getFromIDOrNull (ePrice.getAttribute (ATTR_CURRENCY));
    final BigDecimal aValue = ePrice.getAttributeWithConversion (ATTR_VALUE, BigDecimal.class);
    return new ReadonlyCurrencyValue (eCurrency, aValue);
  }
}