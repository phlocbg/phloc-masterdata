package com.phloc.masterdata.locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.id.IHasID;
import com.phloc.commons.lang.EnumHelper;

public enum EContinent implements IHasID <String>
{
  AFRICA ("af"),
  AMERICA ("am"),
  ASIA ("as"),
  AUSTRALIA ("au"),
  EUROPE ("eu");

  private String m_sID;

  private EContinent (@Nonnull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static EContinent getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EContinent.class, sID);
  }
}
