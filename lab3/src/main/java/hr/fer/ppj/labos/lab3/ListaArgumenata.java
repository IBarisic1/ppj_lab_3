package hr.fer.ppj.labos.lab3;

import java.util.List;

public class ListaArgumenata {
	
	private List<String> tipovi;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public ListaArgumenata(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		
	}

	public List<String> getTipovi() {
		return tipovi;
	}

	public void setTipovi(List<String> tipovi) {
		this.tipovi = tipovi;
	}
	
}
