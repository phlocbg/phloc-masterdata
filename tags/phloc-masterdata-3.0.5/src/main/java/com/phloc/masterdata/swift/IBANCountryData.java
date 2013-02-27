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
package com.phloc.masterdata.swift;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.string.ToStringGenerator;
import com.phloc.datetime.period.LocalDatePeriod;

/**
 * The IBAN country data defines a list of elements that are contained within
 * the IBAN number of that country.
 * 
 * @author philip
 */
public final class IBANCountryData extends LocalDatePeriod
{
  private final List <IBANElement> m_aElements;
  private final int m_nExpectedLength;

  /**
   * @param nExpectedLength
   *        The total expected length. Serves mainly as a checksum field to
   *        check whether the length of the passed fields matches.
   * @param aElements
   *        The IBAN elements for this country. May not be <code>null</code>.
   * @param aValidFrom
   *        Validity start date. May be <code>null</code>.
   * @param aValidTo
   *        Validity end date. May be <code>null</code>.
   */
  public IBANCountryData (@Nonnegative final int nExpectedLength,
                          @Nonnull final List <IBANElement> aElements,
                          @Nullable final LocalDate aValidFrom,
                          @Nullable final LocalDate aValidTo)
  {
    super (aValidFrom, aValidTo);
    if (aElements == null)
      throw new NullPointerException ("elements");
    m_aElements = new ArrayList <IBANElement> (aElements);
    int nCalcedLength = 0;
    for (final IBANElement aChar : aElements)
      nCalcedLength += aChar.getLength ();
    if (nCalcedLength != nExpectedLength)
      throw new IllegalArgumentException ("Expected length=" + nExpectedLength + "; calced length=" + nCalcedLength);
    m_nExpectedLength = nCalcedLength;
  }

  /**
   * @return The length the complete IBAN string needs to have (incl. country
   *         code)
   */
  @Nonnegative
  public int getExpectedLength ()
  {
    return m_nExpectedLength;
  }

  /**
   * @return An unmodifiable list of all IBAN elements for this country.
   */
  @Nonnull
  @ReturnsImmutableObject
  public List <IBANElement> getElements ()
  {
    return ContainerHelper.makeUnmodifiable (m_aElements);
  }

  /**
   * Parse a given IBAN number string and convert it to elements according to
   * this country's definition of IBAN numbers.
   * 
   * @param sIBAN
   *        The IBAN number string to parse. May not be <code>null</code>.
   * @return The list of parsed elements.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <IBANElementValue> parseToElementValues (@Nonnull final String sIBAN)
  {
    if (sIBAN == null)
      throw new NullPointerException ("ibanString");

    final String sRealIBAN = IBANManager.unifyIBAN (sIBAN);
    if (sRealIBAN.length () != m_nExpectedLength)
      throw new IllegalArgumentException ("Passed IBAN has an invalid length. Expected " +
                                          m_nExpectedLength +
                                          " but found " +
                                          sRealIBAN.length ());

    final List <IBANElementValue> ret = new ArrayList <IBANElementValue> ();
    int nIndex = 0;
    for (final IBANElement aElement : m_aElements)
    {
      final String sIBANPart = sRealIBAN.substring (nIndex, nIndex + aElement.getLength ());
      ret.add (new IBANElementValue (aElement, sIBANPart));
      nIndex += aElement.getLength ();
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("elements", m_aElements)
                                       .append ("expectedLength", m_nExpectedLength)
                                       .toString ();
  }

  /**
   * This method is used to create an instance of this class from a string
   * representation.
   * 
   * @param nExpectedLength
   *        The expected length having only validation purpose.
   * @param sDesc
   *        The string description of this country data. May not be
   *        <code>null</code>.
   * @param aValidFrom
   *        Validity start date. May be <code>null</code>.
   * @param aValidTo
   *        Validity end date. May be <code>null</code>.
   * @return The parsed county data.
   */
  @Nonnull
  public static IBANCountryData createFromString (@Nonnegative final int nExpectedLength,
                                                  @Nonnull final String sDesc,
                                                  @Nullable final LocalDate aValidFrom,
                                                  @Nullable final LocalDate aValidTo)
  {
    if (sDesc == null)
      throw new NullPointerException ("desc");
    if (sDesc.length () < 4)
      throw new IllegalArgumentException ("Cannot converted passed string!");

    final List <IBANElement> aList = new ArrayList <IBANElement> ();
    aList.add (new IBANElement (EIBANElementType.COUNTRY_CODE, 2));

    EIBANElementType eLastCharType = null;
    int nLastLength = 0;
    final int len = sDesc.length ();
    for (int i = 2; i < len; ++i)
    {
      final char c = sDesc.charAt (i);

      // ignore all whitespaces
      if (Character.isWhitespace (c))
        continue;

      // resolve current character to an element type
      final EIBANElementType eCharType = EIBANElementType.getElementTypeFromChar (c);
      if (eCharType == null)
        throw new IllegalArgumentException ("Illegal char '" + c + "' in description '" + sDesc + "'");

      if (eLastCharType == null || eLastCharType == eCharType)
      {
        // first group, or same as before
        eLastCharType = eCharType;
        nLastLength++;
      }
      else
      {
        // new group
        aList.add (new IBANElement (eLastCharType, nLastLength));
        eLastCharType = eCharType;
        nLastLength = 1;
      }
    }

    // add rest
    if (nLastLength > 0)
      aList.add (new IBANElement (eLastCharType, nLastLength));

    // And we're done
    return new IBANCountryData (nExpectedLength, aList, aValidFrom, aValidTo);
  }
}
