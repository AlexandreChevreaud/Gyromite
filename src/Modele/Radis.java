package Modele;

public class Radis extends EntiteStatic{
    public Radis(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Radis;
    }
}
