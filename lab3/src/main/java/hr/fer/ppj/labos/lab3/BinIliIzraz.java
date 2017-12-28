package hr.fer.ppj.labos.lab3;

public class BinIliIzraz extends Izraz implements CvorAtributnogStabla{

	public BinIliIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();

		if (desnaStranaProdukcije.equals("<bin_xili_izraz>")) {
			BinXiliIzraz binXiliIzraz = new BinXiliIzraz(trenutniCvor.getDjeca().get(0));
			binXiliIzraz.provjeri();
			tip = binXiliIzraz.getTip();
			l_izraz = binXiliIzraz.isL_izraz();
		} else if (desnaStranaProdukcije.equals("<bin_ili_izraz> OP_BIN_ILI <bin_xili_izraz>")) {
			BinIliIzraz binIliIzraz = new BinIliIzraz(trenutniCvor.getDjeca().get(0));
			binIliIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(binIliIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if (!SemantickiAnalizator.implicitnaPretvorba.get(binIliIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			BinXiliIzraz binXiliIzraz = new BinXiliIzraz(trenutniCvor.getDjeca().get(2));
			binXiliIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(binXiliIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if (!SemantickiAnalizator.implicitnaPretvorba.get(binXiliIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
