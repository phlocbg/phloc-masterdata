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
package com.phloc.masterdata.email;

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
public final class EmailAddressTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  private static abstract class AbstractBaseConverter implements IMicroTypeConverter
  {
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

  private static final String ATTR_TYPE = "type";
  private static final String ATTR_ADDRESS = "address";
  private static final String ATTR_PERSONAL = "personal";

  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (ExtendedEmailAddress.class, new AbstractBaseConverter ()
    {
      @Nonnull
      public ExtendedEmailAddress convertToNative (@Nonnull final IMicroElement eEmail)
      {
        final EEmailAddressType eType = EEmailAddressType.getFromIDOrNull (eEmail.getAttribute (ATTR_TYPE));
        final String sAddress = eEmail.getAttribute (ATTR_ADDRESS);
        final String sPersonal = eEmail.getAttribute (ATTR_PERSONAL);
        return new ExtendedEmailAddress (eType, sAddress, sPersonal);
      }
    });
    aRegistry.registerMicroElementTypeConverter (ReadonlyExtendedEmailAddress.class, new AbstractBaseConverter ()
    {
      @Nonnull
      public ReadonlyExtendedEmailAddress convertToNative (@Nonnull final IMicroElement eEmail)
      {
        final EEmailAddressType eType = EEmailAddressType.getFromIDOrNull (eEmail.getAttribute (ATTR_TYPE));
        final String sAddress = eEmail.getAttribute (ATTR_ADDRESS);
        final String sPersonal = eEmail.getAttribute (ATTR_PERSONAL);
        return new ReadonlyExtendedEmailAddress (eType, sAddress, sPersonal);
      }
    });
  }
}
