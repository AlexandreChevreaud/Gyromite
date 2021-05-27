package Modele.Plateau;

public class Vide extends EntiteStatic{
    public Vide(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Vide;
    }
}
