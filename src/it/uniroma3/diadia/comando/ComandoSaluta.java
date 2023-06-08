package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio() != null) {
			this.getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		}else {
			this.getIo().mostraMessaggio("Non c'è alcun personaggio nella stanza!!");
		}
	}

}
