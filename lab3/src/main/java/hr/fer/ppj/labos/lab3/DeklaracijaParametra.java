package hr.fer.ppj.labos.lab3;

public class DeklaracijaParametra implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	
	private String tip;
	private String ime;
	
	public DeklaracijaParametra(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	//TODO provjeri bi li trebalo ovaj parametar dodati u tablicu lokalnih imena ili nešto slično
	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<ime_tipa> IDN")) {
			ImeTipa imeTipa = new ImeTipa(trenutniCvor.getDjeca().get(0));
			imeTipa.provjeri();
			
			if (imeTipa.getTip().equals("void")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
			
			tip = imeTipa.getTip();
			ime = trenutniCvor.getDjeca().get(1).getLeksickaJedinka();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<ime_tipa> IDN L_UGL_ZAGRADA D_UGL_ZAGRADA")) {
			ImeTipa imeTipa = new ImeTipa(trenutniCvor.getDjeca().get(0));
			imeTipa.provjeri();
			
			if (imeTipa.getTip().equals("void")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
			
			tip = "niz(" + imeTipa.getTip() + ")";
			ime = trenutniCvor.getDjeca().get(1).getLeksickaJedinka();
		}
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


	
