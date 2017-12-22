package hr.fer.ppj.labos.lab3;

import java.util.Scanner;

public class SemantickiAnalizator {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ParserGenerativnogStabla parser = new ParserGenerativnogStabla(sc);
		
		GenerativnoStablo gen = parser.parsirajStablo();
	}
}
