package com.assessment.retail.discount.core.application.bill.service;

import com.assessment.retail.discount.core.application.discount.strategy.DiscountStrategy;
import com.assessment.retail.discount.core.application.discount.strategy.EmployeeDiscountStrategy;
import com.assessment.retail.discount.core.application.item.persistence.ItemPersistenceService;
import com.assessment.retail.discount.core.domain.bill.BillModel;
import com.assessment.retail.discount.core.domain.discount.DiscountModelInstanceProvider;
import com.assessment.retail.discount.entrypoint.rest.bill.dto.UserTyeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

class BillDiscountServiceImplTest {

    @InjectMocks
    private BillDiscountServiceImpl discountService;

    @Mock
    private ItemPersistenceService itemPersistenceService;
    @Mock
    private Map<String, DiscountStrategy> discountStrategies;
    @Mock
    private EmployeeDiscountStrategy employeeDiscountStrategy;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

        discountService = new BillDiscountServiceImpl(itemPersistenceService, discountStrategies);
    }

    @Test
    void calculateBillDiscount_itemIsGroceryAndUserEmployee_getAmountBasedDiscount() {

        doReturn(true).when(itemPersistenceService).isItemGrocery(any(UUID.class));

        doReturn(employeeDiscountStrategy).when(discountStrategies).get(anyString());

        doReturn(DiscountModelInstanceProvider.getEmployeeDiscountModel())
                .when(employeeDiscountStrategy).getDiscount(any(BillModel.class));

        final BillModel bill = BillModel.builder()
                .itemUuid(UUID.randomUUID())
                .userTye(UserTyeEnum.EMP.getType())
                .billOriginalAmount(1234.0)
                .build();

        final BillModel billModel = discountService.calculateBillDiscount(bill);

        assertNotNull(billModel);
        assertNotNull(billModel.getBillNetPayableAmount());
        assertEquals(1174.0, billModel.getBillNetPayableAmount());
        assertEquals(4.8622365F, billModel.getBillDiscountPercentage());
        assertEquals("5$ for each 100$", billModel.getBillDiscountDescription());
    }

    @Test
    void calculateBillDiscount_itemIsNotGroceryAndUserEmployee_getAmountBasedDiscount() {

        doReturn(false).when(itemPersistenceService).isItemGrocery(any(UUID.class));

        doReturn(employeeDiscountStrategy).when(discountStrategies).get(anyString());

        doReturn(DiscountModelInstanceProvider.getEmployeeDiscountModel())
                .when(employeeDiscountStrategy).getDiscount(any(BillModel.class));

        final BillModel bill = BillModel.builder()
                .itemUuid(UUID.randomUUID())
                .userTye(UserTyeEnum.EMP.getType())
                .billOriginalAmount(100.0)
                .build();

        final BillModel billModel = discountService.calculateBillDiscount(bill);

        assertNotNull(billModel);
        assertNotNull(billModel.getBillNetPayableAmount());
        assertEquals(95.0, billModel.getBillNetPayableAmount());
        assertEquals(5F, billModel.getBillDiscountPercentage());
        assertEquals("5$ for each 100$", billModel.getBillDiscountDescription());
    }

    @Test
    void calculateBillDiscount_itemIsNotGroceryAndUserDiscount3percentage_getPercentageBasedDiscount() {

        doReturn(false).when(itemPersistenceService).isItemGrocery(any(UUID.class));

        doReturn(employeeDiscountStrategy).when(discountStrategies).get(anyString());

        doReturn(DiscountModelInstanceProvider.get3PercentageDiscountModel())
                .when(employeeDiscountStrategy).getDiscount(any(BillModel.class));

        final BillModel bill = BillModel.builder()
                .itemUuid(UUID.randomUUID())
                .userTye(UserTyeEnum.EMP.getType())
                .billOriginalAmount(100.0)
                .build();

        final BillModel billModel = discountService.calculateBillDiscount(bill);

        assertNotNull(billModel);
        assertNotNull(billModel.getBillNetPayableAmount());
        assertEquals(97.0, billModel.getBillNetPayableAmount());
        assertEquals(3F, billModel.getBillDiscountPercentage());
        assertEquals("User 3% discount", billModel.getBillDiscountDescription());
    }

}