package hr.fer.ppj.labos.lab3;

public class GenerativnoStablo {
	
	private CvorGenerativnogStabla korijen;
	
	public GenerativnoStablo(CvorGenerativnogStabla korijen) {
		this.korijen = korijen;
	}
	
	// poziva se s korijenom stabla i pomakom od 0
	public void ispisiStablo(CvorGenerativnogStabla korijen, int pomak) {
		if (!korijen.getUniformniZnak().equals("<%>")) {
			for (int i = 0; i < pomak; i++) {
				System.out.print(" ");
			}
			System.out.println(korijen);
		}
		for (CvorGenerativnogStabla dijete : korijen.getDjeca()) {
			this.ispisiStablo(dijete, pomak + 1);
		}
	}

	public CvorGenerativnogStabla getKorijen() {
		return korijen;
	}
}
