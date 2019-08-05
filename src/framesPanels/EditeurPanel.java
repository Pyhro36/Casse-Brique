package framesPanels;


import java.applet.Applet;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import main.Fenetre;
import entitees.Bonus;
import entitees.Brique;
import entitees.BriqueBonus;
import entitees.BriqueIndes;
import entitees.BriqueNVies;
import entitees.Fond;
import framesAux.Convertisseur;
import framesAux.Sauvegarde;

/**
 * cette classe permet de construire un éditeur de niveau
 * @author Peter Ludovic
 * @version 0.1
 */
public class EditeurPanel extends NiveauPanel {
    
	/*
     * 
     */
    private static final long serialVersionUID = 5L;
	Rectangle ecran;
    BufferedImage arrierePlan;
    Graphics buffer;
    Brique[][] test = new Brique[18][25];
    long temps;
    boolean grille=false;
    boolean aide=false;
    String active="";
    String niv;
    boolean fenetre=false;
    JeuMouseAdapter jeuMouseAdapter;
    MouseMotionListener MotionMouse;
    String bonusActif="bonusballefeu";
    boolean clicDroit=false;
    boolean clicGauche=false;
    boolean temoin;
    boolean temoinBis;
    Fond fond;
    
    /**Ce constructeur permet d'initialiser les diférentes variable du panel éditeur
     * @param frame la fenetre dans laquelle s'affiche le panel éditeur
     * */
    public EditeurPanel (Fenetre frame){
        super(frame);
        ecran = new Rectangle(0, 0, parent.getSize().width - parent.getInsets().right - parent.getInsets().left - 110, parent.getSize().height -
                parent.getInsets().bottom - parent.getInsets().top);
        arrierePlan =new BufferedImage(parent.getSize().width,parent.getSize().height,
        BufferedImage.TYPE_INT_RGB);
        buffer = arrierePlan.getGraphics();
        jeuMouseAdapter = new JeuMouseAdapter();
        parent.addMouseListener (jeuMouseAdapter);
        MotionMouse=new glisser();
        parent.addMouseMotionListener(MotionMouse);
        /*try {
            fond = new Fond();
        } catch (FileNotFoundException e) {
            fond=null;
        } catch (IOException e) {
            fond=null;
        }
        fond.draw(temps,buffer);
          repaint();*/
    }
    
    /**Ce paint dessine tout les éléments du panel (bouton, zone) ainsi que les briques placées par l'utilisateur
     * @param Graphics g
     * */
    public void paint(Graphics g) {
        buffer.setColor(Color.black);
        buffer.fillRect(0, 0, 800, 600);
        //fond.draw(temps,buffer);

        //bandeau
        buffer.setColor(Color.red);
        buffer.fillRect(684,0,112,573);
        
        
        //bouton grille et gestion de son affichage
        {
        if(grille) buffer.setColor(Color.cyan);
        else buffer.setColor(Color.blue);
        buffer.fillRect(697,14,38,19);
        buffer.setColor(Color.black);
        buffer.drawString("grille",702,29);
        if(grille){
            buffer.setColor(Color.lightGray);
            for (int i=0;i<=684;i=i+38){
                buffer.drawLine(i,0,i,475);
            }
            for (int j=0;j<=475;j=j+19){
                buffer.drawLine(0,j,684,j);
            }
        }
        }
        
        //bouton aide + affichage de l'aide en fonction du contexte 
        {
        if(aide) buffer.setColor(Color.lightGray);
        else buffer.setColor(Color.darkGray);
        buffer.fillRect(747,14,38,19);
        buffer.setColor(Color.black);
        buffer.drawString("aide",752,29);
        if(aide){
            buffer.setColor(Color.white);
            buffer.drawString("clic gauche pour placer la brique sélectionnée                        clic droit pour supprimer une brique",20,500);
            switch (active){
                case ("indes") :
                buffer.drawString("cette Brique ne peut pas étre détruite sauf avec certain bonus (Balle en feu)",20,530);
                break; 
            
                case ("1vie") :
                buffer.drawString("cette Brique est détruite en un coup",20,530);
                break;
            
                case ("2vie") :
                buffer.drawString("cette Brique est détruite en deux coups",20,530);
                break;
            
                case ("3vie") :
                buffer.drawString("cette brique est détruite en trois coups",20,530);
                break;
            
                case ("bonus") :
                buffer.drawString("cette brique libère un bonus (ou un malus) quand on la détruit",20,520);
                buffer.drawString("ATTENTION en mode opposition les briques bonus deviennent des briques à 1 vie",20,540);
                switch(bonusActif){
                    case("bonusballefeu") :
                    buffer.drawString("ce bonus change temporairement la balle en feu, une balle en feu détruit les cases voisines de la brique d'impact",20,560);
                    break;
                
                    case("bonusballeverre") :
                    buffer.drawString("ce malus change temporairement la balle en Verre, une balle en verre est cassée lors d'un impact avec une brique indestructible",20,560);
                    break;
                
                    case("bonusballelente") :
                    buffer.drawString("ce bonus ralentit définitivement la balle",20,560);
                    break;
                
                    case("bonusballerapide") :
                    buffer.drawString("ce malus accélère définitivement la balle",20,560);
                    break;
                
                    case("bonusraquettegrande") :
                    buffer.drawString("ce bonus aggrandit temporairement la taille de la raquette         ATTENTION en mode coopération (devient une brique 1 vie)",20,560);
                    break;
                
                    case("bonusraquettepetite") :
                    buffer.drawString("ce malus diminue temporairement la taille de la raquette          ATTENTION en mode coopération (devient une brique 1 vie)",20,560);
                    break;
                
                    case("bonusmultiballe") :
                    buffer.drawString("ce bonus multiplie le nombre de balle en jeu",20,560);
                    break;
                
                    default :
                    buffer.drawString("cliquez sur un bonus pour le sélectionner",20,560);
                
                }
                break;
                
                default :
                buffer.drawString("cliquez sur une brique pour la sélectionner",20,530);
            }
        }
        }
        
        //affichage du choix de brique et marquage de la brique sélectionnée
        {
        buffer.setColor(Color.black);
        switch (active){
            case ("indes") :
            buffer.fillRect(695,42,42,23);
            break; 
        
            case ("1vie") :
            buffer.fillRect(745,42,42,23);
            break;
        
            case ("2vie") :
            buffer.fillRect(695,72,42,23);
            break;
        
            case ("3vie") :
            buffer.fillRect(745,72,42,23);
            break;
        
            case ("bonus") :
            buffer.fillRect(695,102,42,23);
            switch(bonusActif){
                case("bonusballefeu") :
                buffer.fillOval(703,131,29,29);
                break;
                
                case("bonusballeverre") :
                buffer.fillOval(753,131,29,29);
                break;
            
                case("bonusballelente") :
                buffer.fillRect(695,162,47,20);
                break;
            
                case("bonusballerapide") :
                buffer.fillRect(745,162,44,20);
                break;
            
                case("bonusraquettegrande") :
                buffer.fillRect(700,191,38,22);
                break;
                
                case("bonusraquettepetite") :
                buffer.fillRect(748,191,40,22);
                break;
                          
                case("bonusmultiballe") :
                buffer.fillRect(698,218,40,24);
                break;
            }
            break;       
        }
        //choix des Briques et des bonus
        Brique indes=new BriqueIndes(697,44);indes.draw(temps,buffer);
        BriqueNVies uneVie=new BriqueNVies(747,44,1);uneVie.draw(temps,buffer);
        BriqueNVies deuxVie=new BriqueNVies(697,74,2);deuxVie.draw(temps,buffer);
        BriqueNVies troisVie=new BriqueNVies(747,74,3);troisVie.draw(temps,buffer);
        Bonus bonus =new Bonus(697,104,ecran,"bonusballeverre.png");
        BriqueBonus BBonus = new BriqueBonus(697,104,bonus);BBonus.draw(temps,buffer);
        if(active=="bonus"){
            Bonus balleFeu =new Bonus(707,134,ecran,"bonusballefeu.png"); balleFeu.draw(temps,buffer);
            Bonus balleVerre =new Bonus(757,134,ecran,"bonusballeverre.png"); balleVerre.draw(temps,buffer);
            Bonus fast =new Bonus(750,164,ecran,"bonusballerapide.png"); fast.draw(temps,buffer);
            Bonus slow =new Bonus(698,164,ecran,"bonusballelente.png"); slow.draw(temps,buffer);
            Bonus grand =new Bonus(704,192,ecran,"bonusraquettegrande.png"); grand.draw(temps,buffer);
            Bonus petit =new Bonus(754,192,ecran,"bonusraquettepetite.png"); petit.draw(temps,buffer);
            Bonus multi =new Bonus(705,222,ecran,"bonusmultiballe.png"); multi.draw(temps,buffer);
        }
        }
        
        //affichage des bouton sauvegarder, charger ,retour, nouveau
        {
        buffer.setColor(Color.green);
        buffer.fillRect(747,524,38,19);
        buffer.setColor(Color.yellow);
        buffer.fillRect(697,524,38,19);
        buffer.setColor(Color.magenta);
        buffer.fillRect(697,496,38,19);
        buffer.setColor(Color.cyan);
        buffer.fillRect(747,496,38,19);
        buffer.setColor(Color.black);
        buffer.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        buffer.drawString("sauver",750,536);
        buffer.drawString("charger",699,536);
        buffer.drawString("retour",699,511);
        buffer.drawString("reset",750,511);
        }
        
        //Brique du niveaux
        for(int i=0;i<18;i++){
            for(int j=0;j<25;j++){
                if(test[i][j]!=null) test[i][j].draw(temps,buffer);
            }
        }
        g.drawImage(arrierePlan,0,0,parent);
    }
    
    /**cette méthode permet de faire une impression d'écran de la zone du niveau (zone ou l'on place les briques) et l'enregistre dans un fichier
     * @param String nom le nom du fichier ou l'image sera enregistrée
     * */
    public void screenShot(String nom) throws IOException, AWTException {
        Point pos=getLocationOnScreen();
        pos.setLocation(pos.getX()+getInsets().left,pos.getY()+getInsets().top);
        Rectangle ecranBien= new Rectangle ((int)pos.getX(),(int)pos.getY(),684,571);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(ecranBien);
        // sauvegarde de l'image vers un fichier "png"
        ImageIO.write(image, "png", new File(nom));
    }
    
    /**Cette méthode permet d'enregistrer dans un fichier les données du niveau pour les réutiliser plus tard
     * @param String nom le nom du fichier où le niveau est enregistré
     * */
    public void sauver(String nom){
        try {
            Convertisseur.sauver(nom, test);
        } catch (IOException e) {
        }
        try {
            screenShot(nom+".sc");
        } catch (AWTException | IOException e) {
        } 
        parent.setAlwaysOnTop(false);
        grille=temoin;aide=temoinBis;
    }
    
    /**Cette méthode correspond à un appui sur la touche sauvegarde
     * */
    public void sauvegarde(){
        Sauvegarde nom=new Sauvegarde(this);
    }
    
    
    /**Cette méthode permet de récupérer un niveau précédement enregistrée dans un fichier
    * @param String nom le nom du fichier où le niveau est enregistré
    * */
    public void charger(String nom){
        Brique[][] nivCharg;
        try {
            nivCharg = Convertisseur.charger(nom,ecran);
            for(int i=0;i<18;i++){
                for(int j=0;j<25;j++){
                    test[i][j]=nivCharg[i][j];
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        repaint();
    }
    
    /**cette classe est l'écouteur de la souris associé à un éditeur de niveau
     * */
    private class JeuMouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
            
        }

        @Override
        /**Cette méthode réuni toutes les actions et les effets possibles par un clic de souris dans une zone
         * */
        public void mousePressed(MouseEvent e) {
            int bouton = e.getButton();
            if(bouton == MouseEvent.BUTTON1) clicDroit=true;
            if(bouton == MouseEvent.BUTTON3) clicGauche=true;
            int coordX=(int)(e.getX()-3)/38;
            int coordY=(int)(e.getY()-26)/19;
            if( clicDroit){ //clic droit
                //bouton grille
                if(e.getX()<=738 && e.getX()>=700 && e.getY()>=40& e.getY()<=59){
                    if(grille) grille=false;
                    else grille=true;
                }
                
                //bouton aide
                if(e.getX()<=788 && e.getX()>=750 && e.getY()>=40& e.getY()<=59){
                    if(aide) aide=false;
                    else aide=true;
                }
                
                //bouton charger
                if(e.getX()<=738 && e.getX()>=700 && e.getY()>=550& e.getY()<=569){
                    charge();
                    }
                
                //bouton sauver
                if(e.getX()<=788 && e.getX()>=750 && e.getY()>=550& e.getY()<=569){
                    temoin =grille;
                    temoinBis =aide;
                    grille=false;
                    aide=false;
                    repaint();
                    sauvegarde();     
                } 
                
                //bouton reset
                if(e.getX()<=788 && e.getX()>=750 && e.getY()>=520& e.getY()<=539){
                    for(int i=0;i<18;i++){
                        for(int j=0;j<25;j++){
                            test[i][j]=null;
                        }
                    }
                }
                
                //bouton retour
                if(e.getX()<=738 && e.getX()>=700 && e.getY()>=520& e.getY()<=539){
                    parent.removeMouseListener(jeuMouseAdapter);
                    parent.switchPanel(new MenuPanel(parent));
                }
                
                
                //Changer Brique sélectionnée
                if(e.getX()<=738 && e.getX()>=700 && e.getY()>=70 && e.getY()<=89) active = "indes";
                if(e.getX()<=788 && e.getX()>=750 && e.getY()>=70 && e.getY()<=89) active = "1vie";
                if(e.getX()<=738 && e.getX()>=700 && e.getY()>=100 && e.getY()<=119) active = "2vie";
                if(e.getX()<=788 && e.getX()>=750 && e.getY()>=100 && e.getY()<=119) active = "3vie";
                if(e.getX()<=738 && e.getX()>=700 && e.getY()>=130 && e.getY()<=149) active = "bonus";
                if(active=="bonus"){
                    if(e.getX()<=738 && e.getX()>=700 && e.getY()>=160 && e.getY()<=179) bonusActif ="bonusballefeu";
                    else if(e.getX()<=788 && e.getX()>=750 && e.getY()>=160 && e.getY()<=179) bonusActif ="bonusballeverre";
                    else if(e.getX()<=738 && e.getX()>=700 && e.getY()>=190 && e.getY()<=209) bonusActif ="bonusballelente";
                    else if(e.getX()<=788 && e.getX()>=750 && e.getY()>=190 && e.getY()<=209) bonusActif ="bonusballerapide";
                    else if(e.getX()<=738 && e.getX()>=700 && e.getY()>=210 && e.getY()<=239) bonusActif ="bonusraquettegrande";
                    else if(e.getX()<=788 && e.getX()>=750 && e.getY()>=210 && e.getY()<=239) bonusActif ="bonusraquettepetite";
                    else if(e.getX()<=738 && e.getX()>=700 && e.getY()>=230 && e.getY()<=259) bonusActif ="bonusmultiballe";
                }
            
                //placer une Brique
                if(e.getX()<687 && e.getX()>=3 && e.getY()>=26 && e.getY()<501 && test[coordX][coordY]==null){
                    switch (active){
                        case ("indes") :
                            test[coordX][coordY]=new BriqueIndes (coordX*38,coordY*19);
                        break; 
                    
                        case ("1vie") :
                            test[coordX][coordY]=new BriqueNVies (coordX*38,coordY*19,1);
                        break;
                    
                        case ("2vie") :
                            test[coordX][coordY]=new BriqueNVies (coordX*38,coordY*19,2);
                        break;
                    
                        case ("3vie") :
                            test[coordX][coordY]=new BriqueNVies (coordX*38,coordY*19,3);
                        break;
                    
                        case ("bonus") :
                            Bonus bonus =new Bonus(697,104,ecran,bonusActif+".png");
                            test[coordX][coordY]=new BriqueBonus (coordX*38,coordY*19,bonus);
                        break;
                    }
                }
            
            }else if (clicGauche){ //clic gauche
                //Détruire une Brique.
                if(e.getX()<687 && e.getX()>=3 && e.getY()>=26 && e.getY()<501 && test[coordX][coordY]!=null){
                    test[coordX][coordY]=null;
                }
                
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int bouton = e.getButton();
            if(bouton == MouseEvent.BUTTON1) clicDroit=false;
            if(bouton == MouseEvent.BUTTON3) clicGauche=false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Implement this method
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Implement this method
        }
    }
    
    /**cette classe est l'écouteur des déplacements de la souris associé à un éditeur de niveau
     * */
    public class glisser implements MouseMotionListener{

        /**Cette méthode pemet de placer des Briques dans la zone correspondante par un glissement de la souris
         * */
        public void mouseDragged(MouseEvent e) {
            if(clicDroit){
            int coordX=(int)(e.getX()-3)/38;
            int coordY=(int)(e.getY()-26)/19;
            if(e.getX()<687 && e.getX()>=0 && e.getY()>=0 && e.getY()<501 && test[coordX][coordY]==null){
                switch (active){
                    case ("indes") :
                        test[coordX][coordY]=new BriqueIndes (coordX*38,coordY*19);
                    break; 
                
                    case ("1vie") :
                        test[coordX][coordY]=new BriqueNVies (coordX*38,coordY*19,1);
                    break;
                
                    case ("2vie") :
                        test[coordX][coordY]=new BriqueNVies (coordX*38,coordY*19,2);
                    break;
                
                    case ("3vie") :
                        test[coordX][coordY]=new BriqueNVies (coordX*38,coordY*19,3);
                    break;
                
                    case ("bonus") :
                        Bonus bonus =new Bonus(697,104,ecran,bonusActif+".png");
                        test[coordX][coordY]=new BriqueBonus (coordX*38,coordY*19,bonus);
                    break;
                }
                
            }
            }
            if(clicGauche){
                int coordX=(int)(e.getX()-3)/38;
                int coordY=(int)(e.getY()-26)/19;
                if(e.getX()<687 && e.getX()>=3 && e.getY()>=26 && e.getY()<501 && test[coordX][coordY]!=null){
                    test[coordX][coordY]=null;
                }
            }
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}   