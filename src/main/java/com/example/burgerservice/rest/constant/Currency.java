package com.example.burgerservice.rest.constant;

import java.util.HashMap;
import java.util.Map;

public class Currency {
    public static final Map<String, Double> currencyList;
    static {
        currencyList = new HashMap<>();
        currencyList.put("Eur",83.4);
        currencyList.put("Rub",1.0);
        currencyList.put("Usd", 71.9);
    }
    private Currency() {
    }
}
