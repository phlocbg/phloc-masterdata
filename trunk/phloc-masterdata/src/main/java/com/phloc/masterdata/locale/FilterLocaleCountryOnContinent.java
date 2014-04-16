package com.phloc.masterdata.locale;

import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;

import com.phloc.commons.ValueEnforcer;
import com.phloc.commons.filter.IFilter;

public class FilterLocaleCountryOnContinent implements IFilter <Locale>
{
  private final EContinent m_eContinent;

  public FilterLocaleCountryOnContinent (@Nonnull final EContinent eContinent)
  {
    m_eContinent = ValueEnforcer.notNull (eContinent, "Continent");
  }

  @Nonnull
  public EContinent getContinent ()
  {
    return m_eContinent;
  }

  public boolean matchesFilter (@Nonnull final Locale aValue)
  {
    final Set <EContinent> eContinents = ContinentUtils.getContinentsOfCountry (aValue);
    return eContinents != null && eContinents.contains (m_eContinent);
  }
}
