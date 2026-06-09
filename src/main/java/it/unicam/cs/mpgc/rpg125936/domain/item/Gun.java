package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**Arma da combattimento che infligge danno alla salute dell'avversario.
 * Il danno puo essere ridotto temporaneamente dagli incantesimi nemici
 */
@Entity
@DiscriminatorValue("GUN")
public class Gun extends AbstractFightItem {

    public Gun(String name, double damage, double price) {
        super(name, damage, price);
    }

    public Gun() {}

    ///riduce il danno dell'arma, metodo invocato dopo aver subito un attacco da un incantesimo
    @Override
    public boolean reduceDamage(double amount) {
        setDamage(getDamage() - amount);
        if (getDamage() <= 0) {
            setDamage(0);
            return true;
        }
        return false;
    }

    ///gestisce l'uso dell'arma in un combattimento, riduce la vita dell'avversario
    ///e ritorna una descrizione dell'attacco fatto
    @Override
    public String useInFight(User target, String attackerName){
        target.decreaseHealth((int) getDamage());
        return attackerName + " attacca con " + getName() + " infliggendo " + getDamage() + " danni!";
    }

    @Override
    public Item copy() {
        return new Gun(getName(), getDamage(), getPrice());
    }

}
