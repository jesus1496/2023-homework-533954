package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "posa";

	@Override
	public void esegue(Partita partita) {
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("Attrezzo " + nomeAttrezzo + "non presente nella borsa");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		io.mostraMessaggio("Attrezzo" + attrezzo + "posato!");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public void setIo(IO io) {
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
