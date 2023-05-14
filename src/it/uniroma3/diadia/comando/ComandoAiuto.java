package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	private IO io;
	static final public String[] elencoComandi = {"vai", "aiuto", "fine","prendi", "posa", "guarda"};
	private final static String NOME = "aiuto";

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
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}

}
