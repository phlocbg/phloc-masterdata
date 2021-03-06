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
package com.phloc.masterdata.vat;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;
import javax.annotation.concurrent.NotThreadSafe;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.io.IInputStreamProvider;
import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.locale.LocaleUtils;
import com.phloc.commons.locale.country.CountryCache;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.serialize.MicroReader;
import com.phloc.commons.microdom.utils.MicroUtils;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.StringParser;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.datetime.format.PDTFromString;

/**
 * Manages the available VAT types.
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public class VATManager implements IVATItemResolver
{
  private static final class SingletonHolder
  {
    static final VATManager s_aInstance = readFromXML (new ClassPathResource ("codelists/vat-data.xml"));
  }

  /** Special VAT item with 0% */
  public static final IVATItem VATTYPE_NONE = new VATItem ("_none_", EVATType.OTHER, BigDecimal.ZERO, false);

  private static final Logger s_aLogger = LoggerFactory.getLogger (VATManager.class);
  private static final String DATE_FORMAT = "yyyy-MM-dd";

  // The sources the data comes from
  private final List <String> m_aSources = new ArrayList <String> ();

  // Maps from locale to the available VAT data
  private final Map <Locale, VATCountryData> m_aVATItemsPerCountry = new HashMap <Locale, VATCountryData> ();

  private final Map <Locale, String> m_aVATDefaultPerCountry = ContainerHelper.newMap ();

  // Overall VAT map (ID to item)
  private final Map <String, IVATItem> m_aAllVATItems = new HashMap <String, IVATItem> ();

  public VATManager ()
  {}

  public VATManager (@Nonnull final VATManager aBaseManager)
  {
    this.m_aSources.addAll (aBaseManager.m_aSources);
    this.m_aVATItemsPerCountry.putAll (aBaseManager.m_aVATItemsPerCountry);
    this.m_aAllVATItems.putAll (aBaseManager.m_aAllVATItems);
  }

  @Nullable
  private static String _getCountryString (@Nullable final Locale aLocale)
  {
    if (aLocale == null)
      return null;

    // Is it "all" or "independent"?
    if (LocaleUtils.isSpecialLocale (aLocale))
      return aLocale.getLanguage ();
    return aLocale.getCountry ().toLowerCase (Locale.US);
  }

  public void initFromXML (@Nonnull final IMicroDocument aDoc)
  {
    ValueEnforcer.notNull (aDoc, "Doc");
    ValueEnforcer.notNull (aDoc.getDocumentElement (), "Doc.DocumentElement");

    this.m_aSources.clear ();
    this.m_aVATItemsPerCountry.clear ();
    this.m_aAllVATItems.clear ();

    final IMicroElement eSources = aDoc.getDocumentElement ().getFirstChildElement ("sources");
    if (eSources != null)
      for (final IMicroElement eSource : eSources.getAllChildElements ("source"))
      {
        final String sSource = eSource.getTextContent ();
        if (StringHelper.hasText (sSource))
          this.m_aSources.add (sSource);
      }

    for (final IMicroElement eVATTypes : aDoc.getDocumentElement ().getAllChildElements ("vattypes"))
    {
      // Country
      final String sCountry = eVATTypes.getAttribute ("country");
      final Locale aCountry = CountryCache.getCountry (sCountry);
      if (this.m_aVATItemsPerCountry.containsKey (aCountry))
      {
        s_aLogger.warn ("VAT types for country " + aCountry + " have already been defined!");
        continue;
      }
      final String sCountryName = eVATTypes.getAttribute ("countryname");

      // zero VAT allowed?
      final String sZeroVATAllowed = eVATTypes.getAttribute ("zerovat");
      final boolean bZeroVATAllowed = StringParser.parseBool (sZeroVATAllowed);

      // Internal comment?
      final String sInternalComment = MicroUtils.getChildTextContent (eVATTypes, "comment");

      // read all items
      final VATCountryData aVATCountryData = new VATCountryData (aCountry,
                                                                 bZeroVATAllowed,
                                                                 sCountryName,
                                                                 sInternalComment);
      for (final IMicroElement eVATItem : eVATTypes.getAllChildElements ("item"))
      {
        // item ID
        final String sID = eVATItem.getAttribute ("id");
        if (StringHelper.hasNoText (sID))
        {
          s_aLogger.warn ("VAT item in country " + aCountry + " has no ID. Skipping VAT item.");
          continue;
        }
        final String sRealID = _getCountryString (aCountry) + "." + sID;

        // item type
        final String sType = eVATItem.getAttribute ("type");
        final EVATType eType = EVATType.getFromIDOrNull (sType);
        if (eType == null)
        {
          s_aLogger.warn ("VAT type '" + sType + "' for VAT item " + sRealID + " is illegal. Skipping VAT item.");
          continue;
        }

        // item percentage
        final String sPercentage = eVATItem.getAttribute ("percentage");
        final BigDecimal aPercentage = StringParser.parseBigDecimal (sPercentage, null);
        if (aPercentage == null)
        {
          s_aLogger.warn ("Percentage value '" +
                          sPercentage +
                          "' for VAT item " +
                          sRealID +
                          " is illegal. Skipping VAT item.");
          continue;
        }

        // Deprecated?
        final String sDeprecated = eVATItem.getAttribute ("deprecated");
        final boolean bDeprecated = sDeprecated != null && StringParser.parseBool (sDeprecated);

        // Valid from (optional)
        final String sValidFrom = eVATItem.getAttribute ("validfrom");
        final LocalDate aValidFrom = PDTFromString.getLocalDateFromString (sValidFrom, DATE_FORMAT);

        // Valid to (optional)
        final String sValidTo = eVATItem.getAttribute ("validto");
        final LocalDate aValidTo = PDTFromString.getLocalDateFromString (sValidTo, DATE_FORMAT);

        // build and add item
        final VATItem aVATItem = new VATItem (sRealID, eType, aPercentage, bDeprecated, aValidFrom, aValidTo);
        if (aVATCountryData.addItem (aVATItem).isUnchanged ())
          s_aLogger.warn ("Found duplicate VAT item " + aVATItem + " for country " + aCountry);
        if (this.m_aAllVATItems.put (sRealID, aVATItem) != null)
          s_aLogger.warn ("Found overall duplicate VAT item " + aVATItem);
      }

      if (aVATCountryData.isEmpty ())
        s_aLogger.warn ("No VAT types for country " + aCountry + " defined!");
      this.m_aVATItemsPerCountry.put (aCountry, aVATCountryData);
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getSources ()
  {
    return ContainerHelper.newList (this.m_aSources);
  }

  /**
   * @return All countries for which VAT type definitions are present.
   */
  @Nonnull
  @ReturnsMutableCopy
  public Set <Locale> getAllAvailableCountries ()
  {
    return ContainerHelper.newSet (this.m_aVATItemsPerCountry.keySet ());
  }

  /**
   * Check if zero VAT is allowed for the passed country
   * 
   * @param aCountry
   *        The country to be checked.
   * @param bUndefinedValue
   *        The value to be returned, if no VAT data is available for the passed
   *        country
   * @return <code>true</code> or <code>false</code>
   */
  public boolean isZeroVATAllowed (@Nonnull final Locale aCountry, final boolean bUndefinedValue)
  {
    ValueEnforcer.notNull (aCountry, "Country");

    // first get locale specific VAT types
    final VATCountryData aVATCountryData = this.m_aVATItemsPerCountry.get (CountryCache.getCountry (aCountry));
    return aVATCountryData != null ? aVATCountryData.isZeroVATAllowed () : bUndefinedValue;
  }

  /**
   * Get all VAT types matching the given locale (without any fallback!). It
   * contains both the specific definitions and the locale independent
   * definitions.
   * 
   * @param aCountry
   *        The locale to use. May not be <code>null</code>.
   * @return A non-<code>null</code> map from ID to the matching VAT item. Also
   *         the deprecated VAT items are returned! VATTYPE_NONE.getID () is
   *         used if zero VAT is allowed
   */
  @Nonnull
  public Map <String, IVATItem> getAllVATItemsForCountry (@Nonnull final Locale aCountry)
  {
    ValueEnforcer.notNull (aCountry, "Country");

    final Map <String, IVATItem> ret = new HashMap <String, IVATItem> ();

    // first get locale specific VAT types
    final VATCountryData aVATCountryData = this.m_aVATItemsPerCountry.get (CountryCache.getCountry (aCountry));
    if (aVATCountryData != null)
    {
      if (aVATCountryData.isZeroVATAllowed ())
        ret.put (VATTYPE_NONE.getID (), VATTYPE_NONE);
      ret.putAll (aVATCountryData.getAllItems ());
    }
    return ret;
  }

  public void setVATDefaultForCountry (@Nonnull final Locale aCountry, final String sDefaultID)
  {
    ValueEnforcer.notNull (aCountry, "aCountry");
    final Locale aCtr = CountryCache.getCountry (aCountry);
    if (StringHelper.hasText (sDefaultID))
    {
      this.m_aVATDefaultPerCountry.put (aCtr, sDefaultID);
    }
    else
    {
      this.m_aVATDefaultPerCountry.remove (aCtr);
    }
  }

  @Nullable
  public IVATItem getVATDefaultForCountry (@Nonnull final Locale aCountry)
  {
    ValueEnforcer.notNull (aCountry, "Country");
    final Locale aCtr = CountryCache.getCountry (aCountry);
    final String sDefault = this.m_aVATDefaultPerCountry.get (aCtr);
    if (StringHelper.hasText (sDefault))
    {
      final VATCountryData aVATCountryData = this.m_aVATItemsPerCountry.get (CountryCache.getCountry (aCountry));
      if (aVATCountryData != null)
      {
        return aVATCountryData.getAllItems ().get (sDefault);
      }
    }
    return null;
  }

  /**
   * Get the VAT type with the given ID.
   * 
   * @param sID
   *        The VAT type ID to search.
   * @return <code>null</code> if no such VAT type exists.
   */
  @Override
  @Nullable
  public IVATItem getVATItemOfID (@Nullable final String sID)
  {
    IVATItem ret = this.m_aAllVATItems.get (sID);
    if (ret == null && VATTYPE_NONE.getID ().equals (sID))
      ret = VATTYPE_NONE;
    return ret;
  }

  @Override
  @Nullable
  public IVATItem getVATItemOfID (@Nonnull final Locale aCountry, @Nullable final String sID)
  {
    return getVATItemOfID (_getCountryString (aCountry) + "." + sID);
  }

  /**
   * Find a matching VAT item with the passed properties, independent of the
   * country.
   * 
   * @param eType
   *        The VAT type to use. May be <code>null</code> resulting in a
   *        <code>null</code> result.
   * @param aPercentage
   *        The percentage to find. May be <code>null</code> resulting in a
   *        <code>null</code> result.
   * @return <code>null</code> if no matching item could be found,
   */
  @Nullable
  public IVATItem findVATItem (@Nullable final EVATType eType, @Nullable final BigDecimal aPercentage)
  {
    if (eType != null && aPercentage != null)
      for (final IVATItem aVATItem : this.m_aAllVATItems.values ())
        if (aVATItem.getType ().equals (eType) && EqualsUtils.equals (aVATItem.getPercentage (), aPercentage))
          return aVATItem;
    return null;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("sources", this.m_aSources)
                                       .append ("VATItemsPerCountry", this.m_aVATItemsPerCountry)
                                       .append ("allVATItems", this.m_aAllVATItems)
                                       .toString ();
  }

  @Nonnull
  public static VATManager readFromXML (@Nonnull final IInputStreamProvider aISP)
  {
    ValueEnforcer.notNull (aISP, "InputStreamProvider");

    return readFromXML (aISP.getInputStream ());
  }

  @Nonnull
  public static VATManager readFromXML (@Nonnull @WillClose final InputStream aIS)
  {
    ValueEnforcer.notNull (aIS, "InputStream");

    final IMicroDocument aDoc = MicroReader.readMicroXML (aIS);
    final VATManager ret = new VATManager ();
    ret.initFromXML (aDoc);
    return ret;
  }

  /**
   * @return The default singleton instance. Never <code>null</code>.
   */
  @Nonnull
  public static VATManager getDefaultInstance ()
  {
    return SingletonHolder.s_aInstance;
  }
}
