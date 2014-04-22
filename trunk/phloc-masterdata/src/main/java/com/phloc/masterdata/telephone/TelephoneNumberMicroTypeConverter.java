package com.phloc.masterdata.telephone;

import javax.annotation.Nonnull;

import com.phloc.commons.microdom.IMicroElement;

public final class TelephoneNumberMicroTypeConverter extends AbstractTelephoneNumberMicroTypeConverter
{
  @Nonnull
   public TelephoneNumber convertToNative (@Nonnull final IMicroElement eTelNo)
   {
     final ETelephoneType eType = ETelephoneType.getFromIDOrNull (eTelNo.getAttribute (ATTR_TYPE));
     final String sCountryCode = eTelNo.getAttribute (ATTR_COUNTRYCODE);
     final String sAreaCode = eTelNo.getAttribute (ATTR_AREACODE);
     final String sLine = eTelNo.getAttribute (ATTR_LINE);
     final String sDirectDial = eTelNo.getAttribute (ATTR_DIRECTDIAL);
     return new TelephoneNumber (eType,
                                 sCountryCode,
                                 sAreaCode,
                                 sLine,
                                 sDirectDial);
   }
}