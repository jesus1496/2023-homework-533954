package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public interface Comando {
	/**
	 * 
	 * esecuzione del comando
	 * 
	 */
	public void esegue(Partita partita);
	
	/**
	 * 
	 * set parametro del comando
	 * 
	 */
	public void setParametro(String parametro);
	
	public void setIo(IOConsole io);
	
	public String getParametro();
	
	public String getNome();
}
