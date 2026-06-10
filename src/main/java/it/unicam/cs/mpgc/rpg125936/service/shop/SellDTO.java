package it.unicam.cs.mpgc.rpg125936.service.shop;

/**
 * DTO che rappresenta l'esito di una vendita di materiali
 * descritto dalla riuscita o meno dell'operazione,
 * da un messaggio descrittivo e dal denaro guadagnato dalla vendita.
 */
public class SellDTO {
    private final boolean result;
    private final String message;
    private final int money;

    /**
     * @param result  true se la vendita è andata a buon fine
     * @param message messaggio descrittivo per l'utente
     * @param money   denaro guadagnato dalla vendita
     */
    public SellDTO(boolean result, String message, int money) {
        this.result = result;
        this.message = message;
        this.money = money;
    }

    /** @return true se la vendita è riuscita */
    public boolean isSuccess() { return result; }

    /** @return messaggio descrittivo per l'utente */
    public String getMessage() { return message; }

    /** @return denaro guadagnato dalla vendita */
    public int getMoney() { return money; }
}
