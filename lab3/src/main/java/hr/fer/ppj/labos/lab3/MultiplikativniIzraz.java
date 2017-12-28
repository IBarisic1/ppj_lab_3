package hr.fer.ppj.labos.lab3;

public class MultiplikativniIzraz extends Izraz implements CvorAtributnogStabla{
		
	public MultiplikativniIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<cast_izraz>")) {
			CastIzraz castIzraz = new CastIzraz(trenutniCvor.getDjeca().get(0));
			castIzraz.provjeri();
			tip = castIzraz.getTip();
			l_izraz = castIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<multiplikativni_izraz> OP_PUTA <cast_izraz>")
				|| desnaStranaProdukcije.equals("<multiplikativni_izraz> OP_DIJELI <cast_izraz>")
				|| desnaStranaProdukcije.equals("<multiplikativni_izraz> OP_MOD <cast_izraz>")) {
			MultiplikativniIzraz multiplikativniIzraz = new MultiplikativniIzraz(trenutniCvor.getDjeca().get(0));
			multiplikativniIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(multiplikativniIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(multiplikativniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			CastIzraz castIzraz = new CastIzraz(trenutniCvor.getDjeca().get(2));
			castIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(castIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(castIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
