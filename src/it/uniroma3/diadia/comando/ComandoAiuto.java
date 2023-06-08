package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	static final public String[] elencoComandi = {"vai", "aiuto", "fine","prendi", "posa", "guarda", "saluta", "interagisci", "regala"};
	private final static String NOME = "aiuto";

	@Override
	public void esegui(Partita partita) {
		for(int i=0;i < elencoComandi.length; i++)
			this.getIo().mostraMessaggio(elencoComandi[i] + " ");
		this.getIo().mostraMessaggio("");
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}

}
