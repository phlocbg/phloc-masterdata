package com.phloc.masterdata.locale;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;

public class ISO639_2Item implements Serializable
{
  private final String m_sAlpha3B;
  private final String m_sAlpha3T;
  private final String m_sAlpha2;
  private final String m_sEN;
  private final String m_sFR;

  public ISO639_2Item (@Nonnull @Nonempty final String sAlpha3B,
                       @Nullable final String sAlpha3T,
                       @Nullable final String sAlpha2,
                       @Nonnull @Nonempty final String sEN,
                       @Nonnull @Nonempty final String sFR)
  {
    if (StringHelper.getLength (sAlpha3B) != 3)
      throw new IllegalArgumentException ("Alpha3-bibliographic code must be present and have length 3: '" +
                                          sAlpha3B +
                                          "' - length " +
                                          StringHelper.getLength (sAlpha3B));
    if (sAlpha3T != null && sAlpha3T.length () != 3)
      throw new IllegalArgumentException ("Alpha3-terminologic code must have length 3!");
    if (sAlpha2 != null && sAlpha2.length () != 2)
      throw new IllegalArgumentException ("Alpha2 code must have length 2!");
    if (StringHelper.hasNoText (sEN))
      throw new IllegalArgumentException ("English name is missing");
    if (StringHelper.hasNoText (sFR))
      throw new IllegalArgumentException ("French name is missing");
    m_sAlpha3B = sAlpha3B;
    m_sAlpha3T = sAlpha3T;
    m_sAlpha2 = sAlpha2;
    m_sEN = sEN;
    m_sFR = sFR;
  }

  /**
   * @return The 3-letter bibliographic version. This is the default ISO-639-2
   *         3-letter code. Never <code>null</code>.
   */
  @Nonnull
  @Nonempty
  public String getAlpha3Bibliographic ()
  {
    return m_sAlpha3B;
  }

  /**
   * @return The 3-letter terminologic version. This code is optional and may be
   *         <code>null</code>. If it is not <code>null</code> the length is 3.
   */
  @Nullable
  public String getAlpha3Terminologic ()
  {
    return m_sAlpha3T;
  }

  /**
   * @return The 2-letter version. This code is optional and may be
   *         <code>null</code>. If it is not <code>null</code> the length is 2.
   */
  @Nullable
  public String getAlpha2 ()
  {
    return m_sAlpha2;
  }

  /**
   * @return The English Name of the language. Never <code>null</code>.
   */
  @Nonnull
  @Nonempty
  public String getEnglishName ()
  {
    return m_sEN;
  }

  /**
   * @return The French Name of the language. Never <code>null</code>.
   */
  @Nonnull
  @Nonempty
  public String getFrenchName ()
  {
    return m_sFR;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ISO639_2Item rhs = (ISO639_2Item) o;
    return m_sAlpha3B.equals (rhs.m_sAlpha3B) &&
           EqualsUtils.equals (m_sAlpha3T, rhs.m_sAlpha3T) &&
           EqualsUtils.equals (m_sAlpha2, rhs.m_sAlpha2) &&
           m_sEN.equals (rhs.m_sEN) &&
           m_sFR.equals (rhs.m_sFR);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sAlpha3B)
                                       .append (m_sAlpha3T)
                                       .append (m_sAlpha2)
                                       .append (m_sEN)
                                       .append (m_sFR)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("alpha3b", m_sAlpha3B)
                                       .append ("alpha3t", m_sAlpha3T)
                                       .append ("alpha2", m_sAlpha2)
                                       .append ("English", m_sEN)
                                       .append ("French", m_sFR)
                                       .toString ();
  }
}
