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
package com.phloc.masterdata.person;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ArrayHelper;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.string.StringHelper;

@Immutable
public final class PersonNameUtils
{
  /** By default complex name handling is disabled */
  public static final boolean DEFAULT_COMPLEX_NAME_HANDLING = false;
  /** By default a persons first name comes before the last name */
  public static final boolean DEFAULT_FIRST_NAME_FIRST = true;

  private static final String [] NOBILIARY_PARTICLES = new String [] { "aw",
                                                                       "da",
                                                                       "dalla",
                                                                       "de",
                                                                       "degli",
                                                                       "del",
                                                                       "dem",
                                                                       "der",
                                                                       "di",
                                                                       "du",
                                                                       "of",
                                                                       "ter",
                                                                       "thoe",
                                                                       "tot",
                                                                       "und",
                                                                       "v.",
                                                                       "van",
                                                                       "vom",
                                                                       "von",
                                                                       "zu",
                                                                       "zum" };

  private static final AtomicBoolean s_aComplexNameHandlingEnabled = new AtomicBoolean (DEFAULT_COMPLEX_NAME_HANDLING);
  private static final AtomicBoolean s_aFirstNameFirst = new AtomicBoolean (DEFAULT_FIRST_NAME_FIRST);
  private static INameHandlerDeterminator s_aDeterminator = null;

  private PersonNameUtils ()
  {}

  public static void setComplexNameHandlingEnabled (final boolean bEnabled)
  {
    s_aComplexNameHandlingEnabled.set (bEnabled);
  }

  public static boolean isComplexNameHandlingEnabled ()
  {
    return s_aComplexNameHandlingEnabled.get ();
  }

  public static void setNameHandlerDeterminator (@Nullable final INameHandlerDeterminator aDeterminator)
  {
    s_aDeterminator = aDeterminator;
  }

  private static boolean isApplyNameHandling ()
  {
    return s_aDeterminator == null || s_aDeterminator.isApplyNameHandling ();
  }

  public static void setFirstNameFirst (final boolean bFirstNameFirst)
  {
    s_aFirstNameFirst.set (bFirstNameFirst);
  }

  /**
   * Determine the order how the customer display name is assembled. This was
   * introduced for starkl.hu as they want the lastname before the firstname
   * 
   * @return <code>true</code> if the customer display name is
   *         "firstname lastname". <code>false</code> if the customer display
   *         name is "lastname firstname"
   */
  public static boolean isFirstNameFirst ()
  {
    return s_aFirstNameFirst.get ();
  }

  /**
   * Unify a single name part. Performs the following transformations:
   * <ul>
   * <li>Remove leading and trailing whitespaces</li>
   * <li>If the name is all uppercase, downcase all except the first character
   * </li>
   * </ul>
   * 
   * @param sPart
   *        The string part to be unified
   * @param aSortLocale
   *        The local to use (for case changing)
   * @return The unified part
   */
  @Nullable
  private static String _unifySinglePart (@Nonnull final String sPart, @Nonnull final Locale aSortLocale)
  {
    // empty name?
    String s = sPart.trim ();
    final int nLength = s.length ();
    if (nLength == 0)
      return null;

    // all upper case name?
    if (nLength == 1)
      return s.toUpperCase (aSortLocale);

    // uppercase first only
    s = s.substring (0, 1).toUpperCase (aSortLocale) + s.substring (1).toLowerCase (aSortLocale);

    // special cases: nobiliary particles ;-)
    if (ArrayHelper.contains (NOBILIARY_PARTICLES, s.toLowerCase (aSortLocale)))
      s = s.toLowerCase (aSortLocale);

    return s;
  }

  @Nullable
  public static String unifyName (@Nullable final String sName, @Nonnull final Locale aSortLocale)
  {
    if (sName == null)
      return null;

    if (!isApplyNameHandling ())
    {
      return sName;
    }

    if (!isComplexNameHandlingEnabled ())
    {
      // Use old compatible name handling: to upper first character
      if (sName.length () <= 1)
        return sName.toUpperCase (aSortLocale);
      return sName.substring (0, 1).toUpperCase (aSortLocale) + sName.substring (1);
    }

    // empty name?
    String s = _unifySinglePart (sName, aSortLocale);
    if (s == null)
      return null;

    // double name handling ("Hans-Peter" or "Hans Peter")
    for (final char cSep : new char [] { ' ', '-' })
    {
      final String [] aParts = StringHelper.getExplodedArray (cSep, s);
      if (aParts.length > 1)
      {
        final StringBuilder aSB = new StringBuilder (s.length ());
        for (final String sPart : aParts)
        {
          final String sRealPart = _unifySinglePart (sPart, aSortLocale);
          if (sRealPart == null)
          {
            // Ignore all empty parts (e.g. "Kai Uwe" with 2 spaces between
            continue;
          }

          if (aSB.length () > 0)
            aSB.append (cSep);
          aSB.append (sRealPart);
        }
        s = aSB.toString ();
      }
    }

    return s;
  }

  @Nonnull
  public static String getAsDisplayNameFirstNameFirst (@Nonnull final IReadonlyPersonName aName)
  {
    // Concatenate all non-empty parts
    return StringHelper.getImplodedNonEmpty (' ', aName.getFirstName (), aName.getMiddleName (), aName.getLastName ());
  }

  @Nonnull
  public static String getAsDisplayNameLastNameFirst (@Nonnull final IReadonlyPersonName aName)
  {
    // Concatenate all non-empty parts
    return StringHelper.getImplodedNonEmpty (' ', aName.getLastName (), aName.getFirstName (), aName.getMiddleName ());
  }

  /**
   * Get the name of the person consisting of first name, middle name and last
   * name. Titles are not considered here. {@link #isFirstNameFirst()} is
   * considered!
   * 
   * @param aName
   *        The name to be converted. May not be <code>null</code>.
   * @return The non-<code>null</code> display name
   */
  @Nonnull
  public static String getAsDisplayName (@Nonnull final IReadonlyPersonName aName)
  {
    if (isFirstNameFirst ())
      return getAsDisplayNameFirstNameFirst (aName);
    return getAsDisplayNameLastNameFirst (aName);
  }

  @Nonnull
  public static String getAsCompleteDisplayNameFirstNameFirst (@Nonnull final IReadonlyPersonName aName)
  {
    // Concatenate all non-empty parts
    return StringHelper.getImplodedNonEmpty (' ',
                                             aName.getPrefixTitle (),
                                             aName.getFirstName (),
                                             aName.getMiddleName (),
                                             aName.getLastName (),
                                             aName.getSuffixTitle ());
  }

  @Nonnull
  public static String getAsCompleteDisplayNameLastNameFirst (@Nonnull final IReadonlyPersonName aName)
  {
    // Concatenate all non-empty parts
    return StringHelper.getImplodedNonEmpty (' ',
                                             aName.getPrefixTitle (),
                                             aName.getLastName (),
                                             aName.getFirstName (),
                                             aName.getMiddleName (),
                                             aName.getSuffixTitle ());
  }

  /**
   * Get the display name of the person consisting of titles, first name, middle
   * name and last name. {@link #isFirstNameFirst()} is considered!
   * 
   * @param aName
   *        The name to be converted. May not be <code>null</code>.
   * @return The non-<code>null</code> display name
   */
  @Nonnull
  public static String getAsCompleteDisplayName (@Nonnull final IReadonlyPersonName aName)
  {
    if (isFirstNameFirst ())
      return getAsCompleteDisplayNameFirstNameFirst (aName);
    return getAsCompleteDisplayNameLastNameFirst (aName);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <String> getAllNobiliaryParticles ()
  {
    return ContainerHelper.newList (NOBILIARY_PARTICLES);
  }
}
