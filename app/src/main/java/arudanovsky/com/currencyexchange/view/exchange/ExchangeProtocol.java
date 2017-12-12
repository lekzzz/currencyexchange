package arudanovsky.com.currencyexchange.view.exchange;

import java.math.BigDecimal;
import java.util.List;

import arudanovsky.com.currencyexchange.domain.model.Currency;
import arudanovsky.com.currencyexchange.view.common.IPresenter;
import arudanovsky.com.currencyexchange.view.common.IView;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public interface ExchangeProtocol {
    interface ExchangeView extends IView {

        void populateConvertResult(BigDecimal convertResult);

        void showScreen(ExchangeViewModel exchangeViewModel);
    }
    interface ExchangePresenter extends IPresenter {

        void onButtonClicked();

        void onTextChanged(String text);

        void onItemChosen(SpinnerType type, int pos);
    }
}
