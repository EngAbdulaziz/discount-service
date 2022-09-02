package com.assessment.retail.discount.entrypoint.rest.bill.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class BillDiscountResponseDto {

    private Double billOriginalAmount;
    private Float billDiscountPercentage;
    private Double billNetPayableAmount;
    private String billDiscountDescription;
}
