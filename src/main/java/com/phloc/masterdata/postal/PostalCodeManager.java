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
package com.phloc.masterdata.postal;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.io.IReadableResource;
import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.locale.country.CountryCache;
import com.phloc.commons.state.ETriState;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Manages postal code definitions for different countries.
 * 
 * @author philip
 */
@ThreadSafe
public final class PostalCodeManager
{
  public static final PostalCodeManager DEFAULT_MGR = new PostalCodeManager (new ClassPathResource ("codelists/postal-codes.xml"));

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private final Map <Locale, IPostalCodeCountry> m_aMap = new HashMap <Locale, IPostalCodeCountry> ();

  public PostalCodeManager ()
  {}

  public PostalCodeManager (@Nonnull final IReadableResource aRes)
  {
    if (aRes == null)
      throw new NullPointerException ("resource");

    final PostalCodeListReader aReader = new PostalCodeListReader (this);
    aReader.readFromFile (aRes);
  }

  public void addCountry (@Nonnull final IPostalCodeCountry aPostalCountry)
  {
    if (aPostalCountry == null)
      throw new NullPointerException ("postalCountry");

    // Unify ISO code
    final Locale aCountry = CountryCache.getCountry (aPostalCountry.getISO ());

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aMap.containsKey (aCountry))
        throw new IllegalArgumentException ("A country with code '" + aCountry + "' was already regsitered!");
      m_aMap.put (aCountry, aPostalCountry);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nullable
  public IPostalCodeCountry getPostalCountryOfCountry (@Nullable final String sISO)
  {
    return getPostalCountryOfCountry (CountryCache.getCountry (sISO));
  }

  @Nullable
  public IPostalCodeCountry getPostalCountryOfCountry (@Nullable final Locale aCountry)
  {
    final Locale aRealCountry = CountryCache.getCountry (aCountry);
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (aRealCountry);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <Locale> getAllAvailableCountries ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return ContainerHelper.newSet (m_aMap.keySet ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Check if the passed postal code is valid for the passed country.
   * 
   * @param aCountry
   *        The country to check. May be <code>null</code>.
   * @param sPostalCode
   *        The postal code to check. May be <code>null</code>.
   * @return {@link ETriState#UNDEFINED} if no information for the passed
   *         country are present, {@link ETriState#TRUE} if the postal code is
   *         valid or {@link ETriState#FALSE} if the passed postal code is
   *         explicitly not valid for the passed country.
   */
  @Nonnull
  public ETriState isValidPostalCode (@Nullable final Locale aCountry, @Nullable final String sPostalCode)
  {
    final IPostalCodeCountry aPostalCountry = getPostalCountryOfCountry (aCountry);
    if (aPostalCountry == null)
      return ETriState.UNDEFINED;
    return ETriState.valueOf (aPostalCountry.isValidPostalCode (sPostalCode));
  }

  /**
   * Check if the passed postal code is valid for the passed country. If no
   * information for that specific country is defined, the postal code is
   * assumed valid!
   * 
   * @param aCountry
   *        The country to check. May be <code>null</code>.
   * @param sPostalCode
   *        The postal code to check. May be <code>null</code>.
   * @return <code>true</code> if the postal code is valid for the passed
   *         country or if no information for that country are present,
   *         <code>false</code> otherwise.
   */
  public boolean isValidPostalCodeDefaultYes (@Nullable final Locale aCountry, @Nullable final String sPostalCode)
  {
    return isValidPostalCode (aCountry, sPostalCode).getAsBooleanValue (true);
  }

  /**
   * Check if the passed postal code is valid for the passed country. If no
   * information for that specific country is defined, the postal code is
   * assumed invalid!
   * 
   * @param aCountry
   *        The country to check. May be <code>null</code>.
   * @param sPostalCode
   *        The postal code to check. May be <code>null</code>.
   * @return <code>true</code> if the postal code is valid for the passed
   *         country, <code>false</code> otherwise also if no information for
   *         the passed country are present.
   */
  public boolean isValidPostalCodeDefaultNo (@Nullable final Locale aCountry, @Nullable final String sPostalCode)
  {
    return isValidPostalCode (aCountry, sPostalCode).getAsBooleanValue (false);
  }

  /**
   * Get a list of possible postal code examples for the passed country.
   * 
   * @param aCountry
   *        The country for which the examples are to be retrieved. May be
   *        <code>null</code>.
   * @return <code>null</code> if no postal code definitions exists for the
   *         passed country, a non-empty list with all examples otherwise.
   */
  @Nullable
  @ReturnsMutableCopy
  public List <String> getPostalCodeExamples (@Nullable final Locale aCountry)
  {
    final IPostalCodeCountry aPostalCountry = getPostalCountryOfCountry (aCountry);
    return aPostalCountry == null ? null : aPostalCountry.getAllExamples ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }
}
