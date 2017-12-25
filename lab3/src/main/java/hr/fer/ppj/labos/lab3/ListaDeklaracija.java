package hr.fer.ppj.labos.lab3;

public class ListaDeklaracija implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public ListaDeklaracija(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<deklaracija>")) {
			Deklaracija deklaracija = new Deklaracija(trenutniCvor.getDjeca().get(0));
			deklaracija.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<lista_deklaracija> <deklaracija>")) {
			ListaDeklaracija listaDeklaracija = new ListaDeklaracija(trenutniCvor.getDjeca().get(0));
			listaDeklaracija.provjeri();
			
			Deklaracija deklaracija = new Deklaracija(trenutniCvor.getDjeca().get(1));
			deklaracija.provjeri();
		}
	}
}
