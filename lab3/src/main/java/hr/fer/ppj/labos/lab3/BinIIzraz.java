package hr.fer.ppj.labos.lab3;

public class BinIIzraz extends Izraz implements CvorAtributnogStabla{
	
	public BinIIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}
	
	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<jednakosni_izraz>")) {
			JednakosniIzraz jednakosniIzraz = new JednakosniIzraz(
					trenutniCvor.getDjeca().get(0));
			jednakosniIzraz.provjeri();
			tip = jednakosniIzraz.getTip();
			l_izraz = jednakosniIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<bin_i_izraz> OP_BIN_I <jednakosni_izraz>")) {
			BinIIzraz binIIzraz = new BinIIzraz(trenutniCvor.getDjeca().get(0));
			binIIzraz.provjeri();
			if(!SemantickiAnalizator.implicitnaPretvorba.get(binIIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			JednakosniIzraz jednakosniIzraz = 
					new JednakosniIzraz(trenutniCvor.getDjeca().get(2));
			jednakosniIzraz.provjeri();
			if(!SemantickiAnalizator.implicitnaPretvorba.get(jednakosniIzraz.getTip())
					.contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = "int";
			l_izraz = false;
		}
	}
}
