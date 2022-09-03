package com.assessment.retail.discount.core.domain.discount;

import java.util.UUID;

public final class DiscountModelInstanceProvider {

    public static DiscountModel getEmployeeDiscountModel() {

        return DiscountModel.builder()
                .discountPercentage(30.0F)
                .discountUuid("9e887dd4-b389-413f-9073-b07a51a7b203")
                .discountType("Store employee discount")
                .build();
    }

    public static DiscountModel getAffiliateDiscountModel() {

        return DiscountModel.builder()
                .discountPercentage(10.0F)
                .discountUuid("47b549e9-46c9-4c9f-83ae-6a68a825433d")
                .discountType("Affiliate discount")
                .build();
    }

    public static DiscountModel getCustomerOverTwoYearsDiscountModel() {

        return DiscountModel.builder()
                .discountPercentage(5.0F)
                .discountUuid("9af1f994-2361-4841-8721-c0cec7dc2ff9")
                .discountType("Customer for over 2 years discount")
                .build();
    }

    public static DiscountModel get3PercentageDiscountModel() {

        return DiscountModel.builder()
                .discountPercentage(3.0F)
                .discountUuid(UUID.randomUUID().toString())
                .discountType("User 3% discount")
                .build();
    }

}
