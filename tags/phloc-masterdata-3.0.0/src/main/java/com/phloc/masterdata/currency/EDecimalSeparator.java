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
package com.phloc.masterdata.currency;

import javax.annotation.Nullable;

/**
 * Represents the different possible decimal separators.
 * 
 * @author philip
 */
public enum EDecimalSeparator
{
  COMMA (','),
  POINT ('.');

  private final char m_cChar;

  private EDecimalSeparator (final char c)
  {
    m_cChar = c;
  }

  public char getChar ()
  {
    return m_cChar;
  }

  @Nullable
  public static EDecimalSeparator getFromCharOrNull (final char cSep)
  {
    return getFromCharOrDefault (cSep, null);
  }

  @Nullable
  public static EDecimalSeparator getFromCharOrDefault (final char cSep, @Nullable final EDecimalSeparator eDefault)
  {
    for (final EDecimalSeparator eDecSep : EDecimalSeparator.values ())
      if (eDecSep.getChar () == cSep)
        return eDecSep;
    return eDefault;
  }
}
