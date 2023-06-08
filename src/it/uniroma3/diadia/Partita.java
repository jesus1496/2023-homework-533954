package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;
	private Labirinto labirinto;  //riferimento ad un'istanza di labirinto
	private Giocatore giocatore;  //riferimento ad un'istanza di giocatore
	
	public Partita(Labirinto labirinto){
		this.labirinto = labirinto;
		giocatore = new Giocatore();
		this.finita = false;
		
	}
	
	public Labirinto getLabirinto() {
		return labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
    

	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.getLabirinto().setStanzaIniziale(stanzaCorrente);
	}

	public Stanza getStanzaCorrente() {
		return this.getLabirinto().getStanzaIniziale();
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() > 0;
	}
	
	
}
