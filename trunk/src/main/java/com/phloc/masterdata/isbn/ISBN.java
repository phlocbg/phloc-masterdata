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
package com.phloc.masterdata.isbn;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.regex.RegExHelper;

/**
 * Helper class for validating ISBN numbers.
 * 
 * @author philip
 */
@Immutable
public final class ISBN
{
  private ISBN ()
  {}

  public static boolean isValidISBN10Number (@Nullable final String sValue)
  {
    if (sValue == null || sValue.length () != 10)
      return false;

    // Value needs to be fully numeric with a trailing 'X'
    if (!RegExHelper.stringMatchesPattern ("^[0-9]+[0-9X]?$", sValue))
      return false;

    // calc checksum
    int nRes = 0;
    for (int i = 0; i < 9; i++)
    {
      final int j = Character.digit (sValue.charAt (i), 10);
      nRes += ((j * (10 - i)) % 11);
    }
    nRes = 11 - (nRes % 11);
    final char cChkSum = (nRes == 10 ? 'X' : Character.forDigit (nRes, 10));
    return cChkSum == sValue.charAt (9);
  }

  public static boolean isValidISBN13Number (@Nullable final String sValue)
  {
    if (sValue == null || sValue.length () != 13)
      return false;
    // Value needs to be fully numeric
    if (!RegExHelper.stringMatchesPattern ("^[0-9]+$", sValue))
      return false;

    // calc checksum
    int nRes = 0;
    for (int i = 0; i < 12; i++)
    {
      final int j = Character.digit (sValue.charAt (i), 10);
      final int k = 1 + ((i % 2) * 2);
      nRes += ((j * k) % 10);
    }
    nRes = (10 - (nRes % 10)) % 10;
    return Character.forDigit (nRes, 10) == sValue.charAt (12);
  }

  public static boolean isValidISBNNumber (@Nullable final String sValue)
  {
    return isValidISBN10Number (sValue) || isValidISBN13Number (sValue);
  }
}
