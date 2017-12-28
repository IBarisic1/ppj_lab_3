package hr.fer.ppj.labos.lab3;

public class IzrazPridruzivanja extends Izraz implements CvorAtributnogStabla{
	
	public IzrazPridruzivanja(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();

		if (desnaStranaProdukcije.equals("<log_ili_izraz>")) {
			LogIliIzraz logIliIzraz = new LogIliIzraz(trenutniCvor.getDjeca().get(0));
			logIliIzraz.provjeri();
			tip = logIliIzraz.getTip();
			l_izraz = logIliIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> OP_PRIDRUZI <izraz_pridruzivanja>")) {
			PostfiksIzraz postfiksIzraz = new PostfiksIzraz(trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			if(!postfiksIzraz.isL_izraz()) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(trenutniCvor.getDjeca().get(2));
			izrazPridruzivanja.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(izrazPridruzivanja.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(izrazPridruzivanja.getTip()).
					contains(postfiksIzraz.getTip())) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = postfiksIzraz.getTip();
			l_izraz = false;
		}
	}
}
