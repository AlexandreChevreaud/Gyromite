package Modele;
import java.awt.Point;
import java.io.*;
import java.util.HashMap;
import java.util.Observable;

//TODO peut être deplacer l'observable
public class Jeu extends Observable implements Runnable {

    public static final int SIZE_X = 16;
    public static final int SIZE_Y = 16;
    private int pause = 200; // période de rafraichissement


    private HashMap<Entite,Point> entites = new HashMap<>();
    private Entite[][] map;

    private Personnage personnage;

    public Jeu() {
        this.personnage = new Personnage(this);
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
        File carte = new File("C:\\Users\\alexa\\IdeaProjects\\Gyromite\\Ressources\\carte.txt");
        map = new Entite[SIZE_X][SIZE_Y];
        String carteString ="";
        try {
            FileReader fr = new FileReader(carte);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            carteString = sb.toString();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Entite entite;
        // TODO en lien problème coordonee
        for(int i = 0; i<carteString.length()/SIZE_X;i++)
            for(int j = 0; j<carteString.length()/SIZE_Y;j++){
                switch (carteString.charAt(i*SIZE_X+j)){
                    case 'P':
                        entite = new Platform(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'H':
                        entite = new PlatformVertical(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'B':
                        entite = new ColoneBas(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'D':
                        entite = new PlatformeDroite(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'G':
                        entite = new PlatformeGauche(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'C':
                        entite = new Corde(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'M':
                        entite = new Mur(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'Z':
                        addEntite(this.personnage,i,j);
                        map[i][j] = this.personnage;
                        break;
                        //TODO Ajouter le reste au dessus du case:V
                    case 'V':
                    default:
                        entite = new Vide(this);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                }
            }
    }

    public Boolean deplacerEntite(Entite entite, Direction direction){
        //TODO à faire
        int x = entites.get(entite).x;
        int y = entites.get(entite).y;

        //TODO en lien problème coordonee
        switch (direction){
            case Gauche: if(y-1>=0 && map[x][y-1].getType() == EntiteType.Vide) {
                supprimerEntite(entite,x,y);
                addEntite(entite,x,y-1);
            }break;

            case Droite: if(y+1>=0 && (map[x][y+1].getType() == EntiteType.Vide || map[x][y+1].getType() == EntiteType.Corde)) {
                supprimerEntite(entite,x,y);
                addEntite(entite,x,y+1);
            }break;

            case Bas: if(x+1>=0 && map[x+1][y].getType() == EntiteType.Vide) {
                supprimerEntite(entite,x,y);
                addEntite(entite,x+1,y);
            }break;

            case Haut: if(x-1>=0 && (map[x-1][y].getType() == EntiteType.Vide|| map[x-1][y].getType() == EntiteType.Corde)) {
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
