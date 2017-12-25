package hr.fer.ppj.labos.lab3;

public class Deklaracija implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public Deklaracija(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		//donji if je jedini pa uvijek mora biti odabran
		if (trenutniCvor.desnaStranaProdukcije().equals("<ime_tipa> <lista_init_deklaratora> TOCKAZAREZ")) {
			ImeTipa imeTipa = new ImeTipa(trenutniCvor.getDjeca().get(0));
			imeTipa.provjeri();
			
			ListaInitDeklaratora listaInitDeklaratora = new ListaInitDeklaratora(trenutniCvor.getDjeca().get(1));
			listaInitDeklaratora.setNtip(imeTipa.getTip());
			listaInitDeklaratora.provjeri();
		} 
	}
}
