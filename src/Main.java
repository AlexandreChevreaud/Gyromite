import Controleur.VueControleur;
import Modele.Jeu;


public class Main {
    public static void main(String[] args)  {
        Jeu jeu = new Jeu();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VueControleur vc = new VueControleur(jeu);
        jeu.getOrdonnanceur().addObserver(vc);
        jeu.start(200);
    }
}
