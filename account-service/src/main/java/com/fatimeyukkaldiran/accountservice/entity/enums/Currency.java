package com.fatimeyukkaldiran.accountservice.entity.enums;

public enum Currency {
    TRY("Türk Lirası", "₺"),
    USD("Amerikan Doları", "$"),
    EUR("Euro", "€"),
    ALTIN("Altın(gr)","XAU");

    private String currencyName;
    private String currencySign;

    Currency(String currencyName, String currencySign) {
        this.currencyName = currencyName;
        this.currencySign = currencySign;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySign() {
        return currencySign;
    }
}
