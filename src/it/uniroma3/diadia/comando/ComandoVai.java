package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	
	private IO io;
	private String parametro;
	private final static String NOME = "vai";
	
	/**
	 * 
	 * esecuzione del comando
	 * 
	 */
	@Override
	public void esegue(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(this.parametro == null) {
			System.out.println("Dove vuoi andare? Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(parametro);
		if(prossimaStanza  == null) {
			System.out.println("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		System.out.println(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	@Override
	public String getParametro() {
		return parametro;
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
