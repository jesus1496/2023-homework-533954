package it.uniroma3.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_CANE = "Bau Bau, ti ho tolto i CFU";
	private static final String CIBO_PREFERITO = "osso";
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg = MESSAGGIO_CANE;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risposta = new StringBuilder("Bau grazier per avermi regalato");
		
		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			risposta.append("il mio cibo preferito!");
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("collana",2));
		} else {
			risposta.append(attrezzo.getNome()+", ma non è il mio cibo preferito Bau");
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		}
		return risposta.toString();
	}
}
