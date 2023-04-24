package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;

public interface FabbricaDiComandi {

	public Comando costruisciComando(String istruzione, IOConsole io);
}
