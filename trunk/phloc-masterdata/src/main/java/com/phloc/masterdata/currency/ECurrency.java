/**
 * Copyright (C) 2006-2014 phloc systems
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
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.CodingStyleguideUnaware;
import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.annotations.ReturnsMutableCopy;
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
@NotThreadSafe
public enum ECurrency implements IHasID <String>, IHasDisplayText
{
  // Afghanistan Afghani
  AFN (Currency.getInstance ("AFN"), ECurrencyName.AFN, "_AF"),
  // Albanian Lek
  ALL (Currency.getInstance ("ALL"), ECurrencyName.ALL, "_AL", "sq_AL"),
  // Armenian Dram
  AMD (Currency.getInstance ("AMD"), ECurrencyName.AMD, "_AM"),
  // Angolan Kwanza
  AOA (Currency.getInstance ("AOA"), ECurrencyName.AOA, "_AO"),
  // Argentine Peso
  ARS (Currency.getInstance ("ARS"), ECurrencyName.ARS, "_AR", "es_AR"),
  // Azerbaijani Manat
  AZN (Currency.getInstance ("AZN"), ECurrencyName.AZN, "_AZ"),
  // Bosnia-Herzegovina Convertible Mark
  BAM (Currency.getInstance ("BAM"), ECurrencyName.BAM, "_BA", "sr_BA"),
  // Bulgarian Lev
  BGN (Currency.getInstance ("BGN"), ECurrencyName.BGN, "_BG", "bg_BG"),
  // Belarusian Ruble
  BYR (Currency.getInstance ("BYR"), ECurrencyName.BYR, "_BY", "be_BY"),
  // Swiss Franc
  CHF (Currency.getInstance ("CHF"), ECurrencyName.CHF, "_CH", "_LI", "de_CH", "fr_CH", "it_CH"),
  // Czech Koruna
  CZK (Currency.getInstance ("CZK"), ECurrencyName.CZK, "_CZ", "cs_CZ"),
  // Danish krone
  DKK (Currency.getInstance ("DKK"), ECurrencyName.DKK, "_DK", "_FO", "_GL", "da_DK"),
  // Algerian Dinar
  DZD (Currency.getInstance ("DZD"), ECurrencyName.DZD, "_DZ", "ar_DZ"),
  // Estonian Kroon (until 31.12.2010)
  @Deprecated
  EEK (Currency.getInstance ("EEK"), true, ECurrencyName.EEK, "_EE", "et_EE"),
  // Egyptian Pound
  EGP (Currency.getInstance ("EGP"), ECurrencyName.EGP, "_EG", "ar_EG"),
  // Ethiopian Birr
  ETB (Currency.getInstance ("ETB"), ECurrencyName.ETB, "_ET"),
  // Euro
  EUR (Currency.getInstance ("EUR"), ECurrencyName.EUR, "_AD", "_AT", "_AX", "_BE", "_BL", "_CY", "_DE", "_EE", "_ES", "_FI", "_FR", "_GF", "_GP", "_GR", "_IE", "_IT", "_LU", "_MC", "_ME", "_MF", "_MQ", "_MT", "_NL", "_PM", "_PT", "_RE", "_SI", "_SK", "_SM", "_TF", "_VA", "_YT", "ca_ES", "de_AT", "de_DE", "de_LU", "el_CY", "el_GR", "en_IE", "en_MT", "es_ES", "et_EE", "fi_FI", "fr_BE", "fr_FR", "fr_LU", "ga_IE", "it_IT", "mt_MT", "nl_BE", "nl_NL", "pt_PT", "sk_SK", "sl_SI", "sr_ME"),
  // Pound Sterling
  GBP (Currency.getInstance ("GBP"), ECurrencyName.GBP, "_GB", "_GG", "_GS", "_IM", "_JE", "en_GB"),
  // Georgian Lari
  GEL (Currency.getInstance ("GEL"), ECurrencyName.GEL, "_GE"),
  // Croatian Kuna
  HRK (Currency.getInstance ("HRK"), ECurrencyName.HRK, "_HR", "hr_HR"),
  // Hungarian Forint
  HUF (Currency.getInstance ("HUF"), ECurrencyName.HUF, "_HU", "hu_HU"),
  // Icelandic Krona
  ISK (Currency.getInstance ("ISK"), ECurrencyName.ISK, "_IS", "is_IS"),
  // Lithuanian Litas
  LTL (Currency.getInstance ("LTL"), ECurrencyName.LTL, "_LT", "lt_LT"),
  // Latvian Lats
  LVL (Currency.getInstance ("LVL"), ECurrencyName.LVL, "_LV", "lv_LV"),
  // Moldovan Leu
  MDL (Currency.getInstance ("MDL"), ECurrencyName.MDL, "_MD"),
  // Macedonia Denar
  MKD (Currency.getInstance ("MKD"), ECurrencyName.MKD, "_MK", "mk_MK"),
  // Norwegian Krone
  NOK (Currency.getInstance ("NOK"), ECurrencyName.NOK, "_BV", "_NO", "_SJ", "no_NO", "no_NO_NY"),
  // Polish Zloty (new)
  PLN (Currency.getInstance ("PLN"), ECurrencyName.PLN, "_PL", "pl_PL"),
  // Romanian Leu
  RON (Currency.getInstance ("RON"), ECurrencyName.RON, "_RO", "ro_RO"),
  // Serbian dinar
  RSD (Currency.getInstance ("RSD"), ECurrencyName.RSD, "_RS", "sr_RS"),
  // Russian Ruble
  RUB (Currency.getInstance ("RUB"), ECurrencyName.RUB, "_RU", "ru_RU"),
  // Swedish Krona
  SEK (Currency.getInstance ("SEK"), ECurrencyName.SEK, "_SE", "sv_SE"),
  // New Turkish Lira
  TRY (Currency.getInstance ("TRY"), ECurrencyName.TRY, "_TR", "tr_TR"),
  // Ukrainian Hryvnia
  UAH (Currency.getInstance ("UAH"), ECurrencyName.UAH, "_UA", "uk_UA"),
  // United States Dollar
  USD (Currency.getInstance ("USD"), ECurrencyName.USD, "_AS", "_BQ", "_EC", "_FM", "_GU", "_IO", "_MH", "_MP", "_PR", "_PW", "_TC", "_TL", "_UM", "_US", "_VG", "_VI", "en_US", "es_EC", "es_PR", "es_US"),
  // CFA Franc BEAC
  XAF (Currency.getInstance ("XAF"), ECurrencyName.XAF, "_CF", "_CG", "_CM", "_GA", "_GQ", "_TD");

  /**
   * The default rounding mode to be used for currency values. It may be
   * overridden for each currency individually.
   */
  public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;

  /**
   * Deprecated - use the {@link #getRoundingMode()} method per currency
   * instead!
   */
  @Deprecated
  public static final RoundingMode ROUNDING_MODE = DEFAULT_ROUNDING_MODE;

  /** The default currency */
  public static final ECurrency DEFAULT_CURRENCY = EUR;

  private final Currency m_aCurrency;
  private final boolean m_bIsDeprecated;
  private final IHasDisplayText m_aName;
  private final List <Locale> m_aLocales = new ArrayList <Locale> ();
  private final DecimalFormat m_aCurrencyFormat;
  private final String m_sCurrencyPattern;
  private final String m_sValuePattern;
  private final DecimalFormat m_aValueFormat;
  @CodingStyleguideUnaware
  private RoundingMode m_eRoundingMode;

  @Nonnull
  @ReturnsMutableCopy
  private static List <Locale> _getAsLocales (@Nonnull final String... aCountries)
  {
    final List <Locale> ret = new ArrayList <Locale> ();
    for (final String sCountry : aCountries)
      ret.add (LocaleCache.getLocale (sCountry));
    return ret;
  }

  private ECurrency (@Nonnull final Currency aCurrency,
                     @Nonnull final ECurrencyName aName,
                     @Nonnull final String... aLocales)
  {
    this (aCurrency, false, aName, aLocales);
  }

  private ECurrency (@Nonnull final Currency aCurrency,
                     final boolean bIsDeprecated,
                     @Nonnull final ECurrencyName aName,
                     @Nonnull final String... aLocales)
  {
    ValueEnforcer.notNull (aCurrency, "Currency");
    ValueEnforcer.notNull (aName, "Name");
    ValueEnforcer.notEmptyNoNullValue (aLocales, "Locales");

    Locale aRelevantLocale = null;
    for (final Locale aLocale : _getAsLocales (aLocales))
    {
      if (aLocale == null)
        throw new IllegalArgumentException ("Passed locale is null!");
      if (!CurrencyUtils.localeSupportsCurrencyRetrieval (aLocale))
        throw new IllegalArgumentException ("Passed locale " + aLocale + " does not support currency retrieval!");
      if (!m_aLocales.add (aLocale))
        throw new IllegalArgumentException ("The locale " + aLocale + " is contained more than once.");
      if (aRelevantLocale == null && aLocale.getLanguage ().length () > 0)
      {
        // Use the first locale with a language as the most relevant one
        aRelevantLocale = aLocale;
      }
    }
    if (m_aLocales.isEmpty ())
      throw new IllegalArgumentException ("Passed currency is not valid in a single country!");
    if (aRelevantLocale == null)
    {
      // Fallback to the first locale
      aRelevantLocale = ContainerHelper.getFirstElement (m_aLocales);
    }

    m_aCurrency = aCurrency;
    m_bIsDeprecated = bIsDeprecated;
    m_aName = aName;

    // Note: Locale fr_FR formats locale with a trailing € whereas the locale
    // de_DE formats the € at front!
    m_aCurrencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance (aRelevantLocale);

    // Extract value pattern from currency pattern (without currency symbol)
    m_sCurrencyPattern = m_aCurrencyFormat.toPattern ();
    m_sValuePattern = m_sCurrencyPattern.replace ("\u00A4 ", "").replace (" \u00A4", "").replace ("\u00A4", "");

    // Use the decimal symbols from the currency format
    m_aValueFormat = new DecimalFormat (m_sValuePattern, m_aCurrencyFormat.getDecimalFormatSymbols ());

    // By default the default rounding mode should be used
    m_eRoundingMode = null;
  }

  /**
   * @return The currency code of this currency used as the ID.
   */
  @Nonnull
  public String getID ()
  {
    return m_aCurrency.getCurrencyCode ();
  }

  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aName.getDisplayText (aContentLocale);
  }

  /**
   * @return this as {@link java.util.Currency}.
   */
  @Nonnull
  public Currency getAsCurrency ()
  {
    return m_aCurrency;
  }

  @Nonnull
  public String getCurrencySymbol ()
  {
    return m_aCurrencyFormat.getDecimalFormatSymbols ().getCurrencySymbol ();
  }

  /**
   * @return <code>true</code> if this currency is deprecated and no longer
   *         exists.
   */
  public boolean isDeprecated ()
  {
    return m_bIsDeprecated;
  }

  /**
   * @return An unmodifiable list of all countries (as {@link Locale} objects)
   *         to which this currency applies.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <Locale> getAllMatchingCountries ()
  {
    return ContainerHelper.newList (m_aLocales);
  }

  /**
   * @return The pattern to be used in {@link DecimalFormat} to format this
   *         currency. This pattern includes the currency string.
   */
  @Nonnull
  @Nonempty
  public String getCurrencyPattern ()
  {
    return m_sCurrencyPattern;
  }

  /**
   * @return The pattern to be used in {@link DecimalFormat} to format this
   *         currency. This pattern does NOT includes the currency string.
   */
  @Nonnull
  @Nonempty
  public String getValuePattern ()
  {
    return m_sValuePattern;
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
    ValueEnforcer.isGE0 (nDecimals, "Decimals");
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
   * @return The scaling to be used for BigDecimal operations. Always &ge; 0.
   */
  public int getScale ()
  {
    return m_aCurrency.getDefaultFractionDigits ();
  }

  /**
   * Special currency division method. This method solves the problem of
   * dividing "1/3" as it would result in a never ending series of
   * "0.33333333..." which results in an {@link ArithmeticException} thrown by
   * the divide method!<br>
   * The default scaling of this currency is used.
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
    return getDivided (aDividend, aDivisor, getScale ());
  }

  /**
   * Special currency division method. This method solves the problem of
   * dividing "1/3" as it would result in a never ending series of
   * "0.33333333..." which results in an {@link ArithmeticException} thrown by
   * the divide method!<br>
   * This method takes a custom scaling. If the default scaling of this currency
   * should be used, than {@link #getDivided(BigDecimal, BigDecimal)} should be
   * used instead.
   * 
   * @param aDividend
   *        Dividend
   * @param aDivisor
   *        Divisor
   * @param nFractionDigits
   *        A custom scaling to be used.
   * @return The divided value with the provided scaling
   */
  @Nonnull
  @CheckReturnValue
  public BigDecimal getDivided (@Nonnull final BigDecimal aDividend,
                                @Nonnull final BigDecimal aDivisor,
                                @Nonnegative final int nFractionDigits)
  {
    ValueEnforcer.notNull (aDividend, "Dividend");
    ValueEnforcer.notNull (aDivisor, "Divisor");
    return aDividend.divide (aDivisor, nFractionDigits, getRoundingMode ());
  }

  /**
   * Get the passed value rounded to the appropriate number of fraction digits,
   * based on this currencies default fraction digits.<br>
   * The default scaling of this currency is used.
   * 
   * @param aValue
   *        The value to be rounded. May not be <code>null</code>.
   * @return The rounded value. Never <code>null</code>.
   */
  @Nonnull
  public BigDecimal getRounded (@Nonnull final BigDecimal aValue)
  {
    ValueEnforcer.notNull (aValue, "Value");
    return aValue.setScale (getScale (), getRoundingMode ());
  }

  /**
   * Get the passed value rounded to the appropriate number of fraction digits,
   * based on this currencies default fraction digits.<br>
   * This method takes a custom scaling. If the default scaling of this currency
   * should be used, than {@link #getRounded(BigDecimal)} should be used
   * instead.
   * 
   * @param aValue
   *        The value to be rounded. May not be <code>null</code>.
   * @param nFractionDigits
   *        A custom scaling to be used.
   * @return The rounded value. Never <code>null</code>.
   */
  @Nonnull
  public BigDecimal getRounded (@Nonnull final BigDecimal aValue, @Nonnegative final int nFractionDigits)
  {
    ValueEnforcer.notNull (aValue, "Value");
    return aValue.setScale (nFractionDigits, getRoundingMode ());
  }

  /**
   * @return The rounding mode of this currency. If non is specified,
   *         {@link #DEFAULT_ROUNDING_MODE} is returned instead. May not be
   *         <code>null</code>.
   */
  @Nonnull
  public RoundingMode getRoundingMode ()
  {
    return m_eRoundingMode != null ? m_eRoundingMode : DEFAULT_ROUNDING_MODE;
  }

  /**
   * Change the rounding mode of this currency.
   * 
   * @param eRoundingMode
   *        The rounding mode to be used. May not be <code>null</code>.
   */
  public void setRoundingMode (@Nonnull final RoundingMode eRoundingMode)
  {
    m_eRoundingMode = ValueEnforcer.notNull (eRoundingMode, "RoundingMode");
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

  /**
   * @deprecated Use {@link #getFromLocaleOrNull(Locale)} instead
   */
  @Deprecated
  @Nullable
  public static ECurrency getFromCountryOrNull (@Nullable final Locale aLocale)
  {
    return getFromLocaleOrNull (aLocale);
  }

  @Nullable
  public static ECurrency getFromLocaleOrNull (@Nullable final Locale aLocale)
  {
    return getFromLocaleOrNull (aLocale, false);
  }

  /**
   * @deprecated Use {@link #getFromLocaleOrNull(Locale,boolean)} instead
   */
  @Deprecated
  @Nullable
  public static ECurrency getFromCountryOrNull (@Nullable final Locale aLocale, final boolean bIncludeDeprecated)
  {
    return getFromLocaleOrNull (aLocale, bIncludeDeprecated);
  }

  @Nullable
  public static ECurrency getFromLocaleOrNull (@Nullable final Locale aLocale, final boolean bIncludeDeprecated)
  {
    if (aLocale != null)
      for (final ECurrency eCurrency : values ())
        if (!eCurrency.isDeprecated () || bIncludeDeprecated)
          for (final Locale aCurrencyLocale : eCurrency.m_aLocales)
            if (aLocale.equals (aCurrencyLocale))
              return eCurrency;
    return null;
  }
}
