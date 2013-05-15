package com.phloc.masterdata.locale;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.io.IReadableResource;
import com.phloc.commons.io.resource.ClassPathResource;
import com.phloc.commons.microdom.IMicroDocument;
import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.serialize.MicroReader;

public class ISO639_2Handler
{
  private static final class SingletonHolder
  {
    static final ISO639_2Handler s_aInstance = new ISO639_2Handler ().readFromResource (new ClassPathResource ("codelists/iso639-2-data-20130111.xml"));
  }

  private final Map <String, ISO639_2Item> m_aAlpha3B = new HashMap <String, ISO639_2Item> ();
  private final Map <String, ISO639_2Item> m_aAlpha3T = new HashMap <String, ISO639_2Item> ();
  private final Map <String, ISO639_2Item> m_aAlpha2 = new HashMap <String, ISO639_2Item> ();

  public ISO639_2Handler ()
  {}

  @Nonnull
  public static ISO639_2Handler getDefaultInstance ()
  {
    return SingletonHolder.s_aInstance;
  }

  @Nonnull
  public ISO639_2Handler readFromResource (@Nonnull final IReadableResource aRes)
  {
    final IMicroDocument aDoc = MicroReader.readMicroXML (aRes);
    for (final IMicroElement eItem : aDoc.getDocumentElement ().getAllChildElements ("item"))
    {
      final String sAlpha3B = eItem.getAttribute ("alpha3");
      final String sAlpha3T = eItem.getAttribute ("alpha3t");
      final String sAlpha2 = eItem.getAttribute ("alpha2");
      final String sEN = eItem.getAttribute ("en");
      final String sFR = eItem.getAttribute ("fr");
      final ISO639_2Item aItem = new ISO639_2Item (sAlpha3B, sAlpha3T, sAlpha2, sEN, sFR);
      registerItem (aItem);
    }
    return this;
  }

  public void registerItem (@Nonnull final ISO639_2Item aItem)
  {
    if (aItem == null)
      throw new NullPointerException ("item");

    m_aAlpha3B.put (aItem.getAlpha3Bibliographic (), aItem);

    final String sAlpha3T = aItem.getAlpha3Terminologic ();
    if (sAlpha3T != null)
      m_aAlpha3T.put (sAlpha3T, aItem);

    final String sAlpha2 = aItem.getAlpha2 ();
    if (sAlpha2 != null)
      m_aAlpha2.put (sAlpha2, aItem);
  }

  @Nullable
  public ISO639_2Item getItemOfAlpha3Code (@Nullable final String sAlpha3B)
  {
    return m_aAlpha3B.get (sAlpha3B);
  }

  @Nullable
  public ISO639_2Item getItemOfAlpha3TerminologicCode (@Nullable final String sAlpha3T)
  {
    return m_aAlpha3T.get (sAlpha3T);
  }

  @Nullable
  public ISO639_2Item getItemOfAlpha2Code (@Nullable final String sAlpha2)
  {
    return m_aAlpha2.get (sAlpha2);
  }
}
