package arudanovsky.com.currencyexchange.view.exchange;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EmptyStackException;
import java.util.List;

import arudanovsky.com.currencyexchange.AppPreferences;
import arudanovsky.com.currencyexchange.R;
import arudanovsky.com.currencyexchange.common.EmptyResultException;
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
    private Context mContext;
    private List<Currency> mCurrecies;
    private int mFromPosition = 0;
    private int mToPosition = 0;
    private BigDecimal mFromSum;

    public ExchangePresenter(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(IView view) {
        mView = (ExchangeProtocol.ExchangeView) view;
        mInteractor = new ExchangeInteractorImpl(mContext, this);
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
        mCurrecies = currencies;
        loadLastConversionData();
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof EmptyResultException)
            mView.showError(mContext.getString(R.string.empty_result_error));
        else
            mView.showError(mContext.getString(R.string.unknown_exception));
    }

    @Override
    public void onButtonClicked() {
        if (validate()) {
            BigDecimal convertResult = mFromSum.multiply(mCurrecies.get(mFromPosition).getRateToRub())
                    .divide(mCurrecies.get(mToPosition).getRateToRub(), 2, RoundingMode.HALF_UP);
            mView.populateConvertResult(convertResult);
            saveLastConversion();
        } else {
            mView.showError(mContext.getString(R.string.invalid_data));
        }
    }

    private boolean validate() {
        boolean desicion = true;
        if (mFromSum == null)
            desicion = false;
        if (mFromPosition >= mCurrecies.size() || mFromPosition < 0)
            desicion = false;
        if (mToPosition >= mCurrecies.size() || mToPosition < 0)
            desicion = false;
        return desicion;
    }

    private void saveLastConversion() {
        AppPreferences appPreferences = new AppPreferences(mContext);
        appPreferences.setFromPosition(mFromPosition);
        appPreferences.setFromSum(mFromSum.toString());
        appPreferences.setToPosition(mToPosition);
    }

    private void loadLastConversionData() {
        AppPreferences appPreferences = new AppPreferences(mContext);
        mFromSum = new BigDecimal(appPreferences.getFromSum());
        mFromPosition = appPreferences.getFromPosition();
        mToPosition = appPreferences.getToPosition();
        mView.showScreen(new ExchangeViewModel(mFromSum, mFromPosition, mToPosition, mCurrecies));
    }

    @Override
    public void onTextChanged(String quantity) {
        mFromSum = new BigDecimal(!TextUtils.isEmpty(quantity) && !quantity.equals(",") && !quantity.equals(".") ?
                quantity.replace(",", ".") : "0");
    }

    @Override
    public void onItemChosen(SpinnerType type, int pos) {
        switch (type) {
            case FROM:
                mFromPosition = pos;
                break;
            case TO:
                mToPosition = pos;
                break;
            default:
                break;
        }
    }
}
