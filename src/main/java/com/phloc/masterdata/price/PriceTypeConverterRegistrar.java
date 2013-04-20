/**
 * Copyright (C) 2006-2013 phloc systems
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
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.IsSPIImplementation;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistry;
import com.phloc.commons.microdom.impl.MicroElement;
import com.phloc.commons.string.StringParser;
import com.phloc.masterdata.currency.ECurrency;
import com.phloc.masterdata.currency.ReadonlyCurrencyValue;
import com.phloc.masterdata.vat.VATManager;

@Immutable
@IsSPIImplementation
public final class PriceTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  static final String ATTR_CURRENCY = "currency";
  static final String ATTR_NETAMOUNT = "netamount";
  static final String ATTR_GROSSAMOUNT = "grossamount";
  static final String ATTR_VATITEM = "vatitem";

  /**
   * Common base class for {@link Price} and {@link ReadonlyPrice}
   * 
   * @author philip
   */
  private abstract static class AbstractPriceConverter implements IMicroTypeConverter
  {
    @Nonnull
    public final IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                                      @Nullable final String sNamespaceURI,
                                                      @Nonnull final String sTagName)
    {
      final IReadonlyPrice aPrice = (IReadonlyPrice) aObject;
      final IMicroElement ePrice = new MicroElement (sNamespaceURI, sTagName);
      if (aPrice.getCurrency () != null)
        ePrice.setAttribute (ATTR_CURRENCY, aPrice.getCurrency ().getID ());
      if (aPrice.getNetAmount () != null)
        ePrice.setAttribute (ATTR_NETAMOUNT, aPrice.getNetAmount ().getValue ().toString ());
      if (aPrice.getGrossAmount () != null)
        ePrice.setAttribute (ATTR_GROSSAMOUNT, aPrice.getGrossAmount ().getValue ().toString ());
      if (aPrice.getVATItem () != null)
        ePrice.setAttribute (ATTR_VATITEM, aPrice.getVATItem ().getID ());
      return ePrice;
    }
  }

  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (ReadonlyPrice.class, new AbstractPriceConverter ()
    {
      @Nonnull
      public final ReadonlyPrice convertToNative (@Nonnull final IMicroElement ePrice)
      {
        final ECurrency eCurrency = ECurrency.getFromIDOrNull (ePrice.getAttribute (ATTR_CURRENCY));
        final BigDecimal aValue = StringParser.parseBigDecimal (ePrice.getAttribute (ATTR_NETAMOUNT));
        return new ReadonlyPrice (new ReadonlyCurrencyValue (eCurrency, aValue),
                                  VATManager.getDefaultInstance ().getVATItemOfID (ePrice.getAttribute (ATTR_VATITEM)));
      }
    });
    aRegistry.registerMicroElementTypeConverter (Price.class, new AbstractPriceConverter ()
    {
      @Nonnull
      public final Price convertToNative (@Nonnull final IMicroElement ePrice)
      {
        final Price aPrice = new Price ();
        final ECurrency eCurrency = ECurrency.getFromIDOrNull (ePrice.getAttribute (ATTR_CURRENCY));
        final BigDecimal aValue = StringParser.parseBigDecimal (ePrice.getAttribute (ATTR_NETAMOUNT));
        aPrice.setNetAmount (new ReadonlyCurrencyValue (eCurrency, aValue));
        aPrice.setVATItem (VATManager.getDefaultInstance ().getVATItemOfID (ePrice.getAttribute (ATTR_VATITEM)));
        return aPrice;
      }
    });
  }
}
