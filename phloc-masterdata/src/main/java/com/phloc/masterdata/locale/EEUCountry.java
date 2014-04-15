package com.phloc.masterdata.locale;

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;
import com.phloc.commons.locale.country.CountryCache;
import com.phloc.datetime.PDTFactory;

public enum EEUCountry implements IHasID <String>
{
  BELGIUM ("BE", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1952)),
  BULGARIA ("BG", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2007)),
  DENMARK ("DK", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1973)),
  GERMANY ("DE", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1952)),
  ESTONIA ("EE", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  FINLAND ("FI", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1995)),
  FRANCE ("FR", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1952)),
  GREECE ("GR", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1981)),
  IRELAND ("IE", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1973)),
  ITALY ("IT", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1952)),
  CROATIA ("HR", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2013)),
  LATVIA ("LV", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  LITHUANIA ("LT", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  LUXEMBOURG ("LU", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1952)),
  MALTA ("MT", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  NETHERLANDS ("NL", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1952)),
  AUSTRIA ("AT", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1995)),
  POLAND ("PL", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  PORTUGAL ("PT", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1986)),
  ROMANIA ("RO", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2007)),
  SWEDEN ("SE", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1995)),
  SLOVAKIA ("SK", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  SLOVENIA ("SI", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  SPAIN ("ES", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1986)),
  CZECH_REPUBLIC ("CZ", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  HUNGARY ("HU", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004)),
  UNITED_KINGDOM ("GB", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 1973)),
  CYPRUS ("CY", PDTFactory.createLocalDate (1, DateTimeConstants.JANUARY, 2004));

  private final String m_sCountryCode;
  private final Locale m_aCountry;
  private final LocalDate m_aJoinDate;

  /**
   * @param sCountryCode
   *        Country code
   * @param aJoinDate
   *        Join date
   */
  private EEUCountry (@Nonnull @Nonempty final String sCountryCode, @Nonnull final LocalDate aJoinDate)
  {
    m_sCountryCode = sCountryCode;
    m_aCountry = CountryCache.getCountry (sCountryCode);
    if (m_aCountry == null)
      throw new IllegalStateException ("Failed to resolve country '" + sCountryCode + "'");
    m_aJoinDate = aJoinDate;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sCountryCode;
  }

  @Nonnull
  @Nonempty
  public String getCountryCode ()
  {
    return m_sCountryCode;
  }

  @Nonnull
  public Locale getCountry ()
  {
    return m_aCountry;
  }

  @Nonnull
  public LocalDate getJoinDate ()
  {
    return m_aJoinDate;
  }

  @Nonnegative
  public int getJoinYear ()
  {
    return m_aJoinDate.getYear ();
  }

  @Nullable
  public static EEUCountry getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EEUCountry.class, sID);
  }

  @Nullable
  public static EEUCountry getFromLocalOrNull (@Nullable final Locale aLocale)
  {
    return aLocale == null ? null : getFromIDOrNull (aLocale.getCountry ());
  }

  public static boolean isEUCountry (@Nullable final Locale aLocale)
  {
    return getFromLocalOrNull (aLocale) != null;
  }
}
