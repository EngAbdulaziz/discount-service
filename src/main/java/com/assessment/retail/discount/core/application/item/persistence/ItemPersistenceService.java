package com.assessment.retail.discount.core.application.item.persistence;

import java.util.UUID;

public interface ItemPersistenceService {

    boolean isItemGrocery(final UUID itemUuid);
}
