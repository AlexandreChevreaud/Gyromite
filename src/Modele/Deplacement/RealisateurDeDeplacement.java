package Modele.Deplacement;

import Modele.Plateau.Direction;
import Modele.Plateau.EntiteDynamique;

import java.util.ArrayList;

public abstract class RealisateurDeDeplacement {
    protected ArrayList<EntiteDynamique> lstEntitesDynamiques = new ArrayList<EntiteDynamique>();
    protected abstract boolean realiserDeplacement();

    public void addEntiteDynamique(EntiteDynamique entite) {
        lstEntitesDynamiques.add(entite);
    };


    public abstract void setDirectionCourante(Direction direction);
}
