package com.phloc.masterdata.currency.exchangeratio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.convert.MicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;
import com.phloc.masterdata.currency.ECurrency;

public final class ExchangeRatioListMicroTypeConverter implements IMicroTypeConverter
{
  private static final String ATTR_CURRENCY = "currency";
  private static final String ELEMENT_EXCHANGE_RATIO = "exchangeratio";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final ExchangeRatioList aValue = (ExchangeRatioList) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_CURRENCY, aValue.getCurrencyID ());
    for (final ExchangeRatio aExchangeRatio : aValue.getAllExchangeRatios ())
      aElement.appendChild (MicroTypeConverter.convertToMicroElement (aExchangeRatio,
                                                                      sNamespaceURI,
                                                                      ELEMENT_EXCHANGE_RATIO));
    return aElement;
  }

  @Nonnull
  public ExchangeRatioList convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sCurrencyID = aElement.getAttribute (ATTR_CURRENCY);
    final ECurrency eCurrency = ECurrency.getFromIDOrNull (sCurrencyID);
    if (eCurrency == null)
      throw new IllegalStateException ("Failed to resolve currency with ID '" + sCurrencyID + "'");
    final ExchangeRatioList ret = new ExchangeRatioList (eCurrency);
    for (final IMicroElement eChild : aElement.getAllChildElements (ELEMENT_EXCHANGE_RATIO))
      ret.addExchangeRatio (MicroTypeConverter.convertToNative (eChild, ExchangeRatio.class));
    return ret;
  }
}
