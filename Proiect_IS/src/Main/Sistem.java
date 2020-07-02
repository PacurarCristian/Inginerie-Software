package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import Useri.Bucatar;
import Useri.Client;
import Useri.Livrator;
import Useri.Manager;

public class Sistem {
	private boolean deschis;
	private DBConnect DBConnect;
	private Manager manager;
	private List<Bucatar> bucatari = new ArrayList<Bucatar>();
	private List<Observer> bucatariUI = new ArrayList<Observer>();
	private List<Livrator> livratori = new ArrayList<Livrator>();
	private List<Observer> livratoriUI = new ArrayList<Observer>();
	private Map<Comanda, Integer> comenziLivratori = new HashMap<Comanda, Integer>();
	
	public Sistem(boolean deschis) {
		this.deschis = deschis;
	}
	
	public DBConnect getDBConnect() {
		return DBConnect;
	}

	public void setDBConnect(DBConnect dBConnect) {
		DBConnect = dBConnect;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Bucatar> getBucatari() {
		return bucatari;
	}

	public void setBucatari(List<Bucatar> bucatari) {
		this.bucatari = bucatari;
	}

	public List<Livrator> getLivratori() {
		return livratori;
	}

	public void setLivratori(List<Livrator> livratori) {
		this.livratori = livratori;
	}

	public boolean isDeschis() {
		return deschis;
	}

	public void setDeschis(boolean deschis) {
		this.deschis = deschis;
	}

	public List<Observer> getBucatariUI() {
		return bucatariUI;
	}

	public void setBucatariUI(List<Observer> bucatariUI) {
		this.bucatariUI = bucatariUI;
	}

	public List<Observer> getLivratoriUI() {
		return livratoriUI;
	}

	public void setLivratoriUI(List<Observer> livratoriUI) {
		this.livratoriUI = livratoriUI;
	}

	public Map<Comanda, Integer> getComenziLivratori() {
		return comenziLivratori;
	}

	public void setComenziLivratori(Map<Comanda, Integer> comenziLivratori) {
		this.comenziLivratori = comenziLivratori;
	}

	public Sistem(DBConnect DBConnect, Manager manager, List<Bucatar> bucatari, List<Livrator> livratori) {
		this.DBConnect = DBConnect;
		this.manager = manager;
		this.bucatari = bucatari;
		this.livratori = livratori;
	}
	
	public Sistem() {
		
	}
	
	public void trimiteComandaBucatari(Client client, Map<String, Integer> comanda, String mentiuneComanda, Float total) {
		Integer i = 0;	
		boolean trimis = false;
		
		while(trimis == false && !bucatari.isEmpty()) {
			for(Bucatar bucatar: bucatari) {
				if(bucatar.getComenzi().size() == i) {
					Client clientt = new Client(client.getNume(), client.getPrenume(), client.getAdresa(), client.getTelefon(), client.getNumeUser(), client.getEmail());
					Comanda comandaa = new Comanda(clientt, comanda, mentiuneComanda, total);
					bucatar.getComenzi().add(comandaa);
					notifyBucatariUIObservers();
					trimis = true;
					break;
				}
			}
			
			i++;
		}
	}
	
	public void attach(Observer observer){
		bucatariUI.add(observer);		
	}
	
	public void attach2(Observer observer){
		livratoriUI.add(observer);		
	}

	public void notifyBucatariUIObservers(){
	      for (Observer observer : bucatariUI) {
	    	  observer.update();
	      }
	}
	
	public void notifyLivratoriUIObservers(){
	      for (Observer observer : livratoriUI) {
	    	  observer.update();
	      }
	}
	
	public void trimiteComandaLivratori(Comanda comanda) {
		Random nr = new Random();
		Integer distanta = nr.nextInt(101-1) + 1;
		
		if(comanda != null) {
			comenziLivratori.put(comanda, distanta);
		}
		
		if(comenziLivratori.size() >= 5 || deschis == false) {
			final Map<Comanda, Integer> comenziSortate = comenziLivratori.entrySet()
	                .stream()
	                .sorted(Map.Entry.comparingByValue())
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			
			Set< Map.Entry<Comanda,Integer> > st = comenziSortate.entrySet();
			List<Comanda> comenzi = new ArrayList<Comanda>();
			  
		    for (Map.Entry<Comanda,Integer> me:st) { 
		        comenzi.add(me.getKey());
		    } 
		    
		    for(Livrator livrator: livratori) {
		    	if(livrator.getComenzi().isEmpty() && livrator.isLivreaza()) {
		    		for(Comanda comandaa: comenzi) {
		    			livrator.getComenzi().add(comandaa);
		    		}
		    		
		    		comenziLivratori.clear();
		    		notifyBucatariUIObservers();
		    		break;
		    	}
		    }
		}
	}
	
}
