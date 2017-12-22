package hr.fer.ppj.labos.lab3;

import java.util.HashSet;
import java.util.Set;

public class TablicaLokalnihImena {
	
	private Set<String> tablicaLokalnihImena;
	private TablicaLokalnihImena prethodnaTablica;
	
	public TablicaLokalnihImena() {
		this.tablicaLokalnihImena = new HashSet<>();
	}
	
	public void dodajImeUTablicu(String s) {
		tablicaLokalnihImena.add(s);
	}
	
	public boolean sadrziLiTablicaIme(String s) {
		return tablicaLokalnihImena.contains(s);
	}

	public TablicaLokalnihImena getPrethodnaTablica() {
		return prethodnaTablica;
	}

	public void setPrethodnaTablica(TablicaLokalnihImena prethodnaTablica) {
		this.prethodnaTablica = prethodnaTablica;
	}
	
	
}
