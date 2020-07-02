package Useri;

public class Manager {
	private Integer id;
	private String nume;
	private String numeUser;
	
	public Manager(Integer id, String nume, String numeUser) {
		this.id = id;
		this.nume = nume;
		this.numeUser = numeUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getNumeUser() {
		return numeUser;
	}

	public void setNumeUser(String numeUser) {
		this.numeUser = numeUser;
	}
	
}
