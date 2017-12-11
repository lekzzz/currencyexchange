package arudanovsky.com.currencyexchange.view.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import arudanovsky.com.currencyexchange.R;
import arudanovsky.com.currencyexchange.domain.model.Currency;
import arudanovsky.com.currencyexchange.view.common.BaseFragment;
import arudanovsky.com.currencyexchange.view.exchange.adapter.CurrencyAdapter;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class ExchangeFragment extends BaseFragment implements ExchangeProtocol.ExchangeView{
    private ExchangeProtocol.ExchangePresenter mPresenter;
    private TextView mResult;
    private EditText mEditText;
    private Spinner mFromCurrency, mToCurrency;
    private Button mConvert;
    private CurrencyAdapter fromAdapter, toAdapter;

    public static ExchangeFragment newInstance() {
        return new ExchangeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ExchangePresenter(getContext());
        mPresenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);
        mResult = view.findViewById(R.id.text);
        mEditText = view.findViewById(R.id.sum);
        mFromCurrency = view.findViewById(R.id.from_currency);
        mToCurrency = view.findViewById(R.id.to_currency);
        mConvert = view.findViewById(R.id.convert);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.onTextChanged(editable.toString());
            }
        });

        mConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onButtonClicked();
            }
        });

        fromAdapter = new CurrencyAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, new ArrayList<Currency>());
        toAdapter = new CurrencyAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, new ArrayList<Currency>());

        mFromCurrency.setAdapter(fromAdapter);
//        mFromCurrency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) )
//            }
//        });
        mToCurrency.setAdapter(toAdapter);
        mPresenter.subscribe();
        return view;
    }

    @Override
    public void populateCurrencies(List<Currency> currencies) {
        fromAdapter.updateList(currencies);
        toAdapter.updateList(currencies);
    }
}
