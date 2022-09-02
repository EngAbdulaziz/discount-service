package com.assessment.retail.discount.core.application.discount.strategy;

import com.assessment.retail.discount.core.application.discount.persistence.DiscountPersistenceService;
import com.assessment.retail.discount.core.domain.bill.BillModel;
import com.assessment.retail.discount.core.domain.discount.DiscountModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.assessment.retail.discount.entrypoint.rest.bill.dto.DiscountConstants.CUSTOMER_OVER_2_YEARS;
import static com.assessment.retail.discount.entrypoint.rest.bill.dto.DiscountConstants.DISCOUNT_STRATEGY_PREFIX;

@Component(value = CUSTOMER_OVER_2_YEARS + DISCOUNT_STRATEGY_PREFIX)
@RequiredArgsConstructor
public class CustomerOverTwoYearsDiscountStrategy implements DiscountStrategy {

    private static final Long CUSTOMER_OVER_2_YEARS_DISCOUNT_ID = 3L;
    private final DiscountPersistenceService discountPersistenceService;

    @Override
    public DiscountModel getDiscount(BillModel model) {

        return discountPersistenceService.findDiscountById(CUSTOMER_OVER_2_YEARS_DISCOUNT_ID);
    }
}
