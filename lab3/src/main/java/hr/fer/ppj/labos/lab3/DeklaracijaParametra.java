package hr.fer.ppj.labos.lab3;

public class DeklaracijaParametra implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	
	private String tip;
	private String ime;
	
	public DeklaracijaParametra(CvorGenerativnogStabla trenutniCvor) {
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

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
}


	
