package Modele.Deplacement;
import Modele.Plateau.Direction;
import Modele.Plateau.EntiteDynamique;

public class Ennemi extends RealisateurDeDeplacement {
    private static Direction direction = Direction.Gauche;
    private static Ennemi ennemi;
    private int cpt = 0;


    @Override
    protected boolean realiserDeplacement() {

        for (EntiteDynamique e : lstEntitesDynamiques)
        {

            if (direction != null)
            {
                e.avancerDirectionChoisie(direction);
            }
        }
        cpt++;
        if (cpt == 2)
        {
            cpt = 0;
            ChangeDirection();
        }
        return true;
    }

    @Override
    public void setDirectionCourante(Direction direction) {
        this.direction = direction;
    }

    /**
     * change la direction (car les ennemis n'en ont que 2
     */
    private void ChangeDirection()
    {
        if (direction == Direction.Gauche)
           setDirectionCourante(Direction.Droite);
        else
           setDirectionCourante(Direction.Gauche);

    }

    /**
     * retourne l'instance
     * @return
     */
    public static Ennemi getInstance()
    {
        if (ennemi == null)
        {
            ennemi = new Ennemi();
        }
        return ennemi;
    }

    public void resetDirection() {
        direction = null;
    }

}
