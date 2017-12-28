package hr.fer.ppj.labos.lab3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InitDeklarator implements CvorAtributnogStabla {

	private CvorGenerativnogStabla trenutniCvor;
	private String ntip;
	private String tip;

	public InitDeklarator(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
	}

	public void provjeri() {
		if (trenutniCvor.desnaStranaProdukcije().equals("<izravni_deklarator>")) {
			IzravniDeklarator izravniDeklarator = new IzravniDeklarator(trenutniCvor.getDjeca().get(0));
			izravniDeklarator.setNtip(this.getNtip());
			izravniDeklarator.provjeri();

			List<String> T = Arrays.asList(new String[] { "char", "int" });
			if ((izravniDeklarator.getTip()
					.startsWith(
							"const(")
					&& (T.contains(izravniDeklarator.getTip().substring(6, izravniDeklarator.getTip().length() - 1))
							&& (izravniDeklarator.getTip().endsWith(")"))))
					|| ((izravniDeklarator.getTip().startsWith("niz(const(")) && (T
							.contains(izravniDeklarator.getTip().substring(10, izravniDeklarator.getTip().length() - 1))
							&& (izravniDeklarator.getTip().endsWith(")"))))) {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
		} else if (trenutniCvor.desnaStranaProdukcije().equals("<izravni_deklarator> OP_PRIDRUZI <inicijalizator>")) {
			IzravniDeklarator izravniDeklarator = new IzravniDeklarator(trenutniCvor.getDjeca().get(0));
			izravniDeklarator.setNtip(this.getNtip());
			izravniDeklarator.provjeri();

			Inicijalizator inicijalizator = new Inicijalizator(trenutniCvor.getDjeca().get(2));
			inicijalizator.provjeri();
			
			// tu bi moglo dolaziti do nekakve greske, ako bude provjeri dno 71. i vrh 72. strane
			List<String> T = Arrays.asList(new String[] { "char", "int" });
			
			if (T.contains(izravniDeklarator.getTip()) || ((izravniDeklarator.getTip().startsWith("const("))
					&& (T.contains(izravniDeklarator.getTip().substring(6, izravniDeklarator.getTip().length() - 1))
							&& (izravniDeklarator.getTip().endsWith(")"))))) {
				// provjeri je li donja provjera dobra
				//TODO popravio sam da ispituje može li se pretvoriti u tip
				//izravnog deklaratora a ne bilo koji
				if(SemantickiAnalizator.implicitnaPretvorba.get(inicijalizator.getTip()) == null)
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				if (!(SemantickiAnalizator.implicitnaPretvorba.get(inicijalizator.getTip()).contains(
						izravniDeklarator.getTip()))) {
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				}
				
				//TODO ispravio ispitivanje tipa izravnog deklaratora
			} else if ((izravniDeklarator.getTip()
					.startsWith(
							"niz(")
					&& (T.contains(izravniDeklarator.getTip().substring(4, izravniDeklarator.getTip().length() - 1))
							&& (izravniDeklarator.getTip().endsWith(")"))))
					|| ((izravniDeklarator.getTip().startsWith("niz(const(") && (T
							.contains(izravniDeklarator.getTip().substring(10, izravniDeklarator.getTip().length() - 2))
							&& (izravniDeklarator.getTip().endsWith(")")))))) {

				if (inicijalizator.getBrElem() > izravniDeklarator.getBrElem()) {
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				}
				
				for (String inicijalizatorTip : inicijalizator.getTipovi()) {
					// provjeri je li donja provjera dobra
					//TODO provjeriti kako bi ovo ispravili - ja sam stavio da ispituje
					//za tip izravnog deklaratora
					if(SemantickiAnalizator.implicitnaPretvorba.get(inicijalizatorTip) == null)
						SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
					String tipIzravnogDeklaratora;
					if(izravniDeklarator.getTip().startsWith("niz")) {
						tipIzravnogDeklaratora = izravniDeklarator.getTip().substring
								(4, izravniDeklarator.getTip().length() - 1);
					}
					else tipIzravnogDeklaratora = izravniDeklarator.getTip().substring
							(10, izravniDeklarator.getTip().length() - 1);
					
					if (!(SemantickiAnalizator.implicitnaPretvorba.get(inicijalizatorTip).contains(tipIzravnogDeklaratora))) {
						SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
					}
				}
			} else {
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
		}
	}

	public String getNtip() {
		return ntip;
	}

	public void setNtip(String ntip) {
		this.ntip = ntip;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
