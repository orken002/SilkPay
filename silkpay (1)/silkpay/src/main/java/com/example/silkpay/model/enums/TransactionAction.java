package com.example.silkpay.model.enums;

public enum TransactionAction {

    WITHDRAWAL("Снятие/Перевод"),
    REPLENISHMENT("Пополнение");

    public final String label;

    TransactionAction(String label) {
        this.label = label;
    }

}
