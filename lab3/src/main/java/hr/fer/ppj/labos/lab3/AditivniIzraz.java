package hr.fer.ppj.labos.lab3;

public class AditivniIzraz extends Izraz implements CvorAtributnogStabla{
	
	public AditivniIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<multiplikativni_izraz>")) {
			MultiplikativniIzraz multiplikativniIzraz = new MultiplikativniIzraz(
					trenutniCvor.getDjeca().get(0));
			multiplikativniIzraz.provjeri();
			tip = multiplikativniIzraz.getTip();
			l_izraz = multiplikativniIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<aditivni_izraz> PLUS <multiplikativni_izraz>")
				|| desnaStranaProdukcije.equals("<aditivni_izraz> MINUS <multiplikativni_izraz>")) {
			AditivniIzraz aditivniIzraz = new AditivniIzraz(trenutniCvor.getDjeca().get(0));
			aditivniIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(aditivniIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(aditivniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			MultiplikativniIzraz multiplikativniIzraz = 
					new MultiplikativniIzraz(trenutniCvor.getDjeca().get(2));
			multiplikativniIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(multiplikativniIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(multiplikativniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
