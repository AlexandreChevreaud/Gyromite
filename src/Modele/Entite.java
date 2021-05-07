package Modele;

public abstract class Entite{
    protected Jeu jeu;
    // TODO renommer selon les critère du sujet
    public abstract boolean avancerDirectionChoisie(Direction direction);
    public abstract EntiteType getType();
    public Entite(Jeu jeu){
        this.jeu = jeu;
    }
}
