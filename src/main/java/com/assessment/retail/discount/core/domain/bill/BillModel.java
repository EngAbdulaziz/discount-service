package com.assessment.retail.discount.core.domain.bill;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Builder
@Setter
@Getter
public class BillModel {

    private String userTye;
    private Double billOriginalAmount;
    private UUID itemUuid;
    private Float billDiscountPercentage;
    private Double billNetPayableAmount;
    private String billDiscountDescription;
}
