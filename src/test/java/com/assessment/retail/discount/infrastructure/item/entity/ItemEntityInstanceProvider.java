package com.assessment.retail.discount.infrastructure.item.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemEntityInstanceProvider {

    public static ItemEntity getItemEntityWithTrue() {

        final ItemEntity itemEntity = new ItemEntity();

        itemEntity.setIsGrocery(true);

        return itemEntity;
    }

    public static ItemEntity getItemEntityWithFalse() {

        final ItemEntity itemEntity = new ItemEntity();

        itemEntity.setIsGrocery(false);

        return itemEntity;
    }
}
