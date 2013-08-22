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
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Translatable;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.text.ITextProvider;
import com.phloc.commons.text.impl.TextProvider;
import com.phloc.commons.text.resolve.DefaultTextResolver;

@Translatable
public enum ETaxCategoryUN5305Name implements IHasDisplayText
{
  A ("Gemischter Steuersatz", "Mixed tax rate"),
  AA ("Niedrigerer Steuersatz", "Lower rate"),
  AB ("Ausnahme für Wiederverkauf", "Exempt for resale"),
  AC ("MwSt. ist noch nicht zu zahlen", "Value Added Tax (VAT) not now due for payment"),
  AD ("MwSt. von einer vorherigen Rechnung offen", "Value Added Tax (VAT) due from a previous invoice"),
  AE ("Umkehr der Steuerschuld", "VAT Reverse Charge"),
  B ("Steuer direkt ans Finanzamt abzuführen", "Transferred (VAT)"),
  C ("Lieferant zahlt Schuld", "Duty paid by supplier"),
  E ("Steuerausnahme", "Exempt from tax"),
  G ("Freier Export-Artikel, keine Steuerschuld", "Free export item, tax not charged"),
  H ("Höherer Steuersatz", "Higher rate"),
  O ("Dienstleistung nicht steuerrelevant", "Services outside scope of tax"),
  S ("Standard Steuersatz", "Standard rate"),
  Z ("Steuersatz 0%", "Zero rated goods");

  private final ITextProvider m_aTP;

  private ETaxCategoryUN5305Name (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextProvider.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
  }
}
