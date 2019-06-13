package testJSF;

import java.io.Serializable;

public class Zug implements Serializable {
	private String name;
	private int anzahl;
	
	public Zug(String name, int anzahl) {
		this.name = name;
		this.anzahl = anzahl;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
}
