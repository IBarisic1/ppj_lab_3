package hr.fer.ppj.labos.lab3;

import java.util.ArrayList;
import java.util.List;

public class ListaIzrazaPridruzivanja implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	private List<String> tipovi;
	private int brElem;
	
	public ListaIzrazaPridruzivanja(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
		tipovi = new ArrayList<>();
	}

	public void provjeri() {
		
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
