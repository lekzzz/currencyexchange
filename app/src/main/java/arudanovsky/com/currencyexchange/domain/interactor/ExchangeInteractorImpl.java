package arudanovsky.com.currencyexchange.domain.interactor;

import arudanovsky.com.currencyexchange.data.api.local.DataBaseImpl;
import arudanovsky.com.currencyexchange.data.api.local.Database;
import arudanovsky.com.currencyexchange.data.api.remote.RemoteApiManager;
import arudanovsky.com.currencyexchange.data.api.remote.RemoteApiManagerImpl;
import arudanovsky.com.currencyexchange.data.api.remote.RemoteCallbackListener;
import arudanovsky.com.currencyexchange.data.dto.CurrenciesListTO;
import arudanovsky.com.currencyexchange.domain.interactor.common.BaseInteractor;
import arudanovsky.com.currencyexchange.domain.mapper.CurrenciesListTOMapper;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class ExchangeInteractorImpl extends BaseInteractor implements ExchangeInteractor, RemoteCallbackListener {
    private ExchangeDataListener mListener;
    private RemoteApiManager mRemoteManager;
    private Database mLocalDatabase;

    public ExchangeInteractorImpl (ExchangeDataListener listener) {
        mListener = listener;
    }

    @Override
    public void init() {
        mRemoteManager = new RemoteApiManagerImpl().init(this);
        mLocalDatabase = new DataBaseImpl();
    }

    @Override
    public void destroy() {
        mRemoteManager = null;
        //todo close
        mLocalDatabase = null;
    }

    @Override
    public void provideCurrencies() {
        mRemoteManager.loadCurrencies();
    }

    @Override
    public void onDataLoaded(CurrenciesListTO currenciesListTO) {
        mLocalDatabase.saveList(currenciesListTO);
        if (mListener != null)
            mListener.onDataLoaded(new CurrenciesListTOMapper().apply(currenciesListTO));
    }

    @Override
    public void onErrorHappened(Throwable throwable) {
        provideLocalData();
    }

    private void provideLocalData() {
        mLocalDatabase.provideCurrencies();
    }
}
