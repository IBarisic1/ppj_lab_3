package hr.fer.ppj.labos.lab3;

public class UnarniIzraz extends Izraz implements CvorAtributnogStabla{
	
	public UnarniIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<postfiks_izraz>")) {
			PostfiksIzraz postfiksIzraz = new PostfiksIzraz(
					trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			tip = postfiksIzraz.getTip();
			l_izraz = postfiksIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("OP_DEC <unarni_izraz>")
				|| desnaStranaProdukcije.equals("OP_INC <unarni_izraz>")) {
			UnarniIzraz unarniIzraz = new UnarniIzraz(trenutniCvor.getDjeca().get(1));
			unarniIzraz.provjeri();
			if(!unarniIzraz.isL_izraz() || !SemantickiAnalizator.implicitnaPretvorba.
					get(unarniIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}else if(desnaStranaProdukcije.equals("<unarni_operator> <cast_izraz>")){
			CastIzraz castIzraz = new CastIzraz(trenutniCvor.getDjeca().get(1));
			castIzraz.provjeri();
			if(!SemantickiAnalizator.implicitnaPretvorba.get(castIzraz.getTip()).
					contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}

}
