package hr.fer.ppj.labos.lab3;

import java.util.ArrayList;
import java.util.List;

public class Inicijalizator implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	private List<String> tipovi;
	private int brElem;
	private String tip;

	public Inicijalizator(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
		this.tipovi = new ArrayList<>();
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<izraz_pridruzivanja>")) {
			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(trenutniCvor.getDjeca().get(0));
			izrazPridruzivanja.provjeri();

			boolean jeLiNizZnakova = false;
			CvorGenerativnogStabla provjeravaniCvor = trenutniCvor.getDjeca().get(0);
			while (true) {
				if (provjeravaniCvor.getUniformniZnak().equals("NIZ_ZNAKOVA")) {
					jeLiNizZnakova = true;
					break;
				} else if (provjeravaniCvor.getDjeca().size() == 0) {
					// znaci da smo dosli do zavrsnog znaka, a nije NIZ_ZNAKOVA
					break;
				} else if (provjeravaniCvor.getDjeca().size() > 1) {
					break;
				} else {
					// u ovom else-u je broj djece == 1
					provjeravaniCvor = provjeravaniCvor.getDjeca().get(0);
				}
			}

			if (jeLiNizZnakova) {
				brElem = provjeravaniCvor.getLeksickaJedinka().length() + 1;
				for (int i = 0; i < brElem; i++) {
					tipovi.add("char");
				}
			} else {
				tip = izrazPridruzivanja.getTip();
			}
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("L_VIT_ZAGRADA <lista_izraza_pridruzivanja> D_VIT_ZAGRADA")) {
			ListaIzrazaPridruzivanja listaIzrazaPridruzivanja = new ListaIzrazaPridruzivanja(
					trenutniCvor.getDjeca().get(0));
			listaIzrazaPridruzivanja.provjeri();

			brElem = listaIzrazaPridruzivanja.getBrElem();
			tipovi = listaIzrazaPridruzivanja.getTipovi();
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
