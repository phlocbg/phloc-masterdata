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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;
import com.phloc.masterdata.tax.ETaxCategoryUN5305;

/**
 * Determines the different VAT types.<br>
 * Source: http://de.wikipedia.org/wiki/Umsatzsteuer
 * 
 * @author philip
 */
public enum EVATType implements IHasID <String>
{
  REGULAR ("regular", ETaxCategoryUN5305.S),
  INBETWEEN ("inbetween", ETaxCategoryUN5305.A),
  REDUCED ("reduced", ETaxCategoryUN5305.AA),
  REDUCED_HEAVILY ("reduced_heavily", ETaxCategoryUN5305.AA),
  SERVICE ("service", ETaxCategoryUN5305.O),
  OTHER ("other", null);

  private final String m_sID;
  private final ETaxCategoryUN5305 m_eTaxCategory;

  private EVATType (@Nonnull @Nonempty final String sID, @Nullable final ETaxCategoryUN5305 eTaxCategory)
  {
    m_sID = sID;
    m_eTaxCategory = eTaxCategory;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public ETaxCategoryUN5305 getTaxCategory ()
  {
    return m_eTaxCategory;
  }

  @Nullable
  public static EVATType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EVATType.class, sID);
  }
}
