package com.assessment.retail.discount.infrastructure.item.persistence;

import com.assessment.retail.discount.infrastructure.item.entity.ItemEntityInstanceProvider;
import com.assessment.retail.discount.infrastructure.item.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

class ItemPersistenceServiceImpTest {

    @InjectMocks
    private ItemPersistenceServiceImp itemPersistenceService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        itemPersistenceService = new ItemPersistenceServiceImp(itemRepository);
    }

    @Test
    void isItemGrocery_itemFoundWithTrue_returnTrue() {

        doReturn(Optional.of(ItemEntityInstanceProvider.getItemEntityWithTrue()))
                .when(itemRepository).findByItemUuid(anyString());

        final boolean isGrocery = itemPersistenceService.isItemGrocery(UUID.randomUUID());

        assertTrue(isGrocery);
    }

    @Test
    void isItemGrocery_itemFoundWithFalse_returnFalse() {

        doReturn(Optional.of(ItemEntityInstanceProvider.getItemEntityWithFalse()))
                .when(itemRepository).findByItemUuid(anyString());

        final boolean isGrocery = itemPersistenceService.isItemGrocery(UUID.randomUUID());

        assertFalse(isGrocery);
    }

    @Test
    void isItemGrocery_itemNotFound_NoSuchElementExceptionIsThrown() {

        doReturn(Optional.empty()).when(itemRepository).findByItemUuid(anyString());

        assertThrows(NoSuchElementException.class, () -> {
            itemPersistenceService.isItemGrocery(UUID.randomUUID());
        });
    }
}