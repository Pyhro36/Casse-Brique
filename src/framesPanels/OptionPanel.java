package framesPanels;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.Fenetre;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Clément
 */

public class OptionPanel extends Panel {

	private static long serialVersionUID = 4L;
	private JButton retour;
	private JButton facile;
	private JButton normal;
	private JButton difficile;
	private JButton hardcore;
	private JLabel title;
	private JLabel difficulte;
        private String actif;
        private Color defaut;
        private JLabel fond;
        private JButton jungle;
        private JButton espace;
        private JButton fractale;
        private JButton aquatique;
        private String fondActif;
        
	public OptionPanel(Fenetre frame) {
		super(frame);
                actif="";
        try {
            recupererOptions();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
                this.setLayout(null);
		title=new JLabel();
		title.setText("Options");
		title.setFont(new Font("Arial", Font.BOLD,40));
		title.setBounds(320,0,400,75);
		title.setForeground(Color.red);
		this.add(title);
		
		difficulte=new JLabel();
		difficulte.setText("Difficulté");
		difficulte.setFont(new Font("Arial",Font.BOLD,25));
		difficulte.setBounds(20,100,400,100);
		this.add(difficulte);
                
                fond=new JLabel();
                fond.setText("Design");
                fond.setFont(new Font("Arial",Font.BOLD,25));
                fond.setBounds(20,250,400,100);
                this.add(fond);
		
		retour=new JButton();
		retour.setText("Retour");
		retour.setBounds(620,520,160,40);
		retour.setFont(new Font("Arial", Font.BOLD, 25));
                defaut = retour.getBackground();
		this.add(retour);
                retour.addActionListener(new GestionOptions());
		
		facile=new JButton();
		facile.setText("Facile");
		facile.setBounds(20,200,160,40);
		facile.setFont(new Font("Arial",Font.BOLD, 25));
                if(actif.equals("facile")) facile.setBackground(Color.green);
		this.add(facile);
                facile.addActionListener(new GestionOptions());
		
		normal=new JButton();
		normal.setText("Normal");
		normal.setBounds(220,200,160,40);
		normal.setFont(new Font("Arial",Font.BOLD, 25));
                if(actif.equals("normal")) normal.setBackground(Color.yellow);
		this.add(normal);
                normal.addActionListener(new GestionOptions());
		
		difficile=new JButton();
		difficile.setText("Difficile");
		difficile.setBounds(420,200,160,40);
		difficile.setFont(new Font("Arial",Font.BOLD, 25));
                if(actif.equals("difficile")) difficile.setBackground(Color.orange);
		this.add(difficile);
                difficile.addActionListener(new GestionOptions());
		
		hardcore=new JButton();
		hardcore.setText("Héroïque !");
		hardcore.setBounds(620,200,160,40);
		hardcore.setFont(new Font("Arial",Font.BOLD, 25));
                if(actif.equals("hardcore")) hardcore.setBackground(Color.red);
		this.add(hardcore);
                hardcore.addActionListener(new GestionOptions());
                
	    espace=new JButton();
	    espace.setText("espace");
	    espace.setBounds(20,350,160,40);
	    espace.setFont(new Font("Arial",Font.BOLD, 25));
	    if(fondActif.equals("espace")) espace.setBackground(Color.blue);
	    this.add(espace);
	    espace.addActionListener(new GestionOptions());
	    
	    jungle=new JButton();
	    jungle.setText("jungle");
	    jungle.setBounds(220,350,160,40);
	    jungle.setFont(new Font("Arial",Font.BOLD, 25));
	    if(fondActif.equals("jungle")) jungle.setBackground(Color.green);
	    this.add(jungle);
	    jungle.addActionListener(new GestionOptions());
	    
	    fractale=new JButton();
	    fractale.setText("fractale");
	    fractale.setBounds(420,350,160,40);
	    fractale.setFont(new Font("Arial",Font.BOLD, 25));
	    if(fondActif.equals("fractale")) fractale.setBackground(Color.yellow);
	    this.add(fractale);
	    fractale.addActionListener(new GestionOptions());
	    
	    aquatique=new JButton();
	    aquatique.setText("aquatique");
	    aquatique.setBounds(620,350,160,40);
	    aquatique.setFont(new Font("Arial",Font.BOLD, 25));
	    if(fondActif.equals("aquatique")) aquatique.setBackground(Color.cyan);
	    this.add(aquatique);
	    aquatique.addActionListener(new GestionOptions());
		
	}
		public void paintComponent(Graphics g){
			g.drawLine(0,125,800,125);
			g.drawLine(0,250,800,250);
                        g.drawLine(0,400,800,400);
		}
                
        public void changerCouleurDif(){
            facile.setBackground(defaut);
            normal.setBackground(defaut);
            difficile.setBackground(defaut);
            hardcore.setBackground(defaut);
            switch(actif){
                case("facile") :
                    if(actif.equals("facile")) facile.setBackground(Color.green);
                break;
                case("normal") :
                    if(actif.equals("normal")) normal.setBackground(Color.yellow);
                break;
                case("difficile") :
                    if(actif.equals("difficile")) difficile.setBackground(Color.orange);
                break;
                case("hardcore") :
                    if(actif.equals("hardcore")) hardcore.setBackground(Color.red);
                break;
            }
        }
        public void changerCouleurFond(){
            espace.setBackground(defaut);
            jungle.setBackground(defaut);
            fractale.setBackground(defaut);
            aquatique.setBackground(defaut);
            switch(fondActif){
                case("espace") :
                    espace.setBackground(Color.blue);
                break;
                case("jungle") :
                    jungle.setBackground(Color.green);
                break;
                case("fractale") :
                    fractale.setBackground(Color.yellow);
                break;
                case("aquatique") :
                    aquatique.setBackground(Color.cyan);
                break;
            }
        }
        
                /**cette méthode permet de récupérer la valeur d'options actuelle*/
        public void recupererOptions() throws FileNotFoundException, IOException {
            FileReader fichier1 = new FileReader("options");
            char[] a=new char[2];
            fichier1.read(a);
            switch(a[0]-48){
                case(1) :
                actif="facile";
                break;
            
                case(2) :
                actif="normal";
                break;
            
                case(3) :
                actif="difficile";
                break;
            
                case(4) :
                actif="hardcore";
                break;
                
                default :
                actif="";
            }
            switch(a[1]-48){
                case(1) :
                fondActif="espace";
                break;
            
                case(2) :
                fondActif="jungle";
                break;
            
                case(3) :
                fondActif="fractale";
                break;
            
                case(4) :
                fondActif="aquatique";
                break;
                
                default :
                fondActif="";
            }
        }
                
        public void enregistrerOptions() throws IOException {
            FileWriter fichier = new FileWriter ("options");
            fichier.flush();
            String chaine="";
            switch(actif){
                case("facile") :
                    chaine=chaine+"1";
                break;
                case("normal") :
                    chaine=chaine+"2";
                break;
                case("difficile") :
                    chaine=chaine+"3";
                break;
                case("hardcore") :
                    chaine=chaine+"4";
                break;
                default:
                    chaine=chaine+" ";
                }
            switch(fondActif){
                case("espace") :
                    chaine=chaine+"1";
                break;
                case("jungle") :
                    chaine=chaine+"2";
                break;
                case("fractale") :
                    chaine=chaine+"3";
                break;
                case("aquatique") :
                    chaine=chaine+"4";
                break;
            
                    default:
                    chaine=chaine+" ";
                }
            fichier.write(chaine);
            fichier.close();
        }
                
    public class GestionOptions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==hardcore){
                actif="hardcore";
                changerCouleurDif();
            }
            if(e.getSource()==facile){
                actif="facile";
                changerCouleurDif();
            }
            if(e.getSource()==difficile){
                actif="difficile";
                changerCouleurDif();
            }
            if(e.getSource()==normal){
                actif="normal";
                changerCouleurDif();
            }
            if(e.getSource()==aquatique){
                fondActif="aquatique";
                changerCouleurFond();
            }
            if(e.getSource()==espace){
                fondActif="espace";
                changerCouleurFond();
            }
            if(e.getSource()==fractale){
                fondActif="fractale";
                changerCouleurFond();
            }
            if(e.getSource()==jungle){
                fondActif="jungle";
                changerCouleurFond();
            }
            if(e.getSource()==retour){
                try {
                    enregistrerOptions();
                } catch (IOException f) {
                }
                parent.switchPanel(new MenuPanel(parent));
                
            }
        }    
    }
}
