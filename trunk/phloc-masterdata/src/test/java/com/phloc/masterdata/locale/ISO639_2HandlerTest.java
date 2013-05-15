package com.phloc.masterdata.locale;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Test class for class {@link ISO639_2Handler}.
 * 
 * @author Philip Helger
 */
public final class ISO639_2HandlerTest
{
  @Test
  public void testDefaultInstance ()
  {
    final ISO639_2Handler aHdl = ISO639_2Handler.getDefaultInstance ();
    assertNotNull (aHdl);
    assertNotNull (aHdl.getItemOfAlpha3Code ("ger"));
    assertNull (aHdl.getItemOfAlpha3TerminologicCode ("ger"));
    assertSame (aHdl.getItemOfAlpha3Code ("ger"), aHdl.getItemOfAlpha2Code ("de"));
  }
}
