package com.phloc.masterdata.company;

import java.util.Locale;

import javax.annotation.Nullable;

public enum ECompanySiteStatus
{
 VALID ("valid", ECompanySiteStatusName.VALID), //$NON-NLS-1$
 CONSTRUCTION ("construction", ECompanySiteStatusName.CONSTRUCTION),
 NEW ("new", ECompanySiteStatusName.NEW); //$NON-NLS-1$

  private String m_sID;
  private ECompanySiteStatusName m_eName;

  private ECompanySiteStatus (final String sID, final ECompanySiteStatusName eName)
  {
    this.m_sID = sID;
    this.m_eName = eName;
  }

  public String getID ()
  {
    return this.m_sID;
  }

  public String getName (final Locale aLocale)
  {
    return this.m_eName.getDisplayText (aLocale);
  }

  /**
   * Tries to resolve the enum entry corresponding to the passed ID
   * 
   * @param sID
   * @return The resolved enum entry, or <code>null</code>
   */
  @Nullable
  public static ECompanySiteStatus getFromID (final String sID)
  {
    for (final ECompanySiteStatus eValue : values ())
    {
      if (eValue.getID ().equals (sID))
      {
        return eValue;
      }
    }
    return VALID;
  }

}
