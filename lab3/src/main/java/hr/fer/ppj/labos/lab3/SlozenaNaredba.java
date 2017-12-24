package hr.fer.ppj.labos.lab3;

public class SlozenaNaredba implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public SlozenaNaredba(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("L_VIT_ZAGRADA <lista_naredbi> D_VIT_ZAGRADA")) {
			ListaNaredbi listaNaredbi = new ListaNaredbi(trenutniCvor.getDjeca().get(1));
			listaNaredbi.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("L_VIT_ZAGRADA <lista_deklaracija> <lista_naredbi> D_VIT_ZAGRADA")) {
			TablicaLokalnihImena novaTablicaLokImena = new TablicaLokalnihImena();
			novaTablicaLokImena.setPrethodnaTablica(SemantickiAnalizator.tablicaLokalnihImena);
			SemantickiAnalizator.tablicaLokalnihImena = novaTablicaLokImena;

			ListaDeklaracija listaDeklaracija = new ListaDeklaracija(trenutniCvor.getDjeca().get(1));
			listaDeklaracija.provjeri();
			ListaNaredbi listaNaredbi = new ListaNaredbi(trenutniCvor.getDjeca().get(2));
			listaNaredbi.provjeri();

			// povratak na tablicu imena ugnježđujućeg bloka (vanjskog bloka)
			SemantickiAnalizator.tablicaLokalnihImena = SemantickiAnalizator.tablicaLokalnihImena.getPrethodnaTablica();
		}

	}
}
