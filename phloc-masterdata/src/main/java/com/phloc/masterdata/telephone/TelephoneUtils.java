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
package com.phloc.masterdata.telephone;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.regex.RegExHelper;
import com.phloc.commons.string.StringHelper;

/**
 * Telephone handling utils.
 * 
 * @author Philip Helger
 */
@Immutable
public final class TelephoneUtils
{
  private TelephoneUtils ()
  {}

  /**
   * Get the string representation of this phone number
   * 
   * @param aTelNo
   *        The telephone number to print
   * @return The resulting string in format <code>+43 (0) 1 345678-23</code>
   */
  @Nullable
  @CheckReturnValue
  public static String getTelephoneStringExtended (@Nullable final IReadonlyTelephoneNumber aTelNo)
  {
    if (aTelNo == null)
      return null;

    final StringBuilder ret = new StringBuilder ();
    // Country and area code
    if (StringHelper.hasText (aTelNo.getCountryCode ()) && StringHelper.hasText (aTelNo.getAreaCode ()))
    {
      // prepend "+" if necessary
      if (!StringHelper.startsWith (aTelNo.getCountryCode (), '+'))
        ret.append ('+');
      ret.append (aTelNo.getCountryCode ()).append (' ');

      // area code
      if (StringHelper.startsWith (aTelNo.getAreaCode (), '0'))
      {
        ret.append ("(0) ");
        ret.append (aTelNo.getAreaCode ().substring (1));
      }
      else
      {
        ret.append (aTelNo.getAreaCode ());
      }
    }
    else
      if (aTelNo.getAreaCode () != null)
        ret.append (aTelNo.getAreaCode ());

    if (ret.length () > 0)
    {
      ret.append (' ');
    }
    // main line
    if (aTelNo.getLine () != null)
      ret.append (aTelNo.getLine ());

    // direct dial
    if (StringHelper.hasText (aTelNo.getDirectDial ()))
      ret.append ('-').append (aTelNo.getDirectDial ());
    return ret.toString ();
  }

  /**
   * Get the string representation of this phone number
   * 
   * @param aTelNo
   *        The telephone number to print
   * @return The resulting string in format <code>+43/1/345678-23</code>
   */
  @Nullable
  @CheckReturnValue
  public static String getTelephoneString (@Nullable final IReadonlyTelephoneNumber aTelNo)
  {
    if (aTelNo == null)
      return null;

    final StringBuilder ret = new StringBuilder ();
    // Country and area code
    if (StringHelper.hasText (aTelNo.getCountryCode ()) && StringHelper.hasText (aTelNo.getAreaCode ()))
    {
      // prepend "+" if necessary
      if (!StringHelper.startsWith (aTelNo.getCountryCode (), '+'))
        ret.append ('+');
      ret.append (aTelNo.getCountryCode ()).append ('/');

      // area code
      if (StringHelper.startsWith (aTelNo.getAreaCode (), '0'))
        ret.append (aTelNo.getAreaCode ().substring (1));
      else
        ret.append (aTelNo.getAreaCode ());
    }
    else
      if (aTelNo.getAreaCode () != null)
        ret.append (aTelNo.getAreaCode ());

    if (ret.length () > 0)
      ret.append ('/');

    // main line
    if (aTelNo.getLine () != null)
      ret.append (aTelNo.getLine ());

    // direct dial
    if (StringHelper.hasText (aTelNo.getDirectDial ()))
      ret.append ('-').append (aTelNo.getDirectDial ());
    return ret.toString ();
  }

  @Nullable
  @CheckReturnValue
  public static String getCleanedLine (@Nullable final String sLine)
  {
    String ret = StringHelper.trim (sLine);
    if (StringHelper.hasText (ret))
    {
      // Remove the Skype highlighting :)
      ret = RegExHelper.stringReplacePattern ("begin_of_the_skype_highlighting.+end_of_the_skype_highlighting",
                                              ret,
                                              "");
    }
    return ret;
  }
}
