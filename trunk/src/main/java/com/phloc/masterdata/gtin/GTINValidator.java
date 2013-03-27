package com.phloc.masterdata.gtin;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.commons.string.StringHelper;

@Immutable
public final class GTINValidator
{
  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final GTINValidator s_aInstance = new GTINValidator ();

  private GTINValidator ()
  {}

  private static boolean _isNumeric (@Nonnull final char [] aChars)
  {
    for (final char c : aChars)
      if (!Character.isDigit (c))
        return false;
    return true;
  }

  private static int _getDigit (final char c)
  {
    return Character.digit (c, 10);
  }

  private static int _calcChecksumBase (@Nonnull final char [] aChars, @Nonnegative final int nLen)
  {
    int nChecksumBase = 0;
    int nFactor = (nLen % 2) == 0 ? 1 : 3;
    for (int i = 0; i < nLen; ++i)
    {
      nChecksumBase += _getDigit (aChars[i]) * nFactor;
      nFactor = 4 - nFactor;
    }
    return nChecksumBase;
  }

  private static boolean _isValidChecksum (@Nonnull final char [] aChars)
  {
    final int nChecksumBase = _calcChecksumBase (aChars, aChars.length - 1);
    final int nChecksum = _getDigit (aChars[aChars.length - 1]);
    // 1000 is larger as "18*9*3" (18 == length of SSCC; 9 == largest possible
    // number; 3 == largest multiplication factor)
    return (1000 - nChecksumBase) % 10 == nChecksum;
  }

  private static boolean _isValidGTIN (@Nullable final String sGTIN, @Nonnegative final int nExpectedLength)
  {
    if (StringHelper.getLength (sGTIN) != nExpectedLength)
      return false;
    final char [] aChars = sGTIN.toCharArray ();
    if (!_isNumeric (aChars))
      return false;
    return _isValidChecksum (aChars);
  }

  public static boolean isValidGTIN8 (@Nullable final String sGTIN8)
  {
    return _isValidGTIN (sGTIN8, 8);
  }

  public static boolean isValidGTIN12 (@Nullable final String sGTIN12)
  {
    return _isValidGTIN (sGTIN12, 12);
  }

  public static boolean isValidGTIN13 (@Nullable final String sGTIN13)
  {
    return _isValidGTIN (sGTIN13, 13);
  }

  public static boolean isValidGTIN14 (@Nullable final String sGTIN14)
  {
    return _isValidGTIN (sGTIN14, 14);
  }

  public static boolean isValidSSCC (@Nullable final String sSSCC)
  {
    return _isValidGTIN (sSSCC, 18);
  }
}
