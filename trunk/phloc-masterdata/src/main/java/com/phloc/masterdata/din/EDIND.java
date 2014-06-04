package com.phloc.masterdata.din;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.lang.EnumHelper;

/**
 * DIN D. Width and height are in portrait mode.<br>
 * <a href=
 * "http://www.din-formate.de/reihe-d-din-groessen-aufstellung-groessentabelle-blattmasse-werte-in-mm-dpi-pixel.html"
 * >Source</a>
 * 
 * @author Philip Helger
 */
public enum EDIND implements IDINSize
{
  D0 ("d0", 771, 1090),
  D1 ("d1", 545, 771),
  D2 ("d2", 385, 545),
  D3 ("d3", 272, 285),
  D4 ("d4", 192, 272),
  D5 ("d5", 136, 192),
  D6 ("d6", 96, 136),
  D7 ("d7", 68, 96),
  D8 ("d8", 48, 68),
  D9 ("d9", 34, 48),
  D10 ("d10", 24, 34);

  // TODO replace with CGlobal constant in phloc-commons > 4.3.3
  private static final double MM_PER_INCH = 25.4;

  private final String m_sID;
  private final int m_nWidthMM;
  private final int m_nHeightMM;

  private EDIND (@Nonnull @Nonempty final String sID, @Nonnegative final int nWidthMM, @Nonnegative final int nHeightMM)
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
  public static EDIND getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDIND.class, sID);
  }

  @Nullable
  public static EDIND getFromIDOrDefault (@Nullable final String sID, @Nullable final EDIND eDefault)
  {
    return EnumHelper.getFromIDOrDefault (EDIND.class, sID, eDefault);
  }
}
