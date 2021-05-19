package Modele;

public class ColoneBasPlatform extends EntiteDynamique{
    public ColoneBasPlatform(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.ColoneBasPlatform;
    }
}
