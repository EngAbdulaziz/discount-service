package com.assessment.retail.discount.core.application.discount.strategy;

import com.assessment.retail.discount.core.application.discount.persistence.DiscountPersistenceService;
import com.assessment.retail.discount.core.domain.bill.BillModel;
import com.assessment.retail.discount.core.domain.discount.DiscountModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.assessment.retail.discount.entrypoint.rest.bill.dto.DiscountConstants.DISCOUNT_STRATEGY_PREFIX;
import static com.assessment.retail.discount.entrypoint.rest.bill.dto.DiscountConstants.EMPLOYEE;

@Component(value = EMPLOYEE + DISCOUNT_STRATEGY_PREFIX)
@RequiredArgsConstructor
public class EmployeeDiscountStrategy implements DiscountStrategy {

    private static final Long EMPLOYEE_DISCOUNT_ID = 1L;
    private final DiscountPersistenceService discountPersistenceService;

    @Override
    public DiscountModel getDiscount(BillModel model) {

        return discountPersistenceService.findDiscountById(EMPLOYEE_DISCOUNT_ID);
    }
}
