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
package com.phloc.masterdata.tax;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.phloc.commons.annotations.Translatable;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.text.ITextProvider;
import com.phloc.commons.text.impl.TextProvider;
import com.phloc.commons.text.resolve.DefaultTextResolver;

@Translatable
public enum ETaxTypeUN5153Name implements IHasDisplayText
{
  AAA ("Erdölsteuer", "Petroleum tax"),
  AAB ("Provisorische Ausgleichsabgabe", "Provisional countervailing duty cash"),
  AAC ("Provisorische Ausgleichsabgabe Schuld", "Provisional countervailing duty bond"),
  AAD ("Tabaksteuer", "Tobacco tax"),
  AAE ("Energieabgabe", "Energy fee"),
  AAF ("Kaffeesteuer", "Coffee tax"),
  AAG ("Harmonisierte Umsatzsteuer", "Harmonised sales tax, Canadian"),
  AAH ("Quebecs' Umsatzsteuer", "Quebec sales tax"),
  AAI ("Kanadische Regionale Umsatzsteuer", "Canadian provincial sales tax"),
  AAJ ("Tax on replacement part", "Tax on replacement part"),
  AAK ("Mineralölsteuer", "Mineral oil tax"),
  AAL ("Spezialsteuer", "Special tax"),
  ADD ("Anti-dumping Abgabe", "Anti-dumping duty"),
  BOL ("Stempelgebühr/Irland: Grunderwerbssteuer", "Stamp duty (Imposta di Bollo)"),
  CAP ("Agrarabgabe", "Agricultural levy"),
  CAR ("Kraftfahrzeugssteuer", "Car tax"),
  COC ("Paper consortium tax (Italy)", "Paper consortium tax (Italy)"),
  CST ("Spezielle Zollabgaben", "Commodity specific tax"),
  CUD ("Zollabgaben", "Customs duty"),
  CVD ("Ausgleichsabgabe", "Countervailing duty"),
  ENV ("Umweltsteuer", "Environmental tax"),
  EXC ("Verbrauchssteuer", "Excise duty"),
  EXP ("Agrar-Ausfuhrvergütung", "Agricultural export rebate"),
  FET ("Bundesverbrauchssteuer", "Federal excise tax"),
  FRE ("Frei", "Free"),
  GCN ("Allgemeine Bausteuer", "General construction tax"),
  GST ("Waren- und Dienstleistungssteuer", "Goods and services tax"),
  ILL ("Leuchtmittelsteuer", "Illuminants tax"),
  IMP ("Importsteuer", "Import tax"),
  IND ("Individual-Steuer", "Individual tax"),
  LAC ("Gewerbesteuer", "Business license fee"),
  LCN ("Regionale Bausteuer", "Local construction tax"),
  LDP ("Light dues payable", "Light dues payable"),
  LOC ("Local sales tax", "Local sales tax"),
  LST ("Luststeuer", "Lust tax"),
  MCA ("Monetary compensatory amount", "Monetary compensatory amount"),
  MCD ("Miscellaneous cash deposit", "Miscellaneous cash deposit"),
  OTH ("Andere Steuern", "Other taxes"),
  PDB ("Provisorische Abgabenschuld", "Provisional duty bond"),
  PDC ("Provisorische Abgabenschuld", "Provisional duty cash"),
  PRF ("Vorzugsabgabe", "Preference duty"),
  SCN ("Spezialbausteuer", "Special construction tax"),
  SSS ("Verschiebung der Sozialen Sicherheit", "Shifted social securities"),
  STT ("Regionale Umsatzsteuer", "State/provincial sales tax"),
  SUP ("Außer Kraft gesetzte Steuer", "Suspended duty"),
  SUR ("Zusatzsteuer/Zuschlag/Ergänzungsabgabe", "Surtax"),
  SWT ("Shifted wage tax", "Shifted wage tax"),
  TAC ("Alkohol mark tax", "Alcohol mark tax"),
  TOT ("Summe/Gesamt", "Total"),
  TOX ("Turnover tax", "Turnover tax"),
  TTA ("Tonnage taxes", "Tonnage taxes"),
  VAD ("Valuation deposit", "Valuation deposit"),
  VAT ("Umsatzsteuer", "Value added tax");

  private final ITextProvider m_aTP;

  private ETaxTypeUN5153Name (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextProvider.create_DE_EN (sDE, sEN);
  }

  public String getDisplayText (final Locale aContentLocale)
  {
    return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
  }
}
