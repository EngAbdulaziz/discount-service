package com.assessment.retail.discount.infrastructure.item.repository;

import com.assessment.retail.discount.infrastructure.item.entity.ItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ItemRepository extends MongoRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByItemUuid(final String uuid);
}
