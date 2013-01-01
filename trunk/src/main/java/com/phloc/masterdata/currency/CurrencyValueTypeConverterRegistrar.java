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
package com.phloc.masterdata.currency;

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

@Immutable
@IsSPIImplementation
public final class CurrencyValueTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  private static final String ATTR_CURRENCY = "currency";
  private static final String ATTR_VALUE = "value";

  /**
   * Common base class for {@link CurrencyValue} and
   * {@link ReadonlyCurrencyValue}
   * 
   * @author philip
   */
  private static abstract class AbstractCurrencyValueConverter implements IMicroTypeConverter
  {
    @Nonnull
    public final IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                                      @Nullable final String sNamespaceURI,
                                                      @Nonnull final String sTagName)
    {
      final IReadonlyCurrencyValue aPrice = (IReadonlyCurrencyValue) aObject;
      final IMicroElement ePrice = new MicroElement (sNamespaceURI, sTagName);
      if (aPrice.getCurrency () != null)
        ePrice.setAttribute (ATTR_CURRENCY, aPrice.getCurrency ().getID ());
      if (aPrice.getValue () != null)
        ePrice.setAttribute (ATTR_VALUE, aPrice.getValue ().toString ());
      return ePrice;
    }
  }

  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (ReadonlyCurrencyValue.class, new AbstractCurrencyValueConverter ()
    {
      @Nonnull
      public final ReadonlyCurrencyValue convertToNative (@Nonnull final IMicroElement ePrice)
      {
        final ECurrency eCurrency = ECurrency.getFromIDOrNull (ePrice.getAttribute (ATTR_CURRENCY));
        final BigDecimal aValue = StringParser.parseBigDecimal (ePrice.getAttribute (ATTR_VALUE));
        return new ReadonlyCurrencyValue (eCurrency, aValue);
      }
    });
    aRegistry.registerMicroElementTypeConverter (CurrencyValue.class, new AbstractCurrencyValueConverter ()
    {
      @Nonnull
      public final CurrencyValue convertToNative (@Nonnull final IMicroElement ePrice)
      {
        final ECurrency eCurrency = ECurrency.getFromIDOrNull (ePrice.getAttribute (ATTR_CURRENCY));
        final BigDecimal aValue = StringParser.parseBigDecimal (ePrice.getAttribute (ATTR_VALUE));
        return new CurrencyValue (eCurrency, aValue);
      }
    });
  }
}
