package hr.fer.ppj.labos.lab3;

public class SpecifikatorTipa {
	
	private String tip;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public SpecifikatorTipa(CvorGenerativnogStabla trenutniCvor) {
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
	
}