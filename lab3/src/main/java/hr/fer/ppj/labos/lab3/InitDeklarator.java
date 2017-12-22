package hr.fer.ppj.labos.lab3;

public class InitDeklarator implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	private String ntip;
	private String tip;
	
	public InitDeklarator(CvorGenerativnogStabla trenutniCvor) {
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

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
}
