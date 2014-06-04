package com.phloc.masterdata.din;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.lang.EnumHelper;

/**
 * DIN C. Width and height are in portrait mode.<br>
 * <a href=
 * "http://www.din-formate.de/reihe-c-din-groessen-liste-papierformate-seitengroesse-masseinheiten-in-dpi-mm-pixel.html"
 * >Source</a>
 * 
 * @author Philip Helger
 */
public enum EDINC implements IDINSize
{
  C0 ("c0", 917, 1297),
  C1 ("c1", 648, 917),
  C2 ("c2", 458, 648),
  C3 ("c3", 324, 458),
  C4 ("c4", 229, 324),
  C5 ("c5", 162, 229),
  C6 ("c6", 114, 162),
  C7 ("c7", 81, 114),
  C8 ("c8", 57, 81),
  C9 ("c9", 40, 57),
  C10 ("c10", 28, 40);

  // TODO replace with CGlobal constant in phloc-commons > 4.3.3
  private static final double MM_PER_INCH = 25.4;

  private final String m_sID;
  private final int m_nWidthMM;
  private final int m_nHeightMM;

  private EDINC (@Nonnull @Nonempty final String sID, @Nonnegative final int nWidthMM, @Nonnegative final int nHeightMM)
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
  public static EDINC getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EDINC.class, sID);
  }

  @Nullable
  public static EDINC getFromIDOrDefault (@Nullable final String sID, @Nullable final EDINC eDefault)
  {
    return EnumHelper.getFromIDOrDefault (EDINC.class, sID, eDefault);
  }
}
