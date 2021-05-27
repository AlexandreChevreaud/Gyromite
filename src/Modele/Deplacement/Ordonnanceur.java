package Modele.Deplacement;

import Modele.Plateau.Jeu;

import java.util.ArrayList;
import java.util.Observable;

public class Ordonnanceur extends Observable implements Runnable{
    private Jeu jeu;
    private long pause;

    private ArrayList<RealisateurDeDeplacement> lstDeplacements = new ArrayList<>();

    public void add(RealisateurDeDeplacement deplacement) {
        lstDeplacements.add(deplacement);
    }
    
    public void start(long _pause) {
        pause = _pause;
        new Thread(this).start();
    }

    public void remove(RealisateurDeDeplacement deplacement) {
        lstDeplacements.remove(deplacement);
    }

    public Ordonnanceur(Jeu _jeu) {
        jeu = _jeu;
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        boolean doitUpdate = false;
        setChanged();
        notifyObservers();
        while (!jeu.isGameWin()){
            for (int i = 0; i < lstDeplacements.size(); i++) {
                RealisateurDeDeplacement d = lstDeplacements.get(i);
                if (d.realiserDeplacement())
                    doitUpdate = true;
            }
            Controle4Directions.getInstance().resetDirection();
            if (doitUpdate) {
                setChanged();
                notifyObservers();
            }

            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
