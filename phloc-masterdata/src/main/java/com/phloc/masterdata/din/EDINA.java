package com.phloc.masterdata.din;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.lang.EnumHelper;

/**
 * DIN A. Width and height are in portrait mode.<br>
 * <a href="http://www.din-formate.de/reihe-a-din-groessen-mm-pixel-dpi.html">
 * Source</a>
 * 
 * @author Philip Helger
 */
public enum EDINA implements IDINSize
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

  // TODO replace with CGlobal constant in phloc-commons > 4.3.3
  private static final double MM_PER_INCH = 25.4;

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
    return nDPI * m_nWidthMM / MM_PER_INCH;
  }

  @Nonnegative
  public long getWidthPixelLong (@Nonnegative final int nDPI)
  {
    return Math.round (getWidthPixel (nDPI));
  }

  @Nonnegative
  public double getWidthDPI (@Nonnegative final int nPixel)
  {
    return nPixel * MM_PER_INCH / m_nWidthMM;
  }

  @Nonnegative
  public long getWidthDPILong (@Nonnegative final int nPixel)
  {
    return Math.round (getWidthDPI (nPixel));
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
    return nDPI * m_nHeightMM / MM_PER_INCH;
  }

  @Nonnegative
  public long getHeightPixelLong (@Nonnegative final int nDPI)
  {
    return Math.round (getHeightPixel (nDPI));
  }

  @Nonnegative
  public double getHeightDPI (@Nonnegative final int nPixel)
  {
    return nPixel * MM_PER_INCH / m_nHeightMM;
  }

  @Nonnegative
  public long getHeightDPILong (@Nonnegative final int nPixel)
  {
    return Math.round (getHeightDPI (nPixel));
  }

  @Nullable
  public static IDINSize getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDINA.class, sID);
  }

  @Nullable
  public static IDINSize getFromIDOrDefault (@Nullable final String sID, @Nullable final EDINA eDefault)
  {
    return EnumHelper.getFromIDOrDefault (EDINA.class, sID, eDefault);
  }
}
