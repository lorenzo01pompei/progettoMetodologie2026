package it.unicam.cs.mpgc.rpg125936.service.shop;

/// classe usata per rappresentare l'esito di una vendita di materiali
public class SellDTO {
    private final boolean result;
    private final String message;
    private final int money;


    public SellDTO(boolean result, String message, int money) {
        this.result = result;
        this.message = message;
        this.money = money;
    }

    public String getMessage() {
        return message;
    }
}

