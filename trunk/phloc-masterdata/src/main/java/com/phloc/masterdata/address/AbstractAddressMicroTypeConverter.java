package com.phloc.masterdata.address;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;
import com.phloc.commons.string.StringHelper;

public abstract class AbstractAddressMicroTypeConverter implements IMicroTypeConverter
{
  protected static final String ATTR_COUNTRY = "country";
  protected static final String ATTR_STATE = "state";
  protected static final String ATTR_POSTALCODE = "zipcode";
  protected static final String ATTR_CITY = "city";
  protected static final String ATTR_STREET = "street";
  protected static final String ATTR_BUILDINGNUMBER = "buildingno";
  protected static final String ATTR_POBOX = "pobox";
  protected static final String ATTR_TYPE = "type";

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
