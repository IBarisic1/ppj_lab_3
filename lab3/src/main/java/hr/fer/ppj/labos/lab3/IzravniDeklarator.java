package hr.fer.ppj.labos.lab3;

public class IzravniDeklarator implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;
	private String ntip;
	private String tip;
	private int brElem;

	public IzravniDeklarator(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("IDN")) {;
			if (ntip.equals("void")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			String ime = trenutniCvor.getDjeca().get(0).getLeksickaJedinka();
			if (SemantickiAnalizator.tablicaLokalnihImena.sadrziIme(ime)) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			tip = ntip;
			SemantickiAnalizator.tablicaLokalnihImena.dodajImeUTablicu(ime, tip);
		} else if (trenutniCvor.desnaStranaProdukcije().equals("IDN L_UGL_ZAGRADA BROJ D_UGL_ZAGRADA")) {
			if (ntip.equals("void")) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			String ime = trenutniCvor.getDjeca().get(0).getLeksickaJedinka();
			if (SemantickiAnalizator.tablicaLokalnihImena.sadrziIme(ime)) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			int brojElemenata = Integer.parseInt(trenutniCvor.getDjeca().get(2).getLeksickaJedinka());
			if (brojElemenata <= 0 || brojElemenata > 1024) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}

			tip = "niz(" + ntip + ")";
			this.brElem = brojElemenata;

			SemantickiAnalizator.tablicaLokalnihImena.dodajImeUTablicu(ime, tip);
			//TODO ispravio sam tako da dodaje u listu deklariranih fja
		} else if (trenutniCvor.desnaStranaProdukcije().equals("IDN L_ZAGRADA KR_VOID D_ZAGRADA")) {
			String ime = trenutniCvor.getDjeca().get(0).getLeksickaJedinka();
			tip = "funkcija(void -> " + ntip + ")";

			TablicaLokalnihImena trenutnaTablica = SemantickiAnalizator.tablicaLokalnihImena;
			while (trenutnaTablica != null) {
				if (trenutnaTablica.sadrziIme(ime)) {
					if (!trenutnaTablica.dohvatiTipZaIme(ime).equals(tip)) {
						SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
					}
					break;
				}
				trenutnaTablica = trenutnaTablica.getPrethodnaTablica();
			}

			// ako je zapis vec bio u tablici nista se nece promijeniti
			SemantickiAnalizator.tablicaLokalnihImena.dodajImeUTablicu(ime, tip);
			SemantickiAnalizator.deklariraneFunkcije.put(ime, tip);
		} else if (trenutniCvor.desnaStranaProdukcije().equals("IDN L_ZAGRADA <lista_parametara> D_ZAGRADA")) {
			ListaParametara listaParametara = new ListaParametara(trenutniCvor.getDjeca().get(2));
			listaParametara.provjeri();

			tip = "funkcija(";
			boolean prviParametar = true;
			for (String tipParametra : listaParametara.getTipovi()) {
				if (prviParametar) {
					tip += tipParametra;
					prviParametar = false;
				} else {
					tip += (", " + tipParametra);
				}
			}
			tip += (" -> " + ntip + ")");
			
			//TODO vratio provjeru samo u lokalni djelokrug
			String ime = trenutniCvor.getDjeca().get(0).getLeksickaJedinka();
			TablicaLokalnihImena trenutnaTablica = SemantickiAnalizator.tablicaLokalnihImena;
			if (trenutnaTablica.sadrziIme(ime)) {
				if (!trenutnaTablica.dohvatiTipZaIme(ime).equals(tip)) {
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				}
			}

			// ako je zapis vec bio u tablici nista se nece promijeniti
			SemantickiAnalizator.tablicaLokalnihImena.dodajImeUTablicu(ime, tip);
			SemantickiAnalizator.deklariraneFunkcije.put(ime, tip);
		}

	}

	public String getNtip() {
		return ntip;
	}

	public void setNtip(String ntip) {
		this.ntip = ntip;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getBrElem() {
		return brElem;
	}

	public void setBrElem(int brElem) {
		this.brElem = brElem;
	}

}
