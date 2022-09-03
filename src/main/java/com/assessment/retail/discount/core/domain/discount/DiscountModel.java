package com.assessment.retail.discount.core.domain.discount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountModel {

    private String discountUuid;
    private String discountType;
    private Float discountPercentage;
}
