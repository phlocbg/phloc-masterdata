/**
 * Copyright (C) 2006-2014 phloc systems
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
package com.phloc.masterdata.telephone;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.IsSPIImplementation;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistry;
import com.phloc.commons.microdom.impl.MicroElement;

@Immutable
@IsSPIImplementation
public final class TelephoneTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  private abstract static class AbstractBaseConverter implements IMicroTypeConverter
  {
    @Nonnull
    public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                                @Nullable final String sNamespaceURI,
                                                @Nonnull final String sTagName)
    {
      final IReadonlyTelephoneNumber aTelNo = (IReadonlyTelephoneNumber) aObject;
      final IMicroElement eTelNo = new MicroElement (sNamespaceURI, sTagName);
      if (aTelNo.getType () != null)
        eTelNo.setAttribute (ATTR_TYPE, aTelNo.getType ().getID ());
      eTelNo.setAttribute (ATTR_COUNTRYCODE, aTelNo.getCountryCode ());
      eTelNo.setAttribute (ATTR_AREACODE, aTelNo.getAreaCode ());
      eTelNo.setAttribute (ATTR_LINE, aTelNo.getLine ());
      eTelNo.setAttribute (ATTR_DIRECTDIAL, aTelNo.getDirectDial ());
      return eTelNo;
    }
  }

  private static final String ATTR_TYPE = "type";
  private static final String ATTR_COUNTRYCODE = "countrycode";
  private static final String ATTR_AREACODE = "areacode";
  private static final String ATTR_LINE = "line";
  private static final String ATTR_DIRECTDIAL = "directdial";

  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (TelephoneNumber.class, new AbstractBaseConverter ()
    {
      @Nonnull
      public TelephoneNumber convertToNative (@Nonnull final IMicroElement eTelNo)
      {
        final ETelephoneType eType = ETelephoneType.getFromIDOrNull (eTelNo.getAttribute (ATTR_TYPE));
        final String sCountryCode = eTelNo.getAttribute (ATTR_COUNTRYCODE);
        final String sAreaCode = eTelNo.getAttribute (ATTR_AREACODE);
        final String sLine = eTelNo.getAttribute (ATTR_LINE);
        final String sDirectDial = eTelNo.getAttribute (ATTR_DIRECTDIAL);
        return new TelephoneNumber (eType, sCountryCode, sAreaCode, sLine, sDirectDial);
      }
    });
    aRegistry.registerMicroElementTypeConverter (ReadonlyTelephoneNumber.class, new AbstractBaseConverter ()
    {
      @Nonnull
      public ReadonlyTelephoneNumber convertToNative (@Nonnull final IMicroElement eTelNo)
      {
        final ETelephoneType eType = ETelephoneType.getFromIDOrNull (eTelNo.getAttribute (ATTR_TYPE));
        final String sCountryCode = eTelNo.getAttribute (ATTR_COUNTRYCODE);
        final String sAreaCode = eTelNo.getAttribute (ATTR_AREACODE);
        final String sLine = eTelNo.getAttribute (ATTR_LINE);
        final String sDirectDial = eTelNo.getAttribute (ATTR_DIRECTDIAL);
        return new ReadonlyTelephoneNumber (eType, sCountryCode, sAreaCode, sLine, sDirectDial);
      }
    });
  }
}
