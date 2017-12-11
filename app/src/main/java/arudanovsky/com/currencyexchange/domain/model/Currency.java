package arudanovsky.com.currencyexchange.domain.model;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class Currency {
    private String id;
    private String code;
    private String name;
    private BigDecimal rateToRub;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRateToRub() {
        return rateToRub;
    }

    public void setRateToRub(BigDecimal rateToRub) {
        this.rateToRub = rateToRub;
    }
}
