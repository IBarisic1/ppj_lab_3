package hr.fer.ppj.labos.lab3;

public class IzravniDeklarator implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	private String ntip;
	private String tip;
	private int brElem;
	
	public IzravniDeklarator(CvorGenerativnogStabla trenutniCvor) {
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

	public int getBrElem() {
		return brElem;
	}

	public void setBrElem(int brElem) {
		this.brElem = brElem;
	}

}
