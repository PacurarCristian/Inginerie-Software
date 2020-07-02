package Useri;

public class Client {
	private String nume;
	private String prenume;
	private String adresa;
	private String telefon;
	private String numeUser;
	private String email;
	
	public Client(String nume, String prenume, String adresa, String telefon, String numeUser, String email) {
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;
		this.telefon = telefon;
		this.numeUser = numeUser;
		this.email = email;
	}
	
	public Client(String numeUser, String email) {
		this.numeUser = numeUser;
		this.email = email;
	}
	
	public Client() {
		
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getNumeUser() {
		return numeUser;
	}

	public void setNumeUser(String numeUser) {
		this.numeUser = numeUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
