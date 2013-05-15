package com.phloc.masterdata.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import com.phloc.commons.charset.CCharset;
import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.impl.MicroDocument;
import com.phloc.commons.microdom.serialize.MicroWriter;
import com.phloc.commons.string.StringHelper;

/**
 * Source of the file: http://www.loc.gov/standards/iso639-2/ISO-639-2_utf-8.txt
 * 
 * @author Philip Helger
 */
public class MainReadISO639_2CodeList
{
  public static void main (final String [] args) throws IOException
  {
    final String sRevision = "20130111";
    final BufferedReader aReader = new BufferedReader (new ClassPathResource ("ISO-639-2_utf-8.txt").getReader (CCharset.CHARSET_UTF_8_OBJ));
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("iso639-2");
    String sLine;
    while ((sLine = aReader.readLine ()) != null)
    {
      // An alpha-3 (bibliographic) code
      // an alpha-3 (terminologic) code (when given)
      // an alpha-2 code (when given)
      // an English name
      // a French name
      final String [] aParts = StringHelper.getExplodedArray ('|', sLine);
      if (aParts.length != 5)
        throw new IllegalStateException ();
      final String sAlpha3B = aParts[0];
      final String sAlpha3T = aParts[1];
      final String sAlpha2 = aParts[2];
      final String sEN = aParts[3];
      final String sFR = aParts[4];

      if (StringHelper.hasNoText (sAlpha3B))
        throw new IllegalArgumentException ("Alpha3B");
      if (StringHelper.hasNoText (sEN))
        throw new IllegalArgumentException ("EN");
      if (StringHelper.hasNoText (sFR))
        throw new IllegalArgumentException ("FR");

      final IMicroElement eItem = eRoot.appendElement ("item");
      eItem.setAttribute ("alpha3", sAlpha3B);
      if (StringHelper.hasText (sAlpha3T))
        eItem.setAttribute ("alpha3t", sAlpha3T);
      if (StringHelper.hasText (sAlpha2))
        eItem.setAttribute ("alpha2", sAlpha2);
      eItem.setAttribute ("en", sEN);
      eItem.setAttribute ("fr", sFR);
    }
    MicroWriter.writeToFile (aDoc, new File ("src/main/resources/codelists/iso639-2-data-" + sRevision + ".xml"));
  }
}
