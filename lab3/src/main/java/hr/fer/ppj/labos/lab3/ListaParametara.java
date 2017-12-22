package hr.fer.ppj.labos.lab3;

import java.util.LinkedList;
import java.util.List;

public class ListaParametara implements CvorAtributnogStabla{
	
	private List<String> tipovi;
	private List<String> imena;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public ListaParametara(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
		
		this.tipovi = new LinkedList<>();
		this.imena = new LinkedList<>();
	}
	
	public void provjeri() {
		
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
