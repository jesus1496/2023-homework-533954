package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	static final public int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	/**
	 * 
	 * @return il numero di CFU corrente
	 */
	public int getCfu() {
		return this.cfu;
	}
	
	/**
	 * aggiorna il numero di cfu
	 * @param cfu aggiornate
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	/**
	 * 
	 * @return la borsa del giocatore
	 */
	public Borsa getBorsa() {
		return borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
	
}
