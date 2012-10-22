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

import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.phloc.commons.regex.RegExPool;
import com.phloc.commons.string.StringHelper;

/**
 * This class manages the SWIFT Bank Identification Code (BIC).
 * 
 * @author philip
 */
public final class BICManager
{
  private static final Pattern s_aBICPattern = RegExPool.getPattern ("(?i)^[a-z]{6}[a-z0-9]{2}([a-z0-9]{3})?$");

  private BICManager ()
  {}

  /**
   * Check if the passed BIC is valid.<br>
   * 
   * <pre>
   * BBBB  4-stelliger Bankcode, vom Geldinstitut frei wählbar (nur Alphazeichen)
   *  CC    2-stelliger Ländercode nach ISO 3166-1 (nur Alphazeichen)
   *  LL    2-stellige Codierung des Ortes (alphanumerische Zeichen; wenn das zweite
   *        Zeichen eine 1 ist, so handelt es sich um einen passiven SWIFT-Teilnehmer;
   *        wenn das zweite Zeichen eine 0 ist, so handelt es sich um einen Test-BIC)
   *  bbb   3-stellige Kennzeichnung (Branch-Code) der Filiale oder Abteilung (optional,
   *        Standard: <code>XX X</code> (ohne Leerzeichen!), kann weggelassen werden,
   *        andere Kennzeichen nicht) (alphanumerische Zeichen)
   * </pre>
   * 
   * @param sBIC
   *        The BIC to check. May be <code>null</code>.
   * @return <code>true</code> if the passed BIC is valid, <code>false</code>
   *         otherwise.
   */
  public static boolean isValidBIC (@Nullable final String sBIC)
  {
    if (StringHelper.hasNoText (sBIC))
      return false;
    return s_aBICPattern.matcher (sBIC).matches ();
  }

  public static boolean isPassiveBICParticipant (@Nullable final String sBIC)
  {
    return isValidBIC (sBIC) && sBIC.charAt (7) == '1';
  }

  /**
   * Determine whether the passed BIC is a test-only BIC.
   * 
   * @param sBIC
   *        The BIC to be tested.
   * @return <code>true</code> if the passed BIC is a test BIC,
   *         <code>false</code> otherwise.
   */
  public static boolean isTestBIC (@Nullable final String sBIC)
  {
    return isValidBIC (sBIC) && sBIC.charAt (7) == '0';
  }
}
