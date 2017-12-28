package hr.fer.ppj.labos.lab3;

public class NaredbaGrananja implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public NaredbaGrananja(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("KR_IF L_ZAGRADA <izraz> D_ZAGRADA <naredba>")) {
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(2));
			izraz.provjeri();
			

			if(SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if (!SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()).contains("int")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			Naredba naredba = new Naredba(trenutniCvor.getDjeca().get(4));
			naredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("KR_IF L_ZAGRADA <izraz> D_ZAGRADA <naredba> KR_ELSE <naredba>")) {
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(2));
			izraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if (!SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()).contains("int")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			Naredba naredba1 = new Naredba(trenutniCvor.getDjeca().get(4));
			naredba1.provjeri();
			
			Naredba naredba2 = new Naredba(trenutniCvor.getDjeca().get(6));
			naredba2.provjeri();
		}
	}
}
