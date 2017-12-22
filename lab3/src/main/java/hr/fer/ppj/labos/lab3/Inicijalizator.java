package hr.fer.ppj.labos.lab3;

import java.util.ArrayList;
import java.util.List;

public class Inicijalizator implements CvorAtributnogStabla{
	
	private CvorGenerativnogStabla trenutniCvor;
	
	private List<String> tipovi;
	private int brElem;
	private String tip;
	
	public Inicijalizator(CvorGenerativnogStabla trenutniCvor) {
		this.trenutniCvor = trenutniCvor;
		this.tipovi = new ArrayList<>();
	}
	
	public void provjeri() {
		
	}

	public List<String> getTipovi() {
		return tipovi;
	}

	public void setTipovi(List<String> tipovi) {
		this.tipovi = tipovi;
	}

	public int getBrElem() {
		return brElem;
	}

	public void setBrElem(int brElem) {
		this.brElem = brElem;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
}
