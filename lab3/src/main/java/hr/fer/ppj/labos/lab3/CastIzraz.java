package hr.fer.ppj.labos.lab3;

public class CastIzraz extends Izraz implements CvorAtributnogStabla{
	
	public CastIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<unarni_izraz>")) {
			UnarniIzraz unarniIzraz = new UnarniIzraz(trenutniCvor.getDjeca().get(0));
			unarniIzraz.provjeri();
			tip = unarniIzraz.getTip();
			l_izraz = unarniIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("L_ZAGRADA <ime_tipa> D_ZAGRADA <cast_izraz>")){
			ImeTipa imeTipa = new ImeTipa(trenutniCvor.getDjeca().get(1));
			imeTipa.provjeri();
			CastIzraz castIzraz = new CastIzraz(trenutniCvor.getDjeca().get(3));
			castIzraz.provjeri();
			if(!SemantickiAnalizator.implicitnaPretvorba.get(castIzraz.getTip()).
					contains(imeTipa.getTip()) && !(castIzraz.getTip() == "int" &&
					imeTipa.getTip() == "char")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = imeTipa.getTip();
			l_izraz = false;
		}
	}
}
