package com.phloc.masterdata.address;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.system.SystemHelper;

public class ReadonlyAddressMicroTypeConverter extends AbstractAddressMicroTypeConverter
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
}
