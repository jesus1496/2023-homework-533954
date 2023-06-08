package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	
	private static final String NOME = "fine";
	public static final String MESSAGGIO_FINE = "Grazie di aver giocato!";

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio(MESSAGGIO_FINE);
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
