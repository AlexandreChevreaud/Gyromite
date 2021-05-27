package Modele.Plateau;

public class Platform extends EntiteStatic{
    public Platform(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Platform;
    }
}
