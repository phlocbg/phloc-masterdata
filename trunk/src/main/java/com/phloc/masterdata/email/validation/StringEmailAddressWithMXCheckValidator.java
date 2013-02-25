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
 * Validator for email addresses. Uses the {@link EmailAddressValidator} class
 * explicitly using a MX record check.<br>
 * Warning: this may be slow when called very often, as a DNS lookup is
 * performed
 * 
 * @see EmailAddressValidator
 * @see StringEmailAddressValidator
 * @see StringEmailAddressNoMXCheckValidator
 * @author philip
 */
public final class StringEmailAddressWithMXCheckValidator extends AbstractStringValidator
{
  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (EmailAddressValidator.isValidWithMXCheck (sValue))
      return ValidationResultSuccess.getInstance ();
    return new ValidationResultError (EStandardValidationErrorTexts.INVALID_EMAIL_ADDRESS);
  }
}
