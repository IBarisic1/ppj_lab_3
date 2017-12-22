package hr.fer.ppj.labos.lab3;

public class IzrazNaredba implements CvorAtributnogStabla{
	
	private String tip;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public IzrazNaredba(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		
	}
}
