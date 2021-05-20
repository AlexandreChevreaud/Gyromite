package Modele.Deplacement;

import Modele.Direction;
import Modele.EntiteDynamique;

import java.util.ArrayList;

public abstract class RealisateurDeDeplacement {
    protected ArrayList<EntiteDynamique> lstEntitesDynamiques = new ArrayList<EntiteDynamique>();
    protected abstract boolean realiserDeplacement();

    public void addEntiteDynamique(EntiteDynamique entite) {
        lstEntitesDynamiques.add(entite);
    };
//    public void removeEntiteDynamique(EntiteDynamique entite) {
//        lstEntitesDynamiques.remove(entite);
//    };

    public abstract void setDirectionCourante(Direction direction);
}
