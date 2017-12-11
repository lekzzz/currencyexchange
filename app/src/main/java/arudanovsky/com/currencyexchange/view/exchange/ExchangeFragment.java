package arudanovsky.com.currencyexchange.view.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import arudanovsky.com.currencyexchange.R;
import arudanovsky.com.currencyexchange.domain.model.Currency;
import arudanovsky.com.currencyexchange.view.common.BaseFragment;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class ExchangeFragment extends BaseFragment implements ExchangeProtocol.ExchangeView{
    private ExchangeProtocol.ExchangePresenter mPresenter;
    private TextView tv;

    public static ExchangeFragment newInstance() {
        return new ExchangeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ExchangePresenter();
        mPresenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);
        tv = view.findViewById(R.id.text);
        mPresenter.subscribe();
        return view;
    }

    @Override
    public void populateCurrencies(List<Currency> currencies) {
        tv.setText(currencies.toString());
    }
}
