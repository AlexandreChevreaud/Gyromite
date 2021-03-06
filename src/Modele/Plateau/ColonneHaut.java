package Modele.Plateau;

public class ColonneHaut extends EntiteDynamique implements ColonneExtremite{

    public ColonneHaut(Jeu jeu) {
        super(jeu);
    }
    private Boolean isPlatform = false;

    @Override
    public EntiteType getType() {
        return EntiteType.ColonneHaut;
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
