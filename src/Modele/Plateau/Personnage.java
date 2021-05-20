package Modele.Plateau;

public class Personnage extends EntiteDynamique{

    public Personnage(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Personnage;
    }
}
