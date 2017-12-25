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

			List<String> T = Arrays.asList(new String[] { "char", "int" });
			if (T.contains(izravniDeklarator.getTip()) || ((izravniDeklarator.getTip().startsWith("niz(const("))
					&& (T.contains(izravniDeklarator.getTip().substring(10, izravniDeklarator.getTip().length() - 1))
							&& (izravniDeklarator.getTip().endsWith(")"))))) {
				// provjeri je li donja provjera dobra
				if (!(SemantickiAnalizator.implicitnaPretvorba.get(inicijalizator.getTip()).contains("int")
						|| SemantickiAnalizator.implicitnaPretvorba.get(inicijalizator.getTip()).contains("char"))) {
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				}
			} else if ((izravniDeklarator.getTip()
					.startsWith(
							"const(")
					&& (T.contains(izravniDeklarator.getTip().substring(6, izravniDeklarator.getTip().length() - 1))
							&& (izravniDeklarator.getTip().endsWith(")"))))
					|| ((izravniDeklarator.getTip().startsWith("niz(const(")) && (T
							.contains(izravniDeklarator.getTip().substring(10, izravniDeklarator.getTip().length() - 1))
							&& (izravniDeklarator.getTip().endsWith(")"))))) {
				if (inicijalizator.getBrElem() > izravniDeklarator.getBrElem()) {
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
				}
				
				for (String tip : inicijalizator.getTipovi()) {
					// provjeri je li donja provjera dobra
					if (!(SemantickiAnalizator.implicitnaPretvorba.get(tip).contains("int")
							|| SemantickiAnalizator.implicitnaPretvorba.get(tip).contains("char"))) {
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
