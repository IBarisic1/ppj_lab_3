package hr.fer.ppj.labos.lab3;

public class ListaInitDeklaratora implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	private String ntip;
	
	public ListaInitDeklaratora(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		
	}
	
	public String getNtip() {
		return ntip;
	}

	public void setNtip(String ntip) {
		this.ntip = ntip;
	}
	
	
}
