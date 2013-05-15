package com.phloc.masterdata.locale;

import static org.junit.Assert.assertNotNull;

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
  }
}
