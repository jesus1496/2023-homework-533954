package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	
	private IO io;
	private String parametro;
	private static final String NOME = "Abstract Comando";
	
	abstract public void esegui(Partita partita);
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public IO getIo() {
		return this.io;
	}
	
	@Override
	public void setIo(IO io) {
		this.io = io;
	}
	
	@Override
	public String getNome() {
		return NOME;
	}
}
