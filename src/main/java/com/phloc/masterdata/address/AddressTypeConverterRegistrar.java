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
package com.phloc.masterdata.address;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.IsSPIImplementation;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistry;
import com.phloc.commons.microdom.impl.MicroElement;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.system.SystemHelper;

@Immutable
@IsSPIImplementation
public final class AddressTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  private static final String ATTR_COUNTRY = "country";
  private static final String ATTR_STATE = "state";
  private static final String ATTR_POSTALCODE = "zipcode";
  private static final String ATTR_CITY = "city";
  private static final String ATTR_STREET = "street";
  private static final String ATTR_BUILDINGNUMBER = "buildingno";
  private static final String ATTR_POBOX = "pobox";
  private static final String ATTR_TYPE = "type";

  private abstract static class AbstractBaseConverter implements IMicroTypeConverter
  {
    @Nonnull
    public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                                @Nullable final String sNamespaceURI,
                                                @Nonnull final String sTagName)
    {
      final IReadonlyAddress aAddress = (IReadonlyAddress) aObject;
      final IMicroElement eAddress = new MicroElement (sNamespaceURI, sTagName);
      if (aAddress.getType () != null)
        eAddress.setAttribute (ATTR_TYPE, aAddress.getType ().getID ());
      if (StringHelper.hasText (aAddress.getCountry ()))
        eAddress.setAttribute (ATTR_COUNTRY, aAddress.getCountry ());
      if (StringHelper.hasText (aAddress.getState ()))
        eAddress.setAttribute (ATTR_STATE, aAddress.getState ());
      if (StringHelper.hasText (aAddress.getPostalCode ()))
        eAddress.setAttribute (ATTR_POSTALCODE, aAddress.getPostalCode ());
      if (StringHelper.hasText (aAddress.getCity ()))
        eAddress.setAttribute (ATTR_CITY, aAddress.getCity ());
      if (StringHelper.hasText (aAddress.getStreet ()))
        eAddress.setAttribute (ATTR_STREET, aAddress.getStreet ());
      if (StringHelper.hasText (aAddress.getBuildingNumber ()))
        eAddress.setAttribute (ATTR_BUILDINGNUMBER, aAddress.getBuildingNumber ());
      if (StringHelper.hasText (aAddress.getPostOfficeBox ()))
        eAddress.setAttribute (ATTR_POBOX, aAddress.getPostOfficeBox ());
      return eAddress;
    }
  }

  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (Address.class, new AbstractBaseConverter ()
    {
      @Nonnull
      public Address convertToNative (@Nonnull final IMicroElement eAddress)
      {
        final Locale aLocale = SystemHelper.getSystemLocale ();
        final EAddressType eType = EAddressType.getFromIDOrNull (eAddress.getAttribute (ATTR_TYPE));
        final String sCountry = eAddress.getAttribute (ATTR_COUNTRY);
        final String sState = eAddress.getAttribute (ATTR_STATE);
        final String sPostalCode = eAddress.getAttribute (ATTR_POSTALCODE);
        final String sCity = eAddress.getAttribute (ATTR_CITY);
        final String sStreet = eAddress.getAttribute (ATTR_STREET);
        final String sBuildingNumber = eAddress.getAttribute (ATTR_BUILDINGNUMBER);
        final String sPostOfficeBox = eAddress.getAttribute (ATTR_POBOX);
        return new Address (eType,
                            sCountry,
                            sState,
                            sPostalCode,
                            sCity,
                            sStreet,
                            sBuildingNumber,
                            sPostOfficeBox,
                            aLocale);
      }
    });
    aRegistry.registerMicroElementTypeConverter (ReadonlyAddress.class, new AbstractBaseConverter ()
    {
      @Nonnull
      public ReadonlyAddress convertToNative (@Nonnull final IMicroElement eAddress)
      {
        final Locale aLocale = SystemHelper.getSystemLocale ();
        final EAddressType eType = EAddressType.getFromIDOrNull (eAddress.getAttribute (ATTR_TYPE));
        final String sCountry = eAddress.getAttribute (ATTR_COUNTRY);
        final String sState = eAddress.getAttribute (ATTR_STATE);
        final String sPostalCode = eAddress.getAttribute (ATTR_POSTALCODE);
        final String sCity = eAddress.getAttribute (ATTR_CITY);
        final String sStreet = eAddress.getAttribute (ATTR_STREET);
        final String sBuildingNumber = eAddress.getAttribute (ATTR_BUILDINGNUMBER);
        final String sPostOfficeBox = eAddress.getAttribute (ATTR_POBOX);
        return new ReadonlyAddress (eType,
                                    sCountry,
                                    sState,
                                    sPostalCode,
                                    sCity,
                                    sStreet,
                                    sBuildingNumber,
                                    sPostOfficeBox,
                                    aLocale);
      }
    });
  }
}
