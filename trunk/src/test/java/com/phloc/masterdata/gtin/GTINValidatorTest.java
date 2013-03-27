package com.phloc.masterdata.gtin;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for class {@link GTINValidator}.
 * 
 * @author philip
 */
public final class GTINValidatorTest
{
  @Test
  public void testIsValidGTIN8 ()
  {
    assertTrue (GTINValidator.isValidGTIN8 ("11111115"));
    assertFalse (GTINValidator.isValidGTIN8 ("11111114"));
    assertFalse (GTINValidator.isValidGTIN8 ("11111116"));
    assertFalse (GTINValidator.isValidGTIN8 ("1111111"));
    assertFalse (GTINValidator.isValidGTIN8 ("111111159"));
    assertFalse (GTINValidator.isValidGTIN8 (""));
    assertFalse (GTINValidator.isValidGTIN8 (null));
  }

  @Test
  public void testIsValidGTIN13 ()
  {
    assertTrue (GTINValidator.isValidGTIN13 ("6291041500213"));
    assertFalse (GTINValidator.isValidGTIN13 ("6291041500212"));
    assertFalse (GTINValidator.isValidGTIN13 ("6291041500214"));
    assertFalse (GTINValidator.isValidGTIN13 ("629104150021"));
    assertFalse (GTINValidator.isValidGTIN13 ("62910415002133"));
    assertFalse (GTINValidator.isValidGTIN13 (""));
    assertFalse (GTINValidator.isValidGTIN13 (null));
  }
}
