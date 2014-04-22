package com.phloc.masterdata.currency;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;

/**
 * Common base class for {@link CurrencyValue} and {@link ReadonlyCurrencyValue}
 * 
 * @author Philip Helger
 */
public abstract class AbstractCurrencyMicroTypeConverter implements IMicroTypeConverter
{
  protected static final String ATTR_CURRENCY = "currency";
  protected static final String ATTR_VALUE = "value";

  @Nonnull
  public final IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                                    @Nullable final String sNamespaceURI,
                                                    @Nonnull final String sTagName)
  {
    final IReadonlyCurrencyValue aPrice = (IReadonlyCurrencyValue) aObject;
    final IMicroElement ePrice = new MicroElement (sNamespaceURI, sTagName);
    ePrice.setAttribute (ATTR_CURRENCY, aPrice.getCurrency ().getID ());
    ePrice.setAttributeWithConversion (ATTR_VALUE, aPrice.getValue ());
    return ePrice;
  }
}
