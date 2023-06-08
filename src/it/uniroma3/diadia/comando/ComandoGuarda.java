package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	private static final String NOME = "guarda";

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().toString());
		this.getIo().mostraMessaggio("Informazione partita: " + partita.getGiocatore().toString());
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
