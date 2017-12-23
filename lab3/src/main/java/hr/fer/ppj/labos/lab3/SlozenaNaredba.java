package hr.fer.ppj.labos.lab3;

public class SlozenaNaredba implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;

	public SlozenaNaredba(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		TablicaLokalnihImena novaTablicaLokImena = new TablicaLokalnihImena();
		novaTablicaLokImena.setPrethodnaTablica(SemantickiAnalizator.tablicaLokalnihImena);
		SemantickiAnalizator.tablicaLokalnihImena = novaTablicaLokImena;
		if (trenutniCvor.desnaStranaProdukcije().equals("L_VIT_ZAGRADA <lista_naredbi> D_VIT_ZAGRADA ")) {
			ListaNaredbi listaNaredbi = (ListaNaredbi) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(1));
			listaNaredbi.provjeri();
		} else if (trenutniCvor.desnaStranaProdukcije()
				.equals("L_VIT_ZAGRADA <lista_deklaracija> <lista_naredbi> D_VIT_ZAGRADA ")) {
			ListaDeklaracija listaDeklaracija = (ListaDeklaracija) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(1));
			listaDeklaracija.provjeri();
			ListaNaredbi listaNaredbi = (ListaNaredbi) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(2));
			listaNaredbi.provjeri();
		}
		//povratak na tablicu imena ugnježđujućeg bloka (vanjskog bloka)
		SemantickiAnalizator.tablicaLokalnihImena = SemantickiAnalizator.tablicaLokalnihImena.getPrethodnaTablica();
	}
}
