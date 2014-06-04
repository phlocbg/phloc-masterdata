package com.phloc.masterdata.din;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.phloc.commons.string.StringHelper;

/**
 * Unit test class for class {@link EDINA}
 * 
 * @author Philip Helger
 */
public class EDINATest
{
  @Test
  public void testBasic ()
  {
    for (final EDINA e : EDINA.values ())
    {
      assertTrue (StringHelper.hasText (e.getID ()));
      assertSame (e, EDINA.getFromIDOrNull (e.getID ()));
      assertSame (e, EDINA.getFromIDOrDefault (e.getID (), EDINA.A0));
    }
  }

  @Test
  public void testConversion ()
  {
    assertEquals (9933, Math.round (EDINA.A0.getWidthPixel (300)));
    assertEquals (14043, Math.round (EDINA.A0.getHeightPixel (300)));
    assertEquals (4967, Math.round (EDINA.A0.getWidthPixel (150)));
    assertEquals (7022, Math.round (EDINA.A0.getHeightPixel (150)));
    assertEquals (2384, Math.round (EDINA.A0.getWidthPixel (72)));
    assertEquals (3370, Math.round (EDINA.A0.getHeightPixel (72)));

    assertEquals (9933, EDINA.A0.getWidthPixelLong (300));
    assertEquals (14043, EDINA.A0.getHeightPixelLong (300));
    assertEquals (4967, EDINA.A0.getWidthPixelLong (150));
    assertEquals (7022, EDINA.A0.getHeightPixelLong (150));
    assertEquals (2384, EDINA.A0.getWidthPixelLong (72));
    assertEquals (3370, EDINA.A0.getHeightPixelLong (72));
  }
}
