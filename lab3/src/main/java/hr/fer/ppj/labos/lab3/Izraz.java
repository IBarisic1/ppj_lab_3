package hr.fer.ppj.labos.lab3;

public class Izraz {
	
	private String tip;
	private boolean l_izraz;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public Izraz(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public boolean isL_izraz() {
		return l_izraz;
	}

	public void setL_izraz(boolean l_izraz) {
		this.l_izraz = l_izraz;
	}
}
