package Modele;

public abstract class EntiteStatic extends Entite{

    public EntiteStatic(Jeu jeu) {
        super(jeu);
    }

    public abstract EntiteType getType();
    @Override
    public boolean avancerDirectionChoisie(Direction direction) {
        // TODO a faire
        return false;
    }
}
