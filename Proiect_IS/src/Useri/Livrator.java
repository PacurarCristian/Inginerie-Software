package Useri;

import java.util.ArrayList;
import java.util.List;

import Main.Comanda;

public class Livrator {
	private Integer id;
	private String nume;
	private String numeUser;
	private List<Comanda> comenzi;
	private boolean livreaza;
	
	public Livrator(String nume, String numeUser, Integer id) {
		this.id = id;
		this.nume = nume;
		this.numeUser = numeUser;
		this.comenzi = new ArrayList<Comanda>();
		this.livreaza = true;
	}
	
	public Livrator() {
		
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

	public List<Comanda> getComenzi() {
		return comenzi;
	}

	public void setComenzi(List<Comanda> comenzi) {
		this.comenzi = comenzi;
	}

	public boolean isLivreaza() {
		return livreaza;
	}

	public void setLivreaza(boolean livreaza) {
		this.livreaza = livreaza;
	}	
	
} 
