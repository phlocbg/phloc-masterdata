package com.phloc.masterdata.din;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.phloc.commons.string.StringHelper;

/**
 * Unit test class for class {@link EDIND}
 * 
 * @author Philip Helger
 */
public class EDINDTest
{
  @Test
  public void testBasic ()
  {
    for (final EDIND e : EDIND.values ())
    {
      assertTrue (StringHelper.hasText (e.getID ()));
      assertSame (e, EDIND.getFromIDOrNull (e.getID ()));
      assertSame (e, EDIND.getFromIDOrDefault (e.getID (), EDIND.D0));
    }
  }

  @Test
  public void testConversion ()
  {
    assertEquals (9106, Math.round (EDIND.D0.getWidthPixel (300)));
    assertEquals (12874, Math.round (EDIND.D0.getHeightPixel (300)));
    assertEquals (4553, Math.round (EDIND.D0.getWidthPixel (150)));
    assertEquals (6437, Math.round (EDIND.D0.getHeightPixel (150)));
    assertEquals (2186, Math.round (EDIND.D0.getWidthPixel (72)));
    assertEquals (3090, Math.round (EDIND.D0.getHeightPixel (72)));

    assertEquals (9106, EDIND.D0.getWidthPixelLong (300));
    assertEquals (12874, EDIND.D0.getHeightPixelLong (300));
    assertEquals (4553, EDIND.D0.getWidthPixelLong (150));
    assertEquals (6437, EDIND.D0.getHeightPixelLong (150));
    assertEquals (2186, EDIND.D0.getWidthPixelLong (72));
    assertEquals (3090, EDIND.D0.getHeightPixelLong (72));
  }
}
