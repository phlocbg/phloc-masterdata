package com.phloc.masterdata.din;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;

/**
 * DIN A. Width and height are in portrait mode.<br>
 * Source: http://www.din-formate.de/reihe-a-din-groessen-mm-pixel-dpi.html
 * 
 * @author Philip Helger
 */
public enum EDINA implements IHasID <String>
{
  A0 ("a0", 841, 1189),
  A1 ("a1", 594, 841),
  A2 ("a2", 420, 594),
  A3 ("a3", 297, 420),
  A4 ("a4", 210, 297),
  A5 ("a5", 148, 210),
  A6 ("a6", 105, 148),
  A7 ("a7", 74, 105),
  A8 ("a8", 52, 74),
  A9 ("a9", 37, 52),
  A10 ("a10", 26, 37);

  private static final double CM_PER_INCH = 2.54;

  private final String m_sID;
  private final int m_nWidthMM;
  private final int m_nHeightMM;

  private EDINA (@Nonnull @Nonempty final String sID, @Nonnegative final int nWidthMM, @Nonnegative final int nHeightMM)
  {
    m_sID = sID;
    m_nWidthMM = nWidthMM;
    m_nHeightMM = nHeightMM;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnegative
  public int getWidthMM ()
  {
    return m_nWidthMM;
  }

  @Nonnegative
  public double getWidthCM ()
  {
    return m_nWidthMM / 10.0;
  }

  @Nonnegative
  public double getWidthPixel (@Nonnegative final int nDPI)
  {
    return nDPI * getWidthCM () / CM_PER_INCH;
  }

  @Nonnegative
  public double getWidthDPI (@Nonnegative final int nPixel)
  {
    return nPixel * CM_PER_INCH / getWidthCM ();
  }

  @Nonnegative
  public int getHeightMM ()
  {
    return m_nHeightMM;
  }

  @Nonnegative
  public double getHeightCM ()
  {
    return m_nHeightMM / 10.0;
  }

  @Nonnegative
  public double getHeightPixel (@Nonnegative final int nDPI)
  {
    return nDPI * getHeightCM () / CM_PER_INCH;
  }

  @Nonnegative
  public double getHeightDPI (@Nonnegative final int nPixel)
  {
    return nPixel * CM_PER_INCH / getHeightCM ();
  }

  @Nullable
  public static EDINA getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDINA.class, sID);
  }
}
