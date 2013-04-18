package com.phloc.masterdata.ean;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.commons.string.StringHelper;

/**
 * Utility class that makes verification of GTIN numbers a bit simpler :)
 * 
 * @author philip
 */
@Immutable
public final class GTINValidator
{
  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final GTINValidator s_aInstance = new GTINValidator ();

  private GTINValidator ()
  {}

  private static boolean _containsValidChecksum (@Nonnull final char [] aChars)
  {
    final int nCalcedChecksum = AbstractUPCEAN.calcChecksum (aChars, aChars.length - 1);
    final int nChecksum = AbstractUPCEAN.asInt (aChars[aChars.length - 1]);
    return nCalcedChecksum == nChecksum;
  }

  private static boolean _isValidGTIN (@Nullable final String sGTIN, @Nonnegative final int nExpectedLength)
  {
    if (StringHelper.getLength (sGTIN) != nExpectedLength)
      return false;
    final char [] aChars = sGTIN.toCharArray ();
    if (AbstractUPCEAN.validateMessage (aChars).isInvalid ())
      return false;
    return _containsValidChecksum (aChars);
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

  public static boolean isValidGLN (@Nullable final String sGLN)
  {
    return isValidGTIN13 (sGLN);
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
