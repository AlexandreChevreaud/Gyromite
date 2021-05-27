package Modele.Deplacement;

import Modele.Plateau.*;
import java.util.Collections;

public class Colonne extends RealisateurDeDeplacement{
    private static Direction POSITION = Direction.Haut;
    private static Direction direction = null;
    private static Colonne colonne;
    private int cpt=0;

    @Override
    protected boolean realiserDeplacement() {
        boolean res = false;
        if(direction != null) {
            cpt++;
            if (direction == Direction.Bas) {
                Collections.reverse(lstEntitesDynamiques);
            }
            for (EntiteDynamique e : lstEntitesDynamiques) {
                if(POSITION != direction)
                    res = e.avancerDirectionChoisie(direction) && !res;
                if(cpt == 2){
                    switch (direction) {
                        case Haut -> {
                            if(e.getType()== EntiteType.ColoneBas)
                                ((ColonneBas)e).setIsPlatform(true);
                            if(e.getType()== EntiteType.ColonneHaut)
                                ((ColonneHaut)e).setIsPlatform(false);
                        }
                        case Bas -> {
                            if(e.getType()== EntiteType.ColoneBas)
                                ((ColonneBas)e).setIsPlatform(false);
                            if(e.getType()== EntiteType.ColonneHaut)
                                ((ColonneHaut)e).setIsPlatform(true);
                        }
                        default -> System.out.println("La colonne ne pas aller à droite ou à gauche");
                    }
                }

            }
            if (direction == Direction.Bas) {
                Collections.reverse(lstEntitesDynamiques);
            }
            if(cpt == 2){
                cpt =0;
                switch (direction) {
                    case Haut -> POSITION = Direction.Haut;
                    case Bas -> POSITION = Direction.Bas;
                    default -> System.out.println("La colonne ne pas aller à droite ou à gauche");
                }
                resetDirection();
            }
        }

        return res;
    }

    @Override
    public void setDirectionCourante(Direction direction) {
        switch (direction) {
            case Haut -> this.direction = Direction.Haut;
            case Bas -> this.direction = Direction.Bas;
            default -> System.out.println("La colonne ne pas aller à droite ou à gauche");
        }
    }

    public static Colonne getInstance() {
        if (colonne == null) {
            colonne = new Colonne();
        }
        return colonne;
    }

    public void resetDirection() {
        direction = null;
    }
}
