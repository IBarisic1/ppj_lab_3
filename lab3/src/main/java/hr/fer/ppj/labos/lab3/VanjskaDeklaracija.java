package hr.fer.ppj.labos.lab3;

public class VanjskaDeklaracija implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public VanjskaDeklaracija(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<definicija_funkcije>")) {
			DefinicijaFunkcije definicijaFunkcije = new DefinicijaFunkcije(trenutniCvor.getDjeca().get(0));
			definicijaFunkcije.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<deklaracija>")) {
			Deklaracija deklaracija = new Deklaracija(trenutniCvor.getDjeca().get(0));
			deklaracija.provjeri();
		}
	}
}
