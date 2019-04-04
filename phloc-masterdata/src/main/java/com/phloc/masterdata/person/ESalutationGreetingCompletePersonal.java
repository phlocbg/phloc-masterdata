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
package com.phloc.masterdata.person;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.phloc.commons.annotations.Translatable;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.text.ITextProvider;
import com.phloc.commons.text.impl.TextProvider;
import com.phloc.commons.text.resolve.DefaultTextResolver;

@Translatable
public enum ESalutationGreetingCompletePersonal implements IHasDisplayText
{
 MISTER ("Lieber Herr", "Dear Mr"),
 MISSES ("Liebe Frau", "Dear Ms"),
 FAMILY ("Liebe Familie", "Dear Familiy"),
 COMPANY ("Liebe Damen und Herren", "Dear Sir or Madam"),
 CLUB ("Liebe Damen und Herren", "Dear Sir or Madam");

  private final ITextProvider m_aTP;

  private ESalutationGreetingCompletePersonal (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    this.m_aTP = TextProvider.create_DE_EN (sDE, sEN);
  }

  @Override
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getText (this, this.m_aTP, aContentLocale);
  }
}
