package hr.fer.ppj.labos.lab3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SemantickiAnalizator {
	
	static TablicaLokalnihImena tablicaLokalnihImena = new TablicaLokalnihImena();
	
	/**
	 * String je tip koji implicitno pretvaramo, a Set<String> je popis
	 * tipova u koje se može implicitno pretvoriti.
	 */
	static Map<String, Set<String>> implicitnaPretvorba = new HashMap<>();
	//TODO napuniti tablicu s implicitnom pretvorbom
	
	/**
	 * String je ime funkcije, drugi String je tip deklaracije funkcije.
	 */
	static Map<String, String> definiraneFunkcije = new HashMap<>();
	
	/**
	 * String je ime funkcije, a drugi String je tip deklaracije funkcije.
	 */
	static Map<String, String> deklariraneFunkcije = new HashMap<>();

	//Treba li dopustiti overloading?
	
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		ParserGenerativnogStabla parser = new ParserGenerativnogStabla(sc);
		
		GenerativnoStablo gen = parser.parsirajStablo();
		
		PrijevodnaJedinica pj = 
				(PrijevodnaJedinica) Tvornica.napraviAtributniCvor(gen.getKorijen());
		
		pj.provjeri();
		
		//provjere na kraju
		String tipMain = "funkcija(void -> int)";
		if(!tipMain.equals(definiraneFunkcije.get("main"))){
			System.out.println("main");
			return;
		}
		
		for (Map.Entry<String, String> deklariranaFunkcija :
				deklariraneFunkcije.entrySet()) {
			String imeFunkcije = deklariranaFunkcija.getKey();
			String tipFunkcije = deklariranaFunkcija.getValue();
			if(!definiraneFunkcije.containsKey(imeFunkcije) ||
					!tipFunkcije.equals(definiraneFunkcije.get(imeFunkcije))) {
				System.out.println("funkcija");
				return;
			}		
		}
		
	}
	
	public static boolean jeLIzraz(String tip) {
		if(tip.equals("int") || tip.equals("char")) return true;
		return false;
	}
	
	public static void ispisiGreskuUProdukciji(CvorGenerativnogStabla g) {
		System.out.println(g + " ::= " + g.desnaStranaProdukcije());
		System.exit(0);
	}
}
