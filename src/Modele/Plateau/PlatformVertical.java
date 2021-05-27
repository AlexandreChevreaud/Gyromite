package Modele.Plateau;

public class PlatformVertical extends EntiteStatic{

    public PlatformVertical(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.PlatformVertical;
    }
}
