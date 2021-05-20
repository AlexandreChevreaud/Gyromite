package Modele.Plateau;

public class Corde extends EntiteStatic {
    public Corde(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Corde;
    }
}
