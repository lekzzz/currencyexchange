package arudanovsky.com.currencyexchange.data.api.local;

import arudanovsky.com.currencyexchange.data.dto.CurrenciesListTO;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public interface Database {
    void init();
    void saveList(CurrenciesListTO currenciesListTO);
    void provideCurrencies();
}
