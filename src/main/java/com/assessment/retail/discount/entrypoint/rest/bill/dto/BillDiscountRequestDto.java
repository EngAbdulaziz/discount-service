package com.assessment.retail.discount.entrypoint.rest.bill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class BillDiscountRequestDto {

    @JsonProperty("userTye")
    @NotNull(message = "invalid user type")
    private UserTyeEnum userTye;

    @JsonProperty("billAmount")
    @NotNull(message = "invalid bill amount")
    @Min(value = 0, message = "invalid bill amount")
    private Double billAmount;

    @JsonProperty("itemId")
    @NotNull(message = "invalid item id")
    private UUID itemUuid;
}
