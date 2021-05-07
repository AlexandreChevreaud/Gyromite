package Modele;

public abstract class EntiteDynamique extends Entite{

    public EntiteDynamique(Jeu jeu) {
        super(jeu);
    }

    public abstract EntiteType getType();
    @Override
    public boolean avancerDirectionChoisie(Direction direction) {
        // TODO Ajouter les mouvements
        return jeu.deplacerEntite(this, direction);
    }
}
