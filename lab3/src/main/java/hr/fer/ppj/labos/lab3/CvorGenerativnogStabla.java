package hr.fer.ppj.labos.lab3;

import java.util.LinkedList;
import java.util.List;

public class CvorGenerativnogStabla {
	
	private String uniformniZnak;
	private int brojRetka;
	private String leksickaJedinka;
	private boolean ispisatiSamoUniformniZnak;
	private List<CvorGenerativnogStabla> djeca;
	
	private int brojBjelina;
	private int indeksLinije;
	
	public CvorGenerativnogStabla(String uniformniZnak, int brojRetka, String leksickaJedinka,
			boolean ispisatiSamoUniformniZnak, int brojBjelina, int indeksLinije) {
		this.uniformniZnak = uniformniZnak;
		this.brojRetka = brojRetka;
		this.leksickaJedinka = leksickaJedinka;
		this.ispisatiSamoUniformniZnak = ispisatiSamoUniformniZnak;
		djeca = new LinkedList<>();
		this.brojBjelina = brojBjelina;
		this.indeksLinije = indeksLinije;
	}

	public CvorGenerativnogStabla(String uniformniZnak, boolean ispisatiSamoUniformniZnak,
			int brojBjelina, int indeksLinije) {
		this(uniformniZnak, -1, null, ispisatiSamoUniformniZnak, 
				brojBjelina, indeksLinije);
	}
	
	public int getIndeksLinije() {
		return indeksLinije;
	}
	
	public void dodajDijete(CvorGenerativnogStabla dijete) {
		djeca.add(dijete);
	}

	public String getUniformniZnak() {
		return uniformniZnak;
	}

	public int getBrojRetka() {
		return brojRetka;
	}

	public String getLeksickaJedinka() {
		return leksickaJedinka;
	}

	public boolean isIspisatiSamoUniformniZnak() {
		return ispisatiSamoUniformniZnak;
	}

	
	public List<CvorGenerativnogStabla> getDjeca() {
		return djeca;
	}
	
	public int getBrojBjelina() {
		return brojBjelina;
	}
	
	public void setIspisatiSamoUniformniZnak(boolean jeNezavrsniZnak) {
		this.ispisatiSamoUniformniZnak = jeNezavrsniZnak;
	}

	@Override
	public String toString() {
		if (ispisatiSamoUniformniZnak)
			return uniformniZnak;
		else {
			StringBuilder sb = new StringBuilder();
			sb.append(uniformniZnak);
			sb.append(" ");
			sb.append(brojRetka);
			sb.append(" ");
			sb.append(leksickaJedinka);
			return sb.toString();
		}
	}
}
