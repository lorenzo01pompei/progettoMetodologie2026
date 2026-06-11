package it.unicam.cs.mpgc.rpg125936.service.shop;

/// classe usata per rappresentare l'esito di una singola operazione di acquisto

public class PurchaseDTO {
    private final boolean result;
    private final String message;

    public PurchaseDTO(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean isSuccess() { return result; }

    public String getMessage() { return message; }
}
