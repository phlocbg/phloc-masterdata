/**
 * Copyright (C) 2006-2012 phloc systems
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
package com.phloc.masterdata.swift;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Possible elements of an IBAN description.
 * 
 * @author philip
 */
public enum EIBANElementType
{
  COUNTRY_CODE (""),
  CHECKSUM ("kK"),
  BANK_CODE ("B"),
  BANK_BRANCH ("b"),
  ACCOUNT_NUMBER ("C"),
  SWIFT_BIC_CODE ("S"),
  RESERVED ("R"),
  ID ("X"),
  CURRENCY_CODE ("d");

  private final String m_sField;

  private EIBANElementType (@Nonnull final String sField)
  {
    m_sField = sField;
  }

  @Nonnull
  public String getField ()
  {
    return m_sField;
  }

  @Nullable
  public static EIBANElementType getElementTypeFromChar (final char c)
  {
    for (final EIBANElementType eIBANElement : values ())
      if (eIBANElement.m_sField.indexOf (c) != -1)
        return eIBANElement;
    return null;
  }
}
