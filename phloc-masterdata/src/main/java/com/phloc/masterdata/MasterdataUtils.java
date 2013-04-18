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
package com.phloc.masterdata;

import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.phloc.commons.string.StringHelper;

/**
 * Utility methods applies to all packages.
 * 
 * @author philip
 */
@Immutable
public final class MasterdataUtils
{
  private MasterdataUtils ()
  {}

  @Nullable
  public static String getEnsuredLength (@Nullable final String s, @Nonnegative final int nMaxLen)
  {
    if (s == null)
      return null;
    final String ret = StringHelper.getCutAfterLength (s, nMaxLen, null);
    if (ret.length () < s.length ())
      MasterdataLogger.getInstance ().warn ("String value '" + s + "' was cut to length " + nMaxLen);
    return ret;
  }
}
