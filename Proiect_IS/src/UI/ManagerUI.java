package UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Main.Sistem;
import Useri.Manager;
import Main.DBConnect;

public class ManagerUI {
	private Sistem sistem;
	private Manager manager;
	private DBConnect DBConnect;
	
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panelTabel;
	private String tabelCurent = new String();
	
	public ManagerUI(Sistem sistem, Manager manager, DBConnect DBConnect) {
		this.sistem = sistem;
		this.manager = manager;
		this.DBConnect = DBConnect;
	}
	
	public Manager getManager() {
		return this.manager;
	}
	
	public void afisare() {
		JFrame frame = new JFrame ("Manager");
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		frame.setSize(1300, 710);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		
		panelTabel = new JPanel();
		panelTabel.setPreferredSize(new Dimension(780, 650));
		panelTabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		JPanel panelButoane = new JPanel();
		panelButoane.setPreferredSize(new Dimension(480, 650));
		panelButoane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		ManagerUI ManagerUI = this;
		String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8"};
		afisareTabel(ManagerUI, columnNames, null);
		
		scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(770, 640));
		panelTabel.add(scrollPane);
		
		JButton butonSelectMeniu = new JButton("Meniu");
		
		butonSelectMeniu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Id", "Nume", "Ingrediente", "Gramaj", "Pret", "Imagine"};
				afisareTabel(ManagerUI, columnNames, "meniu");
				updateSP(ManagerUI);
				tabelCurent = "meniu";
			}
		});
		
		butonSelectMeniu.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonSelectMeniu);
		
		JButton butonSelectUseri = new JButton("Useri");
		
		butonSelectUseri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Id", "Nume", "Parola", "Email", "Functie", "Data Inregistrare"};
				tabelCurent = "useri";
				afisareTabel(ManagerUI, columnNames, tabelCurent);
				updateSP(ManagerUI);
			}
		});
		
		butonSelectUseri.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonSelectUseri);
		
		JButton butonSelectAngajati = new JButton("Angajati");
		
		butonSelectAngajati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Id", "Nume", "Prenume", "Functie", "Email", "Adresa", "Salar", "Nume user"};
				tabelCurent = "angajati";
				afisareTabel(ManagerUI, columnNames, tabelCurent);
				updateSP(ManagerUI);
			}
		});
		
		butonSelectAngajati.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonSelectAngajati);
		
		JButton butonSelectIngrediente = new JButton("Ingrediente");
		
		butonSelectIngrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Id", "Nume", "Cantitate"};
				tabelCurent = "ingrediente";
				afisareTabel(ManagerUI, columnNames, tabelCurent);
				updateSP(ManagerUI);
			}
		});
		
		butonSelectIngrediente.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonSelectIngrediente);
		
		JButton butonSelectFurnizori = new JButton("Furnizori");
		
		butonSelectFurnizori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Id", "Nume firma", "Nume ingredient"};
				tabelCurent = "furnizori";
				afisareTabel(ManagerUI, columnNames, tabelCurent);
				updateSP(ManagerUI);
			}
		});
		
		butonSelectFurnizori.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonSelectFurnizori);
		
		JButton butonSelectIstoricFurnizare = new JButton("Istoric Furnizari");
		
		butonSelectIstoricFurnizare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Id", "Nume firma", "Nume ingredient", "Cantitate", "Data furnizare", "Id furnizor"};
				tabelCurent = "istoricFurnizare";
				afisareTabel(ManagerUI, columnNames, tabelCurent);
				updateSP(ManagerUI);
			}
		});
		
		butonSelectIstoricFurnizare.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonSelectIstoricFurnizare);
		
		JButton butonSelectIstoricComenzi = new JButton("Istoric Comenzi");
		
		butonSelectIstoricComenzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Id", "Nume client", "Adresa", "Telefon", "Email", "Produse", "Data comanda"};
				tabelCurent = "istoricComenzi";
				afisareTabel(ManagerUI, columnNames, tabelCurent);
				updateSP(ManagerUI);
			}
		});
		
		butonSelectIstoricComenzi.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonSelectIstoricComenzi);
		
		JButton butonInsert = new JButton("Adauga rand nou");
		
		butonInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertTabel(ManagerUI);
			}
		});
		
		butonInsert.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonInsert);
		
		JButton butonUpdate = new JButton("Actualizeaza rand");
		
		butonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRow(ManagerUI);
			}
		});
		
		butonUpdate.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonUpdate);
		
		JButton butonDelete = new JButton("Sterge rand");
		
		butonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRow(ManagerUI);
			}
		});
		
		butonDelete.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonDelete);
		
		JButton butonOnOff = new JButton("Deschide / Inchide Restaurant");
		
		butonOnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sistem.isDeschis()) {
					sistem.setDeschis(false);
					JOptionPane.showMessageDialog(null, "Restaurantul s-a inchis!", "Succes", JOptionPane.WARNING_MESSAGE);
				}
				else {
					sistem.setDeschis(true);
					JOptionPane.showMessageDialog(null, "Restaurantul s-a deschis!", "Succes", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		butonOnOff.setPreferredSize(new Dimension(400, 50));
		panelButoane.add(butonOnOff);
		
		frame.add(panelTabel);
		frame.add(panelButoane);
		frame.setVisible(true);
	}
	
	private static void afisareTabel(ManagerUI ManagerUI, String[] columnNames, String numeTabel) {
		Object[][] dataDB = null;
		Object[][] data = null;
		Integer nrColoane = 0;
		Integer nrLinii = 0;
		
		if(numeTabel == null) {
			data = new Object[13][8];
			nrColoane = 8;
		}
		else {
			try {
				dataDB = ManagerUI.DBConnect.select(numeTabel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			nrColoane = ManagerUI.DBConnect.nrColoane(numeTabel);
			
			try {
				nrLinii = ManagerUI.DBConnect.nrLinii(numeTabel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(nrLinii < 13) {
				data = new Object[13][nrColoane];
			}
			else {
				data = new Object[nrLinii + 1][nrColoane];
			}
			
			for(int i = 0; i < nrLinii; i++) {
				for(int j = 0; j < nrColoane; j++) {
					data[i][j] = dataDB[i][j];
				}
			}
		}
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		ManagerUI.table = new JTable(model);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        ManagerUI.table.setRowHeight(50);
        
        for(int i = 0; i < nrColoane; i++) {
        	ManagerUI.table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
	}
	
	private static void updateSP(ManagerUI ManagerUI) {
		ManagerUI.panelTabel.remove(ManagerUI.scrollPane);
		ManagerUI.scrollPane = new JScrollPane(ManagerUI.table);
		ManagerUI.scrollPane.setPreferredSize(new Dimension(770, 640));
		ManagerUI.panelTabel.add(ManagerUI.scrollPane);
		
		ManagerUI.panelTabel.setVisible(false);
		ManagerUI.panelTabel.setVisible(true);
	}
	
	private static void insertTabel(ManagerUI ManagerUI) {
		if(ManagerUI.table.getSelectedRow() != -1) {
			Integer row = ManagerUI.table.getSelectedRow();
			Integer nrColoane = ManagerUI.DBConnect.nrColoane(ManagerUI.tabelCurent);
			String valori = new String("");
			
			if(ManagerUI.tabelCurent.contentEquals("useri") || ManagerUI.tabelCurent.contentEquals("istoricComenzi") || ManagerUI.tabelCurent.contentEquals("istoricFurnizare")) {
				nrColoane--;
			}
			
			for(int i = 1; i < nrColoane; i++) {
				if(ManagerUI.table.getValueAt(row, i) != null) {
					valori = valori + "\"" + ManagerUI.table.getValueAt(row, i) + "\"";
					
					if(i != nrColoane - 1) {
						valori = valori + ", ";
					}
				}
			}
			
			try {
				ManagerUI.DBConnect.insertRow(ManagerUI.tabelCurent, valori);
			} catch (SQLException e) {
				if(ManagerUI.tabelCurent.contentEquals("angajati")) {
					JOptionPane.showMessageDialog(null, "Userul trebuie sa existe!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(ManagerUI.tabelCurent.contentEquals("istoricFurnizare")) {
					JOptionPane.showMessageDialog(null, "Id-ul furnizorului trebuie sa existe!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(ManagerUI.tabelCurent.contentEquals("furnizori")) {
					JOptionPane.showMessageDialog(null, "Numele ingredientului trebuie sa existe!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	private static void updateRow(ManagerUI ManagerUI) {
		if(ManagerUI.table.getSelectedRow() != -1) {
			Integer row = ManagerUI.table.getSelectedRow();
			Integer nrColoane = ManagerUI.DBConnect.nrColoane(ManagerUI.tabelCurent);
			String[] valori = new String[nrColoane - 1];
			
			for(int i = 1; i < nrColoane; i++) {
				if(ManagerUI.table.getValueAt(row, i) != null) {
					valori[i-1] = "\"" + ManagerUI.table.getValueAt(row, i) + "\"";
					
					if(i != nrColoane - 1) {
						valori[i - 1] = valori[i - 1] + ", ";
					}
				}
			}
			
			try {
				ManagerUI.DBConnect.updateRow(ManagerUI.tabelCurent, valori, (Integer) ManagerUI.table.getValueAt(row, 0));
			} catch (SQLException e) {
				if(ManagerUI.tabelCurent.contentEquals("angajati")) {
					JOptionPane.showMessageDialog(null, "Userul trebuie sa existe!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(ManagerUI.tabelCurent.contentEquals("istoricFurnizare")) {
					JOptionPane.showMessageDialog(null, "Id-ul furnizorului trebuie sa existe!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(ManagerUI.tabelCurent.contentEquals("furnizori")) {
					JOptionPane.showMessageDialog(null, "Numele ingredientului trebuie sa existe!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	private static void deleteRow(ManagerUI ManagerUI) {
		if(ManagerUI.table.getSelectedRow() != -1) {
			Integer row = ManagerUI.table.getSelectedRow();
			
			try {
				ManagerUI.DBConnect.deleteRow(ManagerUI.tabelCurent, (Integer) ManagerUI.table.getValueAt(row, 0));
			} catch (SQLException e) {
				if(ManagerUI.tabelCurent.contentEquals("useri")) {
					JOptionPane.showMessageDialog(null, "Exista un angajat cu acest user!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(ManagerUI.tabelCurent.contentEquals("ingrediente")) {
					JOptionPane.showMessageDialog(null, "Exista un furnizor care aprovizioneaza acest ingredient!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(ManagerUI.tabelCurent.contentEquals("furnizori")) {
					JOptionPane.showMessageDialog(null, "Exista un istoric al acestui furnizor!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

}
