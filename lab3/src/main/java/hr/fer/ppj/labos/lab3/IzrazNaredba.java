package hr.fer.ppj.labos.lab3;

public class IzrazNaredba implements CvorAtributnogStabla{
	
	private String tip;
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public IzrazNaredba(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("TOCKAZAREZ")) {
			tip = "int";
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<izraz> TOCKAZAREZ")) {
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(0));
			izraz.provjeri();
			tip = izraz.getTip();
		}
	}
}
