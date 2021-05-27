package Modele.Plateau;

public class ColonneBas extends EntiteDynamique implements ColonneExtremite{

    private Boolean isPlatform = false;

    public ColonneBas(Jeu jeu) {
        super(jeu);
    }

    @Override
    public EntiteType getType() {
        return EntiteType.ColoneBas;
    }

    @Override
    public boolean isPlatform() {
        return isPlatform;
    }

    @Override
    public void setIsPlatform(boolean isPlatform) {
        this.isPlatform = isPlatform;
    }
}
