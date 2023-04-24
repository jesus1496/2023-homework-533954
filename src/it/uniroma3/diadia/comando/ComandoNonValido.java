package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	
	private IOConsole io;
	private static final String NOME = "non_valido";

	@Override
	public void esegue(Partita partita) {
		io.mostraMessaggio("Comando non valido !");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}
	
	public void setIo(IOConsole io) {
		this.io = io;
	}
	
	public String getParametro() {
		return null;
	}
	
	public String getNome() {
		return NOME;
	}

}
