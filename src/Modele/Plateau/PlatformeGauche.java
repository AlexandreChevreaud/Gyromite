package Modele.Plateau;

public class PlatformeGauche extends EntiteStatic{
    public PlatformeGauche(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.PlatformeGauche;
    }
}
