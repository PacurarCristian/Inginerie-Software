package Main;

import java.util.Map;

import Useri.Client;

public class Comanda {
	private Integer nrComanda;
	private Client client;
	private Map<String, Integer> produse;
	private String mentiune;
	private Float total;
	
	public Comanda(Client client, Map<String, Integer> comanda, String mentiune, Float total) {
		this.client = client;
		this.produse = comanda;
		this.mentiune = mentiune;
		this.total = total;
	}
	
	public Client getClient() {
		return client;
	}
	
	public Map<String, Integer> getProduse() {
		return produse;
	}
	
	public String getMentiune() {
		return mentiune;
	}

	public Integer getNrComanda() {
		return nrComanda;
	}

	public void setNrComanda(Integer nrComanda) {
		this.nrComanda = nrComanda;
	}

	public Float getTotal() {
		return total;
	}
	
}
