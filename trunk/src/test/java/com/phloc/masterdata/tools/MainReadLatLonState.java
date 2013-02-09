package com.phloc.masterdata.tools;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;

import com.phloc.commons.charset.CCharset;
import com.phloc.commons.io.file.FileUtils;
import com.phloc.commons.io.resource.FileSystemResource;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.impl.MicroDocument;
import com.phloc.commons.microdom.serialize.MicroWriter;
import com.phloc.commons.string.StringParser;

/**
 * Read average county longitude and latitude from CSV. The CSV can be
 * downloaded and read as-is!
 * 
 * @author philip
 */
public final class MainReadLatLonState
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (MainReadLatLonState.class);

  public static void main (final String [] args) throws IOException
  {
    final String sRevision = "20130209";
    final String sSource = "http://dev.maxmind.com/geoip/codes/state_latlon";

    final CSVReader aReader = new CSVReader (FileSystemResource.getReader (new File ("src/test/resources/state_latlon-" +
                                                                                     sRevision +
                                                                                     ".csv"),
                                                                           CCharset.CHARSET_ISO_8859_1_OBJ));
    // Skip one row
    aReader.readNext ();

    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("root");
    final IMicroElement eHeader = eRoot.appendElement ("header");
    eHeader.appendElement ("source").appendText (sSource);
    eHeader.appendElement ("revision").appendText (sRevision);

    String [] aLine;
    while ((aLine = aReader.readNext ()) != null)
    {
      final String sISO = aLine[0];
      final BigDecimal aLatitude = StringParser.parseBigDecimal (aLine[1]);
      final BigDecimal aLongitude = StringParser.parseBigDecimal (aLine[2]);
      eRoot.appendElement ("entry")
           .setAttribute ("id", sISO)
           .setAttributeWithConversion ("latitude", aLatitude)
           .setAttributeWithConversion ("longitude", aLongitude);
    }
    aReader.close ();
    MicroWriter.writeToStream (aDoc,
                               FileUtils.getOutputStream ("src/main/resources/codelists/latitude-longitude-us-" +
                                                          sRevision +
                                                          ".xml"));
    s_aLogger.info ("Done");
  }
}
