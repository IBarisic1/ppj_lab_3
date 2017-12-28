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
				if (!SemantickiAnalizator.deklariraneFunkcije.containsKey(imeFje)) {
					SemantickiAnalizator.deklariraneFunkcije.put(imeFje, tipFje);
				}
			}
			SemantickiAnalizator.definiraneFunkcije.put(imeFje, tipFje);

			SemantickiAnalizator.tipUgnjezdujuceFunkcije = tipFje;
			SlozenaNaredba slozenaNaredba = new SlozenaNaredba(trenutniCvor.getDjeca().get(5));
			slozenaNaredba.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("<ime_tipa> IDN L_ZAGRADA <lista_parametara> D_ZAGRADA <slozena_naredba>")) {
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

			ListaParametara listaParametara = new ListaParametara(trenutniCvor.getDjeca().get(3));
			listaParametara.provjeri();

			String tipFje = "funkcija(";
			boolean prviParametar = true;
			for (String tipParametra : listaParametara.getTipovi()) {
				if (prviParametar) {
					tipFje += tipParametra;
					prviParametar = false;
				} else {
					tipFje += (", " + tipParametra);
				}
			}
			tipFje += (" -> " + imeTipa.getTip() + ")");
			// pretpostavka je da se ovdje tablicaLokalnihImena odnosi na
			// globalni djelokrug
			if (SemantickiAnalizator.tablicaLokalnihImena.sadrziIme(imeFje)) {
				if (!SemantickiAnalizator.tablicaLokalnihImena.dohvatiTipZaIme(imeFje).equals(tipFje)) {
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				}
			} else {
				SemantickiAnalizator.tablicaLokalnihImena.dodajImeUTablicu(imeFje, tipFje);
				if (!SemantickiAnalizator.deklariraneFunkcije.containsKey(imeFje)) {
					SemantickiAnalizator.deklariraneFunkcije.put(imeFje, tipFje);
				}
			}
			SemantickiAnalizator.definiraneFunkcije.put(imeFje, tipFje);
			//TODO dodao provjeravanje slozene naredbe
			SemantickiAnalizator.tipUgnjezdujuceFunkcije = tipFje;
			SlozenaNaredba slozenaNaredba = new SlozenaNaredba(trenutniCvor.getDjeca().get(5));
			TablicaLokalnihImena tablica = slozenaNaredba.getTablicaLokalnihImena();
			List<String> tipovi = listaParametara.getTipovi();
			List<String> imena = listaParametara.getImena();
			for (int i = 0, n = listaParametara.getTipovi().size(); i < n; i++) {
				tablica.dodajImeUTablicu(imena.get(i), tipovi.get(i));
			}
			slozenaNaredba.provjeri();
		}

		// oznaka da smo zavrsili s definicijom fje te se ponovo nalazimo u
		// globalnom djelokrugu
		SemantickiAnalizator.tipUgnjezdujuceFunkcije = null;
	}
}
