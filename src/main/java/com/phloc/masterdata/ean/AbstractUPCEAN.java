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
package com.phloc.masterdata.ean;

import javax.annotation.Nonnull;

import com.phloc.commons.state.EValidity;

public abstract class AbstractUPCEAN
{
  protected static final EEANChecksumMode DEFAULT_CHECKSUM_MODE = EEANChecksumMode.AUTO;

  private final String m_sMsg;
  private final EEANChecksumMode m_eChecksumMode;

  /**
   * Main constructor
   * 
   * @param sMsg
   *        The code string.
   * @param eMode
   *        the checksum mode
   */
  public AbstractUPCEAN (@Nonnull final String sMsg, @Nonnull final EEANChecksumMode eMode)
  {
    if (sMsg == null)
      throw new NullPointerException ("msg");
    if (eMode == null)
      throw new NullPointerException ("checksumMode");
    m_sMsg = sMsg;
    m_eChecksumMode = eMode;
  }

  @Nonnull
  public String getMessage ()
  {
    return m_sMsg;
  }

  /**
   * Returns the current checksum mode.
   * 
   * @return the checksum mode
   */
  @Nonnull
  public EEANChecksumMode getChecksumMode ()
  {
    return m_eChecksumMode;
  }

  /**
   * Validate this code.
   * 
   * @return {@link EValidity#VALID} if the msg is valid,
   *         {@link EValidity#INVALID} otherwise.
   */
  @Nonnull
  protected abstract EValidity validate ();

  /**
   * Validates a UPC/EAN message.
   * 
   * @param sMsg
   *        the message to validate
   * @return {@link EValidity#VALID} if the msg is valid,
   *         {@link EValidity#INVALID} otherwise.
   */
  @Nonnull
  protected static EValidity validateMessage (@Nonnull final String sMsg)
  {
    if (sMsg == null)
      throw new NullPointerException ("msg");

    final char [] aChars = sMsg.toCharArray ();
    for (final char c : aChars)
      if (c < '0' || c > '9')
        return EValidity.INVALID;
    return EValidity.VALID;
  }

  /**
   * Calculates the check character for a given message
   * 
   * @param sMsg
   *        the message
   * @return char the check character
   */
  protected static final char calcChecksum (@Nonnull final String sMsg)
  {
    if (sMsg == null)
      throw new NullPointerException ("msg");

    final char [] aChars = sMsg.toCharArray ();
    final int nLen = aChars.length;
    int nChecksumBase = 0;
    int nFactor = (nLen % 2) == 0 ? 1 : 3;
    for (int i = 0; i < nLen; ++i)
    {
      nChecksumBase += Character.digit (aChars[i], 10) * nFactor;
      nFactor = 4 - nFactor;
    }
    final int nChecksum = (1000 - nChecksumBase) % 10;
    return Character.forDigit (nChecksum, 10);
  }
}
