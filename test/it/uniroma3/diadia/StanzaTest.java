package it.uniroma3.diadia;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private static final String ATTREZZO = "AttrezzoDiTest";
	private static final String STANZA = "StanzaTest";
	private static final String STANZA_ADIACENTE = "StanzaAdiacente";
	private static final String NORD = "nord";
	
	protected Stanza stanza;
	
	@Before
	public void setUp() {
		this.stanza = new Stanza(STANZA);
	}

	@Test
	public void testImpostaStanzaAdiacenteSingola() {
		Stanza adiacente = creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		assertEquals(adiacente, this.stanza.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testCambiaStanzaAdiacente() {
		creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		Stanza nuova = creaStanzaEImpostaAdiacente(this.stanza,"Nuova Adiacente", NORD);
		assertEquals(nuova, this.stanza.getStanzaAdiacente(NORD));
	}
	
	//testare questo metodo che non ha inserito la nuova direzione quindi la nuova
	@Test
	public void testImportaMassimo4Stanze() {
		Stanza adiacente = new Stanza(STANZA_ADIACENTE);
		String[] direzioni = {"nord", "sud", "ovest", "est"};
		for(String direzione: direzioni)  //per andare a impostare la stanza adiacente
			this.stanza.impostaStanzaAdiacente(direzione, adiacente);
		
		String direzioneNuova = "sud-ovest";
		creaStanzaEImpostaAdiacente(this.stanza,"Da non inserire",direzioneNuova);
		
		assertNotContains(this.stanza.getDirezioni(),direzioneNuova);
	}
	
	//con questo metodo sono andato a capire se nel mio array è contenuta la direzione nuova, se contenuta
	//restituisce il fallimento del metodo 
	private void assertNotContains(String[] direzioni, String direzioneNuova) {
		boolean contiene = false;
		for(String direzione: direzioni)
			if(direzione != null && direzione.equals(direzioneNuova))
				contiene = true;
				
		assertFalse(contiene);
	}
	
	//non ho messo nessuna stanza adiacente dentro Stanza, vado a testare che alla prima chiamata non ci sia
	@Test
	public void testGetStanzaAdiacenteNonEsistente() {
		assertNull(this.stanza.getStanzaAdiacente(NORD));
	}
	
	//crea una stanza è verifica che quella stanza non sia null
	@Test
	public void testGetStanzaAdiacenteEsistente() {
		creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		assertNotNull(this.stanza.getStanzaAdiacente(NORD));
	}
	
	//crea una stanza adiacente, è in quella stanza adiacente non dovrei trovare nulla
	@Test
	public void testGetStanzaAdiacenteDirezioneNonValida() {
		creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		assertNull(this.stanza.getStanzaAdiacente("non valida"));
	}
	
	//testa l'arra delle direzioni , che deve essere vuoto quando viene istanziata la stanza
	@Test
	public void testGetDirezioniVuoto() {
		assertArrayEquals(new String[0], this.stanza.getDirezioni());
	}
	
	//testa tutti gli oggetti all'intero dell'array che siano equals
	@Test
	public void testGetDirezioniSingleton() {
		creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
		String[] direzioni = new String[1];
		direzioni[0] = NORD;
		assertEquals(direzioni, this.stanza.getDirezioni());
	}
	
	//aggiunge l'attrezzo alla stanza e va a testare che quel attrezzo sia presente nella stanza
	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzoSemplice = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzoSemplice);
		assertEquals(attrezzoSemplice, this.stanza.getAttrezzo(ATTREZZO));
	}
	
	//aggiunge l'attrezzo alla
	@Test
	public void testAddAttrezziOltreMassimo() {
		for(int i = 0; i < Stanza.NUMERO_MASSIMO_ATTREZZI; i++) {
			Attrezzo attrezzoSemplice = new Attrezzo(ATTREZZO+i, 1);
			assertTrue(this.stanza.addAttrezzo(attrezzoSemplice)); //aggiunge l'attrezzo alla stanza
		}
		Attrezzo attrezzoDiTroppo = new Attrezzo(ATTREZZO+Stanza.NUMERO_MASSIMO_ATTREZZI, 1);
		assertFalse(this.stanza.addAttrezzo(attrezzoDiTroppo));
	}
	
	@Test
	public void testHasAttrezzoSingolo () {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo(ATTREZZO));
	}
	
	//
	@Test
	public void testHasAttrezzoInesistente() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(attrezzo);
		assertFalse(this.stanza.hasAttrezzo("inesistente"));
	}

	//ho creato un metodo utilità
	private Stanza creaStanzaEImpostaAdiacente(Stanza stanzaDiPartenza,String nomeStanzaAdiacente, String direzione) {
		Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente); //istanzio una nuova stanza
		stanzaDiPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente); // imposta la stanza adiacente como stanza di partenza
		return stanzaAdiacente;                                              //poi la restituisco
	}

}








