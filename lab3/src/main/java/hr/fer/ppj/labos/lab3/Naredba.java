package hr.fer.ppj.labos.lab3;

public class Naredba implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public Naredba(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<slozena_naredba>")) {
			SlozenaNaredba slozenaNaredba = (SlozenaNaredba) Tvornica
					.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			slozenaNaredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<izraz_naredba>")) {
			IzrazNaredba izrazNaredba = (IzrazNaredba) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			izrazNaredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("<naredba_grananja>")) {
			NaredbaGrananja naredbaGrananja = (NaredbaGrananja) Tvornica
					.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			naredbaGrananja.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<naredba_petlje>")) {
			NaredbaPetlje naredbaPetlje = (NaredbaPetlje) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			naredbaPetlje.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<naredba_skoka>")) {
			NaredbaSkoka naredbaSkoka = (NaredbaSkoka) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			naredbaSkoka.provjeri();
		}
	}
}
