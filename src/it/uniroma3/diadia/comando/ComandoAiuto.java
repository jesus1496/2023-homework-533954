package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	private IOConsole io;
	private String[] elencoComandi;
	private final static String NOME = "aiuto";
	
	public ComandoAiuto(String[] elencoComandi) {
		this.elencoComandi = elencoComandi;
	}

	@Override
	public void esegue(Partita partita) {
		for(int i=0;i < elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i] + " ");
		io.mostraMessaggio("");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
	}
	
	public String getParametro() {
		return null;
	}
	
	public String getNome() {
		return NOME;
	}
	
	public void setIo(IOConsole io) {
		this.io = io;
	}

}
