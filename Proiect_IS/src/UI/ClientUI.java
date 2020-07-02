package UI;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Main.DBConnect;
import Main.Sistem;
import Useri.Client;

public class ClientUI {
	private DBConnect DBConnect;
	private Sistem sistem;
	private Client client;
	
	private Float total;
	private String mentiune;
	private Map<String, Integer> comanda;
	private String status;
	private String raiting;
	private JTextArea textArea;
	
	public ClientUI(DBConnect DBConnects, Client clientul, Sistem sistem) {
		this.DBConnect = DBConnects;
		this.client = clientul;
		this.sistem = sistem;
		this.mentiune = new String("Fara mentiuni");
		this.comanda = new HashMap<String, Integer>();
		this.status = new String(" Comanda nu a fost trimisa\n");
		this.raiting = new String(" x/5\n");
		this.textArea = new JTextArea(21, 38);
		this.total = 0.0f;
	}
	
	private DBConnect getDBConnect() {
		return DBConnect;
	}

	private Object[][] dateTabel() throws SQLException{
		Object[][] dataDB = this.getDBConnect().select("meniu");
		Integer nrLinii = this.getDBConnect().nrLinii("meniu");
		Object[][] data;
		
		if(nrLinii < 6) {
			data = new Object[6][7];
		}
		else {
			data = new Object[nrLinii][7];
		}
		
		Integer j = 0;
		
		for(int i = 0; i < nrLinii; i++) {
			data[i][j++] = new ImageIcon("imagini/" + dataDB[i][5].toString());
			data[i][j++] = dataDB[i][1];
			data[i][j++] = dataDB[i][2];
			data[i][j++] = dataDB[i][3];
			data[i][j++] = dataDB[i][4];
			data[i][j++] = false;
			j = 0;
		}
		
		return data;
	}
	
	public void afisare() throws SQLException {
		JFrame frame = new JFrame ("Client");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		frame.setSize(1300, 710);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		
		JPanel panelMeniu = new JPanel();
		panelMeniu.setPreferredSize(new Dimension(780, 650));
		//panelMeniu.setBackground(Color.blue);
		panelMeniu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		frame.add(panelMeniu);
		
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(480, 650));
		//panel2.setBackground(Color.red);
		panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		frame.add(panel2);
		
		JTextField textFieldButoane = new JTextField("Optiuni"){
			private static final long serialVersionUID = 1L;
			@Override public void setBorder(Border border) {}
		};
		textFieldButoane.setPreferredSize(new Dimension(10, 30));
		textFieldButoane.setHorizontalAlignment(JTextField.CENTER);
		textFieldButoane.setFont(textFieldButoane.getFont().deriveFont(Font.BOLD, 12f));
		textFieldButoane.setEditable(false);
		panel2.add(textFieldButoane);
		
		JPanel panelButoane = new JPanel();
		panelButoane.setPreferredSize(new Dimension(460, 180));
		//panelButoane.setBackground(Color.green);
		panelButoane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		//panelButoane.setLayout(new GridLayout(0,2));
		panelButoane.setLayout(new FlowLayout());
		panel2.add(panelButoane);
		
		JTextField textFieldInformatii = new JTextField("Informatii"){
			private static final long serialVersionUID = 1L;
			@Override public void setBorder(Border border) {}
		};
		textFieldInformatii.setPreferredSize(new Dimension(10, 30));
		textFieldInformatii.setHorizontalAlignment(JTextField.CENTER);
		textFieldInformatii.setFont(textFieldInformatii.getFont().deriveFont(Font.BOLD, 12f));
		textFieldInformatii.setEditable(false);
		panel2.add(textFieldInformatii);
		
		JPanel panelInformatii = new JPanel();
		panelInformatii.setPreferredSize(new Dimension(460, 445));
		//panelInformatii.setBackground(Color.yellow);
		//panelInformatii.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panel2.add(panelInformatii);
		
		JLabel labelMeniu = new JLabel("Meniu");
		labelMeniu.setPreferredSize(new Dimension(100, 20));
		panelMeniu.add(labelMeniu);
		
		String[] columnNames = {"Imagine", "Nume", "Ingrediente", "Gramaj", "Pret", "Adauga", "Cantitate"};
		Object[][] data = this.dateTabel();
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return ImageIcon.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return Float.class;
                    case 5:
                        return Boolean.class;
                    case 6:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        };
        
        
        table.setRowHeight(100);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(130);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        for(int i = 1; i < 7; i++) {
        	if(i != 5) {
        		table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        	}
        }
		
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(770, 605));
		panelMeniu.add(scrollPane);
		
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane2 = new JScrollPane(textArea); 
		
		textArea.setFont(textArea.getFont().deriveFont(14f));
		panelInformatii.add(scrollPane2);
		
		ClientUI ClientUI = this;
		textArea.setText(panouInformatii(comanda, client, status, raiting, mentiune, ClientUI));
		
		JButton butonCreareComanda = new JButton("Actualizare produse comanda");
		
		Integer nrLinii = this.getDBConnect().nrLinii("meniu");
		
		butonCreareComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean adauga = false;
				//comanda.clear();
				comanda = new HashMap<String, Integer>();
				
				for(int i = 0; i < nrLinii; i++) {
					adauga = (boolean) table.getModel().getValueAt(i, 5);
					
					if(adauga == true) {
						String nume = (String) table.getModel().getValueAt(i, 1);
						Integer cantitate = (Integer) table.getModel().getValueAt(i, 6);
						
						if(cantitate == null) {
							JOptionPane.showMessageDialog(null, "Completati cantitatea dorita fiecarui produs selectat!", "Error", JOptionPane.WARNING_MESSAGE);
						}
						else {
							comanda.put(nume, cantitate);
						}
					}
				}
				
				total = 0.0f;
				textArea.setText(panouInformatii(comanda, client, status, raiting, mentiune, ClientUI));
			}
		});
		
		butonCreareComanda.setPreferredSize(new Dimension(220, 50));
		panelButoane.add(butonCreareComanda);
		
		JButton butonInformatiiLivrare = new JButton("Informatii livrare");
		
		butonInformatiiLivrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afisareInformatiiLivrare(client, ClientUI);
			}
		});
		
		butonInformatiiLivrare.setPreferredSize(new Dimension(220, 50));
		panelButoane.add(butonInformatiiLivrare);
		
		JButton butonMentiuniComanda = new JButton("Mentiuni comanda");
		
		butonMentiuniComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afisareMentiuniComanda(ClientUI);
			}
		});
		
		butonMentiuniComanda.setPreferredSize(new Dimension(220, 50));
		panelButoane.add(butonMentiuniComanda);
		
		JButton butonTrimiteComanda = new JButton("Finalizare comanda");
		
		butonTrimiteComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sistem.isDeschis() == false || sistem.getBucatari().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Restaurantul este inchis! Comanda nu a fost trimisa.", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int dialogButton = JOptionPane.showConfirmDialog (null, "Finalizare comanda?");
					
					if(dialogButton == 0) { //yes
						if(client.getNume() == null || client.getPrenume() == null || client.getAdresa() == null || client.getTelefon() == null) {
							JOptionPane.showMessageDialog(null, "Informatiile pentru livrare trebuie completate!", "Error", JOptionPane.WARNING_MESSAGE);
						}
						else if(comanda.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Comanda este goala!", "Error", JOptionPane.WARNING_MESSAGE);
						}
						else {
							if(mentiune == null) {
								int dialogButton2 = JOptionPane.showConfirmDialog (null, "Trimiteti comanda fara mentiuni?");
								
								if(dialogButton2 == 0) {
									Map<String, Integer> comandaAux = new HashMap<String, Integer>(comanda);
									sistem.trimiteComandaBucatari(client, comandaAux, mentiune, total);
									status = " Comanda trimisa\n";
									total = 0.0f;
									comanda.clear();
									textArea.setText(panouInformatii(comanda, client, status, raiting, mentiune, ClientUI));
								}
							}
							else {
								Map<String, Integer> comandaAux = new HashMap<String, Integer>(comanda);
								sistem.trimiteComandaBucatari(client, comandaAux, mentiune, total);
								status = " Comanda trimisa\n";
								total = 0.0f;
								comanda.clear();
								textArea.setText(panouInformatii(comanda, client, status, raiting, mentiune, ClientUI));
							}
						}
					}
				}
			}
		});
		
		butonTrimiteComanda.setPreferredSize(new Dimension(220, 50));
		panelButoane.add(butonTrimiteComanda);
		
		JButton butonRaiting = new JButton("Raiting restaurant");
		
		butonRaiting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afisareRaiting(ClientUI);
			}
		});
		
		butonRaiting.setPreferredSize(new Dimension(220, 50));
		panelButoane.add(butonRaiting);
		
		JButton butonContact = new JButton("Contact");
		
		butonContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afisareContact();
			}
		});
		
		butonContact.setPreferredSize(new Dimension(220, 50));
		panelButoane.add(butonContact);
		
		frame.setVisible(true);
	}
	
	private static void afisareInformatiiLivrare(Client client, ClientUI ClientUI) {
		JFrame frame = new JFrame ("Informatii livrare");
		frame.setSize(300, 190);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(280, 135));
		//panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JLabel labelNume = new JLabel("     Nume:");
		JTextField textFieldNume = new JTextField(20);
		
		JLabel labelPrenume = new JLabel("Prenume:");
		JTextField textFieldPrenume = new JTextField(20);
		
		JLabel labelAdresa = new JLabel("   Adresa:");
		JTextField textFieldAdresa = new JTextField(20);
		
		JLabel labelTelefon = new JLabel("   Telefon:");
		JTextField textFieldTelefon = new JTextField(20);
		
		panel.add(labelNume);
		panel.add(textFieldNume);
		panel.add(labelPrenume);
		panel.add(textFieldPrenume);
		panel.add(labelAdresa);
		panel.add(textFieldAdresa);
		panel.add(labelTelefon);
		panel.add(textFieldTelefon);
		
		JButton butonSalvare = new JButton("Salveaza");
		
		butonSalvare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nume = textFieldNume.getText();
				String prenume = textFieldPrenume.getText();
				String adresa = textFieldAdresa.getText();
				String telefon = textFieldTelefon.getText();
				
				if(nume == null || prenume == null || adresa == null || telefon == null) {
					JOptionPane.showMessageDialog(panel, "Toate campurile trebuie completate!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					client.setNume(nume);
					client.setPrenume(prenume);
					client.setAdresa(adresa);
					client.setTelefon(telefon);
					ClientUI.total = 0.0f;
					ClientUI.textArea.setText(panouInformatii(ClientUI.comanda, client, ClientUI.status, ClientUI.raiting, ClientUI.mentiune, ClientUI));
					frame.setVisible(false);
				}
			}
		});
		
		panel.add(butonSalvare);
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	private static void afisareMentiuniComanda(ClientUI ClientUI) {
		JFrame frame = new JFrame ("Mentiuni comanda");
		frame.setSize(300, 310);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(280, 280));
		//panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JTextArea textAreaa = new JTextArea(11, 21);
		textAreaa.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textAreaa); 
		
		Font font = textAreaa.getFont();
		float size = font.getSize() + 2.5f;
		textAreaa.setFont(font.deriveFont(size));
		panel.add(scrollPane);
		
		JButton butonSalvare = new JButton("Salveaza");
		
		butonSalvare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientUI.mentiune = textAreaa.getText();
				ClientUI.total = 0.0f;
				ClientUI.textArea.setText(panouInformatii(ClientUI.comanda, ClientUI.client, ClientUI.status, ClientUI.raiting, ClientUI.mentiune, ClientUI));
				frame.setVisible(false);
			}
		});
		
		panel.add(butonSalvare);
		
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	private static String panouInformatii(Map<String, Integer> comanda, Client client, String status, String raiting, String mentiune, ClientUI ClientUI) {
		String bunVenitText = "                                                 Bun venit!\n";
		String produseComandaText = " ---                                    ~ Produse comanda ~\n";
		
		String produse = new String();
		//Float total = 0.0f;
		Set< Map.Entry< String,Integer> > st = comanda.entrySet();  
		  
	    for (Map.Entry< String,Integer> me:st) { 
	    	produse = produse + " " + me.getKey() + " : x" + me.getValue() + "\n";
	    	
	    	try {
				ClientUI.total = ClientUI.total + (ClientUI.DBConnect.total(me.getKey()) * me.getValue());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    String mentiuniText = " ---                                            ~ Mentiuni ~\n";
	    String mentiuni = " " + mentiune + "\n";
		
		String totalText = " ---                                              ~ Total ~\n";
		String totalString = new String();
		totalString = " " + ClientUI.total.toString() + " lei\n";
		
		String dateLivrareText = " ---                                         ~ Date livrare ~\n";
		String nume = " Nume: " + client.getNume() + "\n";
		String prenume = " Prenume: " + client.getPrenume() + "\n";
		String adresa = " Adresa: " + client.getAdresa() + "\n";
		String telefon = " Telefon: " + client.getTelefon() + "\n";
		
		String statusComandaText = " ---                                      ~ Status comanda ~\n";
		String raitingText = " ---                                             ~ Raiting ~\n";
		
		String rezultat = bunVenitText + produseComandaText + produse + totalText + totalString + dateLivrareText + nume + prenume + adresa + telefon + mentiuniText + mentiuni + statusComandaText + status + raitingText + raiting;
		
		return rezultat;
	}
	
	private static void afisareRaiting(ClientUI ClientUI) {
		JFrame frame = new JFrame ("Raiting");
		frame.setSize(300, 150);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(280, 95));
		//panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JLabel labelRestaurant = new JLabel("Restaurant:");
		JTextField textFieldRestaurant = new JTextField(20);
		
		JButton butonTrimitere = new JButton("Trimite");
		
		butonTrimitere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientUI.raiting = " " + textFieldRestaurant.getText() + "/5\n";
				ClientUI.total = 0.0f;
				ClientUI.textArea.setText(panouInformatii(ClientUI.comanda, ClientUI.client, ClientUI.status, ClientUI.raiting, ClientUI.mentiune, ClientUI));
				frame.setVisible(false);
			}
		});
		
		panel.add(labelRestaurant);
		panel.add(textFieldRestaurant);
		panel.add(butonTrimitere);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	private static void afisareContact() {
		JFrame frame = new JFrame ("Contact");
		frame.setSize(300, 190);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(280, 30));
		panel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(280, 30));
		panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(280, 30));
		panel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(280, 30));
		panel4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JLabel label1 = new JLabel("NUME RESTAURANT");
		JLabel label2 = new JLabel("ADRESA SEDIU: ");
		JLabel label3 = new JLabel("TELEFON: ");
		JLabel label4 = new JLabel("ORAR: ");
		
		panel1.add(label1);
		panel2.add(label2);
		panel3.add(label3);
		panel4.add(label4);
		
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
		
		frame.setVisible(true);
	}
	
}
