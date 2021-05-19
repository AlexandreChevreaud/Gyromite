package Modele;

public class Colonne extends EntiteDynamique{
    public Colonne(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Colonne;
    }
}
