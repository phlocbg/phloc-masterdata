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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;
import com.phloc.commons.name.IHasDisplayText;

/**
 * Represents salutations for persons.
 *
 * @author Philip Helger
 */
public enum ESalutation implements IHasDisplayText, IHasID <String>
{
 MISTER ("mr", EGender.MALE, ESalutationName.MISTER, ESalutationGreeting.MISTER, ESalutationGreetingComplete.MISTER, ESalutationGreetingCompletePersonal.MISTER),
 MISSES ("mrs", EGender.FEMALE, ESalutationName.MISSES, ESalutationGreeting.MISSES, ESalutationGreetingComplete.MISSES, ESalutationGreetingCompletePersonal.MISSES),
 FAMILY ("fam", null, ESalutationName.FAMILY, ESalutationGreeting.FAMILY, ESalutationGreetingComplete.FAMILY, ESalutationGreetingCompletePersonal.FAMILY),
 COMPANY ("com", null, ESalutationName.COMPANY, ESalutationGreeting.COMPANY, ESalutationGreetingComplete.COMPANY, ESalutationGreetingCompletePersonal.COMPANY),
 CLUB ("cl", null, ESalutationName.CLUB, ESalutationGreeting.CLUB, ESalutationGreetingComplete.CLUB, ESalutationGreetingCompletePersonal.CLUB);

  private final String m_sID;
  private final EGender m_eGender;
  private final IHasDisplayText m_aName;
  private final IHasDisplayText m_aGreeting;
  private final IHasDisplayText m_aGreetingComplete;
  private final IHasDisplayText m_aGreetingCompletePersonal;

  private ESalutation (@Nonnull final String sID,
                       @Nullable final EGender eGender,
                       @Nonnull final ESalutationName eName,
                       @Nonnull final ESalutationGreeting eGreeting,
                       @Nonnull final ESalutationGreetingComplete eGreetingComplete,
                       @Nonnull final ESalutationGreetingCompletePersonal eGreetingCompletePersonal)
  {
    this.m_sID = sID;
    this.m_eGender = eGender;
    this.m_aName = eName;
    this.m_aGreeting = eGreeting;
    this.m_aGreetingComplete = eGreetingComplete;
    this.m_aGreetingCompletePersonal = eGreetingCompletePersonal;
  }

  @Override
  @Nonnull
  public String getID ()
  {
    return this.m_sID;
  }

  /**
   * @return The gender matching this salutation. May be <code>null</code> for
   *         non-individual salutations.
   */
  @Nullable
  public EGender getGender ()
  {
    return this.m_eGender;
  }

  @Nonnull
  public IHasDisplayText getName ()
  {
    return this.m_aName;
  }

  @Override
  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return this.m_aName.getDisplayText (aContentLocale);
  }

  @Nonnull
  public IHasDisplayText getGreeting ()
  {
    return this.m_aGreeting;
  }

  @Nullable
  public String getGreeting (final Locale aContentLocale)
  {
    return this.m_aGreeting.getDisplayText (aContentLocale);
  }

  @Nullable
  public String getGreetingComplete (final Locale aContentLocale)
  {
    return this.m_aGreetingComplete.getDisplayText (aContentLocale);
  }

  @Nullable
  public String getGreetingCompletePersonal (final Locale aContentLocale)
  {
    return this.m_aGreetingCompletePersonal.getDisplayText (aContentLocale);
  }

  @Nullable
  public static ESalutation getFromIDOrNull (@Nullable final String sSalutationID)
  {
    return EnumHelper.getFromIDOrNull (ESalutation.class, sSalutationID);
  }

  @Nullable
  public static ESalutation getFromIDOrDefault (@Nullable final String sSalutationID,
                                                @Nullable final ESalutation eDefault)
  {
    return EnumHelper.getFromIDOrDefault (ESalutation.class, sSalutationID, eDefault);
  }

  @Nullable
  public static ESalutation getFromDisplayNameOrNull (@Nullable final String sSalutation,
                                                      @Nonnull final Locale aContentLocale)
  {
    return getFromDisplayNameOrDefault (sSalutation, aContentLocale, null);
  }

  @Nullable
  public static ESalutation getFromDisplayNameOrDefault (@Nullable final String sSalutation,
                                                         @Nonnull final Locale aContentLocale,
                                                         @Nullable final ESalutation eDefault)
  {
    if (sSalutation != null)
      for (final ESalutation eSalutation : values ())
      {
        final String sDisplayName = eSalutation.getDisplayText (aContentLocale);
        if (sDisplayName.equals (sSalutation))
          return eSalutation;
      }
    return eDefault;
  }
}
