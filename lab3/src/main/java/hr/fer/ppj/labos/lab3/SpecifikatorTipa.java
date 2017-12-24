package hr.fer.ppj.labos.lab3;

public class SpecifikatorTipa implements CvorAtributnogStabla{
	
	private String tip;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public SpecifikatorTipa(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("KR_VOID")) tip = "void";
		else if(desnaStranaProdukcije.equals("KR_CHAR")) tip = "char";
		else if(desnaStranaProdukcije.equals("KR_INT")) tip = "int";	
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
}
