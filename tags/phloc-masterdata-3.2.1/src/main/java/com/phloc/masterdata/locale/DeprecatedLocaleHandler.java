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
package com.phloc.masterdata.locale;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.io.IInputStreamProvider;
import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.locale.LocaleCache;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.serialize.MicroReader;
import com.phloc.commons.string.ToStringGenerator;

/**
 * This class manages the deprecated locales.
 * 
 * @author philip
 */
public final class DeprecatedLocaleHandler
{
  private static final class SingletonHolder
  {
    static final DeprecatedLocaleHandler s_aInstance = readFromXML (new ClassPathResource ("codelists/locale-deprecated.xml"));
  }

  private static final class LocaleParts
  {
    private final String m_sLanguage;
    private final String m_sCountry;
    private final String m_sVariant;

    public LocaleParts (@Nullable final String sLanguage,
                        @Nullable final String sCountry,
                        @Nullable final String sVariant)
    {
      m_sLanguage = sLanguage;
      m_sCountry = sCountry;
      m_sVariant = sVariant;
    }

    public boolean matchesLocale (@Nonnull final Locale aLocale)
    {
      final boolean bLanguage = m_sLanguage == null || m_sLanguage.equals (aLocale.getLanguage ());
      final boolean bCountry = m_sCountry == null || m_sCountry.equals (aLocale.getCountry ());
      final boolean bVariant = m_sVariant == null || m_sVariant.equals (aLocale.getVariant ());
      return bLanguage && bCountry && bVariant;
    }
  }

  private static final Logger s_aLogger = LoggerFactory.getLogger (DeprecatedLocaleHandler.class);

  private final Set <Locale> m_aLocales = new HashSet <Locale> ();
  private final Set <LocaleParts> m_aLocaleParts = new HashSet <LocaleParts> ();

  public void initFromXML (@Nonnull final IMicroDocument aDoc)
  {
    if (aDoc == null)
      throw new NullPointerException ("doc");
    if (aDoc.getDocumentElement () == null)
      throw new IllegalArgumentException ("documentElement is null");

    m_aLocales.clear ();

    for (final IMicroElement eLocale : aDoc.getDocumentElement ().getAllChildElements ("locale"))
    {
      final String sLanguage = eLocale.getAttribute ("language");
      final String sCountry = eLocale.getAttribute ("country");
      final String sVariant = eLocale.getAttribute ("variant");

      final Locale aLocale = LocaleCache.getLocale (sLanguage, sCountry, sVariant);
      if (aLocale == null)
        s_aLogger.warn ("Deprecated locale could not be resolved!");
      else
      {
        m_aLocales.add (aLocale);
        m_aLocaleParts.add (new LocaleParts (sLanguage, sCountry, sVariant));
      }
    }
  }

  /**
   * @return A set of all locales as specified in the file.
   */
  @Nonnull
  @ReturnsImmutableObject
  public Set <Locale> getAllDeprecatedLocales ()
  {
    return ContainerHelper.makeUnmodifiable (m_aLocales);
  }

  /**
   * Check if the passed locale is directly deprecated.
   * 
   * @param aLocale
   *        The locale to check
   * @return <code>true</code> if it is deprecated
   */
  public boolean isDeprecatedLocale (@Nullable final Locale aLocale)
  {
    return m_aLocales.contains (aLocale);
  }

  /**
   * Check if the passed locale is deprecated. Also checks fallbacks (e.g. the
   * country "CS" is marked deprecated, therefore the locale "sr_CS" is also
   * implicitly deprecated)
   * 
   * @param aLocale
   *        The locale to check
   * @return <code>true</code> if it is deprecated
   */
  public boolean isDeprecatedLocaleWithFallback (@Nullable final Locale aLocale)
  {
    if (aLocale != null)
    {
      if (m_aLocales.contains (aLocale))
        return true;
      for (final LocaleParts aParts : m_aLocaleParts)
        if (aParts.matchesLocale (aLocale))
          return true;
    }
    return false;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("locales", m_aLocales).toString ();
  }

  @Nonnull
  public static DeprecatedLocaleHandler readFromXML (@Nonnull final IInputStreamProvider aISP)
  {
    if (aISP == null)
      throw new NullPointerException ("inputStreamProvider");
    return readFromXML (aISP.getInputStream ());
  }

  @Nonnull
  public static DeprecatedLocaleHandler readFromXML (@Nonnull @WillClose final InputStream aIS)
  {
    if (aIS == null)
      throw new NullPointerException ("inputStream");

    final IMicroDocument aDoc = MicroReader.readMicroXML (aIS);
    final DeprecatedLocaleHandler ret = new DeprecatedLocaleHandler ();
    ret.initFromXML (aDoc);
    return ret;
  }

  /**
   * @return The default singleton instance
   */
  @Nonnull
  public static DeprecatedLocaleHandler getDefaultInstance ()
  {
    return SingletonHolder.s_aInstance;
  }
}
