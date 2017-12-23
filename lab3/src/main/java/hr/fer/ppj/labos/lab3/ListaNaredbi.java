package hr.fer.ppj.labos.lab3;

public class ListaNaredbi implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	
	public ListaNaredbi (CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}
	
	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<naredba> ")) {
			Naredba naredba = (Naredba) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			naredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("<lista_naredbi> <naredba> ")) {
			ListaNaredbi listaNaredbi = (ListaNaredbi) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			listaNaredbi.provjeri();
			Naredba naredba = (Naredba) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(1));
			naredba.provjeri();
		}
	}
}
