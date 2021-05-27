package Modele;

public class Mur extends EntiteStatic{
    public Mur(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.Mur;
    }
}
