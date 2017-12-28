package hr.fer.ppj.labos.lab3;

import java.util.Arrays;
import java.util.List;

public class PrimarniIzraz extends Izraz implements CvorAtributnogStabla{
	
	public PrimarniIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("IDN")) {
			String imeIdentifikatora = trenutniCvor.getDjeca().get(0).
					getLeksickaJedinka();
			//TODO ispravio pretraživanje tablice lokalnih imena
			if(SemantickiAnalizator.tablicaLokalnihImena.
					jeDeklarirano(imeIdentifikatora)) {
				TablicaLokalnihImena trenutnaTablica = 
						SemantickiAnalizator.tablicaLokalnihImena;
				while(trenutnaTablica != null) {
					if(trenutnaTablica.sadrziIme(imeIdentifikatora)) {
						tip = trenutnaTablica.dohvatiTipZaIme(imeIdentifikatora);
						break;
					}
					trenutnaTablica = trenutnaTablica.getPrethodnaTablica();
				}
				l_izraz = SemantickiAnalizator.jeLIzraz(tip);
			}
			
			else SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
		}
		
		else if(desnaStranaProdukcije.equals("BROJ")) {
			double vrijednostBroja = Double.parseDouble(trenutniCvor.getDjeca().get(0).
					getLeksickaJedinka());
			
			if(vrijednostBroja >= Integer.MIN_VALUE && 
					vrijednostBroja <= Integer.MAX_VALUE) {
				tip = "int";
				l_izraz = false;
			}
			else SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
		}
		
		else if(desnaStranaProdukcije.equals("ZNAK")) {
			
			String znakovnaKonstanta = trenutniCvor.getDjeca().get(0).
					getLeksickaJedinka();
			
			if(znakovnaKonstanta.length() == 4)
				znakovnaKonstanta = znakovnaKonstanta.substring(2, 3);
			
			List<String> prihvatljiviZnakovi = Arrays.asList(new String[] {
					"t", "n", "0", "'", "\"", "\\"
			});
			//3 zbog jednostrukih navodnika
			if(znakovnaKonstanta.length() == 3 || 
					prihvatljiviZnakovi.contains(znakovnaKonstanta)) {
				
				tip = "char"; 
				l_izraz = false;
				
			}else SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
		}
		
		else if(desnaStranaProdukcije.equals("NIZ_ZNAKOVA")) {
			
			String nizZnakova = trenutniCvor.getDjeca().get(0).
					getLeksickaJedinka();
			
			String testirajNiz = nizZnakova;
			
			List<String> prihvatljiviZnakovi = Arrays.asList(new String[] {
					"t", "n", "0", "'", "\"", "\\"
			});
			
			while(testirajNiz.contains("\\")) {
				int index = testirajNiz.indexOf("\\");
				if(!prihvatljiviZnakovi.contains(
						new StringBuilder(testirajNiz.charAt(index + 1)).toString()))
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				testirajNiz = testirajNiz.substring(index);
			}
			
			tip = "niz(const(char))";
			l_izraz = false;
		}
		
		else if(desnaStranaProdukcije.equals("L_ZAGRADA <izraz> D_ZAGRADA")) {
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(1));
			izraz.provjeri();
			tip = izraz.getTip();
			l_izraz = izraz.isL_izraz();
		}
	}
	
	
}
