package com.phloc.masterdata.person;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.system.SystemHelper;

public final class PersonNameMicroTypeConverter implements IMicroTypeConverter
{
  static final String ATTR_SALUTATION = "salutation";
  static final String ATTR_PREFIXTITLE = "prefixtitle";
  static final String ATTR_FIRSTNAME = "firstname";
  static final String ATTR_MIDDLENAME = "middlename";
  static final String ATTR_LASTNAME = "lastname";
  static final String ATTR_SUFFIXTITLE = "suffixtitle";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final PersonName aAddress = (PersonName) aObject;
    final IMicroElement eName = new MicroElement (sNamespaceURI, sTagName);
    if (aAddress.getSalutation () != null)
      eName.setAttribute (ATTR_SALUTATION, aAddress.getSalutationID ());
    if (StringHelper.hasText (aAddress.getPrefixTitle ()))
      eName.setAttribute (ATTR_PREFIXTITLE, aAddress.getPrefixTitle ());
    if (StringHelper.hasText (aAddress.getFirstName ()))
      eName.setAttribute (ATTR_FIRSTNAME, aAddress.getFirstName ());
    if (StringHelper.hasText (aAddress.getMiddleName ()))
      eName.setAttribute (ATTR_MIDDLENAME, aAddress.getMiddleName ());
    if (StringHelper.hasText (aAddress.getLastName ()))
      eName.setAttribute (ATTR_LASTNAME, aAddress.getLastName ());
    if (StringHelper.hasText (aAddress.getSuffixTitle ()))
      eName.setAttribute (ATTR_SUFFIXTITLE, aAddress.getSuffixTitle ());
    return eName;
  }

  @Nonnull
  public PersonName convertToNative (@Nonnull final IMicroElement eAddress)
  {
    final Locale aLocale = SystemHelper.getSystemLocale ();
    final PersonName aName = new PersonName ();
    aName.setSalutation (ESalutation.getFromIDOrNull (eAddress.getAttribute (ATTR_SALUTATION)));
    aName.setPrefixTitle (eAddress.getAttribute (ATTR_PREFIXTITLE));
    aName.setFirstName (eAddress.getAttribute (ATTR_FIRSTNAME), aLocale);
    aName.setMiddleName (eAddress.getAttribute (ATTR_MIDDLENAME), aLocale);
    aName.setLastName (eAddress.getAttribute (ATTR_LASTNAME), aLocale);
    aName.setSuffixTitle (eAddress.getAttribute (ATTR_SUFFIXTITLE));
    return aName;
  }
}