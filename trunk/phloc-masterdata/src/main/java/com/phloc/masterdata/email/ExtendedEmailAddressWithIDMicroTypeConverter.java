package com.phloc.masterdata.email;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.string.StringParser;

public final class ExtendedEmailAddressWithIDMicroTypeConverter extends AbstractExtendedEmailAddressMicroTypeConverter
{
  protected static final String ATTR_ID = "type";

  @Override
  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final ExtendedEmailAddressWithID aEmail = (ExtendedEmailAddressWithID) aObject;
    final IMicroElement ret = super.convertToMicroElement (aObject, sNamespaceURI, sTagName);
    ret.setAttribute (ATTR_ID, aEmail.getID ());
    return ret;
  }

  @Nonnull
  public ExtendedEmailAddressWithID convertToNative (@Nonnull final IMicroElement eEmail)
  {
    final int nID = StringParser.parseInt (eEmail.getAttribute (ATTR_ID), -1);
    final EEmailAddressType eType = EEmailAddressType.getFromIDOrNull (eEmail.getAttribute (ATTR_TYPE));
    final String sAddress = eEmail.getAttribute (ATTR_ADDRESS);
    final String sPersonal = eEmail.getAttribute (ATTR_PERSONAL);
    return new ExtendedEmailAddressWithID (nID, eType, sAddress, sPersonal);
  }
}
