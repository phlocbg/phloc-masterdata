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
package com.phloc.masterdata.vat;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.compare.AbstractCollationComparator;

/**
 * Comparator that sorts {@link IVATItem} objects by their name.
 * 
 * @author Philip Helger
 */
public final class ComparatorVATItemName extends AbstractCollationComparator <IVATItem>
{
  private final Locale m_aContentLocale;

  public ComparatorVATItemName (@Nullable final Locale aSortLocale, @Nonnull final Locale aContentLocale)
  {
    super (aSortLocale);
    if (aContentLocale == null)
      throw new NullPointerException ("contentLocale");
    m_aContentLocale = aContentLocale;
  }

  @Override
  protected String asString (@Nonnull final IVATItem aVATItem)
  {
    return aVATItem.getDisplayText (m_aContentLocale);
  }
}
