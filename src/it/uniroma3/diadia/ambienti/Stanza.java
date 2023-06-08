package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final public int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    protected Map<String,Attrezzo> numero2attrezzo;
    private Map<Direzione,Stanza> direzione2StanzaAdiacente;
	private String[] direzioni;
	private int numeroAttrezzi;
	
	private AbstractPersonaggio personaggio;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nomeStanzaIniziale il nome della stanza
     */
    public Stanza(String nomeStanzaIniziale) {
        this.nome = nomeStanzaIniziale;
        this.numero2attrezzo = new HashMap<>();
        this.direzione2StanzaAdiacente = new HashMap<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
        if(this.direzione2StanzaAdiacente.size() >= NUMERO_MASSIMO_DIREZIONI)
        	return;
        this.direzione2StanzaAdiacente.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public List<Stanza> getStanzaAdiacente() {
        List<Stanza> listaStanzeAdiacenti = new ArrayList<>();
        for(Stanza s : direzione2StanzaAdiacente.values()) {
        	listaStanzeAdiacenti.add(s);
        }
        return listaStanzeAdiacenti;
	}
	
	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;
		if (this.direzione2StanzaAdiacente.containsKey(direzione))
			stanza = this.direzione2StanzaAdiacente.get(direzione);
		return stanza;
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
        return this.numero2attrezzo.values();
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if(this.numero2attrezzo.size() > NUMERO_MASSIMO_ATTREZZI)
        	return false;
        this.numero2attrezzo.put(attrezzo.getNome(), attrezzo);
        return true;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (int i = 0; i < this.numero2attrezzo.size(); i++) {
    		risultato.append(this.numero2attrezzo+" ");
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.numero2attrezzo.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param attrezzo2
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String attrezzo2) {
		return this.numero2attrezzo.get(attrezzo2);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		Attrezzo a = this.numero2attrezzo.remove(attrezzo.getNome());
		if(a== null)
			return false;
		return true;
	}


	public Set<Direzione> getDirezioni() {
		return this.direzione2StanzaAdiacente.keySet();
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public int getNumeroAttrezzi() {
		return numeroAttrezzi; 
	}

}