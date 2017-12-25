package hr.fer.ppj.labos.lab3;

public class ListaInitDeklaratora implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;
	private String ntip;

	public ListaInitDeklaratora(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<init_deklarator>")) {
			InitDeklarator initDeklarator = new InitDeklarator(trenutniCvor.getDjeca().get(0));
			initDeklarator.setNtip(this.getNtip());
			initDeklarator.provjeri();
		}  else if (trenutniCvor.desnaStranaProdukcije().equals("<lista_init_deklaratora> ZAREZ <init_deklarator>")) {
			ListaInitDeklaratora listaInitDeklaratora = new ListaInitDeklaratora(trenutniCvor.getDjeca().get(0));
			listaInitDeklaratora.setNtip(this.getNtip());
			listaInitDeklaratora.provjeri();
			
			InitDeklarator initDeklarator = new InitDeklarator(trenutniCvor.getDjeca().get(2));
			initDeklarator.setNtip(this.getNtip());
			initDeklarator.provjeri();
		}

	}

	public String getNtip() {
		return ntip;
	}

	public void setNtip(String ntip) {
		this.ntip = ntip;
	}

}
