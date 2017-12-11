package arudanovsky.com.currencyexchange.domain.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import arudanovsky.com.currencyexchange.data.dto.CurrencyTO;
import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class CurrencyTOMapper {

    public static Currency apply(CurrencyTO currencyTO) {
        if (currencyTO == null) return null;
        Currency currency = new Currency();
        currency.setName(currencyTO.getName());
        currency.setCode(currencyTO.getCode());
        currency.setId(currencyTO.getId());
        currency.setRateToRub(new BigDecimal(currencyTO.getValue().replace(",", ".")).divide(new BigDecimal(currencyTO.getNominal().replace(",", ".")), 8, RoundingMode.HALF_UP));
        return currency;
    }
}
