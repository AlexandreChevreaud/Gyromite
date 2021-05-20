package Modele.Deplacement;

import Modele.*;

public class Controle4Directions extends RealisateurDeDeplacement{
    private static Direction directionCourante;
    //private static Direction lastDirection;
    private static Controle4Directions controle4Directions;

    @Override
    protected boolean realiserDeplacement() {
        boolean res = false;
        if(directionCourante != null)
            for (EntiteDynamique e : lstEntitesDynamiques){
                return e.avancerDirectionChoisie(directionCourante);
            }
        return res;
    }
    public void resetDirection() {
        directionCourante = null;
    }
    @Override
    public void setDirectionCourante(Direction direction) {
        directionCourante = direction;
    }

    public static Controle4Directions getInstance() {
        if (controle4Directions == null) {
            controle4Directions = new Controle4Directions();
        }
        return controle4Directions;
    }
}
