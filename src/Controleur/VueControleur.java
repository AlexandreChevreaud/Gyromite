package Controleur;
import Modele.Direction;
import Modele.Entite;
import Modele.EntiteType;
import Modele.Jeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class VueControleur extends JFrame implements Observer {

    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private int sizeX; // taille de la grille affichée
    private int sizeY;
    private JLabel[][] tabJLabel;

    private final int TAILLE_IMG = 40;

    //Icones
    ImageIcon platform;
    ImageIcon corde;
    ImageIcon mur;
    ImageIcon vide;
    ImageIcon murGauche;
    ImageIcon murDroite;
    ImageIcon platformVertical;
    ImageIcon tuyauxBleuLargeHautPlatform;
    ImageIcon tuyauxBleuLargeHaut;
    ImageIcon tuyauxBleu;
    ImageIcon tuyauxBleuPlatform;
    ImageIcon tuyauxBleuLargeBasPlatform;
    ImageIcon tuyauxBleuLargeBas;
    ImageIcon tuyauxBleuLargeHautCoupe;
    ImageIcon tuyauxBleuLargeBasCoupe;
    ImageIcon hero;
    ImageIcon heroSurCorde;
    ImageIcon radis;


    public VueControleur(Jeu _jeu)  {
        sizeX = Jeu.SIZE_X;
        sizeY = Jeu.SIZE_Y;
        jeu = _jeu;

        InitialisationComposantsGraphique();
        ajouterEcouteurClavier();
        recupererImages();
    }
// TODO voir pourquoi c'est pas x,y
    private void ajouterEcouteurClavier() {
        // TODO modifier
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : jeu.getPersonnage().avancerDirectionChoisie(Direction.Gauche); break;
                    case KeyEvent.VK_RIGHT : jeu.getPersonnage().avancerDirectionChoisie(Direction.Droite);break;
                    case KeyEvent.VK_DOWN : jeu.getPersonnage().avancerDirectionChoisie(Direction.Bas); break;
                    case KeyEvent.VK_UP : jeu.getPersonnage().avancerDirectionChoisie(Direction.Haut); break;
                }
            }
        });
    }

    private void InitialisationComposantsGraphique(){
        setTitle("Gyromite");
        setSize(sizeX * TAILLE_IMG, sizeY * TAILLE_IMG);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX));

        tabJLabel = new JLabel[sizeY][sizeX];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[y][x] = jlab;
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels);
    }

    private void recupererImages()  {
        BufferedImage bufferDecors;
        BufferedImage bufferPersonnage;
        try {
            String path = new File(".").getCanonicalPath();

            File decors = new File(path.replace("\\","\\\\")+"\\Ressources\\Decor.png");
            File personnage = new File(path.replace("\\","\\\\")+"\\Ressources\\Personnage.png");

            // TODO HasHmap ? ou un truc du genre
            bufferDecors = ImageIO.read(decors);
            bufferPersonnage = ImageIO.read(personnage);
            hero = new ImageIcon(bufferPersonnage.getSubimage(0,0,20,25).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            heroSurCorde = new ImageIcon(bufferPersonnage.getSubimage(1,52,20,25).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            radis = new ImageIcon(bufferPersonnage.getSubimage(72,255,13,14).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            platform = new ImageIcon(bufferDecors.getSubimage(0,0,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            corde = new ImageIcon(bufferDecors.getSubimage(16,0,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            mur = new ImageIcon(bufferDecors.getSubimage(32,0,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            vide = new ImageIcon(bufferDecors.getSubimage(48,0,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            platformVertical = new ImageIcon(bufferDecors.getSubimage(0,16,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            murGauche = new ImageIcon(bufferDecors.getSubimage(16,16,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            murDroite = new ImageIcon(bufferDecors.getSubimage(32,16,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleuLargeHautPlatform = new ImageIcon(bufferDecors.getSubimage(0,32,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleuPlatform = new ImageIcon(bufferDecors.getSubimage(16,32,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleuLargeBasPlatform = new ImageIcon(bufferDecors.getSubimage(32,32,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleuLargeHaut = new ImageIcon(bufferDecors.getSubimage(0,48,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleu = new ImageIcon(bufferDecors.getSubimage(16,48,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleuLargeBas = new ImageIcon(bufferDecors.getSubimage(32,48,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleuLargeHautCoupe = new ImageIcon(bufferDecors.getSubimage(48,48,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
            tuyauxBleuLargeBasCoupe = new ImageIcon(bufferDecors.getSubimage(64,48,16,16).getScaledInstance(TAILLE_IMG, TAILLE_IMG,java.awt.Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void mettreAJourAffichage() {

        Entite[][] entites = this.jeu.getCarte().getMap();
        for (int i  = 0;i<this.sizeY;i++)
            for (int j  = 0;j<this.sizeX;j++){
                switch (entites[i][j].getType()){
                    case Vide : tabJLabel[i][j].setIcon(vide); break;
                    case Personnage:
                        if (jeu.getCarte().getCaseActuelle().getType() == EntiteType.Corde) {
                            tabJLabel[i][j].setIcon(heroSurCorde);
                        } else {
                            tabJLabel[i][j].setIcon(hero);
                        }
                        break;
                    case Mur: tabJLabel[i][j].setIcon(mur); break;
                    case PlatformeDroite: tabJLabel[i][j].setIcon(murGauche); break;
                    case PlatformeGauche: tabJLabel[i][j].setIcon(murDroite); break;
                    case PlatformVertical: tabJLabel[i][j].setIcon(platformVertical); break;
                    case Platform: tabJLabel[i][j].setIcon(platform); break;
                    case Corde: tabJLabel[i][j].setIcon(corde); break;
                    case ColoneBasPlatform: tabJLabel[i][j].setIcon(tuyauxBleuLargeBasPlatform); break;
                    case ColonneHaut: tabJLabel[i][j].setIcon(tuyauxBleuLargeHaut); break;
                    case Colonne: tabJLabel[i][j].setIcon(tuyauxBleu); break;
                    case Radis: tabJLabel[i][j].setIcon(radis); break;
                }

            }
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO a mieux faire
        mettreAJourAffichage();

    }
}
