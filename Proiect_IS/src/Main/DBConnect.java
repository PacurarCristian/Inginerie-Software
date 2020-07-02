package Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

public class DBConnect {
	private Connection con;
	
	public void connect() throws SQLException {
		String host = "jdbc:mysql://localhost:3306/Proiect_IS";
		String uName = "root";
		String uPass= "cristi";
		
		con = DriverManager.getConnection(host, uName, uPass);
	}
	
	private Connection getCon() {
		return con;
	}
	
	public Object[][] select(String table) throws SQLException {
		Statement stmt = this.getCon().createStatement();
		ResultSet rs = stmt.executeQuery("select * from " + table);
		Integer n = this.nrLinii(table);
		Integer m = this.nrColoane(table);
		Integer i = 0;
		Integer j = 0;
		
		Object[][] data = new Object[n][m];
		
		while(rs.next()) {
			if(table.contentEquals("useri")) {	
				data[i][j++] = rs.getInt("idUser");
				data[i][j++] = rs.getString("nume");
				data[i][j++] = rs.getString("parola");
				data[i][j++] = rs.getString("email");
				data[i][j++] = rs.getString("functie");
				data[i][j++] = rs.getString("dataInregistrare");
			}
			else if(table.contentEquals("meniu")) {
				data[i][j++] = rs.getInt("idProdus");
				data[i][j++] = rs.getString("nume");
				data[i][j++] = rs.getString("ingrediente");
				data[i][j++] = rs.getInt("gramaj");
				data[i][j++] = rs.getFloat("pret");
				data[i][j++] = rs.getString("imagine");
			}
			else if(table.contentEquals("angajati")) {
				data[i][j++] = rs.getInt("idAngajat");
				data[i][j++] = rs.getString("nume");
				data[i][j++] = rs.getString("prenume");
				data[i][j++] = rs.getString("functie");
				data[i][j++] = rs.getString("email");
				data[i][j++] = rs.getString("adresa");
				data[i][j++] = rs.getFloat("salar");
				data[i][j++] = rs.getString("numeUser");
			}
			else if(table.contentEquals("ingrediente")) {
				data[i][j++] = rs.getInt("idIngredient");
				data[i][j++] = rs.getString("nume");
				data[i][j++] = rs.getInt("cantitate");
			}
			else if(table.contentEquals("furnizori")) {
				data[i][j++] = rs.getInt("idFurnizor");
				data[i][j++] = rs.getString("numeFirma");
				data[i][j++] = rs.getString("numeIngredient");
			}
			else if(table.contentEquals("istoricFurnizare")) {
				data[i][j++] = rs.getInt("idFurnizare");
				data[i][j++] = rs.getString("numeFirma");
				data[i][j++] = rs.getString("numeIngredient");
				data[i][j++] = rs.getInt("cantitate");
				data[i][j++] = rs.getInt("idFurnizor");
				data[i][j++] = rs.getString("dataFurnizare");
			}
			else if(table.contentEquals("istoricComenzi")) {
				data[i][j++] = rs.getInt("idComanda");
				data[i][j++] = rs.getString("numeClient");
				data[i][j++] = rs.getString("adresa");
				data[i][j++] = rs.getString("telefon");
				data[i][j++] = rs.getString("email");
				data[i][j++] = rs.getString("produse");
				data[i][j++] = rs.getString("dataComanda");
			}
			
			i++;
			j = 0;
		}
		
		return data;
	}
	
	public Integer nrLinii(String table) throws SQLException {
		Statement stmt = this.getCon().createStatement();
		ResultSet rs = stmt.executeQuery("select * from " + table);
		Integer n = 0;
		
		while(rs.next()) {
			n++;
		}
		
		return n;
	}
	
	public Integer nrColoane(String table) {
		Integer n = 0;
		
		if(table.contentEquals("useri")) {
			n = 6;
		}
		else if(table.contentEquals("meniu")) {
			n = 6;
		}
		else if(table.contentEquals("angajati")) {
			n = 8;
		}
		else if(table.contentEquals("ingrediente")) {
			n = 3;
		}
		else if(table.contentEquals("furnizori")) {
			n = 3;
		}
		else if(table.contentEquals("istoricFurnizare")) {
			n = 6;
		}
		else if(table.contentEquals("istoricComenzi")) {
			n = 7;
		}
		
		return n;
	}
	
	public Object[][] clientInfo(String nume, String parola) throws SQLException {
		Object[][] data = new Object[1][6];
		Statement stmt = this.getCon().createStatement();
		ResultSet rs = stmt.executeQuery("select * from useri where nume = \"" + nume + "\" and parola = \"" + parola + "\"");
		
		while(rs.next()) {
			data[0][0] = rs.getInt("idUser");
			data[0][1] = rs.getString("functie");
			data[0][2] = rs.getString("email");
		}
		
		return data;
	}
	
	public void insertUser(String nume, String parola, String email) throws SQLException {
		Statement stmt = this.getCon().createStatement();
		stmt.executeUpdate("insert into useri (nume, parola, email, functie) values (\"" + nume + "\", \"" + parola + "\", \"" + email + "\", \"user\")");
	}
	
	public Float total(String produs) throws SQLException {
		Float total = 0.0f;
		
		Statement stmt = this.getCon().createStatement();
		ResultSet rs = stmt.executeQuery("select pret from meniu where nume = \"" + produs + "\"");
		
		while(rs.next()) {
			total = rs.getFloat("pret");
		}
		
		return total;
	}
	
	public Object[][] numeIdAngajat(String numeUser) throws SQLException {
		Object[][] data = new Object[1][2];
		
		Statement stmt = this.getCon().createStatement();
		ResultSet rs = stmt.executeQuery("select idAngajat, nume from angajati where numeUser = \"" + numeUser + "\"");
		
		while(rs.next()) {
			data[0][0] = rs.getInt("idAngajat");
			data[0][1] = rs.getString("nume");
		}
		
		return data;
	}
	
	public void insertIstoricComanda(Comanda comanda) throws SQLException {
		Statement stmt = this.getCon().createStatement();
		//Integer nrc = comanda.getNrComanda();
		String nc = "\"" + comanda.getClient().getNume() + " " + comanda.getClient().getPrenume() + "\"";
		String a = "\"" + comanda.getClient().getAdresa() + "\"";
		String t = "\"" + comanda.getClient().getTelefon() + "\"";
		String e = "\"" + comanda.getClient().getEmail() + "\"";
		String p1 = new String("");
		
		Set< Map.Entry< String,Integer> > st = comanda.getProduse().entrySet();    
		  
	    for (Map.Entry< String,Integer> me:st) { 
	        p1 = p1 + me.getKey() + "(" + me.getValue() + "), ";
	    } 
	    
	    String p2 = "\"" + p1 + "\"";
	    
		stmt.executeUpdate("insert into istoricComenzi (numeClient, adresa, telefon, email, produse) values (" + nc + ", " + a + ", " + t + ", " + e + ", " + p2 + ")");
	}
	
	public void insertRow(String tabel, String valori) throws SQLException {
		Statement stmt = this.getCon().createStatement();
		
		if(tabel.contentEquals("useri")) {
			stmt.executeUpdate("insert into useri (nume, parola, email, functie) values (" + valori + ")");
		}
		else if(tabel.contentEquals("angajati")) {
			stmt.executeUpdate("insert into angajati (nume, prenume, functie, email, adresa, salar, numeUser) values(" + valori + ")");
		}
		else if(tabel.contentEquals("meniu")) {
			stmt.executeUpdate("insert into meniu (nume, ingrediente, gramaj, pret, imagine) values(" + valori + ")");
		}
		else if(tabel.contentEquals("ingrediente")) {
			stmt.executeUpdate("insert into ingrediente (nume, cantitate) values(" + valori + ")");
		}
		else if(tabel.contentEquals("furnizori")) {
			stmt.executeUpdate("insert into furnizori (numeFirma, numeIngredient) values(" + valori + ")");
		}
		else if(tabel.contentEquals("istoricFurnizare")) {
			stmt.executeUpdate("insert into istoricFurnizare (numeFirma, numeIngredient, cantitate, idFurnizor) values(" + valori + ")");
		}
		else if(tabel.contentEquals("istoricComenzi")) {
			stmt.executeUpdate("insert into istoricComenzi (numeClient, adresa, telefon, email, produse) values(" + valori + ")");
		}
	}
	
	public void updateRow(String tabel, String[] valori, Integer id) throws SQLException {
		Statement stmt = this.getCon().createStatement();
		
		if(tabel.contentEquals("useri")) {
			stmt.executeUpdate("update useri set nume = " + valori[0] + "parola = " + valori[1] + "email = " + valori[2] + "functie = " + valori[3] + "dataInregistrare = " + valori[4] + " where idUser = " + id);
		}
		else if(tabel.contentEquals("angajati")) {
			stmt.executeUpdate("update angajati set nume = " + valori[0] + "prenume = " + valori[1] + "functie = " + valori[2] + "email = " + valori[3] + "adresa = " + valori[4] + "salar = " + valori[5] + "numeUser = " + valori[6] + " where idAngajat = " + id);
		}
		else if(tabel.contentEquals("meniu")) {
			stmt.executeUpdate("update meniu set nume = " + valori[0] + "ingrediente = " + valori[1] + "gramaj = " + valori[2] + "pret = " + valori[3] + "imagine = " + " where idProdus = " + id);
		}
		else if(tabel.contentEquals("ingrediente")) {
			stmt.executeUpdate("update ingrediente set nume = " + valori[0] + "cantitate = " + valori[1] + " where idIngredient = " + id);
		}
		else if(tabel.contentEquals("furnizori")) {
			stmt.executeUpdate("update furnizori set numeFirma = " + valori[0] + "numeIngredient = " + valori[1] + " where idFurnizor = " + id);
		}
		else if(tabel.contentEquals("istoricFurnizare")) {
			stmt.executeUpdate("update istoricFurnizare set numeFirma = " + valori[0] + "numeIngredient = " + valori[1] + "cantitate = " + valori[2] + "idFurnizor = " + valori[3] + "dataFurnizare = " + valori[4] + " where idFurnizare = " + id);
		}
		else if(tabel.contentEquals("istoricComenzi")) {
			stmt.executeUpdate("update istoricComenzi set numeClient = " + valori[0] + "adresa = " + valori[1] + "telefon = " + valori[2] + "email = " + valori[3] + "produse = " + valori[4] + "dataComanda = " + valori[5] + " where idComanda = " + id);
		}
	}
	
	public void deleteRow(String tabel, Integer id) throws SQLException {
		Statement stmt = this.getCon().createStatement();
		
		if(tabel.contentEquals("useri")) {
			stmt.executeUpdate("delete from useri where idUser = " + id);
		}
		else if(tabel.contentEquals("angajati")) {
			stmt.executeUpdate("delete from angajati where idAngajat = " + id);
		}
		else if(tabel.contentEquals("meniu")) {
			stmt.executeUpdate("delete from meniu where idProdus = " + id);
		}
		else if(tabel.contentEquals("ingrediente")) {
			stmt.executeUpdate("delete from ingrediente where idIngredient = " + id);
		}
		else if(tabel.contentEquals("furnizori")) {
			stmt.executeUpdate("delete from furnizori where idFurnizor = " + id);
		}
		else if(tabel.contentEquals("istoricFurnizare")) {
			stmt.executeUpdate("delete from istoricFurnizare where idFurnizare = " + id);
		}
		else if(tabel.contentEquals("istoricComenzi")) {
			stmt.executeUpdate("delete from istoricComenzi where idComanda = " + id);
		}
	}
}
