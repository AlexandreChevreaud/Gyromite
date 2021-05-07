package Controleur;
import Modele.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class VueControleur extends JFrame implements Observer {

    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int sizeX; // taille de la grille affichée
    private int sizeY;
    private JLabel[][] tabJLabel;

    public VueControleur(Jeu _jeu){
        sizeX = Jeu.SIZE_X;
        sizeY = Jeu.SIZE_Y;
        jeu = _jeu;

        InitialisationComposantsGraphique();
        ajouterEcouteurClavier();

    }

    private void ajouterEcouteurClavier() {
        // TODO modifier
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                /*switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : jeu.getHeros().gauche(); break;
                    case KeyEvent.VK_RIGHT : jeu.getHeros().droite();break;
                    case KeyEvent.VK_DOWN : jeu.getHeros().bas(); break;
                    case KeyEvent.VK_UP : jeu.getHeros().haut(); break;

                }*/
            }
        });
    }

    private void InitialisationComposantsGraphique(){
        setTitle("Gyromite");
        setSize(sizeX * 10, sizeY * 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX+1));

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab;
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO a faire
    }
}
