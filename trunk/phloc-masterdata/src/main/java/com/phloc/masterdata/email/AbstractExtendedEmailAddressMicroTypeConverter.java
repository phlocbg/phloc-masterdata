package com.phloc.masterdata.email;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;

public abstract class AbstractExtendedEmailAddressMicroTypeConverter implements IMicroTypeConverter
{
  protected static final String ATTR_TYPE = "type";
  protected static final String ATTR_ADDRESS = "address";
  protected static final String ATTR_PERSONAL = "personal";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IReadonlyExtendedEmailAddress aEmail = (IReadonlyExtendedEmailAddress) aObject;
    final IMicroElement eEmail = new MicroElement (sNamespaceURI, sTagName);
    if (aEmail.getType () != null)
      eEmail.setAttribute (ATTR_TYPE, aEmail.getType ().getID ());
    eEmail.setAttribute (ATTR_ADDRESS, aEmail.getAddress ());
    eEmail.setAttribute (ATTR_PERSONAL, aEmail.getPersonal ());
    return eEmail;
  }
}
