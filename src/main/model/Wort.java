package model;

public class Wort {
	private String artikel;
	private String name;
	private String plural;

	public Wort(String artikel, String name, String plural) {
		this.artikel = artikel;
		this.name = name;
		this.plural = plural;
	}

	public String getArtikel() {
		return artikel;
	}

	public void setArtikel(String artikel) {
		this.artikel = artikel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlural() {
		return plural;
	}

	public void setPlural(String plural) {
		this.plural = plural;
	}

}
