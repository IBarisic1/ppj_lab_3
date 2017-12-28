package hr.fer.ppj.labos.lab3;

public class NaredbaPetlje implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public NaredbaPetlje(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		// za BREAK i CONTINUE u NaredbaSkoka bitno je znati jesmo li u petlji
		// ili ne
		SemantickiAnalizator.brojTrenutnihPetlji++;
		if (trenutniCvor.desnaStranaProdukcije().equals("KR_WHILE L_ZAGRADA <izraz> D_ZAGRADA <naredba>")) {
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(2));
			izraz.provjeri();

			if (!SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()).contains("int")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			Naredba naredba = new Naredba(trenutniCvor.getDjeca().get(4));
			naredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("KR_FOR L_ZAGRADA <izraz_naredba> <izraz_naredba> D_ZAGRADA <naredba>")) {
			IzrazNaredba izrazNaredba1 = new IzrazNaredba(trenutniCvor.getDjeca().get(2));
			izrazNaredba1.provjeri();

			IzrazNaredba izrazNaredba2 = new IzrazNaredba(trenutniCvor.getDjeca().get(3));
			izrazNaredba2.provjeri();

			if (!SemantickiAnalizator.implicitnaPretvorba.get(izrazNaredba2.getTip()).contains("int")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			Naredba naredba = new Naredba(trenutniCvor.getDjeca().get(5));
			naredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("KR_FOR L_ZAGRADA <izraz_naredba> <izraz_naredba> <izraz> D_ZAGRADA <naredba>")) {
			IzrazNaredba izrazNaredba1 = new IzrazNaredba(trenutniCvor.getDjeca().get(2));
			izrazNaredba1.provjeri();

			IzrazNaredba izrazNaredba2 = new IzrazNaredba(trenutniCvor.getDjeca().get(3));
			izrazNaredba2.provjeri();

			if (!SemantickiAnalizator.implicitnaPretvorba.get(izrazNaredba2.getTip()).contains("int")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(4));
			izraz.provjeri();

			Naredba naredba = new Naredba(trenutniCvor.getDjeca().get(6));
			naredba.provjeri();
		}
		SemantickiAnalizator.brojTrenutnihPetlji--;
	}
}
