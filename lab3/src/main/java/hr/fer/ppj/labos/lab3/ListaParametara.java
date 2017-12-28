package hr.fer.ppj.labos.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaParametara implements CvorAtributnogStabla {

	private List<String> tipovi;
	private List<String> imena;

	private CvorGenerativnogStabla trenutniCvor;

	public ListaParametara(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;

		this.tipovi = new LinkedList<>();
		this.imena = new LinkedList<>();
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<deklaracija_parametra>")) {
			DeklaracijaParametra deklaracijaParametra = new DeklaracijaParametra(trenutniCvor.getDjeca().get(0));
			deklaracijaParametra.provjeri();

			tipovi.add(deklaracijaParametra.getTip());
			imena.add(deklaracijaParametra.getIme());
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<lista_parametara> ZAREZ <deklaracija_parametra>")) {
			ListaParametara listaParametara = new ListaParametara(trenutniCvor.getDjeca().get(0));
			listaParametara.provjeri();

			DeklaracijaParametra deklaracijaParametra = new DeklaracijaParametra(trenutniCvor.getDjeca().get(2));
			deklaracijaParametra.provjeri();

			if (listaParametara.imena.contains(deklaracijaParametra.getIme())) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			tipovi = new LinkedList<>(listaParametara.getTipovi());
			tipovi.add(deklaracijaParametra.getTip());

			imena = new LinkedList<>(listaParametara.getImena());
			imena.add(deklaracijaParametra.getIme());
		}

	}

	public List<String> getTipovi() {
		return tipovi;
	}

	public void setTipovi(List<String> tipovi) {
		this.tipovi = tipovi;
	}

	public List<String> getImena() {
		return imena;
	}

	public void setImena(List<String> imena) {
		this.imena = imena;
	}

}
