package hr.fer.ppj.labos.lab3;

public class BinXiliIzraz extends Izraz implements CvorAtributnogStabla {

	public BinXiliIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}

	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();

		if (desnaStranaProdukcije.equals("<bin_i_izraz>")) {
			BinIIzraz binIIzraz = new BinIIzraz(trenutniCvor.getDjeca().get(0));
			binIIzraz.provjeri();
			tip = binIIzraz.getTip();
			l_izraz = binIIzraz.isL_izraz();
		} else if (desnaStranaProdukcije.equals("<bin_xili_izraz> OP_BIN_XILI <bin_i_izraz>")) {
			BinXiliIzraz binXiliIzraz = new BinXiliIzraz(trenutniCvor.getDjeca().get(0));
			binXiliIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(binXiliIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if (!SemantickiAnalizator.implicitnaPretvorba.get(binXiliIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			BinIIzraz binIIzraz = new BinIIzraz(trenutniCvor.getDjeca().get(2));
			binIIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(binIIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if (!SemantickiAnalizator.implicitnaPretvorba.get(binIIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
