package Modele.Plateau;

public class Ennemi extends EntiteDynamique {
    public Ennemi(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Ennemi;
    }
}
