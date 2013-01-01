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

/**
 * UPC-A implementation (Universal product code)
 * 
 * @author philip
 */
public final class UPCA extends AbstractUPCEAN
{
  /**
   * Constructor
   * 
   * @param sMsg
   *        The code string.
   */
  public UPCA (@Nonnull final String sMsg)
  {
    this (sMsg, DEFAULT_CHECKSUM_MODE);
  }

  /**
   * Constructor
   * 
   * @param sMsg
   *        The code string.
   * @param eMode
   *        the checksum mode
   */
  public UPCA (@Nonnull final String sMsg, @Nonnull final EEANChecksumMode eMode)
  {
    super (sMsg, eMode);
  }

  @Override
  public EValidity validate ()
  {
    return validateMessage (getMessage ());
  }

  /**
   * Validates a UPC-A message. The method throws IllegalArgumentExceptions if
   * an invalid message is passed.
   * 
   * @param sMsg
   *        the message to validate
   * @return {@link EValidity#VALID} if the msg is valid,
   *         {@link EValidity#INVALID} otherwise.
   */
  @Nonnull
  public static EValidity validateMessage (@Nonnull final String sMsg)
  {
    if (AbstractUPCEAN.validateMessage (sMsg).isValid ())
      if (sMsg.length () >= 11 || sMsg.length () <= 12)
        return EValidity.VALID;
    return EValidity.INVALID;
  }

  /**
   * Does checksum processing according to the checksum mode.
   * 
   * @param sMsg
   *        the message to process
   * @param eMode
   *        the checksum mode
   * @return the possibly modified message
   */
  public static String handleChecksum (@Nonnull final String sMsg, @Nonnull final EEANChecksumMode eMode) throws IllegalArgumentException
  {
    if (sMsg == null)
      throw new NullPointerException ("msg");
    if (eMode == null)
      throw new NullPointerException ("checksumMode");

    EEANChecksumMode eRealMode = eMode;
    if (eRealMode == EEANChecksumMode.AUTO)
    {
      if (sMsg.length () == 11)
        eRealMode = EEANChecksumMode.ADD;
      else
        if (sMsg.length () == 12)
          eRealMode = EEANChecksumMode.CHECK;
        else
        {
          // Shouldn't happen because of validateMessage
          throw new IllegalArgumentException ("Internal error");
        }
    }
    switch (eRealMode)
    {
      case ADD:
      {
        if (sMsg.length () != 11)
          throw new IllegalArgumentException ("Message must be 11 characters long");
        return sMsg + calcChecksum (sMsg);
      }
      case CHECK:
      {
        if (sMsg.length () != 12)
          throw new IllegalArgumentException ("Message must be 12 characters long");
        final char cCheck = sMsg.charAt (11);
        final char cExpected = calcChecksum (sMsg.substring (0, 11));
        if (cCheck != cExpected)
          throw new IllegalArgumentException ("Checksum is bad (" + cCheck + "). Expected: " + cExpected);
        return sMsg;
      }
      case IGNORE:
        return sMsg;
      default:
        throw new IllegalArgumentException ("Unknown checksum mode: " + eRealMode);
    }
  }
}
