package hr.fer.ppj.labos.lab3;

public class LogIliIzraz extends Izraz implements CvorAtributnogStabla{
	
	public LogIliIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();

		if (desnaStranaProdukcije.equals("<log_i_izraz>")) {
			LogIIzraz logIIzraz = new LogIIzraz(trenutniCvor.getDjeca().get(0));
			logIIzraz.provjeri();
			tip = logIIzraz.getTip();
			l_izraz = logIIzraz.isL_izraz();
		} else if (desnaStranaProdukcije.equals("<log_ili_izraz> OP_ILI <log_i_izraz>")) {
			LogIliIzraz logIliIzraz = new LogIliIzraz(trenutniCvor.getDjeca().get(0));
			logIliIzraz.provjeri();
			if (!SemantickiAnalizator.implicitnaPretvorba.get(logIliIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			LogIIzraz logIIzraz = new LogIIzraz(trenutniCvor.getDjeca().get(2));
			logIIzraz.provjeri();
			if (!SemantickiAnalizator.implicitnaPretvorba.get(logIIzraz.getTip()).contains("int"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
