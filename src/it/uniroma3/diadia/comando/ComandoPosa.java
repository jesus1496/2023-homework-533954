package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	private String nomeAttrezzo;
	private final static String NOME = "posa";

	@Override
	public void esegui(Partita partita) {
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			this.getIo().mostraMessaggio("Attrezzo " + nomeAttrezzo + "non presente nella borsa");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		this.getIo().mostraMessaggio("Attrezzo" + attrezzo + "posato!");
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
