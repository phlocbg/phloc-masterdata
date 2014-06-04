package com.phloc.masterdata.din;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.lang.EnumHelper;

/**
 * DIN B. Width and height are in portrait mode.<br>
 * <a href=
 * "http://www.din-formate.de/reihe-b-din-groessen-uebersicht-auflistung-blattgroesse-masse-in-pixel-mm-dpi.html"
 * >Source</a>
 * 
 * @author Philip Helger
 */
public enum EDINB implements IDINSize
{
  B0 ("b0", 1000, 1414),
  B1 ("b1", 707, 1000),
  B2 ("b2", 500, 707),
  B3 ("b3", 353, 500),
  B4 ("b4", 250, 353),
  B5 ("b5", 176, 250),
  B6 ("b6", 125, 176),
  B7 ("b7", 88, 125),
  B8 ("b8", 62, 88),
  B9 ("b9", 44, 62),
  B10 ("b10", 31, 44);

  // TODO replace with CGlobal constant in phloc-commons > 4.3.3
  private static final double MM_PER_INCH = 25.4;

  private final String m_sID;
  private final int m_nWidthMM;
  private final int m_nHeightMM;

  private EDINB (@Nonnull @Nonempty final String sID, @Nonnegative final int nWidthMM, @Nonnegative final int nHeightMM)
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
  public static EDINB getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDINB.class, sID);
  }

  @Nullable
  public static EDINB getFromIDOrDefault (@Nullable final String sID, @Nullable final EDINB eDefault)
  {
    return EnumHelper.getFromIDOrDefault (EDINB.class, sID, eDefault);
  }
}
