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
package com.phloc.masterdata.person;

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
public final class PersonTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  private static final String ATTR_SALUTATION = "salutation";
  private static final String ATTR_PREFIXTITLE = "prefixtitle";
  private static final String ATTR_FIRSTNAME = "firstname";
  private static final String ATTR_MIDDLENAME = "middlename";
  private static final String ATTR_LASTNAME = "lastname";
  private static final String ATTR_SUFFIXTITLE = "suffixtitle";

  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    final IMicroTypeConverter aConverter = new IMicroTypeConverter ()
    {
      @Nonnull
      public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                                  @Nullable final String sNamespaceURI,
                                                  @Nonnull final String sTagName)
      {
        final PersonName aAddress = (PersonName) aObject;
        final IMicroElement eName = new MicroElement (sNamespaceURI, sTagName);
        if (aAddress.getSalutation () != null)
          eName.setAttribute (ATTR_SALUTATION, aAddress.getSalutationID ());
        if (StringHelper.hasText (aAddress.getPrefixTitle ()))
          eName.setAttribute (ATTR_PREFIXTITLE, aAddress.getPrefixTitle ());
        if (StringHelper.hasText (aAddress.getFirstName ()))
          eName.setAttribute (ATTR_FIRSTNAME, aAddress.getFirstName ());
        if (StringHelper.hasText (aAddress.getMiddleName ()))
          eName.setAttribute (ATTR_MIDDLENAME, aAddress.getMiddleName ());
        if (StringHelper.hasText (aAddress.getLastName ()))
          eName.setAttribute (ATTR_LASTNAME, aAddress.getLastName ());
        if (StringHelper.hasText (aAddress.getSuffixTitle ()))
          eName.setAttribute (ATTR_SUFFIXTITLE, aAddress.getSuffixTitle ());
        return eName;
      }

      @Nonnull
      public PersonName convertToNative (@Nonnull final IMicroElement eAddress)
      {
        final Locale aLocale = SystemHelper.getSystemLocale ();
        final PersonName aName = new PersonName ();
        aName.setSalutation (ESalutation.getFromIDOrNull (eAddress.getAttribute (ATTR_SALUTATION)));
        aName.setPrefixTitle (eAddress.getAttribute (ATTR_PREFIXTITLE));
        aName.setFirstName (eAddress.getAttribute (ATTR_FIRSTNAME), aLocale);
        aName.setMiddleName (eAddress.getAttribute (ATTR_MIDDLENAME), aLocale);
        aName.setLastName (eAddress.getAttribute (ATTR_LASTNAME), aLocale);
        aName.setSuffixTitle (eAddress.getAttribute (ATTR_SUFFIXTITLE));
        return aName;
      }
    };

    aRegistry.registerMicroElementTypeConverter (PersonName.class, aConverter);
  }
}
