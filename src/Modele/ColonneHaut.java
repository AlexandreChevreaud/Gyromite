package Modele;

public class ColonneHaut extends EntiteDynamique{
    public ColonneHaut(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.ColonneHaut;
    }
}
