package com.assessment.retail.discount.core.application.discount.persistence;

import com.assessment.retail.discount.core.domain.discount.DiscountModel;

public interface DiscountPersistenceService {

    DiscountModel findDiscountById(final Long id);
}
