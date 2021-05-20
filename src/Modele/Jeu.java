package Modele;
import java.io.IOException;
import java.util.Observable;

//TODO peut être deplacer l'observable
public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 16;
    public static final int SIZE_Y = 16;
    private int pause = 200; // période de rafraichissement

    private Carte carte;

    private Personnage personnage;

    public Jeu()  {
        this.personnage = new Personnage(this);
        carte = new Carte(this);
        carte.chargerCarte();
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public Carte getCarte() {
        return carte;
    }

    public Boolean deplacerEntite(Entite entite, Direction direction){
        //TODO à faire
        int x = carte.getEntites().get(entite).x;
        int y = carte.getEntites().get(entite).y;
        Boolean deplacer = false;
        //TODO en lien problème coordonee
        switch (direction){
            case Gauche: if(y-1>=0 && (carte.getMap()[x][y-1].getType() == EntiteType.Vide || carte.getMap()[x][y-1].getType() == EntiteType.Corde || carte.getMap()[x][y-1].getType() != EntiteType.Radis)) {
                Entite caseActuelle;
                if(carte.getMap()[x][y-1].getType() != EntiteType.Radis)
                    caseActuelle = carte.getMap()[x][y-1];
                else
                    caseActuelle = new Vide(this);
                carte.supprimerEntite(entite,x,y);
                carte.addEntite(entite,x,y-1);
                carte.setCaseActuelle(caseActuelle);
                deplacer = true;
            }break;

            case Droite: if(y+1>=0 && (carte.getMap()[x][y+1].getType() == EntiteType.Vide || carte.getMap()[x][y+1].getType() == EntiteType.Corde || carte.getMap()[x][y+1].getType() == EntiteType.Radis)) {
                Entite caseActuelle;
                if(carte.getMap()[x][y+1].getType() != EntiteType.Radis)
                    caseActuelle = carte.getMap()[x][y+1];
                else
                    caseActuelle = new Vide(this);
                carte.supprimerEntite(entite,x,y);
                carte.addEntite(entite,x,y+1);
                carte.setCaseActuelle(caseActuelle);
                deplacer = true;
            }break;

            case Bas: if(x+1>=0 && carte.getMap()[x+1][y].getType() == EntiteType.Vide) {
                Entite caseActuelle = carte.getMap()[x+1][y];
                carte.supprimerEntite(entite,x,y);
                carte.addEntite(entite,x+1,y);
                carte.setCaseActuelle(caseActuelle);
                deplacer = true;
            }break;

            case Haut: if(x-1>=0 && (carte.getMap()[x-1][y].getType() == EntiteType.Vide|| carte.getMap()[x-1][y].getType() == EntiteType.Corde)) {
                Entite caseActuelle = carte.getMap()[x-1][y];
                carte.supprimerEntite(entite,x,y);
                carte.addEntite(entite,x-1,y);
                carte.setCaseActuelle(caseActuelle);
                deplacer = true;
            }break;

        }
        return deplacer;
    }

    public void start() {
        new Thread(this).start();
    }
    @Override
    public void run() {
        while(true) {

            setChanged();
            notifyObservers();

            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
