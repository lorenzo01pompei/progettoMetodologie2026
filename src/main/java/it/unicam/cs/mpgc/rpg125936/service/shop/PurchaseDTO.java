package it.unicam.cs.mpgc.rpg125936.service.shop;

/**
 * DTO che rappresenta l'esito di un'operazione di acquisto
 * descritto dalla riuscita o meno dell'operazione
 * e da un messaggio descrittivo.
 */

public class PurchaseDTO {
    private final boolean result;
    private final String message;

    /**
     * @param result true se l'acquisto va a buon fine
     * @param message messaggio descrittivo per l'utente
     */
    public PurchaseDTO(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    /** @return true se l'acquisto è riuscito */
    public boolean isSuccess() { return result; }

    /** @return messaggio descrittivo per l'utente */
    public String getMessage() { return message; }
}
