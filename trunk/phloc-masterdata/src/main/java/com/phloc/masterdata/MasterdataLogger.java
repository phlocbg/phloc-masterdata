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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.log.InMemoryLogger;

/**
 * A singleton instance that keeps master data relevant messages, but may bloat
 * the default logging.
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public final class MasterdataLogger extends InMemoryLogger
{
  private static final MasterdataLogger s_aInstance = new MasterdataLogger ();

  private MasterdataLogger ()
  {}

  @Nonnull
  public static MasterdataLogger getInstance ()
  {
    return s_aInstance;
  }
}
