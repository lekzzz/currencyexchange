package arudanovsky.com.currencyexchange.data.api.local;

import java.util.List;

import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public interface DatabaseDataListener {
    void onDataLoaded(List<Currency> currencies);
    void onError(Throwable throwable);
}
