package com.assessment.retail.discount.infrastructure.security.repository;

import com.assessment.retail.discount.infrastructure.security.model.ERole;
import com.assessment.retail.discount.infrastructure.security.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
