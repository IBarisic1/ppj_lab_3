package hr.fer.ppj.labos.lab3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SemantickiAnalizator {
	
	TablicaLokalnihImena tablicaLokalnihImena = new TablicaLokalnihImena();
	
	/**
	 * String je tip koji implicitno pretvaramo, a Set<String> je popis
	 * tipova u koje se može implicitno pretvoriti.
	 */
	Map<String, Set<String>> implicitnaPretvorba = new HashMap<>();
	
	/**
	 * String je ime funkcije, drugi String je tip deklaracije funkcije.
	 */
	Map<String, String> definiraneFunkcije = new HashMap<>();
	
	/**
	 * String je ime funkcije, a drugi String je tip deklaracije funkcije.
	 */
	Map<String, String> deklariraneFunkcije = new HashMap<>();

	//Treba li dopustiti overloading?
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ParserGenerativnogStabla parser = new ParserGenerativnogStabla(sc);
		
		GenerativnoStablo gen = parser.parsirajStablo();
		
		PrijevodnaJedinica pj = 
				(PrijevodnaJedinica) Tvornica.napraviAtributniCvor(gen.getKorijen());
	}
}
