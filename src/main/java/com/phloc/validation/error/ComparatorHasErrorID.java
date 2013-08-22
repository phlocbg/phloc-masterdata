package com.phloc.validation.error;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.compare.AbstractCollationComparator;
import com.phloc.commons.compare.ESortOrder;

/**
 * Special comparator that compares based on an error ID
 * 
 * @author Philip Helger
 */
public class ComparatorHasErrorID extends AbstractCollationComparator <IHasErrorID>
{
  /**
   * Comparator with default locale {@link Collator} and default sort order.
   */
  public ComparatorHasErrorID ()
  {
    super ();
  }

  /**
   * Comparator with default locale {@link Collator} and default sort order and
   * a nested comparator.
   * 
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorHasErrorID (@Nullable final Comparator <? super Object> aNestedComparator)
  {
    super (aNestedComparator);
  }

  /**
   * Comparator with default locale {@link Collator}.
   * 
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   */
  public ComparatorHasErrorID (@Nonnull final ESortOrder eSortOrder)
  {
    super (eSortOrder);
  }

  /**
   * Comparator with default locale {@link Collator} and a nested comparator.
   * 
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorHasErrorID (@Nonnull final ESortOrder eSortOrder,
                               @Nullable final Comparator <? super Object> aNestedComparator)
  {
    super (eSortOrder, aNestedComparator);
  }

  /**
   * Comparator with default sort order and specified sort locale.
   * 
   * @param aSortLocale
   *        The locale to use. May be <code>null</code>.
   */
  public ComparatorHasErrorID (@Nullable final Locale aSortLocale)
  {
    super (aSortLocale);
  }

  /**
   * Comparator with default sort order but special locale and a nested
   * comparator.
   * 
   * @param aSortLocale
   *        The locale to use. May be <code>null</code>.
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorHasErrorID (@Nullable final Locale aSortLocale,
                               @Nullable final Comparator <? super Object> aNestedComparator)
  {
    super (aSortLocale, aNestedComparator);
  }

  /**
   * Constructor with locale and sort order.
   * 
   * @param aSortLocale
   *        The locale to use. May be <code>null</code>.
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   */
  public ComparatorHasErrorID (@Nullable final Locale aSortLocale, @Nonnull final ESortOrder eSortOrder)
  {
    super (aSortLocale, eSortOrder);
  }

  /**
   * Constructor with locale and sort order and a nested comparator.
   * 
   * @param aSortLocale
   *        The locale to use. May be <code>null</code>.
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorHasErrorID (@Nullable final Locale aSortLocale,
                               @Nonnull final ESortOrder eSortOrder,
                               @Nullable final Comparator <? super Object> aNestedComparator)
  {
    super (aSortLocale, eSortOrder, aNestedComparator);
  }

  /**
   * Constructor with {@link Collator} using the default sort order
   * 
   * @param aCollator
   *        The {@link Collator} to use. May not be <code>null</code>.
   */
  public ComparatorHasErrorID (@Nonnull final Collator aCollator)
  {
    super (aCollator);
  }

  /**
   * Constructor with {@link Collator} using the default sort order and a nested
   * comparator.
   * 
   * @param aCollator
   *        The {@link Collator} to use. May not be <code>null</code>.
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorHasErrorID (@Nonnull final Collator aCollator,
                               @Nullable final Comparator <? super Object> aNestedComparator)
  {
    super (aCollator, aNestedComparator);
  }

  /**
   * Constructor with {@link Collator} and sort order.
   * 
   * @param aCollator
   *        The {@link Collator} to use. May not be <code>null</code>.
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   */
  public ComparatorHasErrorID (@Nonnull final Collator aCollator, @Nonnull final ESortOrder eSortOrder)
  {
    super (aCollator, eSortOrder);
  }

  /**
   * Constructor with {@link Collator} and sort order and a nested comparator.
   * 
   * @param aCollator
   *        The {@link Collator} to use. May not be <code>null</code>.
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorHasErrorID (@Nonnull final Collator aCollator,
                               @Nonnull final ESortOrder eSortOrder,
                               @Nullable final Comparator <? super Object> aNestedComparator)
  {
    super (aCollator, eSortOrder, aNestedComparator);
  }

  @Override
  @Nullable
  protected String asString (@Nonnull final IHasErrorID aObject)
  {
    return aObject.getErrorID ();
  }
}
