package Modele.Plateau;
import Modele.Deplacement.Colonne;
import Modele.Deplacement.Ennemi;
import Modele.Deplacement.Ordonnanceur;

//TODO peut être deplacer l'observable
public class Jeu {

    public static final int SIZE_X = 16;
    public static final int SIZE_Y = 14;

    private boolean isGameWin = false;

    private Carte carte;
    private Personnage personnage;

    private Ordonnanceur ordonnanceur = new Ordonnanceur(this);

    public Jeu()  {
        this.personnage = new Personnage(this);
        carte = new Carte(this);
        carte.chargerCarte();
        getOrdonnanceur().add(Colonne.getInstance());
//        getOrdonnanceur().add(Ennemi.getInstance());
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public Carte getCarte() {
        return carte;
    }

    public Boolean deplacerEntite(Entite entite, Direction direction){
        int x = carte.getEntites().get(entite).x;
        int y = carte.getEntites().get(entite).y;
        Boolean deplacer = false;
        if(entite.getType() == EntiteType.Personnage) {
            switch (direction) {
                case Gauche:
                    if (y - 1 >= 0 && (carte.getMap()[x][y - 1].getType() == EntiteType.Vide || carte.getMap()[x][y - 1].getType() == EntiteType.Corde || carte.getMap()[x][y - 1].getType() == EntiteType.Radis)) {
                        Entite caseActuelle;
                        if (carte.getMap()[x][y - 1].getType() != EntiteType.Radis)
                            caseActuelle = carte.getMap()[x][y - 1];
                        else
                            caseActuelle = new Vide(this);
                        carte.supprimerEntite(entite, x, y);
                        carte.addEntite(entite, x, y - 1);
                        carte.setCaseActuelle(caseActuelle);
                        deplacer = true;
                    }
                    break;

                case Droite:
                    if (y + 1 >= 0 && (carte.getMap()[x][y + 1].getType() == EntiteType.Vide || carte.getMap()[x][y + 1].getType() == EntiteType.Corde || carte.getMap()[x][y + 1].getType() == EntiteType.Radis)) {
                        Entite caseActuelle;
                        if (carte.getMap()[x][y + 1].getType() != EntiteType.Radis)
                            caseActuelle = carte.getMap()[x][y + 1];
                        else
                            caseActuelle = new Vide(this);
                        carte.supprimerEntite(entite, x, y);
                        carte.addEntite(entite, x, y + 1);
                        carte.setCaseActuelle(caseActuelle);
                        deplacer = true;
                    }
                    break;

                case Bas:
                    if (x + 1 >= 0 && carte.getMap()[x + 1][y].getType() == EntiteType.Vide) {
                        Entite caseActuelle = carte.getMap()[x + 1][y];
                        carte.supprimerEntite(entite, x, y);
                        carte.addEntite(entite, x + 1, y);
                        carte.setCaseActuelle(caseActuelle);
                        deplacer = true;
                    }
                    break;

                case Haut:
                    if (x - 1 >= 0 && (carte.getMap()[x - 1][y].getType() == EntiteType.Vide || carte.getMap()[x - 1][y].getType() == EntiteType.Corde)) {
                        Entite caseActuelle = carte.getMap()[x - 1][y];
                        carte.supprimerEntite(entite, x, y);
                        carte.addEntite(entite, x - 1, y);
                        carte.setCaseActuelle(caseActuelle);
                        deplacer = true;
                    }
                    break;

            }

        }
        else{
           switch (direction){
               case Haut -> {
                   if (x - 1 >= 0 && carte.getMap()[x - 1][y].getType() == EntiteType.Vide) {
                       Entite caseActuelle = carte.getMap()[x - 1][y];
                       carte.supprimerEntite(entite, x, y);
                       carte.addEntite(entite, x - 1, y);
                       carte.setCaseActuelle(caseActuelle);
                       deplacer = true;
                   }
               }
               case Bas -> {
                   if (x + 1 >= 0 && carte.getMap()[x + 1][y].getType() == EntiteType.Vide) {
                       Entite caseActuelle = carte.getMap()[x + 1][y];
                       carte.supprimerEntite(entite, x, y);
                       carte.addEntite(entite, x + 1, y);
                       carte.setCaseActuelle(caseActuelle);
                       deplacer = true;
                   }
               }
               default -> {
                   System.out.println("Déplacer une colonne horizontalement n'est pas possible");
               }
           }
        }
        return deplacer;
    }

    public void start(long pause) {
        ordonnanceur.start(pause);
    }

    public boolean isGameWin() {
        return isGameWin;
    }
    public Ordonnanceur getOrdonnanceur() {
        return ordonnanceur;
    }
}
