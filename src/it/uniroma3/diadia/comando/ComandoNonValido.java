package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	private static final String NOME = "non_valido";

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Comando non valido !");
	}
	
	public String getNome() {
		return NOME;
	}

}
