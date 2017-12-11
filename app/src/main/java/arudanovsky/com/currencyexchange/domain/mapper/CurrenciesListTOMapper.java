package arudanovsky.com.currencyexchange.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import arudanovsky.com.currencyexchange.data.dto.CurrenciesListTO;
import arudanovsky.com.currencyexchange.data.dto.CurrencyTO;
import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class CurrenciesListTOMapper {
    private static final CurrencyTOMapper mMapper = new CurrencyTOMapper();
    public static List<Currency> apply(CurrenciesListTO listTO) {
        if (listTO == null) return null;
        List<Currency> currencies = new ArrayList<>();
        for (CurrencyTO cTO : listTO.getCurrencyList())
            currencies.add(mMapper.apply(cTO));
        return currencies;
    }
}
