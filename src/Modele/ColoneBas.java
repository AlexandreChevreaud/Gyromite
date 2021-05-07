package Modele;

public class ColoneBas extends EntiteDynamique{
    public ColoneBas(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.ColoneBas;
    }
}
