package arudanovsky.com.currencyexchange.view.exchange;

import java.math.BigDecimal;
import java.util.List;

import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/12/17.
 */

public class ExchangeViewModel {
    private BigDecimal mFromSum;
    private int mFromPosition;
    private int mToPosition;
    private List<Currency> mList;

    public ExchangeViewModel(BigDecimal fromSum, int fromPosition, int toPosition, List<Currency> list) {
        this.mFromSum = fromSum;
        this.mFromPosition = fromPosition;
        this.mToPosition = toPosition;
        this.mList = list;
    }

    public BigDecimal getFromSum() {
        return mFromSum;
    }

    public int getFromPosition() {
        return mFromPosition;
    }

    public int getToPosition() {
        return mToPosition;
    }

    public List<Currency> getList() {
        return mList;
    }
}
