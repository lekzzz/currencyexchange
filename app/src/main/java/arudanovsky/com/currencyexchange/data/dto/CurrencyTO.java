package arudanovsky.com.currencyexchange.data.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 12/11/17.
 */
@Root(name="Valute")
public class CurrencyTO {
    @Attribute(name="ID") private String id;
    @Element(name="NumCode") private int numCode;
    @Element(name="CharCode") private String code;
    @Element(name="Name") private String name;
    @Element(name="Nominal") private String nominal;
    @Element(name="Value") private String value;

    public int getNumCode() {
        return numCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getNominal() {
        return nominal;
    }

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }
}
