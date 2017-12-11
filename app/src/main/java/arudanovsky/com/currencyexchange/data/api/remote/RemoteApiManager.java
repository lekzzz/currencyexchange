package arudanovsky.com.currencyexchange.data.api.remote;

import arudanovsky.com.currencyexchange.data.dto.CurrenciesListTO;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public interface RemoteApiManager {
    RemoteApiManager init(RemoteCallbackListener listener);
    void loadCurrencies();
}
