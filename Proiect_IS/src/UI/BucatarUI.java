package UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import Main.Comanda;
import Main.Observer;
import Main.Sistem;
import Useri.Bucatar;

public class BucatarUI extends Observer{
	private Sistem sistem;
	private Bucatar bucatar;
	private static Integer nrComanda;
	private JButton butonActualizare;
		
	public Bucatar getBucatar() {
		return this.bucatar;
	}

	public BucatarUI(Bucatar bucatarul, Sistem sistem) {
		this.bucatar = bucatarul;
		this.sistem = sistem;
		nrComanda = 0;
		this.subject = sistem;
		this.subject.attach(this);
		this.butonActualizare = new JButton("Actualizare comenzi");
	}
	
	public void afisare() {
		JFrame frame = new JFrame ("Bucatar");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panelButoane = new JPanel();
		panelButoane.setPreferredSize(new Dimension(680, 50));
		panelButoane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panelComenzi = new JPanel();
		panelComenzi.setPreferredSize(new Dimension(335, 390));
		//panelComenzi.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panelImagine = new JPanel();
		panelImagine.setPreferredSize(new Dimension(335, 390));
		//panelImagine.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JLabel jl = new JLabel();
	    jl.setIcon(new ImageIcon("imagini/bucatar.png"));
	    panelImagine.add(jl);
		
		JTextArea textArea = new JTextArea(20,27);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setFont(textArea.getFont().deriveFont(14f));
		panelComenzi.add(scrollPane);
		
		BucatarUI bucatarUI = this;
		
		//textArea.setText("\n\n\n\n\n\n\n\n                        Actualizati comenzile!");
		textArea.setText("\n\n\n\n\n\n\n\n                          Nu exsita comenzi!");
		
		JButton butonFinalizare = new JButton("Finalizare comanda");
		
		butonFinalizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afisareTrimitereComanda(bucatarUI);
			}
		});
		
		butonFinalizare.setPreferredSize(new Dimension(220, 40));
		panelButoane.add(butonFinalizare);
		
		butonActualizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bucatar.getComenzi().isEmpty()) {
					textArea.setText("\n\n\n\n\n\n\n\n                          Nu exsita comenzi!");
				}
				else {
					textArea.setText(afisareComenzi(bucatarUI));
				}
			}
		});
		
		butonActualizare.setPreferredSize(new Dimension(220, 40));
		//panelButoane.add(butonActualizare);
		
		JButton butonLogout = new JButton("Logout");
		
		
		butonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sistem.getBucatari().size() - 1 != 0 || bucatar.getComenzi().isEmpty()) {
					
					sistem.getBucatari().remove(bucatar);
					sistem.getBucatariUI().remove(bucatarUI);
					
					for(Comanda comanda: bucatar.getComenzi()) {
						sistem.trimiteComandaBucatari(comanda.getClient(), comanda.getProduse(), comanda.getMentiune(), comanda.getTotal());
					}
					
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
				else {
					JOptionPane.showMessageDialog(null, "Nu exista alti bucatari disponibili pentru a prelua comenzile ramase!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		butonLogout.setPreferredSize(new Dimension(220, 40));
		panelButoane.add(butonLogout);
		
		frame.add(panelButoane);
		frame.add(panelComenzi);
		frame.add(panelImagine);
		frame.setVisible(true);
	}
	
	private static String afisareComenzi(BucatarUI BucatarUI) {
		String rez = new String();
		Integer i = 0;
		
		for(Comanda comanda: BucatarUI.getBucatar().getComenzi()) {
			i++;
			String sNrComanda = " Nr. comanda: " + i + "\n";
			String sNumeClient = " Nume client: " + comanda.getClient().getNume() + " " + comanda.getClient().getPrenume() + "\n";
			String sNumeUser = " Nume user: " + comanda.getClient().getNumeUser() + "\n";
			
			Set< Map.Entry< String,Integer> > st = comanda.getProduse().entrySet(); 
			String sProduse = new String();
			  
		    for (Map.Entry< String,Integer> me:st) { 	
		        sProduse = sProduse + "     " + me.getKey() + " : " + me.getValue() + "\n";
		    } 
		    
		    String sMentiune = " Mentiuni: " + comanda.getMentiune() + "\n";
		    String linie = "----------------------------------------\n\n";
		    
		    rez = rez + sNrComanda + sNumeClient + sNumeUser + sProduse + sMentiune + linie;
		}
		
		return rez;
	}

	@Override
	public void update() {
		butonActualizare.doClick();
	}
	
	private static void afisareTrimitereComanda(BucatarUI BucatarUI) {
		JFrame frame = new JFrame ("Nr. comanda");
		frame.setSize(250, 120);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(230, 65));
		//panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JLabel label = new JLabel("Nr. comanda: ");
		JTextField tf = new JTextField(10);
		
		panel.add(label);
		panel.add(tf);
		
		JButton buton = new JButton("Trimite");
		
		buton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer nr = Integer.parseInt(tf.getText());
				
				if(nr - 1 <= BucatarUI.bucatar.getComenzi().size() && nr - 1 >= 0) {
					nrComanda++;
					BucatarUI.bucatar.getComenzi().get(nr - 1).setNrComanda(nrComanda);
					BucatarUI.sistem.trimiteComandaLivratori(BucatarUI.bucatar.getComenzi().get(nr - 1));
					BucatarUI.bucatar.getComenzi().remove(nr - 1);
					BucatarUI.update();
				}
				else {
					JOptionPane.showMessageDialog(null, "Numarul comenzii nu exista!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
				frame.setVisible(false);
			}
		});
		
		panel.add(buton);
		
		frame.add(panel);
		frame.setVisible(true);
	}

}
