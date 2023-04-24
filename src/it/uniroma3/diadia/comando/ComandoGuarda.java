package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	private IOConsole io;
	private static final String NOME = "guarda";

	@Override
	public void esegue(Partita partita) {
		io.mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().toString());
		io.mostraMessaggio("Informazione partita: " + partita.getGiocatore().toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIo(IOConsole io) {
		this.io = io;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNome() {
		return NOME;
	}

}