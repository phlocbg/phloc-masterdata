package com.phloc.masterdata.price;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.vat.VATManager;

public final class PriceMicroTypeConverter extends AbstractPriceMicroTypeConverter
{
  @Nonnull
  public final Price convertToNative (@Nonnull final IMicroElement ePrice)
  {
    final ECurrency eCurrency = ECurrency.getFromIDOrNull (ePrice.getAttribute (ATTR_CURRENCY));
    final BigDecimal aValue = ePrice.getAttributeWithConversion (ATTR_NETAMOUNT, BigDecimal.class);
    final String sVATItemID = ePrice.getAttribute (ATTR_VATITEM);
    return new Price (eCurrency, aValue, VATManager.getDefaultInstance ().getVATItemOfID (sVATItemID));
  }
}
