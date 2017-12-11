package arudanovsky.com.currencyexchange.domain.interactor;

import java.util.List;

import arudanovsky.com.currencyexchange.domain.interactor.common.Interactor;
import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public interface ExchangeInteractor extends Interactor {
    void provideCurrencies();
}
