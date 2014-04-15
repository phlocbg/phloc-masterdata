/**
 * Copyright (C) 2006-2014 phloc systems
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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;

import com.phloc.commons.locale.country.CountryCache;

public final class ContinentUtils
{
  private static final Map <Locale, EContinent> s_aMap = new HashMap <Locale, EContinent> ();

  static
  {
    s_aMap.put (CountryCache.getCountry ("AD"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("AE"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("AF"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("AG"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("AI"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("AL"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("AM"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("AO"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("AR"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("AS"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("AT"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("AU"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("AW"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("AX"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("AZ"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("BA"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("BB"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("BD"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("BE"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("BF"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("BG"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("BH"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("BI"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("BJ"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("BL"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("BM"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("BN"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("BO"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("BR"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("BS"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("BT"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("BW"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("BY"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("BZ"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("CA"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("CC"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("CF"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("CG"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("CH"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("CI"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("CK"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("CL"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("CM"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("CN"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("CO"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("CR"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("CU"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("CV"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("CW"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("CX"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("CY"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("CZ"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("DE"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("DJ"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("DK"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("DM"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("DO"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("DZ"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("EC"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("EE"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("EG"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("ER"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("ES"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("ET"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("FI"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("FJ"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("FK"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("FM"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("FO"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("FR"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("GA"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("GB"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("GD"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("GE"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("GF"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("GG"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("GH"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("GI"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("GL"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("GM"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("GN"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("GP"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("GQ"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("GR"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("GT"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("GU"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("GW"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("GY"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("HK"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("HN"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("HR"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("HT"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("HU"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("ID"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("IE"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("IL"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("IN"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("IO"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("IQ"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("IR"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("IS"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("IT"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("JE"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("JM"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("JO"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("JP"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("KE"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("KG"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("KH"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("KI"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("KM"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("KP"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("KR"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("KW"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("KY"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("KZ"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("LA"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("LB"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("LC"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("LI"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("LK"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("LR"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("LS"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("LT"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("LU"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("LV"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("LY"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("MA"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("MC"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("MD"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("ME"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("MF"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("MG"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("MH"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("MK"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("ML"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("MM"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("MN"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("MP"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("MQ"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("MR"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("MS"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("MT"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("MU"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("MV"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("MW"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("MX"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("MY"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("MZ"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("NA"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("NC"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("NE"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("NF"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("NG"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("NI"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("NL"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("NO"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("NP"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("NR"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("NU"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("NZ"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("OM"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("PA"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("PE"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("PF"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("PG"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("PH"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("PK"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("PL"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("PR"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("PS"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("PT"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("PW"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("PY"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("QA"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("RO"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("RS"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("RU"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("RW"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("SA"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("SB"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("SC"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("SD"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("SE"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("SG"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("SI"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("SK"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("SL"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("SM"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("SN"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("SO"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("SR"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("SV"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("SY"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("SZ"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("TD"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("TG"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("TH"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("TJ"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("TK"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("TM"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("TN"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("TO"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("TR"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("TT"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("TV"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("TW"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("TZ"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("UA"), EContinent.EUROPE);
    s_aMap.put (CountryCache.getCountry ("UG"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("US"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("UY"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("UZ"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("VE"), EContinent.SOUTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("VG"), EContinent.NORTH_AMERICA);
    s_aMap.put (CountryCache.getCountry ("VN"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("VU"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("WS"), EContinent.OCEANIA);
    s_aMap.put (CountryCache.getCountry ("YE"), EContinent.ASIA);
    s_aMap.put (CountryCache.getCountry ("YT"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("ZA"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("ZM"), EContinent.AFRICA);
    s_aMap.put (CountryCache.getCountry ("ZW"), EContinent.AFRICA);
  }

  @Nullable
  public static EContinent getContinentOfCountry (@Nullable final String sCountryID)
  {
    final Locale aCountry = CountryCache.getCountry (sCountryID);
    if (aCountry == null)
      return null;
    return s_aMap.get (aCountry);
  }
}
