package Useri;

import java.util.ArrayList;
import java.util.List;

import Main.Comanda;

public class Bucatar {
	private Integer id;
	private String nume;
	private String numeUser;
	private List<Comanda> comenzi;
	
	public Bucatar(String nume, String numeUser, Integer id) {
		this.id = id;
		this.nume = nume;
		this.numeUser = numeUser;
		this.comenzi = new ArrayList<Comanda>();
	}
	
	public Bucatar() {
		this.comenzi = new ArrayList<Comanda>();
	}

	public String getNumeUser() {
		return numeUser;
	}

	public List<Comanda> getComenzi() {
		return comenzi;
	}

	public void setComenzi(List<Comanda> comenzi) {
		this.comenzi = comenzi;
	}

	public String getNume() {
		return nume;
	}

	public Integer getId() {
		return id;
	}
	
}
