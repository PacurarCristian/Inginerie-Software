package UI;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Main.DBConnect;
import Main.Sistem;
import Useri.Bucatar;
import Useri.Client;
import Useri.Livrator;
import Useri.Manager;

public class LoginUI {
	private DBConnect DBConnect;
	private Sistem sistem;
	
	public LoginUI(DBConnect DBConnect, Sistem sistem) {
		this.DBConnect = DBConnect;
		this.sistem = sistem;
	}
	
	public void afisare() {
		JFrame frame = new JFrame ("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 170);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		JPanel panelLogin = new JPanel();
		panelLogin.setPreferredSize(new Dimension(280, 115));
		//panelLogin.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JLabel labelNume = new JLabel(" *Nume:");
		JTextField textFieldNume = new JTextField(20);
		
		JLabel labelParola = new JLabel("*Parola:");
		JPasswordField textFieldParola = new JPasswordField(20);
		
		JLabel labelEmail = new JLabel("   Email:");
		JTextField textFieldEmail = new JTextField(20);
		
		panelLogin.add(labelNume);
		panelLogin.add(textFieldNume);
		panelLogin.add(labelParola);
		panelLogin.add(textFieldParola);
		panelLogin.add(labelEmail);
		panelLogin.add(textFieldEmail);
		
		frame.add(panelLogin);
		
		JButton butonLogin = new JButton("Login");
		
		butonLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Object[][] data = null;
				String parolaCriptata = criptareParola(textFieldParola.getText());
				
				try {
					//data = DBConnect.clientInfo(textFieldNume.getText(), textFieldParola.getText());
					data = DBConnect.clientInfo(textFieldNume.getText(), parolaCriptata);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if(data[0][1] == null) {
					JOptionPane.showMessageDialog(panelLogin, "Nume sau parola gresita!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String tipUser = data[0][1].toString();
					String email = data[0][2].toString();
					
					if(tipUser.contentEquals("user")) {
						Client Client = new Client(textFieldNume.getText(), email);
						ClientUI ClientUI = new ClientUI(DBConnect, Client, sistem);
						
						try {
							ClientUI.afisare();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						//frame.setVisible(false);
					}
					else if(tipUser.contentEquals("bucatar")) {
						Object[][] dataAngajat = new Object[1][2];
						
						try {
							dataAngajat = DBConnect.numeIdAngajat(textFieldNume.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						Bucatar bucatar = new Bucatar((String) dataAngajat[0][1], textFieldNume.getText(), (Integer) dataAngajat[0][0]);
						sistem.getBucatari().add(bucatar);
						BucatarUI BucatarUI = new BucatarUI(bucatar, sistem);
						
						BucatarUI.afisare();
					}
					else if(tipUser.contentEquals("livrator")) {
						Object[][] dataAngajat = new Object[1][2];
						
						try {
							dataAngajat = DBConnect.numeIdAngajat(textFieldNume.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						Livrator livrator = new Livrator((String) dataAngajat[0][1], textFieldNume.getText(), (Integer) dataAngajat[0][0]);
						sistem.getLivratori().add(livrator);
						LivratorUI LivratorUI = new LivratorUI(livrator, sistem, DBConnect);
						
						LivratorUI.afisare();
					}
					else if(tipUser.contentEquals("manager")) {
						Object[][] dataAngajat = new Object[1][2];
						
						try {
							dataAngajat = DBConnect.numeIdAngajat(textFieldNume.getText());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						Manager manager = new Manager((Integer) dataAngajat[0][0], (String) dataAngajat[0][1], textFieldNume.getText());
						sistem.setManager(manager);
						ManagerUI ManagerUI = new ManagerUI(sistem, manager, DBConnect);
						
						ManagerUI.afisare();
					}
				}
			}
		});
		
		panelLogin.add(butonLogin);
		
		JButton butonInregistrare = new JButton("Inregistrare");
		
		butonInregistrare.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String nume = textFieldNume.getText();
				String parola = textFieldParola.getText();
				String email = textFieldEmail.getText();
				
				if(nume.contentEquals("") || parola.contentEquals("") || email.contentEquals("")) {
					JOptionPane.showMessageDialog(panelLogin, "Introduceti numele, parola si emailul!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						String parolaCriptata = criptareParola(parola);
						DBConnect.insertUser(nume, parolaCriptata, email);
						JOptionPane.showMessageDialog(panelLogin, "Inregistrare reusita!", "Succes", JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e1) {
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(panelLogin, "Nume existent!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		panelLogin.add(butonInregistrare);
		
		frame.setVisible(true);
	}
	
	private String criptareParola(String parola) {
		String parolaCriptata = new String();
		
		for(int i = 0; i < parola.length(); i++) {
			int nr = parola.charAt(i);
			parolaCriptata = parolaCriptata + nr;
		}
		
		return parolaCriptata;
	}

}
