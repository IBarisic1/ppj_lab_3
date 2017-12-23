package hr.fer.ppj.labos.lab3;

public abstract class Tvornica {
	
	public static CvorAtributnogStabla 
				napraviAtributniCvor(CvorGenerativnogStabla g){
		
		String imeCvoraGenerativnogStabla = g.getUniformniZnak();
		
//		String[] podijeljenoIme = imeCvoraGenerativnogStabla.substring(1, 
//				imeCvoraGenerativnogStabla.length() - 1).split("_");
//		StringBuilder sb = new StringBuilder();
//		String paket = CvorAtributnogStabla.class.getPackageName();
//		sb.append(paket + ".");
//		for(int i = 0; i < podijeljenoIme.length; i++) {
//			char[] znakovi = podijeljenoIme[i].toCharArray();
//			znakovi[0] = Character.toUpperCase(znakovi[0]);
//			podijeljenoIme[i] = new String(znakovi);
//			sb.append(podijeljenoIme[i]);
//		}
//		
//		String punoIme = sb.toString();
//		
//		return (CvorAtributnogStabla) Class.forName(punoIme).
//				getConstructor(CvorGenerativnogStabla.class).newInstance(g);
		
		if(imeCvoraGenerativnogStabla.equals("<aditivni_izraz>"))
			return new AditivniIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<bin_i_izraz>"))
			return new BinIIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<bin_ili_izraz>"))
			return new BinIliIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<bin_xili_izraz>"))
			return new BinXiliIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<cast_izraz>"))
			return new CastIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<definicija_funkcije>")) 
			return new DefinicijaFunkcije(g);
		else if(imeCvoraGenerativnogStabla.equals("<deklaracija>"))
			return new Deklaracija(g);
		else if(imeCvoraGenerativnogStabla.equals("<deklaracija_parametra>"))
			return new DeklaracijaParametra(g);
		else if(imeCvoraGenerativnogStabla.equals("<ime_tipa>"))
			return new ImeTipa(g);
		else if(imeCvoraGenerativnogStabla.equals("<inicijalizator>"))
			return new Inicijalizator(g);
		else if(imeCvoraGenerativnogStabla.equals("<init_deklarator>"))
			return new InitDeklarator(g);
		else if(imeCvoraGenerativnogStabla.equals("<izravni_deklarator>"))
			return new IzravniDeklarator(g);
		else if(imeCvoraGenerativnogStabla.equals("<izraz>"))
			return new Izraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<izraz_naredba>"))
			return new IzrazNaredba(g);
		else if(imeCvoraGenerativnogStabla.equals("<izraz_pridruzivanja>"))
			return new IzrazPridruzivanja(g);
		else if(imeCvoraGenerativnogStabla.equals("<jednakosni_izraz>"))
			return new JednakosniIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<lista_argumenata>"))
			return new ListaArgumenata(g);
		else if(imeCvoraGenerativnogStabla.equals("<lista_deklaracija>"))
			return new ListaArgumenata(g);
		else if(imeCvoraGenerativnogStabla.equals("<lista_init_deklaratora>"))
			return new ListaInitDeklaratora(g);
		else if(imeCvoraGenerativnogStabla.equals("<lista_izraza_pridruzivanja>"))
			return new ListaIzrazaPridruzivanja(g);
		else if(imeCvoraGenerativnogStabla.equals("<lista_naredbi>"))
			return new ListaNaredbi(g);
		else if(imeCvoraGenerativnogStabla.equals("<lista_parametara>"))
			return new ListaParametara(g);
		else if(imeCvoraGenerativnogStabla.equals("<log_i_izraz>"))
			return new LogIIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<log_ili_izraz>"))
			return new LogIliIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<multiplikativni_izraz>"))
			return new MultiplikativniIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<naredba>"))
			return new Naredba(g);
		else if(imeCvoraGenerativnogStabla.equals("<naredba_grananja>"))
			return new NaredbaGrananja(g);
		else if(imeCvoraGenerativnogStabla.equals("<naredba_petlje>"))
			return new NaredbaPetlje(g);
		else if(imeCvoraGenerativnogStabla.equals("<naredba_skoka>"))
			return new NaredbaSkoka(g);
		else if(imeCvoraGenerativnogStabla.equals("<odnosni_izraz>"))
			return new OdnosniIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<postfiks_izraz>"))
			return new PostfiksIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<prijevodna_jedinica>"))
			return new PrijevodnaJedinica(g);
		else if(imeCvoraGenerativnogStabla.equals("<primarni_izraz>"))
			return new PrimarniIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<slozena_naredba>"))
			return new SlozenaNaredba(g);
		else if(imeCvoraGenerativnogStabla.equals("<specifikator_tipa>"))
			return new SpecifikatorTipa(g);
		else if(imeCvoraGenerativnogStabla.equals("<unarni_izraz>"))
			return new UnarniIzraz(g);
		else if(imeCvoraGenerativnogStabla.equals("<vanjska_deklaracija>"))
			return new VanjskaDeklaracija(g);
		else throw new IllegalArgumentException("Uneseno krivo ime.");
	}
}
