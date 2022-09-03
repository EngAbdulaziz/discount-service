package com.assessment.retail.discount.infrastructure.discount.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DiscountEntityInstanceProvider {

    static final String DISCOUNT_UUID = UUID.randomUUID().toString();

    public static DiscountEntity getDiscountEntity() {

        final DiscountEntity discountEntity = new DiscountEntity();

        discountEntity.setDiscountType("employee discount");
        discountEntity.setDiscountUuid(DISCOUNT_UUID);
        discountEntity.setDiscountPercentage(30F);
        discountEntity.setId(1L);

        return discountEntity;
    }
}
