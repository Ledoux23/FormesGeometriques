package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modele.Carre;
import modele.Cercle;
import modele.Formes;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FrmFormes {

	private JFrame frame;
	private JTextField textCote;
	private JTextField textRayon;
	private JLabel lblPerimetreCarre;
	private JLabel lblSurfaceCarre;
	private JLabel lblPerimetreCercle;
	private JLabel lblSurfaceCercle;

	private ArrayList lesFormes = new ArrayList<Formes>(); 
	//Pour la gestion de la collection (la liste dans ScrollPane)
	private DefaultListModel contenuLstFormes = new DefaultListModel(); 
	private JList lstFormes;
	//Un model qui permet de gérer le contenu de la liste
	//contenuLstFormes est ensuite insérer au code source de lstFormes
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFormes window = new FrmFormes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * Demande de la vue pour obtenir le périmèetre et le surface de la forme
	 * @param typeFormes "carre" ou "cercle"
	 * @param valeur rayon ou cote
	 */
	public void demandeFrmFormesValeurs(String typeFormes, Object valeur) {
		
		Formes uneForme; //Une forme de type Formes est soit un carré soit un cercle
		
		if(typeFormes == "carre") {
			uneForme = new Carre((float) valeur); //transtypage de Object
			afficheResultCarre(uneForme.perimetre(), uneForme.surface());
		}else {
			uneForme = new Cercle((float) valeur);
			afficheResultCercle(uneForme.perimetre(), uneForme.surface());
		}
		//Ajout de la nouvelle forme dans la collection
		lesFormes.add(uneForme);
		//Demande de remplissage de la liste des formes
		majListFormes(lesFormes);
	}
	
	/*
	 * Méthode d'affichage du périmètre et de la surface du carré
	 */
	public void afficheResultCarre(float perimetre, float surface) {
		lblPerimetreCarre.setText("" + perimetre); 
		lblSurfaceCarre.setText("" + surface);
		//on concatène l'argument dans une chaine vide car le paramètre de setText est un String
	}
	/* on pouvait aussi partir de la classe Float et utiliser la méthode toString
	public void afficheResultCarre(Float perimetre, Float surface) {
		lblPerimetreCarre.setText(perimetre.toString()); 
		lblPerimetreCarre.setText(surface.toString());
		//Ici on écrit Float et non float
	}
	*/
	
	/*
	 * Méthode d'affichage du périmètre et de la surface du cercle
	 */
	public void afficheResultCercle(float perimetre, float surface) {
		lblPerimetreCercle.setText("" + perimetre); 
		lblSurfaceCercle.setText("" + surface);
	}
	
	/*
	 * Méthode de remplissage de la liste avec les informations de la forme
	 * @param lesFormes
	 */
	public void majListFormes(ArrayList<Formes> lesFormes) {
		//Vider la liste
		contenuLstFormes.clear();
		//Boucle forEach pour remplir la liste
		for(Formes uneForme: lesFormes) {
			String ligne;
			if(uneForme instanceof Carre) {
				ligne = "CARRE : côté = " +((Carre)uneForme).getCote(); //transtypage nécessaire pour accéder aux méthodes de Carre
			} else {
				ligne = "CERCLE : rayon = " +((Cercle)uneForme).getRayon();
			}
			//On concatène la suite, puisque c'est un String
			ligne += " Périmètre = " + uneForme.perimetre() + " Surface = " + uneForme.surface();
			//Ajout dans la liste
			contenuLstFormes.addElement(ligne);
		}
	}
	
	/*
	 * Méthode pour supprimer une forme de la liste
	 */
	public void demandeFrmFormesDelete(int indice) {
		//Possibilité de sélectionner plusieurs éléments via le parcours d'un tableau d'indices
		int[] lesIndices = lstFormes.getSelectedIndices(); 
		for(int k =lesIndices.length-1; k >= 0; k--) {
			//Suppression dans la collection
			lesFormes.remove(lesIndices[k]);
		}
		//Demande de mise à jour de la liste
		majListFormes(lesFormes);
	}
	
	/**
	 * Create the application.
	 */
	public FrmFormes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 642);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 204, 154);
		lblNewLabel.setIcon(new ImageIcon(FrmFormes.class.getResource("/media/carré.png")));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setIcon(new ImageIcon(FrmFormes.class.getResource("/media/cercle.jpg")));
		lblNewLabel_1.setBounds(20, 210, 128, 133);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("côté =");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(224, 33, 95, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("périmètre =");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(224, 70, 118, 24);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("surface =");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(224, 104, 84, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		textCote = new JTextField();
		textCote.setBounds(301, 33, 96, 23);
		frame.getContentPane().add(textCote);
		textCote.setColumns(10);
		
		lblPerimetreCarre = new JLabel("0");
		lblPerimetreCarre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPerimetreCarre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerimetreCarre.setBounds(318, 70, 79, 24);
		frame.getContentPane().add(lblPerimetreCarre);
		
		lblSurfaceCarre = new JLabel("0");
		lblSurfaceCarre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSurfaceCarre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurfaceCarre.setBounds(301, 104, 96, 21);
		frame.getContentPane().add(lblSurfaceCarre);
		
		JButton btnCalculCarre = new JButton("Calcul");
		btnCalculCarre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//A l'appel de la méthode demandeFrmFormesValeurs()
				//le côté est récupéré avec un transtypage en float
				demandeFrmFormesValeurs("carre", Float.parseFloat(textCote.getText()));
			//-----------------------------------------------au clic sur le bouton Calcul du carré
			}
		});
		btnCalculCarre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalculCarre.setBounds(472, 36, 85, 21);
		frame.getContentPane().add(btnCalculCarre);
		
		JLabel lblNewLabel_6 = new JLabel("rayon =");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(224, 210, 95, 24);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("périmètre =");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(224, 256, 118, 27);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("surface =");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_8.setBounds(224, 317, 95, 13);
		frame.getContentPane().add(lblNewLabel_8);
		
		textRayon = new JTextField();
		textRayon.setBounds(301, 205, 96, 24);
		frame.getContentPane().add(textRayon);
		textRayon.setColumns(10);
		
		JButton btnCalculCercle = new JButton("Calcul");
		btnCalculCercle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//A l'appel de la méthode demandeFrmFormesValeurs()
				//le rayon est récupéré avec un transtypage en float
				demandeFrmFormesValeurs("cercle", Float.parseFloat(textRayon.getText()));
			//------------------------------------------------au clic sur le bouton Calcul du cercle
			}
		});
		btnCalculCercle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalculCercle.setBounds(472, 210, 85, 21);
		frame.getContentPane().add(btnCalculCercle);
		
		lblPerimetreCercle = new JLabel("0");
		lblPerimetreCercle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPerimetreCercle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerimetreCercle.setBounds(318, 260, 79, 23);
		frame.getContentPane().add(lblPerimetreCercle);
		
		lblSurfaceCercle = new JLabel("0");
		lblSurfaceCercle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSurfaceCercle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurfaceCercle.setBounds(318, 309, 79, 21);
		frame.getContentPane().add(lblSurfaceCercle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 379, 566, 154);
		frame.getContentPane().add(scrollPane);
		
		lstFormes = new JList(contenuLstFormes);
		scrollPane.setViewportView(lstFormes);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				demandeFrmFormesDelete(0);
			//------------------------------------------------au clic sur le bouton Delete
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(472, 555, 85, 21);
		frame.getContentPane().add(btnDelete);
	}

}
