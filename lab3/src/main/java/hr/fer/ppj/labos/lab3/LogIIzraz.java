package hr.fer.ppj.labos.lab3;

public class LogIIzraz extends Izraz implements CvorAtributnogStabla{
	
	public LogIIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();

		if (desnaStranaProdukcije.equals("<bin_ili_izraz>")) {
			BinIliIzraz binIliIzraz = new BinIliIzraz(trenutniCvor.getDjeca().get(0));
			binIliIzraz.provjeri();
			tip = binIliIzraz.getTip();
			l_izraz = binIliIzraz.isL_izraz();
		} else if (desnaStranaProdukcije.equals("<log_i_izraz> OP_I <bin_ili_izraz>")) {
			LogIIzraz logIIzraz = new LogIIzraz(trenutniCvor.getDjeca().get(0));
			logIIzraz.provjeri();
			if (!SemantickiAnalizator.implicitnaPretvorba.get(logIIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			BinIliIzraz binIliIzraz = new BinIliIzraz(trenutniCvor.getDjeca().get(2));
			binIliIzraz.provjeri();
			if (!SemantickiAnalizator.implicitnaPretvorba.get(binIliIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
