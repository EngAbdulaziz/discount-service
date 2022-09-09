package com.assessment.retail.discount.infrastructure.discount.entity;

import com.assessment.retail.discount.infrastructure.auditing.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "discount")
@Data
public class DiscountEntity extends Auditable {

    @Id
    private Long id;

    @Field(name = "discount_uuid")
    private String discountUuid;

    @Field(name = "discount_type")
    private String discountType;

    @Field(name = "discount_percentage")
    private Float discountPercentage;

}
