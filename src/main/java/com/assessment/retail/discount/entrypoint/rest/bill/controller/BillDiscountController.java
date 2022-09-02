package com.assessment.retail.discount.entrypoint.rest.bill.controller;

import com.assessment.retail.discount.core.application.bill.service.BillDiscountService;
import com.assessment.retail.discount.core.domain.bill.BillModel;
import com.assessment.retail.discount.entrypoint.rest.bill.converter.BillDiscountModelEntrypointConverter;
import com.assessment.retail.discount.entrypoint.rest.bill.dto.BillDiscountRequestDto;
import com.assessment.retail.discount.entrypoint.rest.bill.dto.BillDiscountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bill/discount")
public class BillDiscountController {

    private final BillDiscountService billDiscountService;

    @PostMapping
    public ResponseEntity<BillDiscountResponseDto> getBillPayableAmountAfterDiscount
            (@RequestBody @Valid final BillDiscountRequestDto billDiscountRequestDto) {

        final BillModel billModel =
                billDiscountService.calculateBillDiscount
                        (BillDiscountModelEntrypointConverter.convert(billDiscountRequestDto));

        return ResponseEntity.ok(BillDiscountModelEntrypointConverter.convert(billModel));
    }
}
