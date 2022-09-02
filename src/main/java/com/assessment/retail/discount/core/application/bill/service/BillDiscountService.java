package com.assessment.retail.discount.core.application.bill.service;

import com.assessment.retail.discount.core.domain.bill.BillModel;

public interface BillDiscountService {

    BillModel calculateBillDiscount(final BillModel model);
}
