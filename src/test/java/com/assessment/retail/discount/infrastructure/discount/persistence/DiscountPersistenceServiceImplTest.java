package com.assessment.retail.discount.infrastructure.discount.persistence;

import com.assessment.retail.discount.core.domain.discount.DiscountModel;
import com.assessment.retail.discount.infrastructure.discount.entity.DiscountEntityInstanceProvider;
import com.assessment.retail.discount.infrastructure.discount.repository.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

class DiscountPersistenceServiceImplTest {

    @InjectMocks
    private DiscountPersistenceServiceImpl discountPersistenceService;

    @Mock
    private DiscountRepository discountRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        discountPersistenceService = new DiscountPersistenceServiceImpl(discountRepository);
    }

    @Test
    void findDiscountById_discountFound_discountModelWithCorrectValues() {

        doReturn(Optional.of(DiscountEntityInstanceProvider.getDiscountEntity()))
                .when(discountRepository).findById(anyLong());

        final DiscountModel discountModel = discountPersistenceService.findDiscountById(1L);

        assertNotNull(discountModel);
        assertEquals(DiscountEntityInstanceProvider.getDiscountEntity().getDiscountPercentage(),
                discountModel.getDiscountPercentage());
        assertEquals(DiscountEntityInstanceProvider.getDiscountEntity().getDiscountType(),
                discountModel.getDiscountType());
        assertEquals(DiscountEntityInstanceProvider.getDiscountEntity().getDiscountUuid(),
                discountModel.getDiscountUuid());
    }

    @Test
    void findDiscountById_discountNotFound_NoSuchElementExceptionIsThrown() {

        doReturn(Optional.empty()).when(discountRepository).findById(anyLong());

        assertThrows(NoSuchElementException.class, () -> {
            discountPersistenceService.findDiscountById(1L);
        });
    }

}