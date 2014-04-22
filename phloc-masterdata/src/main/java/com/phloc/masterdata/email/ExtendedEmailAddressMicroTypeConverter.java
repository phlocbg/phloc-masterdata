package com.phloc.masterdata.email;

import javax.annotation.Nonnull;

import com.phloc.commons.microdom.IMicroElement;

public final class ExtendedEmailAddressMicroTypeConverter extends AbstractExtendedEmailAddressMicroTypeConverter
{
  @Nonnull
  public ExtendedEmailAddress convertToNative (@Nonnull final IMicroElement eEmail)
  {
    final EEmailAddressType eType = EEmailAddressType.getFromIDOrNull (eEmail.getAttribute (ATTR_TYPE));
    final String sAddress = eEmail.getAttribute (ATTR_ADDRESS);
    final String sPersonal = eEmail.getAttribute (ATTR_PERSONAL);
    return new ExtendedEmailAddress (eType, sAddress, sPersonal);
  }
}
