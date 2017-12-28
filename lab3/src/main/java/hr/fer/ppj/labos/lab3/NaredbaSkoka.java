package hr.fer.ppj.labos.lab3;

public class NaredbaSkoka implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public NaredbaSkoka(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("KR_CONTINUE TOCKAZAREZ")
				|| trenutniCvor.desnaStranaProdukcije().equals("KR_BREAK TOCKAZAREZ")) {
			if (SemantickiAnalizator.brojTrenutnihPetlji <= 0) {
				// nikad ne bi smjelo biti < 0
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
		} else if (trenutniCvor.desnaStranaProdukcije().equals("KR_RETURN TOCKAZAREZ")) {
			String TUF = SemantickiAnalizator.tipUgnjezdujuceFunkcije;
			if (!TUF.startsWith("funkcija(") || !TUF.substring(TUF.length() - 5, TUF.length() - 1).equals("void")
					|| !TUF.endsWith(")")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
		} else if (trenutniCvor.desnaStranaProdukcije().equals("KR_RETURN <izraz> TOCKAZAREZ")) {
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(1));
			izraz.provjeri();

			String TUF = SemantickiAnalizator.tipUgnjezdujuceFunkcije;
			String povratnaVrijednostFunkcije = TUF.substring(TUF.indexOf(" -> ") + 4, TUF.length() - 1);

			if(SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if (!TUF.startsWith("funkcija(") || !TUF.endsWith(")") || !SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()).contains(povratnaVrijednostFunkcije)) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
			
		}
	}
}
