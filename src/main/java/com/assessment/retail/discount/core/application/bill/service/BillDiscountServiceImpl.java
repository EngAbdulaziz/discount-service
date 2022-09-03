package com.assessment.retail.discount.core.application.bill.service;

import com.assessment.retail.discount.core.application.discount.strategy.DiscountStrategy;
import com.assessment.retail.discount.core.application.item.persistence.ItemPersistenceService;
import com.assessment.retail.discount.core.domain.bill.BillModel;
import com.assessment.retail.discount.core.domain.discount.DiscountModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.assessment.retail.discount.entrypoint.rest.bill.dto.DiscountConstants.DISCOUNT_STRATEGY_PREFIX;

@Service
@RequiredArgsConstructor
public class BillDiscountServiceImpl implements BillDiscountService {

    private final ItemPersistenceService itemPersistenceService;
    private final Map<String, DiscountStrategy> discountStrategies;

    @Override
    public BillModel calculateBillDiscount(final BillModel model) {

        /*Since there is no specific requirement for which discount user should get
            if he is eligible for more than one discount, so we will choose the smaller amount*/

        final int numOfHundredUSDinAmount = (int) (model.getBillOriginalAmount() / 100);
        final int amountBasedDiscount = numOfHundredUSDinAmount * 5;

        model.setBillNetPayableAmount(model.getBillOriginalAmount() - amountBasedDiscount);
        model.setBillDiscountPercentage((float) (amountBasedDiscount / model.getBillOriginalAmount()) * 100);
        model.setBillDiscountDescription("5$ for each 100$");

        if (!itemPersistenceService.isItemGrocery(model.getItemUuid())
                && StringUtils.isNotBlank(model.getUserTye())) {

            final DiscountModel userTypeDiscount = discountStrategies.get(model.getUserTye().concat(DISCOUNT_STRATEGY_PREFIX))
                    .getDiscount(model);

            double percentageBasedDiscount = (userTypeDiscount.getDiscountPercentage() * model.getBillOriginalAmount()) / 100;

            if (percentageBasedDiscount < amountBasedDiscount) {
                model.setBillNetPayableAmount(model.getBillOriginalAmount() - percentageBasedDiscount);
                model.setBillDiscountPercentage(userTypeDiscount.getDiscountPercentage());
                model.setBillDiscountDescription(userTypeDiscount.getDiscountType());
            }

        }
        return model;
    }
}
