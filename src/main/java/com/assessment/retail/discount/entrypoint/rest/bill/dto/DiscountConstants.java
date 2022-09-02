package com.assessment.retail.discount.entrypoint.rest.bill.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DiscountConstants {

    public static final String EMPLOYEE = "Employee";
    public static final String AFFILIATE = "Affiliate";
    public static final String CUSTOMER_OVER_2_YEARS = "CustomerOverTwoYears";

    public static final String DISCOUNT_STRATEGY_PREFIX = "DiscountStrategy";
}
