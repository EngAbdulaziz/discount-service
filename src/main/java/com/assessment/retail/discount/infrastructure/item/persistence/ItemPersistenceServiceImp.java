package com.assessment.retail.discount.infrastructure.item.persistence;

import com.assessment.retail.discount.core.application.item.persistence.ItemPersistenceService;
import com.assessment.retail.discount.infrastructure.item.entity.ItemEntity;
import com.assessment.retail.discount.infrastructure.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemPersistenceServiceImp implements ItemPersistenceService {

    private final ItemRepository itemRepository;

    @Override
    public boolean isItemGrocery(final UUID itemUuid) {

        return itemRepository.findByItemUuid(itemUuid.toString())
                .map(ItemEntity::getIsGrocery)
                .orElseThrow();
    }
}
