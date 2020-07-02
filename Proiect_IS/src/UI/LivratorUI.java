package UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import Main.Comanda;
import Main.Observer;
import Main.Sistem;
import Useri.Livrator;
import Main.DBConnect;

public class LivratorUI extends Observer {
	private DBConnect DBConnect;
	private Sistem sistem;
	private Livrator livrator;
	private JButton butonActualizare;
	
	public LivratorUI(Livrator livrator, Sistem sistem, DBConnect DBConnect) {
		this.DBConnect = DBConnect;
		this.sistem = sistem;
		this.livrator = livrator;
		this.subject = sistem;
		this.subject.attach(this);
		this.butonActualizare = new JButton("Actualizare comenzi");
	}
	
	public Livrator getLivrator() {
		return this.livrator;
	}
	
	public void afisare() {
		JFrame frame = new JFrame ("Livrator");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(700, 710);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panelButoane = new JPanel();
		panelButoane.setPreferredSize(new Dimension(680, 50));
		panelButoane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panelHarta = new JPanel();
		panelHarta.setPreferredSize(new Dimension(680, 300));
		panelHarta.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panelComenzi = new JPanel();
		panelComenzi.setPreferredSize(new Dimension(680, 300));
		//panelComenzi.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JTextArea textArea = new JTextArea(15, 56);
	    textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setFont(textArea.getFont().deriveFont(14f));
		panelComenzi.add(scrollPane);
		
		textArea.setText("\n\n\n\n\n\n                                                                      Nu exista comenzi!");
		
		LivratorUI LivratorUI = this;
		
		JButton butonFinalizare = new JButton("Finalizare comanda");
		
		butonFinalizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer nrComanda = livrator.getComenzi().get(0).getNrComanda();
				Integer dialogButton = JOptionPane.showConfirmDialog (null, "Finalizare comanda nr. " + nrComanda + "?");
				
				if(dialogButton == 0) {
					try {
						DBConnect.insertIstoricComanda(livrator.getComenzi().get(0));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					livrator.getComenzi().remove(0);
					textArea.setText(afisareComenzi(LivratorUI));
				}
			}
		});
		
		butonFinalizare.setPreferredSize(new Dimension(220, 40));
		panelButoane.add(butonFinalizare);
		
		butonActualizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(afisareComenzi(LivratorUI));
			}
		});
		
		butonActualizare.setPreferredSize(new Dimension(220, 40));
		panelButoane.add(butonActualizare);
		
		JButton butonLogout = new JButton("Logout");
		
		butonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((sistem.getLivratori().size() - 1 != 0 || sistem.getComenziLivratori().isEmpty()) && livrator.getComenzi().isEmpty()) {
					
					sistem.getLivratori().remove(livrator);
					sistem.getLivratoriUI().remove(LivratorUI);
					
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
				else {
					livrator.setLivreaza(false);
					JOptionPane.showMessageDialog(null, "Nu ati livrat toate comenzile sau nu exista alti livratori pentru preluarea comenzilor ramase!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		butonLogout.setPreferredSize(new Dimension(220, 40));
		panelButoane.add(butonLogout);
		
		JLabel jl = new JLabel();
	    jl.setIcon(new ImageIcon("imagini/hartaCluj.png"));
	    panelHarta.add(jl);
		
		frame.add(panelButoane);
		frame.add(panelHarta);
		frame.add(panelComenzi);
		
		frame.setVisible(true);
	}

	@Override
	public void update() {
		butonActualizare.doClick();
	}
	
	public static String afisareComenzi(LivratorUI LivratorUI) {
		String rez = new String("");
		String s = new String(" (COMENZILE SUNT AFISATE IN FUNCTIE DE RUTA OPTIMA STABILITA DE SISTEM)\n\n");
		rez = s;
		
		for(Comanda comanda: LivratorUI.livrator.getComenzi()) {
			String sNrComanda = " Nr. " + comanda.getNrComanda() + ", ";
			String sNumeClient = "Nume: " + comanda.getClient().getNume() + " " + comanda.getClient().getPrenume() + ", ";
			String sAdresa = "Adresa: " + comanda.getClient().getAdresa() + ", ";
			String sTelefon = "Telefon: " + comanda.getClient().getTelefon() + ", ";
			String sTotal = "Total: " + comanda.getTotal() + " lei \n";
			
			Set< Map.Entry< String,Integer> > st = comanda.getProduse().entrySet(); 
			String produse = new String("");
			  
		    for (Map.Entry< String,Integer> me:st) { 
		    	produse = produse + " " + me.getKey() + " : x" + me.getValue() + "\n";
		    }
		    
		    String linie = "----------------------------------------\n\n";
		    
		    rez = rez + sNrComanda + sNumeClient + sAdresa + sTelefon + sTotal + produse + linie;
		}
		
		return rez;
	}
}
