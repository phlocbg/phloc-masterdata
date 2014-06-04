package com.phloc.masterdata.din;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.phloc.commons.string.StringHelper;

/**
 * Unit test class for class {@link EDINC}
 * 
 * @author Philip Helger
 */
public class EDINCTest
{
  @Test
  public void testBasic ()
  {
    for (final EDINC e : EDINC.values ())
    {
      assertTrue (StringHelper.hasText (e.getID ()));
      assertSame (e, EDINC.getFromIDOrNull (e.getID ()));
      assertSame (e, EDINC.getFromIDOrDefault (e.getID (), EDINC.C0));
    }
  }

  @Test
  public void testConversion ()
  {
    assertEquals (10831, Math.round (EDINC.C0.getWidthPixel (300)));
    assertEquals (15319, Math.round (EDINC.C0.getHeightPixel (300)));
    assertEquals (5415, Math.round (EDINC.C0.getWidthPixel (150)));
    assertEquals (7659, Math.round (EDINC.C0.getHeightPixel (150)));
    assertEquals (2599, Math.round (EDINC.C0.getWidthPixel (72)));
    assertEquals (3677, Math.round (EDINC.C0.getHeightPixel (72)));

    assertEquals (10831, EDINC.C0.getWidthPixelLong (300));
    assertEquals (15319, EDINC.C0.getHeightPixelLong (300));
    assertEquals (5415, EDINC.C0.getWidthPixelLong (150));
    assertEquals (7659, EDINC.C0.getHeightPixelLong (150));
    assertEquals (2599, EDINC.C0.getWidthPixelLong (72));
    assertEquals (3677, EDINC.C0.getHeightPixelLong (72));
  }
}
