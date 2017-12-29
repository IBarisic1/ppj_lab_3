package hr.fer.ppj.labos.lab3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SemantickiAnalizator {

	static TablicaLokalnihImena tablicaLokalnihImena = new TablicaLokalnihImena();

	/**
	 * String je tip koji implicitno pretvaramo, a Set<String> je popis tipova u
	 * koje se moze implicitno pretvoriti.
	 */
	static Map<String, Set<String>> implicitnaPretvorba = new HashMap<>();
	// TODO napuniti tablicu s implicitnom pretvorbom

	/**
	 * String je ime funkcije, drugi String je tip deklaracije funkcije.
	 */
	static Map<String, String> definiraneFunkcije = new HashMap<>();

	/**
	 * String je ime funkcije, a drugi String je tip deklaracije funkcije.
	 */
	static Map<String, String> deklariraneFunkcije = new HashMap<>();

	static int brojTrenutnihPetlji = 0;

	/**
	 * Postavlja se kod svake definicije nove funkcije, da se iz ugnjezdenih
	 * blokova moze znati koji je tip ugnjezdujuce funkcije. Po izlasku iz svake
	 * definicije funkcije postavlja se na null.
	 */
	static String tipUgnjezdujuceFunkcije;

	// Treba li dopustiti overloading?

	public static void main(String[] args) throws Exception {
		//TODO u svim klasama sa ispitivanjem implicitnom provjerom
		//ispravio ispitivanje tako da ako nema nista u tablici baca gresku
		Scanner sc = new Scanner(System.in);

		ParserGenerativnogStabla parser = new ParserGenerativnogStabla();

		GenerativnoStablo gen = parser.parsirajStablo();
		
		inicijalizirajImplicitnuPretvorbu();
		
		PrijevodnaJedinica pj = (PrijevodnaJedinica) Tvornica.napraviAtributniCvor(gen.getKorijen());

		pj.provjeri();

		// provjere na kraju
		String tipMain = "funkcija(void -> int)";
		if (!tipMain.equals(definiraneFunkcije.get("main"))) {
			System.out.println("main");
			return;
		}

		for (Map.Entry<String, String> deklariranaFunkcija : deklariraneFunkcije.entrySet()) {
			String imeFunkcije = deklariranaFunkcija.getKey();
			String tipFunkcije = deklariranaFunkcija.getValue();
			if (!definiraneFunkcije.containsKey(imeFunkcije)
					|| !tipFunkcije.equals(definiraneFunkcije.get(imeFunkcije))) {
				System.out.println("funkcija");
				return;
			}
		}

	}

	public static boolean jeLIzraz(String tip) {
		if (tip.equals("int") || tip.equals("char"))
			return true;
		return false;
	}

	public static void ispisiGreskuUProdukciji(CvorGenerativnogStabla g) {
		System.out.println(g + " ::= " + g.ispisiDesnuStranuZaGresku());
		System.exit(0);
	}
	
	private static void inicijalizirajImplicitnuPretvorbu() {
		implicitnaPretvorba.put("int", new HashSet<String>(Arrays.asList("int", 
				"const(int)")));
		implicitnaPretvorba.put("char", new HashSet<String>(Arrays.asList(
				"int", "char", "const(int)", "const(char)")));
		implicitnaPretvorba.put("const(int)", new HashSet<String>(Arrays.asList("int", 
				"const(int)")));
		implicitnaPretvorba.put("const(char)", new HashSet<String>(Arrays.asList(
				"int", "char", "const(int)", "const(char)")));
		implicitnaPretvorba.put("niz(char)", new HashSet<String>(Arrays.asList(
				"niz(char)", "niz(const(char))")));
		implicitnaPretvorba.put("niz(int)", new HashSet<String>(Arrays.asList(
				"niz(int)", "niz(const(int))")));
		implicitnaPretvorba.put("void", new HashSet<String>());
		implicitnaPretvorba.put("niz(const(char))", new HashSet<String>());
		implicitnaPretvorba.put("niz(const(int))", new HashSet<String>());
		implicitnaPretvorba.put("niz(const(char))", new HashSet<String>());
	}
}
