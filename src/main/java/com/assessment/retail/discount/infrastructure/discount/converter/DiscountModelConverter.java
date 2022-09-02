package com.assessment.retail.discount.infrastructure.discount.converter;

import com.assessment.retail.discount.core.domain.discount.DiscountModel;
import com.assessment.retail.discount.infrastructure.discount.entity.DiscountEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.assertions.Assertions;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DiscountModelConverter {

    public static DiscountModel convert(final DiscountEntity entity) {

        Assertions.notNull("Invalid discount", entity);

        return DiscountModel.builder()
                .discountType(entity.getDiscountType())
                .discountUuid(entity.getDiscountUuid())
                .discountPercentage(entity.getDiscountPercentage())
                .build();
    }
}
