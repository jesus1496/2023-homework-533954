package it.uniroma3.diadia;

public class IOSimulator implements IO {
	
	private String[] righeLette;
	private int indiceRigheLette;
	private String[] messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;
	
	public IOSimulator(String[] righeDaLeggere) {
		this.righeLette = righeDaLeggere;
		this.indiceRigheLette = 0;
		this.indiceMessaggiMostrati =  0;
		this.messaggiProdotti = new String[42*23];
	}
	
	public String[] getMessaggiProdotti() {
		return messaggiProdotti;
	}
	
	public void setMessaggiProdotti(String[] messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}
	
	@Override
	public String leggiRiga() {
		String riga = null;
		riga = this.righeLette[indiceRigheLette];
		this.indiceRigheLette++;
		return riga;
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiProdotti[indiceMessaggiProdotti] = msg;
		this.indiceMessaggiProdotti++;
	}
	
	public String nextMessaggio() {
		String next = this.messaggiProdotti[this.indiceMessaggiMostrati];
		this.indiceMessaggiMostrati++;
		return next;
	}
	
	public boolean hasNextMessaggio() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}
}
