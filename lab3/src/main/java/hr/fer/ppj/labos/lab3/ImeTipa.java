package hr.fer.ppj.labos.lab3;

public class ImeTipa implements CvorAtributnogStabla{

	private String tip;

	private CvorGenerativnogStabla trenutniCvor;

	public ImeTipa(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<specifikator_tipa>")) {
			SpecifikatorTipa specifikatorTipa = new SpecifikatorTipa(trenutniCvor.
					getDjeca().get(0));
			specifikatorTipa.provjeri();
			tip = specifikatorTipa.getTip();
		}else if(desnaStranaProdukcije.equals("KR_CONST <specifikator_tipa>")) {
			SpecifikatorTipa specifikatorTipa = new SpecifikatorTipa(trenutniCvor.
					getDjeca().get(1));
			specifikatorTipa.provjeri();
			if(specifikatorTipa.getTip() == "void") 
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "const(" + specifikatorTipa.getTip() + ")";
		}
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
}
