package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));
		partita.getGiocatore().getBorsa().removeAttrezzo(getNome());
	}

}
