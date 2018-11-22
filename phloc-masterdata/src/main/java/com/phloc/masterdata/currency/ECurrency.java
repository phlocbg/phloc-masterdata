/**
 * Copyright (C) 2006-2015 phloc systems
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
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.LoggerFactory;

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
 AED ("AED", ECurrencyName.AED, "_AE", "ar_AE"),
 AFN ("AFN", ECurrencyName.AFN, "_AF"),
 ALL ("ALL", ECurrencyName.ALL, "_AL", "sq_AL"),
 AMD ("AMD", ECurrencyName.AMD, "_AM"),
 ANG ("ANG", ECurrencyName.ANG, "_AN", "_CW", "_SX"),
 AOA ("AOA", ECurrencyName.AOA, "_AO"),
 ARS ("ARS", ECurrencyName.ARS, "_AR", "es_AR"),
 AUD ("AUD", ECurrencyName.AUD, "_AU", "_CC", "_CX", "_HM", "_KI", "_NF", "_NR", "_TV", "en_AU"),
 AWG ("AWG", ECurrencyName.AWG, "_AW"),
 AZN ("AZN", ECurrencyName.AZN, "_AZ"),
 BAM ("BAM", ECurrencyName.BAM, "_BA", "sr_BA"),
 BBD ("BBD", ECurrencyName.BBD, "_BB"),
 BDT ("BDT", ECurrencyName.BDT, "_BD"),
 BGN ("BGN", ECurrencyName.BGN, "_BG", "bg_BG"),
 BHD ("BHD", ECurrencyName.BHD, "_BH", "ar_BH"),
 BIF ("BIF", ECurrencyName.BIF, "_BI"),
 BMD ("BMD", ECurrencyName.BMD, "_BM"),
 BND ("BND", ECurrencyName.BND, "_BN"),
 BOB ("BOB", ECurrencyName.BOB, "_BO", "es_BO"),
 BRL ("BRL", ECurrencyName.BRL, "_BR", "pt_BR"),
 BSD ("BSD", ECurrencyName.BSD, "_BS"),
 BTN ("BTN", ECurrencyName.BTN, "_BT"),
 BWP ("BWP", ECurrencyName.BWP, "_BW"),
 BYR ("BYR", ECurrencyName.BYR, "_BY", "be_BY"),
 BZD ("BZD", ECurrencyName.BZD, "_BZ"),
 CAD ("CAD", ECurrencyName.CAD, "_CA", "en_CA", "fr_CA"),
 CDF ("CDF", ECurrencyName.CDF, "_CD"),
 CHF ("CHF", ECurrencyName.CHF, "_CH", "_LI", "de_CH", "fr_CH", "it_CH"),
 CLP ("CLP", ECurrencyName.CLP, "_CL", "es_CL"),
 CNY ("CNY", ECurrencyName.CNY, "_CN", "zh_CN"),
 COP ("COP", ECurrencyName.COP, "_CO", "es_CO"),
 CRC ("CRC", ECurrencyName.CRC, "_CR", "es_CR"),
 @Deprecated
 CSD ("CSD", true, ECurrencyName.CSD, "_CS", "sr_CS"),
 CUC ("CUC", ECurrencyName.CUC, "_CU"),
 CUP ("CUP", ECurrencyName.CUP, "_CU"),
 CVE ("CVE", ECurrencyName.CVE, "_CV"),
 CZK ("CZK", ECurrencyName.CZK, "_CZ", "cs_CZ"),
 DJF ("DJF", ECurrencyName.DJF, "_DJ"),
 DKK ("DKK", ECurrencyName.DKK, "_DK", "_FO", "_GL", "da_DK"),
 DOP ("DOP", ECurrencyName.DOP, "_DO", "es_DO"),
 DZD ("DZD", ECurrencyName.DZD, "_DZ", "ar_DZ"),
 // Estonian Kroon (until 31.12.2010)
 @Deprecated
 EEK ("EEK", true, ECurrencyName.EEK, "_EE", "et_EE"),
 EGP ("EGP", ECurrencyName.EGP, "_EG", "ar_EG"),
 ERN ("ERN", ECurrencyName.ERN, "_ER"),
 ETB ("ETB", ECurrencyName.ETB, "_ET"),
 EUR ("EUR", ECurrencyName.EUR, "_AD", "_AT", "_AX", "_BE", "_BL", "_CY", "_DE", "_EE", "_ES", "_FI", "_FR", "_GF", "_GP", "_GR", "_IE", "_IT", "_LU", "_MC", "_ME", "_MF", "_MQ", "_MT", "_NL", "_PM", "_PT", "_RE", "_SI", "_SK", "_SM", "_TF", "_VA", "_YT", "ca_ES", "de_AT", "de_DE", "de_LU", "el_CY", "el_GR", "en_IE", "en_MT", "es_ES", "et_EE", "fi_FI", "fr_BE", "fr_FR", "fr_LU", "ga_IE", "it_IT", "mt_MT", "nl_BE", "nl_NL", "pt_PT", "sk_SK", "sl_SI", "sr_ME"),
 FJD ("FJD", ECurrencyName.FJD, "_FJ"),
 FKP ("FKP", ECurrencyName.FKP, "_FK"),
 GBP ("GBP", ECurrencyName.GBP, "_GB", "_GG", "_GS", "_IM", "_JE", "en_GB"),
 GEL ("GEL", ECurrencyName.GEL, "_GE"),
 GHS ("GHS", ECurrencyName.GHS, "_GH"),
 GIP ("GIP", ECurrencyName.GIP, "_GI"),
 GMD ("GMD", ECurrencyName.GMD, "_GM"),
 GNF ("GNF", ECurrencyName.GNF, "_GN"),
 GTQ ("GTQ", ECurrencyName.GTQ, "_GT", "es_GT"),
 GYD ("GYD", ECurrencyName.GYD, "_GY"),
 HKD ("HKD", ECurrencyName.HKD, "_HK", "zh_HK"),
 HNL ("HNL", ECurrencyName.HNL, "_HN", "es_HN"),
 HRK ("HRK", ECurrencyName.HRK, "_HR", "hr_HR"),
 HTG ("HTG", ECurrencyName.HTG, "_HT"),
 HUF ("HUF", ECurrencyName.HUF, "_HU", "hu_HU"),
 IDR ("IDR", ECurrencyName.IDR, "_ID", "in_ID"),
 ILS ("ILS", ECurrencyName.ILS, "_IL", "_PS", "iw_IL"),
 INR ("INR", ECurrencyName.INR, "_IN", "en_IN", "hi_IN"),
 IQD ("IQD", ECurrencyName.IQD, "_IQ", "ar_IQ"),
 IRR ("IRR", ECurrencyName.IRR, "_IR"),
 ISK ("ISK", ECurrencyName.ISK, "_IS", "is_IS"),
 JMD ("JMD", ECurrencyName.JMD, "_JM"),
 JOD ("JOD", ECurrencyName.JOD, "_JO", "ar_JO"),
 JPY ("JPY", ECurrencyName.JPY, "_JP", "ja_JP", "ja_JP_JP"),
 KES ("KES", ECurrencyName.KES, "_KE"),
 KGS ("KGS", ECurrencyName.KGS, "_KG"),
 KHR ("KHR", ECurrencyName.KHR, "_KH"),
 KMF ("KMF", ECurrencyName.KMF, "_KM"),
 KPW ("KPW", ECurrencyName.KPW, "_KP"),
 KRW ("KRW", ECurrencyName.KRW, "_KR", "ko_KR"),
 KWD ("KWD", ECurrencyName.KWD, "_KW", "ar_KW"),
 KYD ("KYD", ECurrencyName.KYD, "_KY"),
 KZT ("KZT", ECurrencyName.KZT, "_KZ"),
 LAK ("LAK", ECurrencyName.LAK, "_LA"),
 LBP ("LBP", ECurrencyName.LBP, "_LB", "ar_LB"),
 LKR ("LKR", ECurrencyName.LKR, "_LK"),
 LRD ("LRD", ECurrencyName.LRD, "_LR"),
 LSL ("LSL", ECurrencyName.LSL, "_LS"),
 LTL ("LTL", ECurrencyName.LTL, "_LT", "lt_LT"),
 LVL ("LVL", ECurrencyName.LVL, "_LV", "lv_LV"),
 LYD ("LYD", ECurrencyName.LYD, "_LY", "ar_LY"),
 MAD ("MAD", ECurrencyName.MAD, "_EH", "_MA", "ar_MA"),
 MDL ("MDL", ECurrencyName.MDL, "_MD"),
 MGA ("MGA", ECurrencyName.MGA, "_MG"),
 MKD ("MKD", ECurrencyName.MKD, "_MK", "mk_MK"),
 MMK ("MMK", ECurrencyName.MMK, "_MM"),
 MNT ("MNT", ECurrencyName.MNT, "_MN"),
 MOP ("MOP", ECurrencyName.MOP, "_MO"),
 MRO ("MRO", ECurrencyName.MRO, "_MR"),
 MUR ("MUR", ECurrencyName.MUR, "_MU"),
 MVR ("MVR", ECurrencyName.MVR, "_MV"),
 MWK ("MWK", ECurrencyName.MWK, "_MW"),
 MXN ("MXN", ECurrencyName.MXN, "_MX", "es_MX"),
 MYR ("MYR", ECurrencyName.MYR, "_MY", "ms_MY"),
 MZN ("MZN", ECurrencyName.MZN, "_MZ"),
 NAD ("NAD", ECurrencyName.NAD, "_NA"),
 NGN ("NGN", ECurrencyName.NGN, "_NG"),
 NIO ("NIO", ECurrencyName.NIO, "_NI", "es_NI"),
 NOK ("NOK", ECurrencyName.NOK, "_BV", "_NO", "_SJ", "no_NO", "no_NO_NY"),
 NPR ("NPR", ECurrencyName.NPR, "_NP"),
 NZD ("NZD", ECurrencyName.NZD, "_CK", "_NU", "_NZ", "_PN", "_TK", "en_NZ"),
 OMR ("OMR", ECurrencyName.OMR, "_OM", "ar_OM"),
 PAB ("PAB", ECurrencyName.PAB, "_PA", "es_PA"),
 PEN ("PEN", ECurrencyName.PEN, "_PE", "es_PE"),
 PGK ("PGK", ECurrencyName.PGK, "_PG"),
 PHP ("PHP", ECurrencyName.PHP, "_PH", "en_PH"),
 PKR ("PKR", ECurrencyName.PKR, "_PK"),
 PLN ("PLN", ECurrencyName.PLN, "_PL", "pl_PL"),
 PYG ("PYG", ECurrencyName.PYG, "_PY", "es_PY"),
 QAR ("QAR", ECurrencyName.QAR, "_QA", "ar_QA"),
 RON ("RON", ECurrencyName.RON, "_RO", "ro_RO"),
 RSD ("RSD", ECurrencyName.RSD, "_RS", "sr_RS"),
 RUB ("RUB", ECurrencyName.RUB, "_RU", "ru_RU"),
 RWF ("RWF", ECurrencyName.RWF, "_RW"),
 SAR ("SAR", ECurrencyName.SAR, "_SA", "ar_SA"),
 SBD ("SBD", ECurrencyName.SBD, "_SB"),
 SCR ("SCR", ECurrencyName.SCR, "_SC"),
 SDG ("SDG", ECurrencyName.SDG, "_SD", "ar_SD"),
 SEK ("SEK", ECurrencyName.SEK, "_SE", "sv_SE"),
 SGD ("SGD", ECurrencyName.SGD, "_SG", "en_SG", "zh_SG"),
 SHP ("SHP", ECurrencyName.SHP, "_SH"),
 SLL ("SLL", ECurrencyName.SLL, "_SL"),
 SOS ("SOS", ECurrencyName.SOS, "_SO"),
 SRD ("SRD", ECurrencyName.SRD, "_SR"),
 SSP ("SSP", ECurrencyName.SSP, "_SS"),
 STD ("STD", ECurrencyName.STD, "_ST"),
 SVC ("SVC", ECurrencyName.SVC, "_SV", "es_SV"),
 SYP ("SYP", ECurrencyName.SYP, "_SY", "ar_SY"),
 SZL ("SZL", ECurrencyName.SZL, "_SZ"),
 THB ("THB", ECurrencyName.THB, "_TH", "th_TH", "th_TH_TH"),
 TJS ("TJS", ECurrencyName.TJS, "_TJ"),
 TMT ("TMT", ECurrencyName.TMT, "_TM"),
 TND ("TND", ECurrencyName.TND, "_TN", "ar_TN"),
 TOP ("TOP", ECurrencyName.TOP, "_TO"),
 TRY ("TRY", ECurrencyName.TRY, "_TR", "tr_TR"),
 TTD ("TTD", ECurrencyName.TTD, "_TT"),
 TWD ("TWD", ECurrencyName.TWD, "_TW", "zh_TW"),
 TZS ("TZS", ECurrencyName.TZS, "_TZ"),
 UAH ("UAH", ECurrencyName.UAH, "_UA", "uk_UA"),
 UGX ("UGX", ECurrencyName.UGX, "_UG"),
 USD ("USD", ECurrencyName.USD, "_AS", "_BQ", "_EC", "_FM", "_GU", "_IO", "_MH", "_MP", "_PR", "_PW", "_TC", "_TL", "_UM", "_US", "_VG", "_VI", "en_US", "es_EC", "es_PR", "es_US"),
 UYU ("UYU", ECurrencyName.UYU, "_UY", "es_UY"),
 UZS ("UZS", ECurrencyName.UZS, "_UZ"),
 VEF ("VEF", ECurrencyName.VEF, "_VE", "es_VE"),
 VND ("VND", ECurrencyName.VND, "_VN", "vi_VN"),
 VUV ("VUV", ECurrencyName.VUV, "_VU"),
 WST ("WST", ECurrencyName.WST, "_WS"),
 XAF ("XAF", ECurrencyName.XAF, "_CF", "_CG", "_CM", "_GA", "_GQ", "_TD"),
 XCD ("XCD", ECurrencyName.XCD, "_AG", "_AI", "_DM", "_GD", "_KN", "_LC", "_MS", "_VC"),
 XOF ("XOF", ECurrencyName.XOF, "_BF", "_BJ", "_CI", "_GW", "_ML", "_NE", "_SN", "_TG"),
 XPF ("XPF", ECurrencyName.XPF, "_NC", "_PF", "_WF"),
 YER ("YER", ECurrencyName.YER, "_YE", "ar_YE"),
 ZAR ("ZAR", ECurrencyName.ZAR, "_ZA", "en_ZA"),
 @Deprecated
 ZMK ("ZMK", true, ECurrencyName.ZMK, "_ZM"),
 ZMW ("ZMW", true, ECurrencyName.ZMW, "_ZM"),
 @Deprecated
 ZWL ("ZWL", true, ECurrencyName.ZWL, "_ZW");

  /**
   * The default rounding mode to be used for currency values. It may be
   * overridden for each currency individually.
   */
  public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;

  /** The default currency */
  public static final ECurrency DEFAULT_CURRENCY = EUR;

  /** The default scale to be used if no JDK Currency is available */
  public static final int DEFAULT_SCALE = 2;

  private final String m_sID;
  private final Currency m_aCurrency;
  private final int m_nScale;
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

  private ECurrency (@Nonnull @Nonempty final String sCurrencyCode,
                     @Nonnull final ECurrencyName aName,
                     @Nonnull @Nonempty final String... aLocales)
  {
    this (sCurrencyCode, false, aName, aLocales);
  }

  private ECurrency (@Nonnull @Nonempty final String sCurrencyCode,
                     final boolean bIsDeprecated,
                     @Nonnull final ECurrencyName aName,
                     @Nonnull @Nonempty final String... aLocales)
  {
    ValueEnforcer.notEmpty (sCurrencyCode, "CurrencyCode");
    ValueEnforcer.notNull (aName, "Name");
    ValueEnforcer.notEmptyNoNullValue (aLocales, "Locales");

    Locale aRelevantLocale = null;
    for (final Locale aLocale : _getAsLocales (aLocales))
    {
      if (aLocale == null)
        throw new IllegalArgumentException ("Passed locale is null!");
      if (!CurrencyUtils.localeSupportsCurrencyRetrieval (aLocale))
        throw new IllegalArgumentException ("Passed locale " + aLocale + " does not support currency retrieval!");
      if (!this.m_aLocales.add (aLocale))
        throw new IllegalArgumentException ("The locale " + aLocale + " is contained more than once.");
      if (aRelevantLocale == null && aLocale.getLanguage ().length () > 0)
      {
        // Use the first locale with a language as the most relevant one
        aRelevantLocale = aLocale;
      }
    }
    if (this.m_aLocales.isEmpty ())
      throw new IllegalArgumentException ("Passed currency is not valid in a single country (" +
                                          Arrays.toString (aLocales) +
                                          ")");
    if (aRelevantLocale == null)
    {
      // Fallback to the first locale
      aRelevantLocale = ContainerHelper.getFirstElement (this.m_aLocales);
    }

    this.m_sID = sCurrencyCode;
    Currency aCurrency = null;
    try
    {
      aCurrency = Currency.getInstance (sCurrencyCode);
    }
    catch (final IllegalArgumentException ex)
    {
      // Happens when an unsupported currency code is provided
      LoggerFactory.getLogger (ECurrency.class)
                   .error ("Failed to resolve currency with currency code '" +
                           sCurrencyCode +
                           "' - " +
                           aName.getDisplayText (Locale.US));
    }
    this.m_aCurrency = aCurrency;
    this.m_nScale = aCurrency == null ? DEFAULT_SCALE : aCurrency.getDefaultFractionDigits ();
    this.m_bIsDeprecated = bIsDeprecated;
    this.m_aName = aName;

    // Note: Locale fr_FR formats locale with a trailing € whereas the locale
    // de_DE formats the € at front!
    this.m_aCurrencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance (aRelevantLocale);

    // Extract value pattern from currency pattern (without currency symbol)
    this.m_sCurrencyPattern = this.m_aCurrencyFormat.toPattern ();
    this.m_sValuePattern = this.m_sCurrencyPattern.replace ("\u00A4 ", "")
                                                  .replace (" \u00A4", "")
                                                  .replace ("\u00A4", "");

    // Use the decimal symbols from the currency format
    this.m_aValueFormat = new DecimalFormat (this.m_sValuePattern, this.m_aCurrencyFormat.getDecimalFormatSymbols ());

    // By default the default rounding mode should be used
    this.m_eRoundingMode = null;
  }

  /**
   * @return The currency code of this currency used as the ID.
   */
  @Override
  @Nonnull
  @Nonempty
  public String getID ()
  {
    return this.m_sID;
  }

  @Override
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return this.m_aName.getDisplayText (aContentLocale);
  }

  /**
   * @return this as {@link java.util.Currency}. May be <code>null</code> if the
   *         currency is not supported by the JDK! Before v3.3.8 the currency
   *         was non-<code>null</code>.
   */
  @Nullable
  public Currency getAsCurrency ()
  {
    return this.m_aCurrency;
  }

  @Nonnull
  public String getCurrencySymbol ()
  {
    return this.m_aCurrencyFormat.getDecimalFormatSymbols ().getCurrencySymbol ();
  }

  /**
   * @return <code>true</code> if this currency is deprecated and no longer
   *         exists.
   */
  public boolean isDeprecated ()
  {
    return this.m_bIsDeprecated;
  }

  /**
   * @return A list of all locales (as {@link Locale} objects) to which this
   *         currency applies.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public List <Locale> getAllMatchingLocales ()
  {
    return ContainerHelper.newList (this.m_aLocales);
  }

  /**
   * Check if the passed locale filter matches any locale of this currency.
   * 
   * @param aLocaleFilter
   *        The locale filter to be used. May not be <code>null</code>.
   * @return <code>true</code> if the filter matched at least one locale,
   *         <code>false</code> if it matched none
   */
  public boolean isLocaleFilterMatchingAnyLocale (@Nonnull final IFilter <Locale> aLocaleFilter)
  {
    ValueEnforcer.notNull (aLocaleFilter, "LocaleFilter");
    for (final Locale aCurrencyLocale : this.m_aLocales)
      if (aLocaleFilter.matchesFilter (aCurrencyLocale))
        return true;
    return false;
  }

  /**
   * Check if the passed locale filter matches all locales of this currency.
   * 
   * @param aLocaleFilter
   *        The locale filter to be used. May not be <code>null</code>.
   * @return <code>true</code> if the filter matched all locales,
   *         <code>false</code> if it didn't match at least one locale
   */
  public boolean isLocaleFilterMatchingAllLocales (@Nonnull final IFilter <Locale> aLocaleFilter)
  {
    ValueEnforcer.notNull (aLocaleFilter, "LocaleFilter");
    for (final Locale aCurrencyLocale : this.m_aLocales)
      if (!aLocaleFilter.matchesFilter (aCurrencyLocale))
        return false;
    return true;
  }

  /**
   * @return The pattern to be used in {@link DecimalFormat} to format this
   *         currency. This pattern includes the currency string.
   */
  @Nonnull
  @Nonempty
  public String getCurrencyPattern ()
  {
    return this.m_sCurrencyPattern;
  }

  /**
   * @return The pattern to be used in {@link DecimalFormat} to format this
   *         currency. This pattern does NOT includes the currency string.
   */
  @Nonnull
  @Nonempty
  public String getValuePattern ()
  {
    return this.m_sValuePattern;
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
    return (DecimalFormat) this.m_aCurrencyFormat.clone ();
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
    return (DecimalFormat) this.m_aValueFormat.clone ();
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
    return this.m_aCurrencyFormat.getMinimumFractionDigits ();
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
    this.m_aCurrencyFormat.setMinimumFractionDigits (nDecimals);
    this.m_aValueFormat.setMinimumFractionDigits (nDecimals);
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
    return CurrencyUtils.parseCurrency (sRealTextValue, aCurrencyFormat, aDefault, Integer.valueOf (this.m_nScale));
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
    return CurrencyUtils.parseCurrency (sRealTextValue, aValueFormat, aDefault, Integer.valueOf (this.m_nScale));
  }

  /**
   * @return The scaling to be used for BigDecimal operations. Always &ge; 0. If
   *         no underlying JDK currency is present, {@value #DEFAULT_SCALE} is
   *         returned.
   */
  @Nonnegative
  public int getScale ()
  {
    return this.m_nScale;
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
    return this.m_eRoundingMode != null ? this.m_eRoundingMode : DEFAULT_ROUNDING_MODE;
  }

  /**
   * Change the rounding mode of this currency.
   * 
   * @param eRoundingMode
   *        The rounding mode to be used. May not be <code>null</code>.
   */
  public void setRoundingMode (@Nonnull final RoundingMode eRoundingMode)
  {
    this.m_eRoundingMode = ValueEnforcer.notNull (eRoundingMode, "RoundingMode");
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

  @Nullable
  public static ECurrency getFromLocaleOrNull (@Nullable final Locale aLocale)
  {
    return getFromLocaleOrNull (aLocale, false);
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
  public static List <ECurrency> getAllCurrencies ()
  {
    return getAllCurrencies (false);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <ECurrency> getAllCurrencies (final boolean bIncludeDeprecated)
  {
    final List <ECurrency> ret = new ArrayList <ECurrency> ();
    for (final ECurrency eCurrency : values ())
      if (!eCurrency.isDeprecated () || bIncludeDeprecated)
        ret.add (eCurrency);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <ECurrency> getAllCurrenciesWithLocaleFilterMatchingAnyLocale (@Nonnull final IFilter <Locale> aLocaleFilter)
  {
    return getAllCurrenciesWithLocaleFilterMatchingAnyLocale (aLocaleFilter, false);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <ECurrency> getAllCurrenciesWithLocaleFilterMatchingAnyLocale (@Nonnull final IFilter <Locale> aLocaleFilter,
                                                                                    final boolean bIncludeDeprecated)
  {
    ValueEnforcer.notNull (aLocaleFilter, "LocaleFilter");

    final List <ECurrency> ret = new ArrayList <ECurrency> ();
    for (final ECurrency eCurrency : values ())
      if (!eCurrency.isDeprecated () || bIncludeDeprecated)
        if (eCurrency.isLocaleFilterMatchingAnyLocale (aLocaleFilter))
          ret.add (eCurrency);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <ECurrency> getAllCurrenciesWithLocaleFilterMatchingAllLocales (@Nonnull final IFilter <Locale> aLocaleFilter)
  {
    return getAllCurrenciesWithLocaleFilterMatchingAllLocales (aLocaleFilter, false);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <ECurrency> getAllCurrenciesWithLocaleFilterMatchingAllLocales (@Nonnull final IFilter <Locale> aLocaleFilter,
                                                                                     final boolean bIncludeDeprecated)
  {
    ValueEnforcer.notNull (aLocaleFilter, "LocaleFilter");

    final List <ECurrency> ret = new ArrayList <ECurrency> ();
    for (final ECurrency eCurrency : values ())
      if (!eCurrency.isDeprecated () || bIncludeDeprecated)
        if (eCurrency.isLocaleFilterMatchingAllLocales (aLocaleFilter))
          ret.add (eCurrency);
    return ret;
  }
}
