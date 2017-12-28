package hr.fer.ppj.labos.lab3;

public class JednakosniIzraz extends Izraz implements CvorAtributnogStabla{
	
	public JednakosniIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<odnosni_izraz>")) {
			OdnosniIzraz odnosniIzraz = new OdnosniIzraz(
					trenutniCvor.getDjeca().get(0));
			odnosniIzraz.provjeri();
			tip = odnosniIzraz.getTip();
			l_izraz = odnosniIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<jednakosni_izraz> OP_EQ <odnosni_izraz>")
				|| desnaStranaProdukcije.equals("<jednakosni_izraz> OP_NEQ <odnosni_izraz>")) {
			JednakosniIzraz jednakosniIzraz = new JednakosniIzraz(trenutniCvor.getDjeca().get(0));
			jednakosniIzraz.provjeri();
			if(SemantickiAnalizator.implicitnaPretvorba.get(jednakosniIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(jednakosniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			OdnosniIzraz odnosniIzraz = 
					new OdnosniIzraz(trenutniCvor.getDjeca().get(2));
			odnosniIzraz.provjeri();

			if(SemantickiAnalizator.implicitnaPretvorba.get(odnosniIzraz.getTip()) == null)
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			if(!SemantickiAnalizator.implicitnaPretvorba.get(odnosniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}	
	}
}
