package arudanovsky.com.currencyexchange.domain.interactor;

import android.content.Context;

import java.util.List;

import arudanovsky.com.currencyexchange.data.api.local.DataBaseImpl;
import arudanovsky.com.currencyexchange.data.api.local.Database;
import arudanovsky.com.currencyexchange.data.api.local.DatabaseDataListener;
import arudanovsky.com.currencyexchange.data.api.remote.RemoteApiManager;
import arudanovsky.com.currencyexchange.data.api.remote.RemoteApiManagerImpl;
import arudanovsky.com.currencyexchange.data.api.remote.RemoteCallbackListener;
import arudanovsky.com.currencyexchange.data.dto.CurrenciesListTO;
import arudanovsky.com.currencyexchange.domain.interactor.common.BaseInteractor;
import arudanovsky.com.currencyexchange.domain.mapper.CurrenciesListTOMapper;
import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class ExchangeInteractorImpl extends BaseInteractor implements ExchangeInteractor, RemoteCallbackListener, DatabaseDataListener {
    private ExchangeDataListener mListener;
    private RemoteApiManager mRemoteManager;
    private Database mLocalDatabase;
    private Context mContext;

    public ExchangeInteractorImpl(Context context, ExchangeDataListener listener) {
        mListener = listener;
        mContext = context;
    }

    @Override
    public void init() {
        mRemoteManager = new RemoteApiManagerImpl().init(this);
        mLocalDatabase = new DataBaseImpl(mContext, "my_db", null, 1).withListener(this);
        mLocalDatabase.init();
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
        List<Currency> list = new CurrenciesListTOMapper().apply(currenciesListTO);
        mLocalDatabase.saveList(list);
        if (mListener != null)
            mListener.onDataLoaded(list);
    }

    @Override
    public void onErrorHappened(Throwable throwable) {
        provideLocalData();
    }

    private void provideLocalData() {
        mLocalDatabase.provideCurrencies();
    }

    @Override
    public void onDataLoaded(List<Currency> currencies) {
        if (mListener != null)
            mListener.onDataLoaded(currencies);
    }

    @Override
    public void onError(Throwable throwable) {
        if (mListener != null)
            mListener.onError(throwable);
    }
}
