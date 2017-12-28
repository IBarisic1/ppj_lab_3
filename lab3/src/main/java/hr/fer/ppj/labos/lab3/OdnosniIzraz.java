package hr.fer.ppj.labos.lab3;

public class OdnosniIzraz extends Izraz implements CvorAtributnogStabla{
	
	public OdnosniIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<aditivni_izraz>")) {
			AditivniIzraz aditivniIzraz = new AditivniIzraz(trenutniCvor.getDjeca().get(0));
			aditivniIzraz.provjeri();
			tip = aditivniIzraz.getTip();
			l_izraz = aditivniIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<odnosni_izraz> OP_LT <aditivni_izraz>")
				|| desnaStranaProdukcije.equals("<odnosni_izraz> OP_GT <aditivni_izraz>")
				|| desnaStranaProdukcije.equals("<odnosni_izraz> OP_LTE <aditivni_izraz>")
				|| desnaStranaProdukcije.equals("<odnosni_izraz> OP_GTE <aditivni_izraz>")) {
			OdnosniIzraz odnosniIzraz = new OdnosniIzraz(trenutniCvor.getDjeca().get(0));
			odnosniIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(odnosniIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(odnosniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			AditivniIzraz aditivniIzraz = new AditivniIzraz(trenutniCvor.getDjeca().get(2));
			aditivniIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(aditivniIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(aditivniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
