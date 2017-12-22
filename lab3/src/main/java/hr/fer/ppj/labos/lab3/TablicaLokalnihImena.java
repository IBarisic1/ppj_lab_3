package hr.fer.ppj.labos.lab3;

import java.util.HashMap;
import java.util.Map;

public class TablicaLokalnihImena {
	
	private Map<String, String> tablicaLokalnihImena;
	private TablicaLokalnihImena prethodnaTablica;
	
	public TablicaLokalnihImena() {
		this.tablicaLokalnihImena = new HashMap<>();
	}
	
	public void dodajImeUTablicu(String ime, String tip) {
		tablicaLokalnihImena.put(ime, tip);
	}
	
	public boolean sadrziLiTablicaIme(String s) {
		return tablicaLokalnihImena.containsKey(s);
	}

	public String dohvatiTipZaIme(String ime) {
		return tablicaLokalnihImena.get(ime);
	}
	
	public TablicaLokalnihImena getPrethodnaTablica() {
		return prethodnaTablica;
	}

	public void setPrethodnaTablica(TablicaLokalnihImena prethodnaTablica) {
		this.prethodnaTablica = prethodnaTablica;
	}
	
	
}
