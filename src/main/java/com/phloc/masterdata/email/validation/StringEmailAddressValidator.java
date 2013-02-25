package com.phloc.masterdata.email.validation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.validation.EStandardValidationErrorTexts;
import com.phloc.validation.result.IValidationResult;
import com.phloc.validation.result.ValidationResultError;
import com.phloc.validation.result.ValidationResultSuccess;
import com.phloc.validation.validator.string.AbstractStringValidator;
import com.phloc.validation.validator.string.StringEmailAddressNoMXCheckValidator;
import com.phloc.web.smtp.EmailAddressValidator;

/**
 * Validator for email addresses. Uses the {@link EmailAddressValidator} class.<br>
 * Warning: this may be slow if the MX record check is enabled because DNS
 * lookups take place
 * 
 * @see EmailAddressValidator
 * @see StringEmailAddressNoMXCheckValidator
 * @see StringEmailAddressWithMXCheckValidator
 * @author philip
 */
public final class StringEmailAddressValidator extends AbstractStringValidator
{
  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (EmailAddressValidator.isValid (sValue))
      return ValidationResultSuccess.getInstance ();
    return new ValidationResultError (EStandardValidationErrorTexts.INVALID_EMAIL_ADDRESS);
  }
}
