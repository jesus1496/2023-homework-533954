package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Stanza ultimaAggiunta;
	private Map<String, Stanza> nome2stanza;
	
	public LabirintoBuilder() {
		this.labirinto =  new Labirinto();
		this.nome2stanza = new HashMap<String,Stanza>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza iniziale = new Stanza(nomeStanzaIniziale);
		this.labirinto.setStanzaIniziale(iniziale);
		this.aggiungiMappaEAggiornaUltima(iniziale);
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nomeStanzaIniziale) {
		Stanza vincente = new Stanza(nomeStanzaIniziale);
		this.labirinto.setStanzaIniziale(vincente);
		this.aggiungiMappaEAggiornaUltima(vincente);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String partenza, String adiacente, String direzione) {
		Stanza stanzaPartenza = this.nome2stanza.get(partenza);
		Stanza stanzaAdiacente = this.nome2stanza.get(adiacente);
		stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo a = new Attrezzo(nome, peso);
		this.ultimaAggiunta.addAttrezzo(a);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza = new Stanza(nome);
		this.aggiungiMappaEAggiornaUltima(stanza);
		return this;
	}
	
	public void aggiungiMappaEAggiornaUltima(Stanza stanza) {
		this.ultimaAggiunta = stanza;
		this.nome2stanza.put(stanza.getNome(), stanza);
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
