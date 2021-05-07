package Modele;
import java.awt.Point;
public class Jeu {

    public static final int SIZE_X = 100;
    public static final int SIZE_Y = 100;


    private Personnage personnage;

    public Jeu() {
        personnage = new Personnage(this);
    }

    public Personnage getPersonnage() {
        return personnage;
    }

}
