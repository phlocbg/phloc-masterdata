package com.phloc.masterdata.price;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.microdom.IMicroElement;
import com.phloc.commons.microdom.convert.IMicroTypeConverter;
import com.phloc.commons.microdom.impl.MicroElement;

/**
 * Common base class for {@link Price} and {@link ReadonlyPrice}
 * 
 * @author Philip Helger
 */
public abstract class AbstractPriceMicroTypeConverter implements IMicroTypeConverter
{
  protected static final String ATTR_CURRENCY = "currency";
  protected static final String ATTR_NETAMOUNT = "netamount";
  protected static final String ATTR_GROSSAMOUNT = "grossamount";
  protected static final String ATTR_VATITEM = "vatitem";

  @Nonnull
  public final IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                                    @Nullable final String sNamespaceURI,
                                                    @Nonnull final String sTagName)
  {
    final IReadonlyPrice aPrice = (IReadonlyPrice) aObject;
    final IMicroElement ePrice = new MicroElement (sNamespaceURI, sTagName);
    if (aPrice.getCurrency () != null)
      ePrice.setAttribute (ATTR_CURRENCY, aPrice.getCurrency ().getID ());
    if (aPrice.getNetAmount () != null)
      ePrice.setAttribute (ATTR_NETAMOUNT, aPrice.getNetAmount ().getValue ().toString ());
    if (aPrice.getGrossAmount () != null)
      ePrice.setAttribute (ATTR_GROSSAMOUNT, aPrice.getGrossAmount ().getValue ().toString ());
    if (aPrice.getVATItem () != null)
      ePrice.setAttribute (ATTR_VATITEM, aPrice.getVATItem ().getID ());
    return ePrice;
  }
}