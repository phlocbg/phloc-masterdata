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
package com.phloc.masterdata.currency;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.phloc.commons.annotations.DevelopersNote;
import com.phloc.commons.annotations.Translatable;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.text.ITextProvider;
import com.phloc.commons.text.impl.TextProvider;
import com.phloc.commons.text.resolve.DefaultTextResolver;

@Translatable
public enum ECurrencyName implements IHasDisplayText
{
  ALL ("Albanische Lek", "Albanian Lek"),
  AMD ("Armenische Dram", "Armenian Dram"),
  AZN ("Aserbaidschanische Manat", "Azerbaijani Manat"),
  BAM ("Konvertible Marks", "Bosnia-Herzegovina Convertible Mark"),
  BGN ("Bulgarische Leva", "Bulgarian Lev"),
  BYR ("Weißrussische Rubel", "Belarusian Ruble"),
  CHF ("Schweizer Franken", "Swiss Franc"),
  CZK ("Tschechische Kronen", "Czech Koruna"),
  DKK ("Dänische Kronen", "Danish Krone"),
  @Deprecated
  @DevelopersNote ("Was replaced with Euro per 1.1.2011")
  EEK ("Estnische Krone", "Estonian Kroon"),
  EUR ("Euro", "Euro"),
  GBP ("Pfund Sterling", "Pound Sterling"),
  GEL ("Georgische Lari", "Georgian Lari"),
  HRK ("Kroatische Kuna", "Croatian Kuna"),
  HUF ("Ungarische Forint", "Hungarian Forint"),
  ISK ("Isländische Krone", "Icelandic Krona"),
  LTL ("Litauische Litas", "Lithuanian Litas"),
  LVL ("Lettische Lats", "Latvian Lats"),
  MDL ("Moldawische Leu", "Moldovan Leu"),
  MKD ("Mazedonische Dinar", "Macedonia Denar"),
  NOK ("Norwegische Krone", "Norwegian Krone"),
  PLN ("Polnische Złoty", "Polish Złoty"),
  RON ("Rumänische Leu", "Romanian Leu"),
  RSD ("Serbische Dinar", "Serbian dinar"),
  RUB ("Russische Rubel", "Russian Ruble"),
  SEK ("Schwedische Kronen", "Swedish Krona"),
  TRY ("Türkische Lira", "New Turkish Lira"),
  UAH ("Ukrainische Hrywnja", "Ukrainian Hryvnia"),
  USD ("US Dollar", "United States Dollar");

  private final ITextProvider m_aTP;

  private ECurrencyName (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextProvider.create_DE_EN (sDE, sEN);
  }

  public String getDisplayText (final Locale aContentLocale)
  {
    return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
  }
}
