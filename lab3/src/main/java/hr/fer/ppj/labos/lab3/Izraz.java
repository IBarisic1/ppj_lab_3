package hr.fer.ppj.labos.lab3;

public class Izraz implements CvorAtributnogStabla{
	
	protected String tip;
	protected boolean l_izraz;
	
	protected CvorGenerativnogStabla trenutniCvor;
	
	public Izraz(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();

		if (desnaStranaProdukcije.equals("<izraz_pridruzivanja>")) {
			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(trenutniCvor.getDjeca().get(0));
			izrazPridruzivanja.provjeri();
			tip = izrazPridruzivanja.getTip();
			l_izraz = izrazPridruzivanja.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<izraz> ZAREZ <izraz_pridruzivanja>")) {
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(0));
			izraz.provjeri();
			IzrazPridruzivanja izrazPridruzivanja = new IzrazPridruzivanja(trenutniCvor.getDjeca().get(2));
			izrazPridruzivanja.provjeri();
			tip = izrazPridruzivanja.getTip();
			l_izraz = false;
		}
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public boolean isL_izraz() {
		return l_izraz;
	}

	public void setL_izraz(boolean l_izraz) {
		this.l_izraz = l_izraz;
	}
}
