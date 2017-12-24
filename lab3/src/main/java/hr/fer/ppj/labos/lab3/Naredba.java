package hr.fer.ppj.labos.lab3;

public class Naredba implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public Naredba(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<slozena_naredba>")) {
			SlozenaNaredba slozenaNaredba = new SlozenaNaredba(trenutniCvor.getDjeca().get(0));
			slozenaNaredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<izraz_naredba>")) {
			IzrazNaredba izrazNaredba = new IzrazNaredba(trenutniCvor.getDjeca().get(0));
			izrazNaredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("<naredba_grananja>")) {
			NaredbaGrananja naredbaGrananja = new NaredbaGrananja(trenutniCvor.getDjeca().get(0));
			naredbaGrananja.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<naredba_petlje>")) {
			NaredbaPetlje naredbaPetlje = new NaredbaPetlje(trenutniCvor.getDjeca().get(0));
			naredbaPetlje.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<naredba_skoka>")) {
			NaredbaSkoka naredbaSkoka = new NaredbaSkoka(trenutniCvor.getDjeca().get(0));
			naredbaSkoka.provjeri();
		}
	}
}
