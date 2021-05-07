package Modele;
import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;

//TODO peut être deplacer l'observable
public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 20;
    private int pause = 200; // période de rafraichissement

    // TODO never used for now
    private HashMap<Entite,Point> entites = new HashMap<Entite,Point>();
    private Entite[][] map;

    private Personnage personnage;

    public Jeu() {
        chargerCarte();
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public Entite[][] getMap() {
        return map;
    }

    // TODO à faire, carte de test actuellement
    public void chargerCarte(){
        Entite vide = new Vide(this);
        map = new Entite[SIZE_X][SIZE_Y];
        for (int i  = 0;i<SIZE_X;i++)
            for (int j  = 0;j<SIZE_Y;j++){
                map[i][j] = new Vide(this);
                addEntite(vide,i,j);
            }
        this.personnage = new Personnage(this);
        map[3][SIZE_Y-1] = this.personnage;
        addEntite(this.personnage,3,SIZE_Y-1);
    }

    public Boolean deplacerEntite(Entite entite, Direction direction){
        //TODO à faire
        int x = entites.get(entite).x;
        int y = entites.get(entite).y;

        switch (direction){
            case Haut: if(y-1>=0 && map[x][y-1].getType() == EntiteType.Vide) {
                supprimerEntite(entite,x,y);
                addEntite(entite,x,y-1);
            }break;

            case Bas: if(y+1>=0 && map[x][y+1].getType() == EntiteType.Vide) {
                supprimerEntite(entite,x,y);
                addEntite(entite,x,y+1);
            }break;

            case Droite: if(x+1>=0 && map[x+1][y].getType() == EntiteType.Vide) {
                supprimerEntite(entite,x,y);
                addEntite(entite,x+1,y);
            }break;

            case Gauche: if(x-1>=0 && map[x-1][y].getType() == EntiteType.Vide) {
                supprimerEntite(entite,x,y);
                addEntite(entite,x-1,y);
            }break;

        }
        return false;
    }


    private void addEntite(Entite entite, int x, int y) {
        map[x][y] = entite;
        this.entites.put(entite, new Point(x, y));
        notifyObservers();
    }

    private void supprimerEntite(Entite entite, int x, int y){
        map[x][y] = new Vide(this);
        this.entites.remove(entite);
        notifyObservers();
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
