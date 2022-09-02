package com.assessment.retail.discount.infrastructure.discount.repository;

import com.assessment.retail.discount.infrastructure.discount.entity.DiscountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscountRepository extends MongoRepository<DiscountEntity, Long> {
}
