package Modele.Deplacement;

import Modele.Plateau.Direction;
import Modele.Plateau.EntiteDynamique;

import java.util.Collection;
import java.util.Collections;

public class Colonne extends RealisateurDeDeplacement{
    private static Direction POSITION = Direction.Haut;
    private static Direction direction = Direction.Haut;
    private static Colonne colonne;
    private int cpt=0;

    @Override
    protected boolean realiserDeplacement() {
        if(direction != null) {
            if (direction == Direction.Bas)
                Collections.reverse(lstEntitesDynamiques);
            for (EntiteDynamique e : lstEntitesDynamiques) {
                if (!e.avancerDirectionChoisie(direction)) {
                }
                if (direction == Direction.Bas)
                    Collections.reverse(lstEntitesDynamiques);
            }
            cpt++;
            if(cpt ==3){
                resetDirection();
                cpt =0;
            }
        }

        return true;
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
