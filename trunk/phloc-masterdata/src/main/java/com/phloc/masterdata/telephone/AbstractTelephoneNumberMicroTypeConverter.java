package com.phloc.masterdata.telephone;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;

public abstract class AbstractTelephoneNumberMicroTypeConverter implements IMicroTypeConverter
{
  protected static final String ATTR_TYPE = "type";
  protected static final String ATTR_COUNTRYCODE = "countrycode";
  protected static final String ATTR_AREACODE = "areacode";
  protected static final String ATTR_LINE = "line";
  protected static final String ATTR_DIRECTDIAL = "directdial";

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