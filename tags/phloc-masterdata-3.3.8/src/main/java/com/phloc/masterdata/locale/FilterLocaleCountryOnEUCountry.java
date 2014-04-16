package com.phloc.masterdata.locale;

import java.util.Locale;

import javax.annotation.Nullable;

import com.phloc.commons.filter.IFilter;

/**
 * A filter on {@link Locale} that checks if the passed locale is a locale with
 * a country that is part of the EU.
 * 
 * @author Philip Helger
 */
public class FilterLocaleCountryOnEUCountry implements IFilter <Locale>
{
  public boolean matchesFilter (@Nullable final Locale aValue)
  {
    return EEUCountry.isEUCountry (aValue);
  }
}
