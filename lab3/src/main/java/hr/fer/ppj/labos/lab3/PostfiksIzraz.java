package hr.fer.ppj.labos.lab3;

import java.util.Arrays;
import java.util.List;

public class PostfiksIzraz extends Izraz implements CvorAtributnogStabla{
	
	public PostfiksIzraz(CvorGenerativnogStabla trenutniCvor) {
		super(trenutniCvor);
	}

	public void provjeri() {
		String desnaStranaProdukcije = trenutniCvor.desnaStranaProdukcije();
		
		if(desnaStranaProdukcije.equals("<primarni_izraz>")) {
			PrimarniIzraz primarniIzraz = new PrimarniIzraz(trenutniCvor.
					getDjeca().get(0));
			primarniIzraz.provjeri();
			tip = primarniIzraz.getTip();
			l_izraz = primarniIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> L_UGL_ZAGRADA <izraz> D_UGL_ZAGRADA")) {
			PostfiksIzraz postfiksIzraz = new PostfiksIzraz
					(trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			
			List<String> X = Arrays.asList(new String[] {
					"char", "int", "const(char)", "const(int)"
			});
			
			if((!postfiksIzraz.getTip().startsWith("niz")) && !X.contains(postfiksIzraz.getTip().
					substring(4, postfiksIzraz.getTip().length() - 1)))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			
			Izraz izraz = new Izraz(trenutniCvor.getDjeca().get(2));
			izraz.provjeri();
			
			if(!SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()).
					contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			
			tip = postfiksIzraz.getTip().
					substring(4, postfiksIzraz.getTip().length() - 1);
			l_izraz = !tip.startsWith("const");
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> L_ZAGRADA D_ZAGRADA")) {
			PostfiksIzraz postfiksIzraz = new PostfiksIzraz(trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			
			List<String> pov = Arrays.asList(new String[] {
				"char", "int", "void"	
			});
			
			if(!postfiksIzraz.getTip().startsWith("funkcija(void -> ") &&
					!pov.contains(postfiksIzraz.getTip().
							substring("funkcija(void -> ".length(),
									postfiksIzraz.getTip().length() - 1)))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = postfiksIzraz.getTip().
					substring("funkcija(void -> ".length(), 
							postfiksIzraz.getTip().length() - 1);
			l_izraz = false;
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> L_ZAGRADA <lista_argumenata> D_ZAGRADA")) {
			
			PostfiksIzraz postfiksIzraz = new PostfiksIzraz(trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			
			ListaArgumenata listaArgumenata = new ListaArgumenata(trenutniCvor.getDjeca().get(2));
			listaArgumenata.provjeri();
			
			if(!postfiksIzraz.getTip().startsWith("funkcija"))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			
			String[] paramTip = postfiksIzraz.getTip().substring("funkcija(".length(),
					postfiksIzraz.getTip().indexOf(" ->")).split(",");
			
			if(paramTip.length != listaArgumenata.getTipovi().size())
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			
			for(int i = 0; i < paramTip.length; i++) {
				if(!SemantickiAnalizator.implicitnaPretvorba.
						get(listaArgumenata.getTipovi().get(i)).
						contains(paramTip[i].trim())) 
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
			
			tip = postfiksIzraz.getTip().substring(postfiksIzraz.getTip().indexOf("-> ") + 4);
			l_izraz = false;
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> OP_INC") ||
				desnaStranaProdukcije.equals("<postfiks_izraz> OP_DEC")) {
			
			PostfiksIzraz postfiksIzraz = new PostfiksIzraz(trenutniCvor.getDjeca().get(0)); 
					
			postfiksIzraz.provjeri();
			
			if(postfiksIzraz.isL_izraz() && 
					SemantickiAnalizator.implicitnaPretvorba.get(postfiksIzraz.getTip()).contains("int")) {
				tip = "int";
				l_izraz = false;
			}
			
			else SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
		}
		
		
	}
	
}
