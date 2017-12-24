package hr.fer.ppj.labos.lab3;

public class ListaNaredbi implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public ListaNaredbi(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<naredba>")) {
			Naredba naredba = new Naredba(trenutniCvor.getDjeca().get(0));
			naredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<lista_naredbi> <naredba>")) {
			ListaNaredbi listaNaredbi = new ListaNaredbi(trenutniCvor.getDjeca().get(0));
			listaNaredbi.provjeri();
			Naredba naredba = new Naredba(trenutniCvor.getDjeca().get(1));
			naredba.provjeri();
		}
	}
}
