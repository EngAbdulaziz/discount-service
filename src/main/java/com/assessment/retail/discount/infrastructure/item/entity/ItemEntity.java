package com.assessment.retail.discount.infrastructure.item.entity;

import com.assessment.retail.discount.infrastructure.auditing.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends Auditable {

    @Id
    private Integer id;

    @Field(name = "item_uuid")
    private String itemUuid;

    @Field(name = "item_description")
    private String itemDescription;

    @Field(name = "is_grocery")
    private Boolean isGrocery;
}
