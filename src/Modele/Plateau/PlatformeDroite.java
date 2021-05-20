package Modele.Plateau;

public class PlatformeDroite extends EntiteStatic{
    public PlatformeDroite(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.PlatformeDroite;
    }
}
