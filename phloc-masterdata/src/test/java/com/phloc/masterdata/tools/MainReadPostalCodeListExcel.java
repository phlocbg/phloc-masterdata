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
package com.phloc.masterdata.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.annotations.ReturnsImmutableObject;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.collections.multimap.IMultiMapListBased;
import com.phloc.commons.collections.multimap.MultiHashMapArrayListBased;
import com.phloc.commons.compare.AbstractComparator;
import com.phloc.commons.io.file.FileUtils;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.impl.MicroDocument;
import com.phloc.commons.microdom.serialize.MicroWriter;
import com.phloc.commons.string.StringHelper;
import com.phloc.datetime.PDTFactory;
import com.phloc.datetime.PDTUtils;
import com.phloc.masterdata.postal.PostalCodeListReader;
import com.phloc.poi.excel.ExcelReadUtils;

public class MainReadPostalCodeListExcel
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (MainReadPostalCodeListExcel.class);
  private static final String PREFIX_ONE_CODE = "one code: ";
  private static final String NO_CODES = "- no codes -";

  private static final class Item
  {
    private final String m_sCountry;
    private final LocalDate m_aValidFrom;
    private LocalDate m_aValidTo;
    private final String m_sISO;
    private final List <String> m_aFormats;
    private final String m_sNote;

    public Item (@Nonnull @Nonempty final String sCountry,
                 @Nullable final Date aValidFrom,
                 @Nonnull @Nonempty final String sISO,
                 @Nonnull @Nonempty final List <String> aFormats,
                 @Nullable final String sNote)
    {
      if (StringHelper.hasNoText (sCountry))
        throw new IllegalArgumentException ("country");
      if (StringHelper.hasNoText (sISO))
        throw new IllegalArgumentException ("ISO");
      if (ContainerHelper.isEmpty (aFormats))
        throw new IllegalArgumentException ("formats");
      this.m_sCountry = sCountry;
      this.m_aValidFrom = aValidFrom == null ? null : PDTFactory.createLocalDateFromMillis (aValidFrom.getTime ());
      this.m_sISO = sISO;
      this.m_aFormats = aFormats;
      this.m_sNote = sNote;
    }

    @Nonnull
    @Nonempty
    public String getCountry ()
    {
      return this.m_sCountry;
    }

    @Nullable
    public LocalDate getValidFrom ()
    {
      return this.m_aValidFrom;
    }

    @Nullable
    public LocalDate getValidTo ()
    {
      return this.m_aValidTo;
    }

    public void setValidTo (final LocalDate aValidTo)
    {
      this.m_aValidTo = aValidTo;
    }

    @Nonnull
    @Nonempty
    public String getISO ()
    {
      return this.m_sISO;
    }

    @Nonnull
    @ReturnsImmutableObject
    public List <String> getFormats ()
    {
      return ContainerHelper.makeUnmodifiable (this.m_aFormats);
    }

    @Nullable
    public String getNote ()
    {
      return this.m_sNote;
    }
  }

  private static final class ComparatorItemValidFrom extends AbstractComparator <Item>
  {
    @Override
    protected int mainCompare (final Item aElement1, final Item aElement2)
    {
      return PDTUtils.nullSafeCompare (aElement1.getValidFrom (), aElement2.getValidFrom ());
    }
  }

  public static void main (final String [] args) throws Exception
  {
    final String sSource = "http://en.wikipedia.org/wiki/List_of_postal_codes";
    final String sRevision = "20130209";

    final File f = new File ("src/test/resources/" + sRevision + "PostalCodes.xls");
    final Workbook aWB = new HSSFWorkbook (FileUtils.getInputStream (f));
    final Sheet aSheet = aWB.getSheetAt (0);
    final Iterator <Row> it = aSheet.rowIterator ();

    // Skip 1 row
    it.next ();

    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (PostalCodeListReader.ELEMENT_ROOT);
    final IMicroElement eHeader = eRoot.appendElement (PostalCodeListReader.ELEMENT_HEADER);
    eHeader.appendElement (PostalCodeListReader.ELEMENT_SOURCE).appendText (sSource);
    eHeader.appendElement (PostalCodeListReader.ELEMENT_REVISION).appendText (sRevision);

    final IMicroElement eBody = eRoot.appendElement (PostalCodeListReader.ELEMENT_BODY);
    final List <Item> aItems = new ArrayList <Item> ();
    int nRow = 0;
    while (it.hasNext ())
    {
      final Row aRow = it.next ();
      ++nRow;
      final String sCountry = ExcelReadUtils.getCellValueString (aRow.getCell (0));
      if (StringHelper.hasNoText (sCountry))
      {
        s_aLogger.warn ("Line " + nRow + ": No country name present");
        continue;
      }
      final Cell aDateCell = aRow.getCell (1);
      Date aIntroducedDate = null;
      if (aDateCell != null && aDateCell.getCellType () != CellType.BLANK)
      {
        final Number aNum = ExcelReadUtils.getCellValueNumber (aDateCell);
        final int nYear = aNum.intValue ();
        if (nYear > 1800 && nYear < 3000)
          aIntroducedDate = PDTFactory.createLocalDate (nYear, DateTimeConstants.JANUARY, 1).toDate ();
        else
          aIntroducedDate = ExcelReadUtils.getCellValueJavaDate (aDateCell);
      }
      final String sISO = ExcelReadUtils.getCellValueString (aRow.getCell (2));
      if (StringHelper.hasNoText (sISO))
      {
        s_aLogger.warn ("Line " + nRow + ": No ISO code for " + sCountry);
        continue;
      }
      final String sFormat = ExcelReadUtils.getCellValueString (aRow.getCell (3));
      if (NO_CODES.equals (sFormat) || StringHelper.hasNoText (sFormat))
        continue;
      final List <String> aFormats = StringHelper.getExploded ("\n", sFormat);
      final String sNote = ExcelReadUtils.getCellValueString (aRow.getCell (4));
      aItems.add (new Item (sCountry, aIntroducedDate, sISO, aFormats, sNote));
    }

    // Convert to map, where the key is the ISO
    final IMultiMapListBased <String, Item> aMap = new MultiHashMapArrayListBased <String, Item> ();
    for (final Item aItem : aItems)
      aMap.putSingle (aItem.getISO (), aItem);

    // Sort all sub-lists by introduction date
    for (final List <Item> aSubList : aMap.values ())
    {
      ContainerHelper.getSortedInline (aSubList, new ComparatorItemValidFrom ());
      for (int i = 1; i < aSubList.size (); ++i)
      {
        final Item aPrevItem = aSubList.get (i - 1);
        final Item aThisItem = aSubList.get (i);
        if (aThisItem.getValidFrom () != null)
          aPrevItem.setValidTo (aThisItem.getValidFrom ().minusDays (1));
      }
    }

    // Print sorted by ISO code
    for (final Map.Entry <String, List <Item>> aEntry : ContainerHelper.getSortedByKey (aMap).entrySet ())
    {
      IMicroElement eCountry = null;
      for (final Item aItem : aEntry.getValue ())
      {
        if (eCountry == null)
        {
          // First item - ISO and name only once
          eCountry = eBody.appendElement (PostalCodeListReader.ELEMENT_COUNTRY);
          eCountry.setAttribute (PostalCodeListReader.ATTR_ISO, aItem.getISO ());
          eCountry.setAttribute (PostalCodeListReader.ATTR_NAME, aItem.getCountry ());
        }

        final IMicroElement ePostalCodes = eCountry.appendElement (PostalCodeListReader.ELEMENT_POSTALCODES);
        if (aItem.getValidFrom () != null)
          ePostalCodes.setAttribute (PostalCodeListReader.ATTR_VALIDFROM,
                                     ISODateTimeFormat.date ().print (aItem.getValidFrom ()));
        if (aItem.getValidTo () != null)
          ePostalCodes.setAttribute (PostalCodeListReader.ATTR_VALIDTO,
                                     ISODateTimeFormat.date ().print (aItem.getValidTo ()));
        for (final String sSingleFormat : aItem.getFormats ())
          if (sSingleFormat.startsWith (PREFIX_ONE_CODE))
            ePostalCodes.appendElement (PostalCodeListReader.ELEMENT_SPECIFIC)
                        .appendText (sSingleFormat.substring (PREFIX_ONE_CODE.length ()));
          else
          {

            ePostalCodes.appendElement (PostalCodeListReader.ELEMENT_FORMAT).appendText (sSingleFormat);
          }
        if (StringHelper.hasText (aItem.getNote ()))
          ePostalCodes.appendElement (PostalCodeListReader.ELEMENT_NOTE).appendText (aItem.getNote ());
      }
    }

    MicroWriter.writeToStream (aDoc,
                               FileUtils.getOutputStream ("src/main/resources/codelists/postal-codes-" +
                                                          sRevision +
                                                          ".xml"));
    s_aLogger.info ("Done");
  }
}
