package hr.fer.ppj.labos.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ParserGenerativnogStabla {

	public GenerativnoStablo parsirajStablo() {

		List<String> sviZapisiGenerativnogStabla = new ArrayList<>();

		try (Scanner s = new Scanner(System.in)) {
			String sljedecaLinija;
			while (s.hasNextLine()) {
				sljedecaLinija = s.nextLine();
				if (sljedecaLinija.isEmpty()) {
					break;
				}
				sviZapisiGenerativnogStabla.add(sljedecaLinija);
			}
		}


		CvorGenerativnogStabla korijen = new CvorGenerativnogStabla(sviZapisiGenerativnogStabla.get(0), true, 0, 0);

		List<CvorGenerativnogStabla> obradjeniCvorovi = new LinkedList<>();

		obradjeniCvorovi.add(korijen);

		for (int k = 0; k < obradjeniCvorovi.size(); k++) {
			CvorGenerativnogStabla jedanCvor = obradjeniCvorovi.get(k);
			for (int i = jedanCvor.getIndeksLinije() + 1; i < sviZapisiGenerativnogStabla.size(); i++) {
				String jedanZapis = sviZapisiGenerativnogStabla.get(i);
				int brojBjelina = jedanZapis.indexOf(jedanZapis.trim());
				if (brojBjelina <= jedanCvor.getBrojBjelina())
					break;
				else if (brojBjelina == jedanCvor.getBrojBjelina() + 1) {
					boolean ispisatiSamoUniformniZnak = jedanZapis.trim().startsWith("<")
							|| jedanZapis.trim().equals("$");
					CvorGenerativnogStabla noviCvor;
					if (ispisatiSamoUniformniZnak) {
						noviCvor = new CvorGenerativnogStabla(jedanZapis.trim(), ispisatiSamoUniformniZnak, brojBjelina,
								i);
						obradjeniCvorovi.add(noviCvor);
						jedanCvor.dodajDijete(noviCvor);
					} else {
						String[] splittanZapis = jedanZapis.trim().split(" ");
						String uniformniZnak = splittanZapis[0];
						int brojRetka = Integer.parseInt(splittanZapis[1]);
						String leksickaJedinka = jedanZapis.trim()
								.substring(splittanZapis[0].length() + splittanZapis[1].length() + 2);
						noviCvor = new CvorGenerativnogStabla(uniformniZnak, brojRetka, leksickaJedinka,
								ispisatiSamoUniformniZnak, brojBjelina, i);
						obradjeniCvorovi.add(noviCvor);
						jedanCvor.dodajDijete(noviCvor);
					}
				}
			}
		}

		return new GenerativnoStablo(korijen);
	}
}
