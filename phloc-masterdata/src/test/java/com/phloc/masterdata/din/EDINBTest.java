package com.phloc.masterdata.din;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.phloc.commons.string.StringHelper;

/**
 * Unit test class for class {@link EDINB}
 * 
 * @author Philip Helger
 */
public class EDINBTest
{
  @Test
  public void testBasic ()
  {
    for (final EDINB e : EDINB.values ())
    {
      assertTrue (StringHelper.hasText (e.getID ()));
      assertSame (e, EDINB.getFromIDOrNull (e.getID ()));
      assertSame (e, EDINB.getFromIDOrDefault (e.getID (), EDINB.B0));
    }
  }

  @Test
  public void testConversion ()
  {
    assertEquals (11811, Math.round (EDINB.B0.getWidthPixel (300)));
    assertEquals (16701, Math.round (EDINB.B0.getHeightPixel (300)));
    assertEquals (5906, Math.round (EDINB.B0.getWidthPixel (150)));
    assertEquals (8350, Math.round (EDINB.B0.getHeightPixel (150)));
    assertEquals (2835, Math.round (EDINB.B0.getWidthPixel (72)));
    assertEquals (4008, Math.round (EDINB.B0.getHeightPixel (72)));
  }
}
