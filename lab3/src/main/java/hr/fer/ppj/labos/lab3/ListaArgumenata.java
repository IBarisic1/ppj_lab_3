package hr.fer.ppj.labos.lab3;

import java.util.ArrayList;
import java.util.List;

public class ListaArgumenata implements CvorAtributnogStabla{
	
	private List<String> tipovi;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public ListaArgumenata(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
		tipovi = new ArrayList<>();
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		if(desnaStranaProdukcije.equals("<izraz_pridruzivanja>")) {
			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(
					trenutniCvor.getDjeca().get(0));
			izrazPridruzivanja.provjeri();
			tipovi.clear();
			tipovi.add(izrazPridruzivanja.getTip());
		}else if(desnaStranaProdukcije.equals("<lista_argumenata> ZAREZ <izraz_pridruzivanja>")) {
			ListaArgumenata listaArgumenata = new ListaArgumenata(trenutniCvor.getDjeca().get(0));
			listaArgumenata.provjeri();
			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(
					trenutniCvor.getDjeca().get(2));
			izrazPridruzivanja.provjeri();
			tipovi = new ArrayList<>(listaArgumenata.getTipovi());
			tipovi.add(izrazPridruzivanja.getTip());
		}
	}

	public List<String> getTipovi() {
		return tipovi;
	}

	public void setTipovi(List<String> tipovi) {
		this.tipovi = tipovi;
	}
	
}
