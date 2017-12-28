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
			//TODO ispravio ispitivanje eksplicitnog pretvaranja
			if(castIzraz.getTip().startsWith("funkcija") || 
					castIzraz.getTip().startsWith("niz(const")) 
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);

			if(SemantickiAnalizator.implicitnaPretvorba.get(castIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(castIzraz.getTip()).
					contains(imeTipa.getTip()) && !((castIzraz.getTip().equals("int") ||
							castIzraz.getTip().equals("const(int)")) &&
					(imeTipa.getTip().equals("char") || imeTipa.getTip().equals("const(char)")))) 
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = imeTipa.getTip();
			l_izraz = false;
		}
	}
}
