package hr.fer.ppj.labos.lab3;

import java.util.Arrays;
import java.util.List;

public class DefinicijaFunkcije implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public DefinicijaFunkcije(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije()
				.equals("<ime_tipa> IDN L_ZAGRADA KR_VOID D_ZAGRADA <slozena_naredba>")) {
			ImeTipa imeTipa = new ImeTipa(trenutniCvor.getDjeca().get(0));
			imeTipa.provjeri();

			List T = Arrays.asList(new String[] { "int", "char" });
			if (imeTipa.getTip().startsWith("const(") && imeTipa.getTip().endsWith(")")
					&& T.contains(imeTipa.getTip().substring(6, imeTipa.getTip().length() - 1))) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			String imeFje = trenutniCvor.getDjeca().get(1).getLeksickaJedinka();
			if (SemantickiAnalizator.definiraneFunkcije.containsKey(imeFje)) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			String tipFje = "funkcija(void -> " + imeTipa.getTip() + ")";
			// pretpostavka je da se ovdje tablicaLokalnihImena odnosi na
			// globalni djelokrug
			if (SemantickiAnalizator.tablicaLokalnihImena.sadrziIme(imeFje)) {
				if (!SemantickiAnalizator.tablicaLokalnihImena.dohvatiTipZaIme(imeFje).equals(tipFje)) {
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				}
			} else {
				SemantickiAnalizator.tablicaLokalnihImena.dodajImeUTablicu(imeFje, tipFje);
				SemantickiAnalizator.deklariraneFunkcije.put(imeFje, tipFje);
			}
			SemantickiAnalizator.definiraneFunkcije.put(imeFje, tipFje);

			SlozenaNaredba slozenaNaredba = new SlozenaNaredba(trenutniCvor.getDjeca().get(5));
			slozenaNaredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<izraz_naredba>")) {
			IzrazNaredba izrazNaredba = new IzrazNaredba(trenutniCvor.getDjeca().get(0));
			izrazNaredba.provjeri();
		}
	}
}
