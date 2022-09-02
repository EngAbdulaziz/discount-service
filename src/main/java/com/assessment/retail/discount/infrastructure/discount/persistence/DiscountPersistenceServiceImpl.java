package com.assessment.retail.discount.infrastructure.discount.persistence;

import com.assessment.retail.discount.core.application.discount.persistence.DiscountPersistenceService;
import com.assessment.retail.discount.core.domain.discount.DiscountModel;
import com.assessment.retail.discount.infrastructure.discount.converter.DiscountModelConverter;
import com.assessment.retail.discount.infrastructure.discount.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountPersistenceServiceImpl implements DiscountPersistenceService {

    private final DiscountRepository discountRepository;

    @Override
    public DiscountModel findDiscountById(final Long id) {

        return discountRepository.findById(id)
                .map(DiscountModelConverter::convert)
                .orElseThrow();

    }
}
