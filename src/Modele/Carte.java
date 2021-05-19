package Modele;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.net.URL;

public class Carte {

    private Jeu jeu;
    private HashMap<Entite, Point> entites = new HashMap<>();
    private Entite[][] map;

    private Entite caseActuelle;

    public Carte(Jeu jeu) {
        this.jeu = jeu;
        this.caseActuelle = new Vide(jeu);
    }

    // TODO à faire, carte de test actuellement
    public void chargerCarte()  {

        map = new Entite[Jeu.SIZE_X][Jeu.SIZE_Y];
        String carteString ="";
        try {
            String path = new File(".").getCanonicalPath();

            File carte = new File(path.replace("\\","\\\\")+"\\Ressources\\carte.txt");
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
                        break;
                    case 'H':
                        entite = new PlatformVertical(jeu);
                        addEntite(entite,i,j);
                        break;
                    case 'B':
                        entite = new ColoneBas(jeu);
                        addEntite(entite,i,j);
                        break;
                    case 'D':
                        entite = new PlatformeDroite(jeu);
                        addEntite(entite,i,j);
                        break;
                    case 'G':
                        entite = new PlatformeGauche(jeu);
                        addEntite(entite,i,j);
                        break;
                    case 'C':
                        entite = new Corde(jeu);
                        addEntite(entite,i,j);
                        break;
                    case 'M':
                        entite = new Mur(jeu);
                        addEntite(entite,i,j);
                        break;
                    case 'Z':
                        addEntite(jeu.getPersonnage(),i,j);
                        break;
                    //TODO Ajouter le reste au dessus du case:V
                    case 'V':
                    default:
                        entite = new Vide(jeu);
                        addEntite(entite,i,j);
                        break;
                }
            }
    }

    public void addEntite(Entite entite, int x, int y) {
        map[x][y] = entite;
        this.entites.put(entite, new Point(x, y));
    }

    public void supprimerEntite(Entite entite, int x, int y){
        map[x][y] = caseActuelle;
        this.entites.remove(entite);
    }

    public Entite getCaseActuelle() {
        return caseActuelle;
    }

    public HashMap<Entite, Point> getEntites() {
        return entites;
    }

    public Entite[][] getMap() {
        return map;
    }

    public void setCaseActuelle(Entite caseActuelle) {
        this.caseActuelle = caseActuelle;
    }
}
