/**
 * Copyright (C) 2006-2013 phloc systems
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
package com.phloc.masterdata.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;
import com.phloc.commons.locale.LocaleCache;
import com.phloc.commons.name.IHasDisplayText;
import com.phloc.commons.string.StringHelper;

/**
 * A list of pre-selected currencies as specified in ISO 4217.<br>
 * http://en.wikipedia.org/wiki/ISO_4217
 * 
 * @author Philip Helger
 */
public enum ECurrency implements IHasID <String>, IHasDisplayText
{
  // Albanian Lek
  ALL (Currency.getInstance ("ALL"), ECurrencyName.ALL, LocaleCache.getLocale ("sq", "AL")),
  // Armenian Dram
  AMD (Currency.getInstance ("AMD"), ECurrencyName.AMD, LocaleCache.getLocale ("", "AM")),
  // Azerbaijani Manat
  AZN (Currency.getInstance ("AZN"), ECurrencyName.AZN, LocaleCache.getLocale ("", "AZ")),
  // Bosnia-Herzegovina Convertible Mark
  BAM (Currency.getInstance ("BAM"), ECurrencyName.BAM, LocaleCache.getLocale ("sr", "BA")),
  // Bulgarian Lev
  BGN (Currency.getInstance ("BGN"), ECurrencyName.BGN, LocaleCache.getLocale ("bg", "BG")),
  // Belarusian Ruble
  BYR (Currency.getInstance ("BYR"), ECurrencyName.BYR, LocaleCache.getLocale ("be", "BY")),
  // Swiss Franc
  CHF (Currency.getInstance ("CHF"), ECurrencyName.CHF, new Locale [] { LocaleCache.getLocale ("de", "CH"),
                                                                       LocaleCache.getLocale ("fr", "CH"),
                                                                       LocaleCache.getLocale ("it", "CH") }),
  // Czech Koruna
  CZK (Currency.getInstance ("CZK"), ECurrencyName.CZK, LocaleCache.getLocale ("cs", "CZ")),
  // Danish krone
  DKK (Currency.getInstance ("DKK"), ECurrencyName.DKK, LocaleCache.getLocale ("dk", "DK")),
  // Estonian Kroon (until 31.12.1020)
  // EEK (Currency.getInstance ("EEK"), ECurrencyName.EEK, LocaleCache.getLocale
  // ("et", "EE")),
  // Euro
  EUR (Currency.getInstance ("EUR"), ECurrencyName.EUR, new Locale [] { LocaleCache.getLocale ("de", "AT"),
                                                                       LocaleCache.getLocale ("fr", "BE"),
                                                                       LocaleCache.getLocale ("nl", "BE"),
                                                                       LocaleCache.getLocale ("", "CY"),
                                                                       LocaleCache.getLocale ("fi", "FI"),
                                                                       LocaleCache.getLocale ("fr", "FR"),
                                                                       LocaleCache.getLocale ("de", "DE"),
                                                                       LocaleCache.getLocale ("el", "GR"),
                                                                       LocaleCache.getLocale ("en", "IE"),
                                                                       LocaleCache.getLocale ("et", "EE"),
                                                                       LocaleCache.getLocale ("it", "IT"),
                                                                       LocaleCache.getLocale ("de", "LU"),
                                                                       LocaleCache.getLocale ("fr", "LU"),
                                                                       LocaleCache.getLocale ("", "MT"),
                                                                       LocaleCache.getLocale ("nl", "NL"),
                                                                       LocaleCache.getLocale ("pt", "PT"),
                                                                       LocaleCache.getLocale ("sk", "SK"),
                                                                       LocaleCache.getLocale ("sl", "SI"),
                                                                       LocaleCache.getLocale ("ca", "ES"),
                                                                       LocaleCache.getLocale ("es", "ES"),
                                                                       LocaleCache.getLocale ("", "AD"),
                                                                       LocaleCache.getLocale ("", "MC"),
                                                                       LocaleCache.getLocale ("sr", "CS"),
                                                                       LocaleCache.getLocale ("", "SM"),
                                                                       LocaleCache.getLocale ("", "VA") }),
  // Pound Sterling
  GBP (Currency.getInstance ("GBP"), ECurrencyName.GBP, LocaleCache.getLocale ("en", "GB")),
  // Georgian Lari
  GEL (Currency.getInstance ("GEL"), ECurrencyName.GEL, LocaleCache.getLocale ("", "GE")),
  // Croatian Kuna
  HRK (Currency.getInstance ("HRK"), ECurrencyName.HRK, LocaleCache.getLocale ("hr", "HR")),
  // Hungarian Forint
  HUF (Currency.getInstance ("HUF"), ECurrencyName.HUF, LocaleCache.getLocale ("hu", "HU")),
  // Icelandic Krona
  ISK (Currency.getInstance ("ISK"), ECurrencyName.ISK, LocaleCache.getLocale ("is", "IS")),
  // Lithuanian Litas
  LTL (Currency.getInstance ("LTL"), ECurrencyName.LTL, LocaleCache.getLocale ("lt", "LT")),
  // Latvian Lats
  LVL (Currency.getInstance ("LVL"), ECurrencyName.LVL, LocaleCache.getLocale ("lv", "LV")),
  // Moldovan Leu
  MDL (Currency.getInstance ("MDL"), ECurrencyName.MDL, LocaleCache.getLocale ("", "MD")),
  // Macedonia Denar
  MKD (Currency.getInstance ("MKD"), ECurrencyName.MKD, LocaleCache.getLocale ("mk", "MK")),
  // Norwegian Krone
  NOK (Currency.getInstance ("NOK"), ECurrencyName.NOK, LocaleCache.getLocale ("no", "NO")),
  // Polish Zloty (new)
  PLN (Currency.getInstance ("PLN"), ECurrencyName.PLN, LocaleCache.getLocale ("pl", "PL")),
  // Romanian Leu
  RON (Currency.getInstance ("RON"), ECurrencyName.RON, LocaleCache.getLocale ("ro", "RO")),
  // Serbian dinar
  RSD (Currency.getInstance ("RSD"), ECurrencyName.RSD, LocaleCache.getLocale ("sr", "RS")),
  // Russian Ruble
  RUB (Currency.getInstance ("RUB"), ECurrencyName.RUB, LocaleCache.getLocale ("ru", "RU")),
  // Swedish Krona
  SEK (Currency.getInstance ("SEK"), ECurrencyName.SEK, LocaleCache.getLocale ("sv", "SE")),
  // New Turkish Lira
  TRY (Currency.getInstance ("TRY"), ECurrencyName.TRY, LocaleCache.getLocale ("tr", "TR")),
  // Ukrainian Hryvnia
  UAH (Currency.getInstance ("UAH"), ECurrencyName.UAH, LocaleCache.getLocale ("uk", "UA")),
  // United States Dollar
  USD (Currency.getInstance ("USD"), ECurrencyName.USD, LocaleCache.getLocale ("en", "US"));

  /** The rounding mode to be used for currency values */
  public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

  /** The default currency */
  public static final ECurrency DEFAULT_CURRENCY = EUR;

  private final Currency m_aCurrency;
  private final IHasDisplayText m_aName;
  private final List <Locale> m_aCountries = new ArrayList <Locale> ();
  private final DecimalFormat m_aCurrencyFormat;
  private final DecimalFormat m_aValueFormat;

  private ECurrency (@Nonnull final Currency aCurrency,
                     @Nonnull final ECurrencyName aName,
                     @Nonnull final Locale... aCountries)
  {
    if (aCurrency == null)
      throw new NullPointerException ("currency");
    if (aName == null)
      throw new NullPointerException ("name");
    if (aCountries == null)
      throw new NullPointerException ("countries");

    for (final Locale aCountry : aCountries)
    {
      if (aCountry == null)
        throw new IllegalArgumentException ("Passed country is unknown!");
      if (!CurrencyUtils.localeSupportsCurrencyRetrieval (aCountry))
        throw new IllegalArgumentException ("Passed country " + aCountry + " does not support currency retrieval!");
      if (!m_aCountries.add (aCountry))
        throw new IllegalArgumentException ("The country " + aCountry.getCountry () + " is contained more than once.");
    }
    if (m_aCountries.isEmpty ())
      throw new IllegalArgumentException ("Passed currency is not valid in a single country!");

    m_aCurrency = aCurrency;
    m_aName = aName;

    // Always use the first locale as the most relevant one!
    final Locale aRelevantLocale = ContainerHelper.getFirstElement (m_aCountries);

    // Note: Locale fr_FR formats locale with a trailing € whereas the locale
    // de_DE formats the € at front!
    m_aCurrencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance (aRelevantLocale);

    // Extract value pattern from currency pattern (without currency symbol)
    final String sValuePattern = m_aCurrencyFormat.toPattern ()
                                                  .replace ("\u00A4 ", "")
                                                  .replace (" \u00A4", "")
                                                  .replace ("\u00A4", "");

    // Use the decimal symbols from the currency format
    m_aValueFormat = new DecimalFormat (sValuePattern, m_aCurrencyFormat.getDecimalFormatSymbols ());
  }

  /**
   * @return The currency code of this currency used as the ID.
   */
  @Nonnull
  public String getID ()
  {
    return m_aCurrency.getCurrencyCode ();
  }

  public String getDisplayText (final Locale aContentLocale)
  {
    return m_aName.getDisplayText (aContentLocale);
  }

  @Nonnull
  public String getCurrencySymbol ()
  {
    return m_aCurrencyFormat.getDecimalFormatSymbols ().getCurrencySymbol ();
  }

  /**
   * @return this as {@link java.util.Currency}.
   * @deprecated Use {@link #getAsCurrency()} instead
   */
  @Deprecated
  @Nonnull
  public Currency asCurrency ()
  {
    return getAsCurrency ();
  }

  /**
   * @return this as {@link java.util.Currency}.
   */
  @Nonnull
  public Currency getAsCurrency ()
  {
    return m_aCurrency;
  }

  /**
   * @return An unmodifiable list of all countries (as {@link Locale} objects)
   *         to which this currency applies.
   */
  @Nonnull
  @ReturnsImmutableObject
  public List <Locale> getAllMatchingCountries ()
  {
    return ContainerHelper.makeUnmodifiable (m_aCountries);
  }

  /**
   * @return The {@link DecimalFormat} used to format this currency. Always
   *         returns a copy of the contained formatter for thread-safety and
   *         modification.
   */
  @Nonnull
  public DecimalFormat getCurrencyFormat ()
  {
    // DecimalFormat is not thread safe - clone!
    return (DecimalFormat) m_aCurrencyFormat.clone ();
  }

  @Nonnull
  public String getCurrencyFormatted (@Nonnull final BigDecimal aValue)
  {
    return getCurrencyFormat ().format (aValue);
  }

  @Nonnull
  public String getCurrencyFormatted (@Nonnull final BigDecimal aValue, @Nonnegative final int nFractionDigits)
  {
    final DecimalFormat aFormat = getCurrencyFormat ();
    aFormat.setMaximumFractionDigits (nFractionDigits);
    return aFormat.format (aValue);
  }

  /**
   * @return The {@link DecimalFormat} object that formats an object like the
   *         {@link #getCurrencyFormat()} but without the currency sign. Always
   *         returns a copy of the contained formatter for thread-safety and
   *         modification.
   */
  @Nonnull
  public DecimalFormat getValueFormat ()
  {
    // DecimalFormat is not thread safe - clone!
    return (DecimalFormat) m_aValueFormat.clone ();
  }

  @Nonnull
  public String getValueFormatted (@Nonnull final BigDecimal aValue)
  {
    return getValueFormat ().format (aValue);
  }

  @Nonnull
  public String getValueFormatted (@Nonnull final BigDecimal aValue, @Nonnegative final int nFractionDigits)
  {
    final DecimalFormat aFormat = getValueFormat ();
    aFormat.setMaximumFractionDigits (nFractionDigits);
    return aFormat.format (aValue);
  }

  /**
   * @return The minimum fraction digits to be used for formatting.
   */
  @Nonnegative
  public int getMinimumFractionDigits ()
  {
    return m_aCurrencyFormat.getMinimumFractionDigits ();
  }

  /**
   * Set the minimum fraction digits to be used for formatting. Applies to the
   * currency-formatting and the value-formatting.
   * 
   * @param nDecimals
   *        The new minimum fraction digits. May not be negative.
   */
  public void setMinimumFractionDigits (@Nonnegative final int nDecimals)
  {
    if (nDecimals < 0)
      throw new IllegalArgumentException ("Decimals may not be negative: " + nDecimals);
    m_aCurrencyFormat.setMinimumFractionDigits (nDecimals);
    m_aValueFormat.setMinimumFractionDigits (nDecimals);
  }

  /**
   * Adopt the passed text value according to the requested decimal separator.
   * 
   * @param sTextValue
   *        The text to be manipulated. May be <code>null</code>.
   * @param eDecimalSep
   *        The decimal separator that is required. May not be <code>null</code>
   *        .
   * @return The manipulated text so that it matches the required decimal
   *         separator or the original text
   */
  @Nullable
  private static String _getTextValueForDecimalSeparator (@Nullable final String sTextValue,
                                                          @Nonnull final EDecimalSeparator eDecimalSep)
  {
    final String ret = StringHelper.trim (sTextValue);
    if (ret != null)
      switch (eDecimalSep)
      {
        case COMMA:
        {
          // Decimal separator is a ","
          if (ret.indexOf ('.') > -1)
          {
            // Currency expects "," but user passed "."
            return ret.replace ('.', eDecimalSep.getChar ());
          }
          break;
        }
        case POINT:
        {
          // Decimal separator is a "."
          if (ret.indexOf (',') > -1)
          {
            // Pattern contains no "," but value contains ","
            return ret.replace (',', eDecimalSep.getChar ());
          }
          break;
        }
        default:
          throw new IllegalStateException ("Unexpected decimal separator [" + eDecimalSep + "]");
      }
    return ret;
  }

  /**
   * Try to parse a string value formatted by the {@link NumberFormat} object
   * returned from {@link #getCurrencyFormat()}. E.g. <code>5,00 &euro;</code>
   * 
   * @param sTextValue
   *        The string value.
   * @param aDefault
   *        The default value to be used in case parsing fails. May be
   *        <code>null</code>.
   * @return The {@link BigDecimal} value matching the string value.
   */
  @Nullable
  public BigDecimal parseCurrencyFormat (@Nullable final String sTextValue, @Nullable final BigDecimal aDefault)
  {
    final DecimalFormat aCurrencyFormat = getCurrencyFormat ();

    // Adopt the decimal separator
    final EDecimalSeparator eDecSep = EDecimalSeparator.getFromCharOrNull (aCurrencyFormat.getDecimalFormatSymbols ()
                                                                                          .getDecimalSeparator ());
    final String sRealTextValue = _getTextValueForDecimalSeparator (sTextValue, eDecSep);
    return CurrencyUtils.parseCurrency (sRealTextValue, aCurrencyFormat, aDefault);
  }

  /**
   * Try to parse a string value formatted by the {@link DecimalFormat} object
   * returned from {@link #getValueFormat()}
   * 
   * @param sTextValue
   *        The string value.
   * @param aDefault
   *        The default value to be used in case parsing fails. May be
   *        <code>null</code>.
   * @return The {@link BigDecimal} value matching the string value.
   */
  @Nullable
  public BigDecimal parseValueFormat (@Nullable final String sTextValue, @Nullable final BigDecimal aDefault)
  {
    final DecimalFormat aValueFormat = getValueFormat ();

    // Adopt the decimal separator
    final EDecimalSeparator eDecSep = EDecimalSeparator.getFromCharOrNull (aValueFormat.getDecimalFormatSymbols ()
                                                                                       .getDecimalSeparator ());
    final String sRealTextValue = _getTextValueForDecimalSeparator (sTextValue, eDecSep);
    return CurrencyUtils.parseCurrency (sRealTextValue, aValueFormat, aDefault);
  }

  /**
   * Special currency division method. This method solves the problem of
   * dividing "1/3" as it would result in a never ending series of
   * "0.33333333..." which results in an {@link ArithmeticException} thrown by
   * the divide method!
   * 
   * @param aDividend
   *        Dividend
   * @param aDivisor
   *        Divisor
   * @return The divided value with the correct scaling
   */
  @Nonnull
  @CheckReturnValue
  public BigDecimal getDivided (@Nonnull final BigDecimal aDividend, @Nonnull final BigDecimal aDivisor)
  {
    return aDividend.divide (aDivisor, m_aCurrency.getDefaultFractionDigits (), ROUNDING_MODE);
  }

  /**
   * Get the passed value rounded to the appropriate number of fraction digits,
   * based on this currencies default fraction digits.
   * 
   * @param aValue
   *        The value to be rounded.
   * @return The rounded value.
   */
  @Nonnull
  public BigDecimal getRounded (@Nonnull final BigDecimal aValue)
  {
    return aValue.setScale (m_aCurrency.getDefaultFractionDigits (), ROUNDING_MODE);
  }

  @Nonnull
  public static BigDecimal getRounded (@Nonnull final BigDecimal aValue, final int nFractionDigits)
  {
    return aValue.setScale (nFractionDigits, ROUNDING_MODE);
  }

  @Nullable
  public static ECurrency getFromIDOrNull (@Nullable final String sCurrencyCode)
  {
    return EnumHelper.getFromIDOrNull (ECurrency.class, sCurrencyCode);
  }

  @Nullable
  public static ECurrency getFromIDOrDefault (@Nullable final String sCurrencyCode, @Nullable final ECurrency eDefault)
  {
    return EnumHelper.getFromIDOrDefault (ECurrency.class, sCurrencyCode, eDefault);
  }
}
