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
			PrimarniIzraz primarniIzraz = (PrimarniIzraz)
					Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			primarniIzraz.provjeri();
			tip = primarniIzraz.getTip();
			l_izraz = primarniIzraz.isL_izraz();
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> L_UGL_ZAGRADA <izraz> D_UGL_ZAGRADA")) {
			PostfiksIzraz postfiksIzraz = (PostfiksIzraz) 
					Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			
			List<String> X = Arrays.asList(new String[] {
					"char", "int", "const(char)", "const(int)"
			});
			
			if((!postfiksIzraz.getTip().startsWith("niz")) && !X.contains(postfiksIzraz.getTip().
					substring(3, postfiksIzraz.getTip().length() - 1)))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			
			Izraz izraz = (Izraz) Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(2));
			izraz.provjeri();
			
			if(!SemantickiAnalizator.implicitnaPretvorba.get(izraz.getTip()).
					contains("int")) SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			
			tip = postfiksIzraz.getTip().
					substring(3, postfiksIzraz.getTip().length() - 1);
			l_izraz = !tip.startsWith("const");
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> L_ZAGRADA D_ZAGRADA")) {
			PostfiksIzraz postfiksIzraz = (PostfiksIzraz) 
					Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			
			List<String> pov = Arrays.asList(new String[] {
				"char", "int", "void"	
			});
			
			if(!postfiksIzraz.getTip().startsWith("funkcija(void -> ") &&
					!pov.contains(postfiksIzraz.getTip().
							substring("funkcija(void -> ".length())))
				SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			tip = postfiksIzraz.getTip().
					substring("funkcija(void -> ".length());
			l_izraz = false;
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> L_ZAGRADA <lista_argumenata> D_ZAGRADA")) {
			
			PostfiksIzraz postfiksIzraz = (PostfiksIzraz) 
					Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
			postfiksIzraz.provjeri();
			
			ListaArgumenata listaArgumenata = (ListaArgumenata) 
					Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(2));
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
						contains(paramTip[i])) 
					SemantickiAnalizator.ispisiGreskuUProdukciji(trenutniCvor);
			}
			
			tip = postfiksIzraz.getTip().substring(postfiksIzraz.getTip().indexOf("-> ") + 4);
			l_izraz = false;
		}else if(desnaStranaProdukcije.equals("<postfiks_izraz> OP_INC") ||
				desnaStranaProdukcije.equals("<postfiks_izraz> OP_DEC")) {
			
			PostfiksIzraz postfiksIzraz = (PostfiksIzraz) 
					Tvornica.napraviAtributniCvor(trenutniCvor.getDjeca().get(0));
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
