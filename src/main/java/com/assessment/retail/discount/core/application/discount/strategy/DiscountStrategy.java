package com.assessment.retail.discount.core.application.discount.strategy;

import com.assessment.retail.discount.core.domain.bill.BillModel;
import com.assessment.retail.discount.core.domain.discount.DiscountModel;

public interface DiscountStrategy {

    DiscountModel getDiscount(final BillModel model);
}
