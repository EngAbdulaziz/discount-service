package com.assessment.retail.discount.entrypoint.rest.bill.dto;

import static com.assessment.retail.discount.entrypoint.rest.bill.dto.DiscountConstants.*;

public enum UserTyeEnum {

    EMP(EMPLOYEE),
    AFT(AFFILIATE),
    C2Y(CUSTOMER_OVER_2_YEARS);

    private final String type;

    UserTyeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
