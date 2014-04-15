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
import com.phloc.commons.filter.IFilter;
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
  AED (Currency.getInstance ("AED"), ECurrencyName.AED, "_AE", "ar_AE"),
  AFN (Currency.getInstance ("AFN"), ECurrencyName.AFN, "_AF"),
  ALL (Currency.getInstance ("ALL"), ECurrencyName.ALL, "_AL", "sq_AL"),
  AMD (Currency.getInstance ("AMD"), ECurrencyName.AMD, "_AM"),
  ANG (Currency.getInstance ("ANG"), ECurrencyName.ANG, "_AN", "_CW", "_SX"),
  AOA (Currency.getInstance ("AOA"), ECurrencyName.AOA, "_AO"),
  ARS (Currency.getInstance ("ARS"), ECurrencyName.ARS, "_AR", "es_AR"),
  AUD (Currency.getInstance ("AUD"), ECurrencyName.AUD, "_AU", "_CC", "_CX", "_HM", "_KI", "_NF", "_NR", "_TV", "en_AU"),
  AWG (Currency.getInstance ("AWG"), ECurrencyName.AWG, "_AW"),
  AZN (Currency.getInstance ("AZN"), ECurrencyName.AZN, "_AZ"),
  BAM (Currency.getInstance ("BAM"), ECurrencyName.BAM, "_BA", "sr_BA"),
  BBD (Currency.getInstance ("BBD"), ECurrencyName.BBD, "_BB"),
  BDT (Currency.getInstance ("BDT"), ECurrencyName.BDT, "_BD"),
  BGN (Currency.getInstance ("BGN"), ECurrencyName.BGN, "_BG", "bg_BG"),
  BHD (Currency.getInstance ("BHD"), ECurrencyName.BHD, "_BH", "ar_BH"),
  BIF (Currency.getInstance ("BIF"), ECurrencyName.BIF, "_BI"),
  BMD (Currency.getInstance ("BMD"), ECurrencyName.BMD, "_BM"),
  BND (Currency.getInstance ("BND"), ECurrencyName.BND, "_BN"),
  BOB (Currency.getInstance ("BOB"), ECurrencyName.BOB, "_BO", "es_BO"),
  BRL (Currency.getInstance ("BRL"), ECurrencyName.BRL, "_BR", "pt_BR"),
  BSD (Currency.getInstance ("BSD"), ECurrencyName.BSD, "_BS"),
  BTN (Currency.getInstance ("BTN"), ECurrencyName.BTN, "_BT"),
  BWP (Currency.getInstance ("BWP"), ECurrencyName.BWP, "_BW"),
  BYR (Currency.getInstance ("BYR"), ECurrencyName.BYR, "_BY", "be_BY"),
  BZD (Currency.getInstance ("BZD"), ECurrencyName.BZD, "_BZ"),
  CAD (Currency.getInstance ("CAD"), ECurrencyName.CAD, "_CA", "en_CA", "fr_CA"),
  CDF (Currency.getInstance ("CDF"), ECurrencyName.CDF, "_CD"),
  CHF (Currency.getInstance ("CHF"), ECurrencyName.CHF, "_CH", "_LI", "de_CH", "fr_CH", "it_CH"),
  CLP (Currency.getInstance ("CLP"), ECurrencyName.CLP, "_CL", "es_CL"),
  CNY (Currency.getInstance ("CNY"), ECurrencyName.CNY, "_CN", "zh_CN"),
  COP (Currency.getInstance ("COP"), ECurrencyName.COP, "_CO", "es_CO"),
  CRC (Currency.getInstance ("CRC"), ECurrencyName.CRC, "_CR", "es_CR"),
  @Deprecated
  CSD (Currency.getInstance ("CSD"), true, ECurrencyName.CSD, "_CS", "sr_CS"),
  CUC (Currency.getInstance ("CUC"), ECurrencyName.CUC, "_CU"),
  CUP (Currency.getInstance ("CUP"), ECurrencyName.CUP, "_CU"),
  CVE (Currency.getInstance ("CVE"), ECurrencyName.CVE, "_CV"),
  CZK (Currency.getInstance ("CZK"), ECurrencyName.CZK, "_CZ", "cs_CZ"),
  DJF (Currency.getInstance ("DJF"), ECurrencyName.DJF, "_DJ"),
  DKK (Currency.getInstance ("DKK"), ECurrencyName.DKK, "_DK", "_FO", "_GL", "da_DK"),
  DOP (Currency.getInstance ("DOP"), ECurrencyName.DOP, "_DO", "es_DO"),
  DZD (Currency.getInstance ("DZD"), ECurrencyName.DZD, "_DZ", "ar_DZ"),
  // Estonian Kroon (until 31.12.2010)
  @Deprecated
  EEK (Currency.getInstance ("EEK"), true, ECurrencyName.EEK, "_EE", "et_EE"),
  EGP (Currency.getInstance ("EGP"), ECurrencyName.EGP, "_EG", "ar_EG"),
  ERN (Currency.getInstance ("ERN"), ECurrencyName.ERN, "_ER"),
  ETB (Currency.getInstance ("ETB"), ECurrencyName.ETB, "_ET"),
  EUR (Currency.getInstance ("EUR"), ECurrencyName.EUR, "_AD", "_AT", "_AX", "_BE", "_BL", "_CY", "_DE", "_EE", "_ES", "_FI", "_FR", "_GF", "_GP", "_GR", "_IE", "_IT", "_LU", "_MC", "_ME", "_MF", "_MQ", "_MT", "_NL", "_PM", "_PT", "_RE", "_SI", "_SK", "_SM", "_TF", "_VA", "_YT", "ca_ES", "de_AT", "de_DE", "de_LU", "el_CY", "el_GR", "en_IE", "en_MT", "es_ES", "et_EE", "fi_FI", "fr_BE", "fr_FR", "fr_LU", "ga_IE", "it_IT", "mt_MT", "nl_BE", "nl_NL", "pt_PT", "sk_SK", "sl_SI", "sr_ME"),
  FJD (Currency.getInstance ("FJD"), ECurrencyName.FJD, "_FJ"),
  FKP (Currency.getInstance ("FKP"), ECurrencyName.FKP, "_FK"),
  GBP (Currency.getInstance ("GBP"), ECurrencyName.GBP, "_GB", "_GG", "_GS", "_IM", "_JE", "en_GB"),
  GEL (Currency.getInstance ("GEL"), ECurrencyName.GEL, "_GE"),
  GHS (Currency.getInstance ("GHS"), ECurrencyName.GHS, "_GH"),
  GIP (Currency.getInstance ("GIP"), ECurrencyName.GIP, "_GI"),
  GMD (Currency.getInstance ("GMD"), ECurrencyName.GMD, "_GM"),
  GNF (Currency.getInstance ("GNF"), ECurrencyName.GNF, "_GN"),
  GTQ (Currency.getInstance ("GTQ"), ECurrencyName.GTQ, "_GT", "es_GT"),
  GYD (Currency.getInstance ("GYD"), ECurrencyName.GYD, "_GY"),
  HKD (Currency.getInstance ("HKD"), ECurrencyName.HKD, "_HK", "zh_HK"),
  HNL (Currency.getInstance ("HNL"), ECurrencyName.HNL, "_HN", "es_HN"),
  HRK (Currency.getInstance ("HRK"), ECurrencyName.HRK, "_HR", "hr_HR"),
  HTG (Currency.getInstance ("HTG"), ECurrencyName.HTG, "_HT"),
  HUF (Currency.getInstance ("HUF"), ECurrencyName.HUF, "_HU", "hu_HU"),
  IDR (Currency.getInstance ("IDR"), ECurrencyName.IDR, "_ID", "in_ID"),
  ILS (Currency.getInstance ("ILS"), ECurrencyName.ILS, "_IL", "_PS", "iw_IL"),
  INR (Currency.getInstance ("INR"), ECurrencyName.INR, "_IN", "en_IN", "hi_IN"),
  IQD (Currency.getInstance ("IQD"), ECurrencyName.IQD, "_IQ", "ar_IQ"),
  IRR (Currency.getInstance ("IRR"), ECurrencyName.IRR, "_IR"),
  ISK (Currency.getInstance ("ISK"), ECurrencyName.ISK, "_IS", "is_IS"),
  JMD (Currency.getInstance ("JMD"), ECurrencyName.JMD, "_JM"),
  JOD (Currency.getInstance ("JOD"), ECurrencyName.JOD, "_JO", "ar_JO"),
  JPY (Currency.getInstance ("JPY"), ECurrencyName.JPY, "_JP", "ja_JP", "ja_JP_JP"),
  KES (Currency.getInstance ("KES"), ECurrencyName.KES, "_KE"),
  KGS (Currency.getInstance ("KGS"), ECurrencyName.KGS, "_KG"),
  KHR (Currency.getInstance ("KHR"), ECurrencyName.KHR, "_KH"),
  KMF (Currency.getInstance ("KMF"), ECurrencyName.KMF, "_KM"),
  KPW (Currency.getInstance ("KPW"), ECurrencyName.KPW, "_KP"),
  KRW (Currency.getInstance ("KRW"), ECurrencyName.KRW, "_KR", "ko_KR"),
  KWD (Currency.getInstance ("KWD"), ECurrencyName.KWD, "_KW", "ar_KW"),
  KYD (Currency.getInstance ("KYD"), ECurrencyName.KYD, "_KY"),
  KZT (Currency.getInstance ("KZT"), ECurrencyName.KZT, "_KZ"),
  LAK (Currency.getInstance ("LAK"), ECurrencyName.LAK, "_LA"),
  LBP (Currency.getInstance ("LBP"), ECurrencyName.LBP, "_LB", "ar_LB"),
  LKR (Currency.getInstance ("LKR"), ECurrencyName.LKR, "_LK"),
  LRD (Currency.getInstance ("LRD"), ECurrencyName.LRD, "_LR"),
  LSL (Currency.getInstance ("LSL"), ECurrencyName.LSL, "_LS"),
  LTL (Currency.getInstance ("LTL"), ECurrencyName.LTL, "_LT", "lt_LT"),
  LVL (Currency.getInstance ("LVL"), ECurrencyName.LVL, "_LV", "lv_LV"),
  LYD (Currency.getInstance ("LYD"), ECurrencyName.LYD, "_LY", "ar_LY"),
  MAD (Currency.getInstance ("MAD"), ECurrencyName.MAD, "_EH", "_MA", "ar_MA"),
  MDL (Currency.getInstance ("MDL"), ECurrencyName.MDL, "_MD"),
  MGA (Currency.getInstance ("MGA"), ECurrencyName.MGA, "_MG"),
  MKD (Currency.getInstance ("MKD"), ECurrencyName.MKD, "_MK", "mk_MK"),
  MMK (Currency.getInstance ("MMK"), ECurrencyName.MMK, "_MM"),
  MNT (Currency.getInstance ("MNT"), ECurrencyName.MNT, "_MN"),
  MOP (Currency.getInstance ("MOP"), ECurrencyName.MOP, "_MO"),
  MRO (Currency.getInstance ("MRO"), ECurrencyName.MRO, "_MR"),
  MUR (Currency.getInstance ("MUR"), ECurrencyName.MUR, "_MU"),
  MVR (Currency.getInstance ("MVR"), ECurrencyName.MVR, "_MV"),
  MWK (Currency.getInstance ("MWK"), ECurrencyName.MWK, "_MW"),
  MXN (Currency.getInstance ("MXN"), ECurrencyName.MXN, "_MX", "es_MX"),
  MYR (Currency.getInstance ("MYR"), ECurrencyName.MYR, "_MY", "ms_MY"),
  MZN (Currency.getInstance ("MZN"), ECurrencyName.MZN, "_MZ"),
  NAD (Currency.getInstance ("NAD"), ECurrencyName.NAD, "_NA"),
  NGN (Currency.getInstance ("NGN"), ECurrencyName.NGN, "_NG"),
  NIO (Currency.getInstance ("NIO"), ECurrencyName.NIO, "_NI", "es_NI"),
  NOK (Currency.getInstance ("NOK"), ECurrencyName.NOK, "_BV", "_NO", "_SJ", "no_NO", "no_NO_NY"),
  NPR (Currency.getInstance ("NPR"), ECurrencyName.NPR, "_NP"),
  NZD (Currency.getInstance ("NZD"), ECurrencyName.NZD, "_CK", "_NU", "_NZ", "_PN", "_TK", "en_NZ"),
  OMR (Currency.getInstance ("OMR"), ECurrencyName.OMR, "_OM", "ar_OM"),
  PAB (Currency.getInstance ("PAB"), ECurrencyName.PAB, "_PA", "es_PA"),
  PEN (Currency.getInstance ("PEN"), ECurrencyName.PEN, "_PE", "es_PE"),
  PGK (Currency.getInstance ("PGK"), ECurrencyName.PGK, "_PG"),
  PHP (Currency.getInstance ("PHP"), ECurrencyName.PHP, "_PH", "en_PH"),
  PKR (Currency.getInstance ("PKR"), ECurrencyName.PKR, "_PK"),
  PLN (Currency.getInstance ("PLN"), ECurrencyName.PLN, "_PL", "pl_PL"),
  PYG (Currency.getInstance ("PYG"), ECurrencyName.PYG, "_PY", "es_PY"),
  QAR (Currency.getInstance ("QAR"), ECurrencyName.QAR, "_QA", "ar_QA"),
  RON (Currency.getInstance ("RON"), ECurrencyName.RON, "_RO", "ro_RO"),
  RSD (Currency.getInstance ("RSD"), ECurrencyName.RSD, "_RS", "sr_RS"),
  RUB (Currency.getInstance ("RUB"), ECurrencyName.RUB, "_RU", "ru_RU"),
  RWF (Currency.getInstance ("RWF"), ECurrencyName.RWF, "_RW"),
  SAR (Currency.getInstance ("SAR"), ECurrencyName.SAR, "_SA", "ar_SA"),
  SBD (Currency.getInstance ("SBD"), ECurrencyName.SBD, "_SB"),
  SCR (Currency.getInstance ("SCR"), ECurrencyName.SCR, "_SC"),
  SDG (Currency.getInstance ("SDG"), ECurrencyName.SDG, "_SD", "ar_SD"),
  SEK (Currency.getInstance ("SEK"), ECurrencyName.SEK, "_SE", "sv_SE"),
  SGD (Currency.getInstance ("SGD"), ECurrencyName.SGD, "_SG", "en_SG", "zh_SG"),
  SHP (Currency.getInstance ("SHP"), ECurrencyName.SHP, "_SH"),
  SLL (Currency.getInstance ("SLL"), ECurrencyName.SLL, "_SL"),
  SOS (Currency.getInstance ("SOS"), ECurrencyName.SOS, "_SO"),
  SRD (Currency.getInstance ("SRD"), ECurrencyName.SRD, "_SR"),
  STD (Currency.getInstance ("STD"), ECurrencyName.STD, "_ST"),
  SVC (Currency.getInstance ("SVC"), ECurrencyName.SVC, "_SV", "es_SV"),
  SYP (Currency.getInstance ("SYP"), ECurrencyName.SYP, "_SY", "ar_SY"),
  SZL (Currency.getInstance ("SZL"), ECurrencyName.SZL, "_SZ"),
  THB (Currency.getInstance ("THB"), ECurrencyName.THB, "_TH", "th_TH", "th_TH_TH"),
  TJS (Currency.getInstance ("TJS"), ECurrencyName.TJS, "_TJ"),
  TMT (Currency.getInstance ("TMT"), ECurrencyName.TMT, "_TM"),
  TND (Currency.getInstance ("TND"), ECurrencyName.TND, "_TN", "ar_TN"),
  TOP (Currency.getInstance ("TOP"), ECurrencyName.TOP, "_TO"),
  TRY (Currency.getInstance ("TRY"), ECurrencyName.TRY, "_TR", "tr_TR"),
  TTD (Currency.getInstance ("TTD"), ECurrencyName.TTD, "_TT"),
  TWD (Currency.getInstance ("TWD"), ECurrencyName.TWD, "_TW", "zh_TW"),
  TZS (Currency.getInstance ("TZS"), ECurrencyName.TZS, "_TZ"),
  UAH (Currency.getInstance ("UAH"), ECurrencyName.UAH, "_UA", "uk_UA"),
  UGX (Currency.getInstance ("UGX"), ECurrencyName.UGX, "_UG"),
  USD (Currency.getInstance ("USD"), ECurrencyName.USD, "_AS", "_BQ", "_EC", "_FM", "_GU", "_IO", "_MH", "_MP", "_PR", "_PW", "_TC", "_TL", "_UM", "_US", "_VG", "_VI", "en_US", "es_EC", "es_PR", "es_US"),
  UYU (Currency.getInstance ("UYU"), ECurrencyName.UYU, "_UY", "es_UY"),
  UZS (Currency.getInstance ("UZS"), ECurrencyName.UZS, "_UZ"),
  VEF (Currency.getInstance ("VEF"), ECurrencyName.VEF, "_VE", "es_VE"),
  VND (Currency.getInstance ("VND"), ECurrencyName.VND, "_VN", "vi_VN"),
  VUV (Currency.getInstance ("VUV"), ECurrencyName.VUV, "_VU"),
  WST (Currency.getInstance ("WST"), ECurrencyName.WST, "_WS"),
  XAF (Currency.getInstance ("XAF"), ECurrencyName.XAF, "_CF", "_CG", "_CM", "_GA", "_GQ", "_TD"),
  XCD (Currency.getInstance ("XCD"), ECurrencyName.XCD, "_AG", "_AI", "_DM", "_GD", "_KN", "_LC", "_MS", "_VC"),
  XOF (Currency.getInstance ("XOF"), ECurrencyName.XOF, "_BF", "_BJ", "_CI", "_GW", "_ML", "_NE", "_SN", "_TG"),
  XPF (Currency.getInstance ("XPF"), ECurrencyName.XPF, "_NC", "_PF", "_WF"),
  YER (Currency.getInstance ("YER"), ECurrencyName.YER, "_YE", "ar_YE"),
  ZAR (Currency.getInstance ("ZAR"), ECurrencyName.ZAR, "_ZA", "en_ZA"),
  @Deprecated
  ZMK (Currency.getInstance ("ZMK"), true, ECurrencyName.ZMK, "_ZM"),
  @Deprecated
  ZWL (Currency.getInstance ("ZWL"), true, ECurrencyName.ZWL, "_ZW");

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

  @Nonnull
  @ReturnsMutableCopy
  public static List <ECurrency> getAllCurrenciesWithLocaleFilter (@Nonnull final IFilter <Locale> aLocaleFilter)
  {
    return getAllCurrenciesWithLocaleFilter (aLocaleFilter, false);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <ECurrency> getAllCurrenciesWithLocaleFilter (@Nonnull final IFilter <Locale> aLocaleFilter,
                                                                   final boolean bIncludeDeprecated)
  {
    ValueEnforcer.notNull (aLocaleFilter, "LocaleFilter");

    final List <ECurrency> ret = new ArrayList <ECurrency> ();
    for (final ECurrency eCurrency : values ())
      if (!eCurrency.isDeprecated () || bIncludeDeprecated)
        for (final Locale aCurrencyLocale : eCurrency.m_aLocales)
          if (aLocaleFilter.matchesFilter (aCurrencyLocale))
          {
            ret.add (eCurrency);
            // continue with next currency
            break;
          }
    return ret;
  }
}
