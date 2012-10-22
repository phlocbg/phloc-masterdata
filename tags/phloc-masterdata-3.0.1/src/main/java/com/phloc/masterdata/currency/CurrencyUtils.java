/**
 * Copyright (C) 2006-2012 phloc systems
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
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.locale.LocaleCache;
import com.phloc.commons.locale.LocaleFormatter;

/**
 * Some currency helper methods.
 * 
 * @author philip
 */
@Immutable
public final class CurrencyUtils
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (CurrencyUtils.class);

  // Sorted set of all available currencies
  private static Set <Currency> s_aAllCurrencies = new TreeSet <Currency> (new ComparatorCurrencyCode ());

  static
  {
    // For all locales
    for (final Locale aLocale : LocaleCache.getAllLocales ())
      if (aLocale.getCountry ().length () == 2)
      {
        try
        {
          final Currency aCurrency = Currency.getInstance (aLocale);
          if (aCurrency != null)
            s_aAllCurrencies.add (aCurrency);
        }
        catch (final IllegalArgumentException ex)
        {
          // Happens e.g. if unit tests fill the locale cache with obscure
          // values
          s_aLogger.warn ("Failed to retrieve currency of locale " + aLocale.toString ());
        }
      }
  }

  private CurrencyUtils ()
  {}

  @Nonnull
  @ReturnsImmutableObject
  public static Set <Currency> getAllSupportedCurrencies ()
  {
    return ContainerHelper.makeUnmodifiable (s_aAllCurrencies);
  }

  public static boolean isSupportedCurrency (@Nullable final Currency aCurrency)
  {
    return s_aAllCurrencies.contains (aCurrency);
  }

  public static boolean isSupportedCurrency (@Nonnull final ECurrency eCurrency)
  {
    return isSupportedCurrency (eCurrency.getAsCurrency ());
  }

  public static boolean isSupportedCurrencyCode (@Nonnull final String sCurrencyCode)
  {
    try
    {
      return isSupportedCurrency (Currency.getInstance (sCurrencyCode));
    }
    catch (final IllegalArgumentException ex)
    {
      // No such currency code
      return false;
    }
  }

  /**
   * Check if a currency could be available for the given locale.
   * 
   * @param aLocale
   *        The locale to check.
   * @return <code>true</code> if a currency is available for the given locale,
   *         <code>false</code> otherwise.
   */
  public static boolean localeSupportsCurrencyRetrieval (@Nullable final Locale aLocale)
  {
    return aLocale != null && aLocale.getCountry () != null && aLocale.getCountry ().length () == 2;
  }

  @Nullable
  public static Currency getCurrencyOfLocale (@Nonnull final Locale aContentLocale)
  {
    if (!localeSupportsCurrencyRetrieval (aContentLocale))
      throw new IllegalArgumentException ("Cannot get currency of locale " + aContentLocale);

    return Currency.getInstance (aContentLocale);
  }

  /**
   * Parse a currency value from string.<br>
   * Source:
   * <code>http://wheelworkshop.blogspot.com/2006/02/parsing-currency-into-bigdecimal.html</code>
   * 
   * @param sStr
   *        The string to be parsed.
   * @param aFormat
   *        The formatting object to be used. May not be <code>null</code>.
   * @param aDefault
   *        The default value to be returned, if parsing failed.
   * @return Either default value or the {@link BigDecimal} value with the
   *         correct scaling.
   */
  @Nullable
  public static BigDecimal parseCurrency (@Nullable final String sStr,
                                          @Nonnull final DecimalFormat aFormat,
                                          @Nullable final BigDecimal aDefault)
  {
    // So that the call to "parse" returns a BigDecimal
    aFormat.setParseBigDecimal (true);
    aFormat.setRoundingMode (ECurrency.ROUNDING_MODE);

    // Parse as double
    final BigDecimal aNum = LocaleFormatter.parseBigDecimal (sStr, aFormat);
    if (aNum == null)
      return aDefault;

    // And finally do the correct scaling, depending of the decimal format
    // fraction
    return aNum.setScale (aFormat.getMaximumFractionDigits (), ECurrency.ROUNDING_MODE);
  }
}
