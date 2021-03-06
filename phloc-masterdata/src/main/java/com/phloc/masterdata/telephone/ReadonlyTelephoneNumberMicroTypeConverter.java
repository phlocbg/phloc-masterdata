/**
 * Copyright (C) 2006-2015 phloc systems
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

import com.phloc.commons.microdom.IMicroElement;

public final class ReadonlyTelephoneNumberMicroTypeConverter extends AbstractTelephoneNumberMicroTypeConverter
{
  @Nonnull
   public ReadonlyTelephoneNumber convertToNative (@Nonnull final IMicroElement eTelNo)
   {
     final ETelephoneType eType = ETelephoneType.getFromIDOrNull (eTelNo.getAttribute (ATTR_TYPE));
     final String sCountryCode = eTelNo.getAttribute (ATTR_COUNTRYCODE);
     final String sAreaCode = eTelNo.getAttribute (ATTR_AREACODE);
     final String sLine = eTelNo.getAttribute (ATTR_LINE);
     final String sDirectDial = eTelNo.getAttribute (ATTR_DIRECTDIAL);
     return new ReadonlyTelephoneNumber (eType,
                                         sCountryCode,
                                         sAreaCode,
                                         sLine,
                                         sDirectDial);
   }
}