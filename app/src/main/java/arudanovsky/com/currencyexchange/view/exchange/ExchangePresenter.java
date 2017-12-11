package arudanovsky.com.currencyexchange.view.exchange;

import java.util.List;

import arudanovsky.com.currencyexchange.domain.interactor.ExchangeDataListener;
import arudanovsky.com.currencyexchange.domain.interactor.ExchangeInteractor;
import arudanovsky.com.currencyexchange.domain.interactor.ExchangeInteractorImpl;
import arudanovsky.com.currencyexchange.domain.model.Currency;
import arudanovsky.com.currencyexchange.view.common.BasePresenter;
import arudanovsky.com.currencyexchange.view.common.IView;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class ExchangePresenter extends BasePresenter implements ExchangeProtocol.ExchangePresenter, ExchangeDataListener {
    private ExchangeProtocol.ExchangeView mView;
    private ExchangeInteractor mInteractor;

    @Override
    public void onCreate(IView view) {
        mView = (ExchangeProtocol.ExchangeView) view;
        mInteractor = new ExchangeInteractorImpl(this);
        mInteractor.init();
    }

    @Override
    public void subscribe() {
        mInteractor.provideCurrencies();
    }

    @Override
    public void unsubscribe() {
        mInteractor.destroy();
    }

    @Override
    public void onDataLoaded(List<Currency> currencies) {
        mView.populateCurrencies(currencies);
    }

    @Override
    public void onError(Throwable throwable) {
        mView.showError("some error");
    }
}
