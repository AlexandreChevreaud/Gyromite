package Modele;

import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class Carte {

    private Jeu jeu;
    private HashMap<Entite, Point> entites = new HashMap<>();
    private Entite[][] map;

    private Entite casePrécedente;

    public Carte(Jeu jeu) {
        this.jeu = jeu;
        chargerCarte();
        this.casePrécedente = new Vide(jeu);
    }

    // TODO à faire, carte de test actuellement
    public void chargerCarte(){
        File carte = new File("C:\\Users\\alexa\\IdeaProjects\\Gyromite\\Ressources\\carte.txt");
        map = new Entite[Jeu.SIZE_X][Jeu.SIZE_Y];
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
        for(int i = 0; i<carteString.length()/Jeu.SIZE_X;i++)
            for(int j = 0; j<carteString.length()/Jeu.SIZE_Y;j++){
                switch (carteString.charAt(i*Jeu.SIZE_X+j)){
                    case 'P':
                        entite = new Platform(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'H':
                        entite = new PlatformVertical(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'B':
                        entite = new ColoneBas(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'D':
                        entite = new PlatformeDroite(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'G':
                        entite = new PlatformeGauche(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'C':
                        entite = new Corde(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'M':
                        entite = new Mur(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                    case 'Z':
                        addEntite(jeu.getPersonnage(),i,j);
                        map[i][j] = jeu.getPersonnage();
                        break;
                    //TODO Ajouter le reste au dessus du case:V
                    case 'V':
                    default:
                        entite = new Vide(jeu);
                        addEntite(entite,i,j);
                        map[i][j] = entite;
                        break;
                }
            }
    }



    public void addEntite(Entite entite, int x, int y) {
        map[x][y] = entite;
        this.entites.put(entite, new Point(x, y));
    }

    public void supprimerEntite(Entite entite, int x, int y){
        map[x][y] = casePrécedente;
        this.entites.remove(entite);
    }

    public HashMap<Entite, Point> getEntites() {
        return entites;
    }

    public Entite[][] getMap() {
        return map;
    }

    public Entite getCasePrécedente() {
        return casePrécedente;
    }

    public void setCasePrécedente(Entite casePrécedente) {
        this.casePrécedente = casePrécedente;
    }
}
