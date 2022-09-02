package com.assessment.retail.discount.core.domain.discount;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiscountModel {

    private String discountUuid;
    private String discountType;
    private Float discountPercentage;
}
