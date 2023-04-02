package diadia;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class StanzaTest {
	
	private static final String STANZA = "StanzaTest";
	private static final String STANZA_ADIACENTE = "Stanza_Adiacente";
	private static final String NORD = "nord";

	
	protected Stanza stanza;
	
	@Before
	public void setUp() {
		this.stanza = new Stanza(STANZA);
	}
	
	@Test
	public void testImpostaStanzaAdiacenteSingola() {
		Stanza adiacente = creaStanzaEImpostaAdiacente(stanza, STANZA_ADIACENTE, NORD);
		assertEquals(adiacente, this.stanza.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testCambiaStanzaAdiacente() {
		creaStanzaEImpostaAdiacente(stanza, STANZA_ADIACENTE, NORD);
		Stanza nuova = this.creaStanzaEImpostaAdiacente(stanza, "Nuova Adiacente", NORD);
		assertEquals(nuova, this.stanza.getStanzaAdiacente(NORD));
	}
	
	// non ho messo nessuna stanza adiacente dentro Stanza, vado a testare che alla prima chiamata non ci sia
	@Test
	public void testStanzaAdiacenteNonEsistente() {
		assertNull(this.stanza.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testStanzaAdiacenteNonNull() {
		this.creaStanzaEImpostaAdiacente(stanza, "StanzaAdicente", NORD);
		assertNotNull(this.stanza.getStanzaAdiacente(NORD));
	}
	
	// test l'array delle direzioni, che deve essere vuoto quando viene istanziata la istanza
	@Test 
	public void testGetDirezioniVuoto() {
		assertArrayEquals(new String[0], this.stanza.getDirezioni());
	}
	
	@Test
	public void testGetDirezioneSingleton() {
		this.creaStanzaEImpostaAdiacente(stanza, STANZA_ADIACENTE, NORD);
		String[] direzioni = new String[1];
		direzioni[0] = NORD;
		assertEquals(direzioni, this.stanza.getDirezioni());
	}
	
	private Stanza creaStanzaEImpostaAdiacente(Stanza stanzaDiPartenza, String nomeStanzaAdiacente, String direzione) {
		Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente); //instanzo una nuova stanza
		stanzaDiPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente); // imposto la stanzaAdiacente come quella di partenza
		return stanzaAdiacente; // poi la restituisco
	}
}
