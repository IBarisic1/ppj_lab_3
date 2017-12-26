package hr.fer.ppj.labos.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaIzrazaPridruzivanja implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;
	private List<String> tipovi;
	private int brElem;

	public ListaIzrazaPridruzivanja(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
		tipovi = new ArrayList<>();
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<izraz_pridruzivanja>")) {
			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(trenutniCvor.getDjeca().get(0));
			izrazPridruzivanja.provjeri();

			tipovi.add(izrazPridruzivanja.getTip());
			brElem = 1;
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("<lista_izraza_pridruzivanja> ZAREZ <izraz_pridruzivanja>")) {
			ListaIzrazaPridruzivanja listaIzrazaPridruzivanja = new ListaIzrazaPridruzivanja(
					trenutniCvor.getDjeca().get(0));
			listaIzrazaPridruzivanja.provjeri();

			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(trenutniCvor.getDjeca().get(2));
			izrazPridruzivanja.provjeri();

			tipovi = new LinkedList<>(listaIzrazaPridruzivanja.getTipovi());
			tipovi.add(izrazPridruzivanja.getTip());

			brElem = listaIzrazaPridruzivanja.getBrElem() + 1;
		}
	}

	public List<String> getTipovi() {
		return tipovi;
	}

	public void setTipovi(List<String> tipovi) {
		this.tipovi = tipovi;
	}

	public int getBrElem() {
		return brElem;
	}

	public void setBrElem(int brElem) {
		this.brElem = brElem;
	}

}
