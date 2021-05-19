import Controleur.VueControleur;
import Modele.Jeu;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Jeu jeu = new Jeu();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VueControleur vc = new VueControleur(jeu);
        jeu.addObserver(vc);
        jeu.start();
    }
}
