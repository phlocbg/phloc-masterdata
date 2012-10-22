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

    final int nLen = sMsg.length ();
    for (int i = 0; i < nLen; i++)
    {
      final char c = sMsg.charAt (i);
      if (c < '0' || c > '9')
        return EValidity.INVALID;
    }
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

    int nOddSum = 0;
    int nEvenSum = 0;
    final int nLength = sMsg.length ();
    for (int i = nLength - 1; i >= 0; i--)
    {
      if ((nLength - i) % 2 == 0)
        nEvenSum += Character.digit (sMsg.charAt (i), 10);
      else
        nOddSum += Character.digit (sMsg.charAt (i), 10);
    }
    int nCheck = 10 - ((nEvenSum + 3 * nOddSum) % 10);
    if (nCheck >= 10)
      nCheck = 0;
    return Character.forDigit (nCheck, 10);
  }
}
