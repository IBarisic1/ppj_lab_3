package hr.fer.ppj.labos.lab3;

public class PrijevodnaJedinica implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public PrijevodnaJedinica(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<vanjska_deklaracija>")) {
			VanjskaDeklaracija vanjskaDeklaracija = new VanjskaDeklaracija(trenutniCvor.getDjeca().get(0));
			vanjskaDeklaracija.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<prijevodna_jedinica> <vanjska_deklaracija>")) {
			PrijevodnaJedinica prijevodnaJedinica = new PrijevodnaJedinica(trenutniCvor.getDjeca().get(0));
			prijevodnaJedinica.provjeri();
			
			VanjskaDeklaracija vanjskaDeklaracija = new VanjskaDeklaracija(trenutniCvor.getDjeca().get(1));
			vanjskaDeklaracija.provjeri();
		}
	}
}
