package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	private String nomeAttrezzo;
	private static final String NOME = "prendi";

	@Override
	public void esegui(Partita partita) {
		if(!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			this.getIo().mostraMessaggio("Attrezzo " + nomeAttrezzo + " non presente");
			return;
		}
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		partita.getStanzaCorrente().removeAttrezzo(attrezzo);
		this.getIo().mostraMessaggio("Attrezzo" + nomeAttrezzo + "preso!");
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
