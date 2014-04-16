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

import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Set;

import org.junit.Test;

import com.phloc.commons.locale.country.CountryCache;
import com.phloc.commons.string.StringHelper;

/**
 * Test class for class {@link ContinentUtils}.
 * 
 * @author Philip Helger
 */
public class ContinentUtilsTest
{
  @Test
  public void testBasic ()
  {
    assertTrue (ContinentUtils.getContinentsOfCountry ("DE").contains (EContinent.EUROPE));
    assertTrue (ContinentUtils.getContinentsOfCountry ("AT").contains (EContinent.EUROPE));
    assertTrue (ContinentUtils.getContinentsOfCountry ("AO").contains (EContinent.AFRICA));
    assertTrue (ContinentUtils.getContinentsOfCountry ("CN").contains (EContinent.ASIA));
  }

  @Test
  public void wiki ()
  {
    final String s = "AS AF AFG 4 Afghanistan, Islamic Republic of\r\n" +
                     "EU AL ALB 8 Albania, Republic of\r\n" +
                     "AN AQ ATA 10 Antarctica (the territory South of 60 deg S)\r\n" +
                     "AF DZ DZA 12 Algeria, People's Democratic Republic of\r\n" +
                     "OC AS ASM 16 American Samoa\r\n" +
                     "EU AD AND 20 Andorra, Principality of\r\n" +
                     "AF AO AGO 24 Angola, Republic of\r\n" +
                     "NA AG ATG 28 Antigua and Barbuda\r\n" +
                     "EU AZ AZE 31 Azerbaijan, Republic of\r\n" +
                     "AS AZ AZE 31 Azerbaijan, Republic of\r\n" +
                     "SA AR ARG 32 Argentina, Argentine Republic\r\n" +
                     "OC AU AUS 36 Australia, Commonwealth of\r\n" +
                     "EU AT AUT 40 Austria, Republic of\r\n" +
                     "NA BS BHS 44 Bahamas, Commonwealth of the\r\n" +
                     "AS BH BHR 48 Bahrain, Kingdom of\r\n" +
                     "AS BD BGD 50 Bangladesh, People's Republic of\r\n" +
                     "EU AM ARM 51 Armenia, Republic of\r\n" +
                     "AS AM ARM 51 Armenia, Republic of\r\n" +
                     "NA BB BRB 52 Barbados\r\n" +
                     "EU BE BEL 56 Belgium, Kingdom of\r\n" +
                     "NA BM BMU 60 Bermuda\r\n" +
                     "AS BT BTN 64 Bhutan, Kingdom of\r\n" +
                     "SA BO BOL 68 Bolivia, Republic of\r\n" +
                     "EU BA BIH 70 Bosnia and Herzegovina\r\n" +
                     "AF BW BWA 72 Botswana, Republic of\r\n" +
                     "AN BV BVT 74 Bouvet Island (Bouvetoya)\r\n" +
                     "SA BR BRA 76 Brazil, Federative Republic of\r\n" +
                     "NA BZ BLZ 84 Belize\r\n" +
                     "AS IO IOT 86 British Indian Ocean Territory (Chagos Archipelago)\r\n" +
                     "OC SB SLB 90 Solomon Islands\r\n" +
                     "NA VG VGB 92 British Virgin Islands\r\n" +
                     "AS BN BRN 96 Brunei Darussalam\r\n" +
                     "EU BG BGR 100 Bulgaria, Republic of\r\n" +
                     "AS MM MMR 104 Myanmar, Union of\r\n" +
                     "AF BI BDI 108 Burundi, Republic of\r\n" +
                     "EU BY BLR 112 Belarus, Republic of\r\n" +
                     "AS KH KHM 116 Cambodia, Kingdom of\r\n" +
                     "AF CM CMR 120 Cameroon, Republic of\r\n" +
                     "NA CA CAN 124 Canada\r\n" +
                     "AF CV CPV 132 Cape Verde, Republic of\r\n" +
                     "NA KY CYM 136 Cayman Islands\r\n" +
                     "AF CF CAF 140 Central African Republic\r\n" +
                     "AS LK LKA 144 Sri Lanka, Democratic Socialist Republic of\r\n" +
                     "AF TD TCD 148 Chad, Republic of\r\n" +
                     "SA CL CHL 152 Chile, Republic of\r\n" +
                     "AS CN CHN 156 China, People's Republic of\r\n" +
                     "AS TW TWN 158 Taiwan\r\n" +
                     "AS CX CXR 162 Christmas Island\r\n" +
                     "AS CC CCK 166 Cocos (Keeling) Islands\r\n" +
                     "SA CO COL 170 Colombia, Republic of\r\n" +
                     "AF KM COM 174 Comoros, Union of the\r\n" +
                     "AF YT MYT 175 Mayotte\r\n" +
                     "AF CG COG 178 Congo, Republic of the\r\n" +
                     "AF CD COD 180 Congo, Democratic Republic of the\r\n" +
                     "OC CK COK 184 Cook Islands\r\n" +
                     "NA CR CRI 188 Costa Rica, Republic of\r\n" +
                     "EU HR HRV 191 Croatia, Republic of\r\n" +
                     "NA CU CUB 192 Cuba, Republic of\r\n" +
                     "EU CY CYP 196 Cyprus, Republic of\r\n" +
                     "AS CY CYP 196 Cyprus, Republic of\r\n" +
                     "EU CZ CZE 203 Czech Republic\r\n" +
                     "AF BJ BEN 204 Benin, Republic of\r\n" +
                     "EU DK DNK 208 Denmark, Kingdom of\r\n" +
                     "NA DM DMA 212 Dominica, Commonwealth of\r\n" +
                     "NA DO DOM 214 Dominican Republic\r\n" +
                     "SA EC ECU 218 Ecuador, Republic of\r\n" +
                     "NA SV SLV 222 El Salvador, Republic of\r\n" +
                     "AF GQ GNQ 226 Equatorial Guinea, Republic of\r\n" +
                     "AF ET ETH 231 Ethiopia, Federal Democratic Republic of\r\n" +
                     "AF ER ERI 232 Eritrea, State of\r\n" +
                     "EU EE EST 233 Estonia, Republic of\r\n" +
                     "EU FO FRO 234 Faroe Islands\r\n" +
                     "SA FK FLK 238 Falkland Islands (Malvinas)\r\n" +
                     "AN GS SGS 239 South Georgia and the South Sandwich Islands\r\n" +
                     "OC FJ FJI 242 Fiji, Republic of the Fiji Islands\r\n" +
                     "EU FI FIN 246 Finland, Republic of\r\n" +
                     "EU AX ALA 248 Åland Islands\r\n" +
                     "EU FR FRA 250 France, French Republic\r\n" +
                     "SA GF GUF 254 French Guiana\r\n" +
                     "OC PF PYF 258 French Polynesia\r\n" +
                     "AN TF ATF 260 French Southern Territories\r\n" +
                     "AF DJ DJI 262 Djibouti, Republic of\r\n" +
                     "AF GA GAB 266 Gabon, Gabonese Republic\r\n" +
                     "EU GE GEO 268 Georgia\r\n" +
                     "AS GE GEO 268 Georgia\r\n" +
                     "AF GM GMB 270 Gambia, Republic of the\r\n" +
                     "AS PS PSE 275 Palestinian Territory, Occupied\r\n" +
                     "EU DE DEU 276 Germany, Federal Republic of\r\n" +
                     "AF GH GHA 288 Ghana, Republic of\r\n" +
                     "EU GI GIB 292 Gibraltar\r\n" +
                     "OC KI KIR 296 Kiribati, Republic of\r\n" +
                     "EU GR GRC 300 Greece, Hellenic Republic\r\n" +
                     "NA GL GRL 304 Greenland\r\n" +
                     "NA GD GRD 308 Grenada\r\n" +
                     "NA GP GLP 312 Guadeloupe\r\n" +
                     "OC GU GUM 316 Guam\r\n" +
                     "NA GT GTM 320 Guatemala, Republic of\r\n" +
                     "AF GN GIN 324 Guinea, Republic of\r\n" +
                     "SA GY GUY 328 Guyana, Co-operative Republic of\r\n" +
                     "NA HT HTI 332 Haiti, Republic of\r\n" +
                     "AN HM HMD 334 Heard Island and McDonald Islands\r\n" +
                     "EU VA VAT 336 Holy See (Vatican City State)\r\n" +
                     "NA HN HND 340 Honduras, Republic of\r\n" +
                     "AS HK HKG 344 Hong Kong, Special Administrative Region of China\r\n" +
                     "EU HU HUN 348 Hungary, Republic of\r\n" +
                     "EU IS ISL 352 Iceland, Republic of\r\n" +
                     "AS IN IND 356 India, Republic of\r\n" +
                     "AS ID IDN 360 Indonesia, Republic of\r\n" +
                     "AS IR IRN 364 Iran, Islamic Republic of\r\n" +
                     "AS IQ IRQ 368 Iraq, Republic of\r\n" +
                     "EU IE IRL 372 Ireland\r\n" +
                     "AS IL ISR 376 Israel, State of\r\n" +
                     "EU IT ITA 380 Italy, Italian Republic\r\n" +
                     "AF CI CIV 384 Cote d'Ivoire, Republic of\r\n" +
                     "NA JM JAM 388 Jamaica\r\n" +
                     "AS JP JPN 392 Japan\r\n" +
                     "EU KZ KAZ 398 Kazakhstan, Republic of\r\n" +
                     "AS KZ KAZ 398 Kazakhstan, Republic of\r\n" +
                     "AS JO JOR 400 Jordan, Hashemite Kingdom of\r\n" +
                     "AF KE KEN 404 Kenya, Republic of\r\n" +
                     "AS KP PRK 408 Korea, Democratic People's Republic of\r\n" +
                     "AS KR KOR 410 Korea, Republic of\r\n" +
                     "AS KW KWT 414 Kuwait, State of\r\n" +
                     "AS KG KGZ 417 Kyrgyz Republic\r\n" +
                     "AS LA LAO 418 Lao People's Democratic Republic\r\n" +
                     "AS LB LBN 422 Lebanon, Lebanese Republic\r\n" +
                     "AF LS LSO 426 Lesotho, Kingdom of\r\n" +
                     "EU LV LVA 428 Latvia, Republic of\r\n" +
                     "AF LR LBR 430 Liberia, Republic of\r\n" +
                     "AF LY LBY 434 Libyan Arab Jamahiriya\r\n" +
                     "EU LI LIE 438 Liechtenstein, Principality of\r\n" +
                     "EU LT LTU 440 Lithuania, Republic of\r\n" +
                     "EU LU LUX 442 Luxembourg, Grand Duchy of\r\n" +
                     "AS MO MAC 446 Macao, Special Administrative Region of China\r\n" +
                     "AF MG MDG 450 Madagascar, Republic of\r\n" +
                     "AF MW MWI 454 Malawi, Republic of\r\n" +
                     "AS MY MYS 458 Malaysia\r\n" +
                     "AS MV MDV 462 Maldives, Republic of\r\n" +
                     "AF ML MLI 466 Mali, Republic of\r\n" +
                     "EU MT MLT 470 Malta, Republic of\r\n" +
                     "NA MQ MTQ 474 Martinique\r\n" +
                     "AF MR MRT 478 Mauritania, Islamic Republic of\r\n" +
                     "AF MU MUS 480 Mauritius, Republic of\r\n" +
                     "NA MX MEX 484 Mexico, United Mexican States\r\n" +
                     "EU MC MCO 492 Monaco, Principality of\r\n" +
                     "AS MN MNG 496 Mongolia\r\n" +
                     "EU MD MDA 498 Moldova, Republic of\r\n" +
                     "EU ME MNE 499 Montenegro, Republic of\r\n" +
                     "NA MS MSR 500 Montserrat\r\n" +
                     "AF MA MAR 504 Morocco, Kingdom of\r\n" +
                     "AF MZ MOZ 508 Mozambique, Republic of\r\n" +
                     "AS OM OMN 512 Oman, Sultanate of\r\n" +
                     "AF NA NAM 516 Namibia, Republic of\r\n" +
                     "OC NR NRU 520 Nauru, Republic of\r\n" +
                     "AS NP NPL 524 Nepal, State of\r\n" +
                     "EU NL NLD 528 Netherlands, Kingdom of the\r\n" +
                     "NA AN ANT 530 Netherlands Antilles\r\n" +
                     "NA CW CUW 531 Curaçao\r\n" +
                     "NA AW ABW 533 Aruba\r\n" +
                     "NA SX SXM 534 Sint Maarten (Netherlands)\r\n" +
                     "NA BQ BES 535 Bonaire, Sint Eustatius and Saba\r\n" +
                     "OC NC NCL 540 New Caledonia\r\n" +
                     "OC VU VUT 548 Vanuatu, Republic of\r\n" +
                     "OC NZ NZL 554 New Zealand\r\n" +
                     "NA NI NIC 558 Nicaragua, Republic of\r\n" +
                     "AF NE NER 562 Niger, Republic of\r\n" +
                     "AF NG NGA 566 Nigeria, Federal Republic of\r\n" +
                     "OC NU NIU 570 Niue\r\n" +
                     "OC NF NFK 574 Norfolk Island\r\n" +
                     "EU NO NOR 578 Norway, Kingdom of\r\n" +
                     "OC MP MNP 580 Northern Mariana Islands, Commonwealth of the\r\n" +
                     "OC UM UMI 581 United States Minor Outlying Islands\r\n" +
                     "NA UM UMI 581 United States Minor Outlying Islands\r\n" +
                     "OC FM FSM 583 Micronesia, Federated States of\r\n" +
                     "OC MH MHL 584 Marshall Islands, Republic of the\r\n" +
                     "OC PW PLW 585 Palau, Republic of\r\n" +
                     "AS PK PAK 586 Pakistan, Islamic Republic of\r\n" +
                     "NA PA PAN 591 Panama, Republic of\r\n" +
                     "OC PG PNG 598 Papua New Guinea, Independent State of\r\n" +
                     "SA PY PRY 600 Paraguay, Republic of\r\n" +
                     "SA PE PER 604 Peru, Republic of\r\n" +
                     "AS PH PHL 608 Philippines, Republic of the\r\n" +
                     "OC PN PCN 612 Pitcairn Islands\r\n" +
                     "EU PL POL 616 Poland, Republic of\r\n" +
                     "EU PT PRT 620 Portugal, Portuguese Republic\r\n" +
                     "AF GW GNB 624 Guinea-Bissau, Republic of\r\n" +
                     "AS TL TLS 626 Timor-Leste, Democratic Republic of\r\n" +
                     "NA PR PRI 630 Puerto Rico, Commonwealth of\r\n" +
                     "AS QA QAT 634 Qatar, State of\r\n" +
                     "AF RE REU 638 Reunion\r\n" +
                     "EU RO ROU 642 Romania\r\n" +
                     "EU RU RUS 643 Russian Federation\r\n" +
                     "AS RU RUS 643 Russian Federation\r\n" +
                     "AF RW RWA 646 Rwanda, Republic of\r\n" +
                     "NA BL BLM 652 Saint Barthelemy\r\n" +
                     "AF SH SHN 654 Saint Helena\r\n" +
                     "NA KN KNA 659 Saint Kitts and Nevis, Federation of\r\n" +
                     "NA AI AIA 660 Anguilla\r\n" +
                     "NA LC LCA 662 Saint Lucia\r\n" +
                     "NA MF MAF 663 Saint Martin\r\n" +
                     "NA PM SPM 666 Saint Pierre and Miquelon\r\n" +
                     "NA VC VCT 670 Saint Vincent and the Grenadines\r\n" +
                     "EU SM SMR 674 San Marino, Republic of\r\n" +
                     "AF ST STP 678 Sao Tome and Principe, Democratic Republic of\r\n" +
                     "AS SA SAU 682 Saudi Arabia, Kingdom of\r\n" +
                     "AF SN SEN 686 Senegal, Republic of\r\n" +
                     "EU RS SRB 688 Serbia, Republic of\r\n" +
                     "AF SC SYC 690 Seychelles, Republic of\r\n" +
                     "AF SL SLE 694 Sierra Leone, Republic of\r\n" +
                     "AS SG SGP 702 Singapore, Republic of\r\n" +
                     "EU SK SVK 703 Slovakia (Slovak Republic)\r\n" +
                     "AS VN VNM 704 Vietnam, Socialist Republic of\r\n" +
                     "EU SI SVN 705 Slovenia, Republic of\r\n" +
                     "AF SO SOM 706 Somalia, Somali Republic\r\n" +
                     "AF ZA ZAF 710 South Africa, Republic of\r\n" +
                     "AF ZW ZWE 716 Zimbabwe, Republic of\r\n" +
                     "EU ES ESP 724 Spain, Kingdom of\r\n" +
                     "AF SS SSD 728 South Sudan\r\n" +
                     "AF EH ESH 732 Western Sahara\r\n" +
                     "AF SD SDN 736 Sudan, Republic of\r\n" +
                     "SA SR SUR 740 Suriname, Republic of\r\n" +
                     "EU SJ SJM 744 Svalbard & Jan Mayen Islands\r\n" +
                     "AF SZ SWZ 748 Swaziland, Kingdom of\r\n" +
                     "EU SE SWE 752 Sweden, Kingdom of\r\n" +
                     "EU CH CHE 756 Switzerland, Swiss Confederation\r\n" +
                     "AS SY SYR 760 Syrian Arab Republic\r\n" +
                     "AS TJ TJK 762 Tajikistan, Republic of\r\n" +
                     "AS TH THA 764 Thailand, Kingdom of\r\n" +
                     "AF TG TGO 768 Togo, Togolese Republic\r\n" +
                     "OC TK TKL 772 Tokelau\r\n" +
                     "OC TO TON 776 Tonga, Kingdom of\r\n" +
                     "NA TT TTO 780 Trinidad and Tobago, Republic of\r\n" +
                     "AS AE ARE 784 United Arab Emirates\r\n" +
                     "AF TN TUN 788 Tunisia, Tunisian Republic\r\n" +
                     "EU TR TUR 792 Turkey, Republic of\r\n" +
                     "AS TR TUR 792 Turkey, Republic of\r\n" +
                     "AS TM TKM 795 Turkmenistan\r\n" +
                     "NA TC TCA 796 Turks and Caicos Islands\r\n" +
                     "OC TV TUV 798 Tuvalu\r\n" +
                     "AF UG UGA 800 Uganda, Republic of\r\n" +
                     "EU UA UKR 804 Ukraine\r\n" +
                     "EU MK MKD 807 Macedonia, The Republic of\r\n" +
                     "AF EG EGY 818 Egypt, Arab Republic of\r\n" +
                     "EU GB GBR 826 United Kingdom of Great Britain & Northern Ireland\r\n" +
                     "EU GG GGY 831 Guernsey, Bailiwick of\r\n" +
                     "EU JE JEY 832 Jersey, Bailiwick of\r\n" +
                     "EU IM IMN 833 Isle of Man\r\n" +
                     "AF TZ TZA 834 Tanzania, United Republic of\r\n" +
                     "NA US USA 840 United States of America\r\n" +
                     "NA VI VIR 850 United States Virgin Islands\r\n" +
                     "AF BF BFA 854 Burkina Faso\r\n" +
                     "SA UY URY 858 Uruguay, Eastern Republic of\r\n" +
                     "AS UZ UZB 860 Uzbekistan, Republic of\r\n" +
                     "SA VE VEN 862 Venezuela, Bolivarian Republic of\r\n" +
                     "OC WF WLF 876 Wallis and Futuna\r\n" +
                     "OC WS WSM 882 Samoa, Independent State of\r\n" +
                     "AS YE YEM 887 Yemen\r\n" +
                     "AF ZM ZMB 894 Zambia, Republic of\r\n" +
                     "OC XX null null Disputed Territory\r\n" +
                     "AS XE null null Iraq-Saudi Arabia Neutral Zone\r\n" +
                     "AS XD null null United Nations Neutral Zone\r\n" +
                     "AS XS null null Spratly Islands".replace ("\r", "");
    int nLine = 0;
    for (final String sLine : StringHelper.getExploded ('\n', s))
    {
      final String [] p = StringHelper.getExplodedArray (' ', sLine, 5);
      final EContinent eContinent = EContinent.getFromIDOrNull (p[0].toLowerCase (Locale.US));
      if (eContinent == null)
        throw new IllegalStateException ("Continent: " + p[0] + " in line " + nLine);
      final Locale aCountry = CountryCache.getCountry (p[1]);
      if (aCountry == null)
        throw new IllegalStateException ("Country: " + p[1] + " in line " + nLine);
      ++nLine;
      final Set <EContinent> es = ContinentUtils.getContinentsOfCountry (aCountry);
      if (es != null && !es.contains (eContinent))
        System.err.println ("Here the continent for '" + p[1] + "' = " + eContinent + " but stored are " + es);
    }
  }
}
