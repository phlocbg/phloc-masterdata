/**
 * Copyright (C) 2006-2015 phloc systems
 * http://www.phloc.com
 * office[at]phloc[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phloc.validation.error;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.RegEx;
import javax.annotation.concurrent.NotThreadSafe;

import com.phloc.commons.annotations.Nonempty;
import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ArrayHelper;
import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.collections.multimap.IMultiMapListBased;
import com.phloc.commons.collections.multimap.MultiLinkedHashMapArrayListBased;
import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.error.EErrorLevel;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.regex.RegExHelper;
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Handles a list of form global errors.
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public class ErrorList implements IErrorList
{
  private final List <IError> m_aItems = new ArrayList <IError> ();

  public ErrorList ()
  {}

  public ErrorList (@Nullable final IErrorList aErrorList)
  {
    addAll (aErrorList);
  }

  public ErrorList (@Nullable final Collection <? extends IError> aErrorList)
  {
    addAll (aErrorList);
  }

  public ErrorList (@Nullable final IError... aErrorList)
  {
    addAll (aErrorList);
  }

  public void addAll (@Nullable final IErrorList aErrorList)
  {
    if (aErrorList != null)
      for (final IError aFormError : aErrorList.getAllItems ())
        add (aFormError);
  }

  public void addAll (@Nullable final Collection <? extends IError> aErrorList)
  {
    if (aErrorList != null)
      for (final IError aFormError : aErrorList)
        add (aFormError);
  }

  public void addAll (@Nullable final IError... aErrorList)
  {
    if (aErrorList != null)
      for (final IError aFormError : aErrorList)
        add (aFormError);
  }

  /**
   * Add a new item.
   * 
   * @param aItem
   *        The item to be added. May not be <code>null</code>.
   */
  public void add (@Nonnull final IError aItem)
  {
    if (aItem == null)
      throw new NullPointerException ("item");
    m_aItems.add (aItem);
  }

  public void addSuccess (@Nonnull @Nonempty final String sText)
  {
    add (SingleError.createSuccess (sText));
  }

  public void addSuccess (@Nullable final String sFieldName, @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createSuccess (sFieldName, sText));
  }

  public void addSuccess (@Nullable final String sID,
                          @Nullable final String sFieldName,
                          @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createSuccess (sID, sFieldName, sText));
  }

  public void addInfo (@Nonnull @Nonempty final String sText)
  {
    add (SingleError.createInfo (sText));
  }

  public void addInfo (@Nullable final String sFieldName, @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createInfo (sFieldName, sText));
  }

  public void addInfo (@Nullable final String sID,
                       @Nullable final String sFieldName,
                       @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createInfo (sID, sFieldName, sText));
  }

  public void addWarning (@Nonnull @Nonempty final String sText)
  {
    add (SingleError.createWarning (sText));
  }

  public void addWarning (@Nullable final String sFieldName, @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createWarning (sFieldName, sText));
  }

  public void addWarning (@Nullable final String sID,
                          @Nullable final String sFieldName,
                          @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createWarning (sID, sFieldName, sText));
  }

  public void addError (@Nonnull @Nonempty final String sText)
  {
    add (SingleError.createError (sText));
  }

  public void addError (@Nullable final String sFieldName, @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createError (sFieldName, sText));
  }

  public void addError (@Nullable final String sID,
                        @Nullable final String sFieldName,
                        @Nonnull @Nonempty final String sText)
  {
    add (SingleError.createError (sID, sFieldName, sText));
  }

  public boolean isEmpty ()
  {
    return m_aItems.isEmpty ();
  }

  @Nonnegative
  public int getItemCount ()
  {
    return m_aItems.size ();
  }

  public boolean hasErrorsOrWarnings ()
  {
    for (final IError aItem : m_aItems)
      if (aItem.getErrorLevel ().isMoreOrEqualSevereThan (EErrorLevel.WARN))
        return true;
    return false;
  }

  public boolean containsOnlySuccess ()
  {
    if (m_aItems.isEmpty ())
      return false;
    for (final IError aError : m_aItems)
      if (aError.isFailure ())
        return false;
    return true;
  }

  public boolean containsAtLeastOneSuccess ()
  {
    for (final IError aError : m_aItems)
      if (aError.isSuccess ())
        return true;
    return false;
  }

  public boolean containsNoSuccess ()
  {
    for (final IError aError : m_aItems)
      if (aError.isSuccess ())
        return false;
    return true;
  }

  @Nonnegative
  public int getSuccessCount ()
  {
    int ret = 0;
    for (final IError aError : m_aItems)
      if (aError.isSuccess ())
        ret++;
    return ret;
  }

  public boolean containsOnlyFailure ()
  {
    if (m_aItems.isEmpty ())
      return false;
    for (final IError aError : m_aItems)
      if (aError.isSuccess ())
        return false;
    return true;
  }

  public boolean containsAtLeastOneFailure ()
  {
    for (final IError aError : m_aItems)
      if (aError.isFailure ())
        return true;
    return false;
  }

  public boolean containsNoFailure ()
  {
    for (final IError aError : m_aItems)
      if (aError.isFailure ())
        return false;
    return true;
  }

  @Nonnegative
  public int getFailureCount ()
  {
    int ret = 0;
    for (final IError aError : m_aItems)
      if (aError.isFailure ())
        ret++;
    return ret;
  }

  public boolean containsOnlyError ()
  {
    if (m_aItems.isEmpty ())
      return false;
    for (final IError aError : m_aItems)
      if (aError.isNoError ())
        return false;
    return true;
  }

  public boolean containsAtLeastOneError ()
  {
    for (final IError aError : m_aItems)
      if (aError.isError ())
        return true;
    return false;
  }

  public boolean containsNoError ()
  {
    for (final IError aError : m_aItems)
      if (aError.isError ())
        return false;
    return true;
  }

  @Nonnegative
  public int getErrorCount ()
  {
    int ret = 0;
    for (final IError aError : m_aItems)
      if (aError.isError ())
        ret++;
    return ret;
  }

  @Nonnull
  public EErrorLevel getMostSevereErrorLevel ()
  {
    EErrorLevel ret = EErrorLevel.SUCCESS;
    for (final IError aError : m_aItems)
    {
      final EErrorLevel eCur = aError.getErrorLevel ();
      if (eCur.isMoreSevereThan (ret))
      {
        ret = eCur;
        if (ret == EErrorLevel.HIGHEST)
          break;
      }
    }
    return ret;
  }

  /**
   * @return A non-<code>null</code> list of all contained texts, independent of
   *         the level.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllItemTexts ()
  {
    final List <String> ret = new ArrayList <String> ();
    for (final IError aError : m_aItems)
      ret.add (aError.getErrorText ());
    return ret;
  }

  /**
   * @return A copy of all contained items. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <IError> getAllItems ()
  {
    return ContainerHelper.newList (m_aItems);
  }

  /**
   * @return An {@link Iterator} over all contained items. Never
   *         <code>null</code>.
   */
  @Nonnull
  public Iterator <IError> iterator ()
  {
    return m_aItems.iterator ();
  }

  // --- field specific elements ---

  @Nonnull
  @ReturnsMutableCopy
  public ErrorList getListWithoutField ()
  {
    final ErrorList ret = new ErrorList ();
    for (final IError aError : m_aItems)
      if (!aError.hasErrorFieldName ())
        ret.add (aError);
    return ret;
  }

  public boolean hasNoEntryForField (@Nullable final String sSearchFieldName)
  {
    for (final IError aError : m_aItems)
      if (EqualsUtils.equals (sSearchFieldName, aError.getErrorFieldName ()))
        return false;
    return true;
  }

  public boolean hasEntryForField (@Nullable final String sSearchFieldName)
  {
    for (final IError aError : m_aItems)
      if (EqualsUtils.equals (sSearchFieldName, aError.getErrorFieldName ()))
        return true;
    return false;
  }

  public boolean hasEntryForField (@Nullable final String sSearchFieldName, @Nullable final EErrorLevel eErrorLevel)
  {
    if (eErrorLevel != null)
      for (final IError aError : m_aItems)
        if (aError.getErrorLevel ().equals (eErrorLevel) &&
            EqualsUtils.equals (sSearchFieldName, aError.getErrorFieldName ()))
          return true;
    return false;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ErrorList getListOfField (@Nullable final String sSearchFieldName)
  {
    final ErrorList ret = new ErrorList ();
    for (final IError aError : m_aItems)
      if (EqualsUtils.equals (sSearchFieldName, aError.getErrorFieldName ()))
        ret.add (aError);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ErrorList getListOfFields (@Nullable final String... aSearchFieldNames)
  {
    final ErrorList ret = new ErrorList ();
    if (ArrayHelper.isNotEmpty (aSearchFieldNames))
      for (final IError aError : m_aItems)
        if (ArrayHelper.contains (aSearchFieldNames, aError.getErrorFieldName ()))
          ret.add (aError);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ErrorList getListOfFieldsStartingWith (@Nullable final String... aSearchFieldNames)
  {
    final ErrorList ret = new ErrorList ();
    if (ArrayHelper.isNotEmpty (aSearchFieldNames))
      for (final IError aError : m_aItems)
        if (aError.hasErrorFieldName ())
        {
          final String sErrorFieldName = aError.getErrorFieldName ();
          for (final String sSearchField : aSearchFieldNames)
            if (sErrorFieldName.startsWith (sSearchField))
            {
              ret.add (aError);
              break;
            }
        }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ErrorList getListOfFieldsRegExp (@Nonnull @Nonempty @RegEx final String sRegExp)
  {
    if (StringHelper.hasNoText (sRegExp))
      throw new IllegalArgumentException ("Empty RegExp");

    final ErrorList ret = new ErrorList ();
    for (final IError aError : m_aItems)
      if (aError.hasErrorFieldName ())
        if (RegExHelper.stringMatchesPattern (sRegExp, aError.getErrorFieldName ()))
          ret.add (aError);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllItemTextsOfField (@Nullable final String sSearchFieldName)
  {
    final List <String> ret = new ArrayList <String> ();
    for (final IError aError : m_aItems)
      if (EqualsUtils.equals (aError.getErrorFieldName (), sSearchFieldName))
        ret.add (aError.getErrorText ());
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllItemTextsOfFields (@Nullable final String... aSearchFieldNames)
  {
    final List <String> ret = new ArrayList <String> ();
    if (ArrayHelper.isNotEmpty (aSearchFieldNames))
      for (final IError aError : m_aItems)
        if (ArrayHelper.contains (aSearchFieldNames, aError.getErrorFieldName ()))
          ret.add (aError.getErrorText ());
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllItemTextsOfFieldsStartingWith (@Nullable final String... aSearchFieldNames)
  {
    final List <String> ret = new ArrayList <String> ();
    if (ArrayHelper.isNotEmpty (aSearchFieldNames))
      for (final IError aError : m_aItems)
        if (aError.hasErrorFieldName ())
        {
          final String sErrorFieldName = aError.getErrorFieldName ();
          for (final String sSearchField : aSearchFieldNames)
            if (sErrorFieldName.startsWith (sSearchField))
            {
              ret.add (aError.getErrorText ());
              break;
            }
        }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllItemTextsOfFieldsRegExp (@Nonnull @Nonempty @RegEx final String sRegExp)
  {
    if (StringHelper.hasNoText (sRegExp))
      throw new IllegalArgumentException ("Empty RegExp");

    final List <String> ret = new ArrayList <String> ();
    for (final IError aError : m_aItems)
      if (aError.hasErrorFieldName ())
        if (RegExHelper.stringMatchesPattern (sRegExp, aError.getErrorFieldName ()))
          ret.add (aError.getErrorText ());
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public IMultiMapListBased <String, IError> getStructuredByID ()
  {
    final IMultiMapListBased <String, IError> ret = new MultiLinkedHashMapArrayListBased <String, IError> ();
    for (final IError aFormError : m_aItems)
      ret.putSingle (aFormError.getErrorID (), aFormError);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public IMultiMapListBased <String, IError> getStructuredByFieldName ()
  {
    final IMultiMapListBased <String, IError> ret = new MultiLinkedHashMapArrayListBased <String, IError> ();
    for (final IError aFormError : m_aItems)
      ret.putSingle (aFormError.getErrorFieldName (), aFormError);
    return ret;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ErrorList rhs = (ErrorList) o;
    return m_aItems.equals (rhs.m_aItems);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aItems).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("items", m_aItems).toString ();
  }
}
