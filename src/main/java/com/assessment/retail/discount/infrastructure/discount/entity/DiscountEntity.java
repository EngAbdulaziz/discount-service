package com.assessment.retail.discount.infrastructure.discount.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "discount")
@Data
public class DiscountEntity {

    @Id
    private Long id;

    @Field(name = "discount_uuid")
    private String discountUuid;

    @Field(name = "discount_type")
    private String discountType;

    @Field(name = "discount_percentage")
    private Float discountPercentage;

}
