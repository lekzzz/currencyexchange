package arudanovsky.com.currencyexchange.view.exchange.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class CurrencyAdapter extends ArrayAdapter<Currency> {
    private Context mContext;
    private List<Currency> mParameters;

    public CurrencyAdapter(@NonNull Context context, @LayoutRes int resource, List<Currency> parameters) {
        super(context, resource, parameters);
        this.mContext = context;
        this.mParameters = parameters;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }

        Currency rowItem = getItem(position);
        TextView txtTitle = convertView.findViewById(android.R.id.text1);
        txtTitle.setText(rowItem.toString());

        return convertView;
    }

    public void updateList(List<Currency> currencies) {
        mParameters.clear();
        mParameters.addAll(currencies);
        notifyDataSetChanged();
    }
}