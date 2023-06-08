package it.uniroma3.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	
	private static final String MESSAGGIO_SALUTATA = "Solo perche mi hai salutata, non ti mando in uno scantinato";
	
	private static final String MESSAGGIO_NON_SALUTATA = "Sei propria una brutta persona, vai nella stanza con meno attrezzi";
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		List<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getStanzaAdiacente();
		
		Stanza maxAttrezzi = stanzeAdiacenti.get(0);
		Stanza minAttrezzi = stanzeAdiacenti.get(0);
		
		for(Stanza s : stanzeAdiacenti) {
			if(s != null) {
				if(s.getNumeroAttrezzi() > maxAttrezzi.getNumeroAttrezzi()) 
					maxAttrezzi = s;
				if(s.getNumeroAttrezzi() < minAttrezzi.getNumeroAttrezzi())
					minAttrezzi = s;
			}
		}
		
		if(this.haSalutato()) {
			partita.setStanzaCorrente(maxAttrezzi);
			msg = MESSAGGIO_SALUTATA;
		}
		else {
			partita.setStanzaCorrente(minAttrezzi);
			msg = MESSAGGIO_NON_SALUTATA;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "HAHAHHAHHA";
	}
}
