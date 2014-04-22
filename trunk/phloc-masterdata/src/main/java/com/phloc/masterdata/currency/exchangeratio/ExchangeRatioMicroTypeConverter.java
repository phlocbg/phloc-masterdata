package com.phloc.masterdata.currency.exchangeratio;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;

public final class ExchangeRatioMicroTypeConverter implements IMicroTypeConverter
{
  private static final String ATTR_DATE = "date";
  private static final String ATTR_RATIO = "ratio";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final ExchangeRatio aValue = (ExchangeRatio) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttributeWithConversion (ATTR_DATE, aValue.getDate ());
    aElement.setAttributeWithConversion (ATTR_RATIO, aValue.getRatio ());
    return aElement;
  }

  @Nonnull
  public ExchangeRatio convertToNative (@Nonnull final IMicroElement aElement)
  {
    final LocalDate aDate = aElement.getAttributeWithConversion (ATTR_DATE, LocalDate.class);
    final BigDecimal aRatio = aElement.getAttributeWithConversion (ATTR_RATIO, BigDecimal.class);
    return new ExchangeRatio (aDate, aRatio);
  }
}
