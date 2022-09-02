package com.assessment.retail.discount.entrypoint.rest.bill.converter;

import com.assessment.retail.discount.core.domain.bill.BillModel;
import com.assessment.retail.discount.entrypoint.rest.bill.dto.BillDiscountRequestDto;
import com.assessment.retail.discount.entrypoint.rest.bill.dto.BillDiscountResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.assertions.Assertions;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BillDiscountModelEntrypointConverter {

    public static BillModel convert(final BillDiscountRequestDto dto) {

        Assertions.notNull("Invalid bill", dto);

        return BillModel.builder()
                .billOriginalAmount(dto.getBillAmount())
                .itemUuid(dto.getItemUuid())
                .userTye(dto.getUserTye().getType())
                .build();
    }

    public static BillDiscountResponseDto convert(final BillModel model) {

        Assertions.notNull("Invalid bill", model);

        return BillDiscountResponseDto.builder()
                .billDiscountDescription(model.getBillDiscountDescription())
                .billDiscountPercentage(model.getBillDiscountPercentage())
                .billNetPayableAmount(model.getBillNetPayableAmount())
                .billOriginalAmount(model.getBillOriginalAmount())
                .build();
    }
}
