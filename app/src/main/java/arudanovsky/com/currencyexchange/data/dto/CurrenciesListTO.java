package arudanovsky.com.currencyexchange.data.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by arudanovskiy on 12/11/17.
 */
@Root(name="ValCurs")
public class CurrenciesListTO {
    @Attribute(name="Date") private String date;
    @Attribute(name="name") private String name;
    @ElementList(name="Valute", inline = true) private List<CurrencyTO> currencyList;

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public List<CurrencyTO> getCurrencyList() {
        return currencyList;
    }


    public void setCurrencyList(List<CurrencyTO> currencyList) {
        this.currencyList = currencyList;
    }
}
