package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private static final int PESO_MAX_BORSA = 10;
	private static final String NOME_ATTREZZO = "attrezzoTest";
	private static final int PESO_ATTREZZO = 2;
	
	private Borsa borsa;

	@Before
	public void setUp() {
		this.borsa = new Borsa(PESO_MAX_BORSA);
	}

	@Test 
	public void testIsEmptyBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmptyBorsaNonVuota() {
		creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPesoMaxBorsa() {
		assertEquals(PESO_MAX_BORSA, this.borsa.getPesoMax());
	}

	@Test
	public void testGetPesoBorsaPiena() {
		for(int i = 0; i < 5; i++)
			creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO + i, PESO_ATTREZZO);
		assertEquals(PESO_MAX_BORSA, this.borsa.getPeso());
	}
	
//	@Test
//	public void testNumeroAttrezziInizialmenteZero() {
//		assertEquals(0, this.borsa.getNumeroAttrezzi());
//	}
//	
//	@Test
//	public void tesGetNumeroAttrezziUnAttrezzo() {
//		creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
//		assertEquals(1, this.borsa.getNumeroAttrezzi());
//	}
	
	@Test
	public void testAddAttrezzoBorsaVuota() {
		assertTrue(this.borsa.addAttrezzo(new Attrezzo(NOME_ATTREZZO, PESO_ATTREZZO)));
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		for(int i = 0; i < 5; i++)
			creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO + i, PESO_ATTREZZO);
		Attrezzo daNonAggiungere = new Attrezzo("daNonAggiungere", PESO_ATTREZZO);
		assertFalse(this.borsa.addAttrezzo(daNonAggiungere));
		assertFalse(this.borsa.hasAttrezzo("daNonAggiungere"));
	}
	
	@Test
	public void testAddAttrezzoTroppoPesante() {
		Attrezzo troppoPesante = new Attrezzo("troppoPesante", 20);
		assertFalse(this.borsa.addAttrezzo(troppoPesante));
		assertFalse(this.borsa.hasAttrezzo("troppoPesante"));
	}
	
	@Test
	public void testGetAttrezzoEsistente() {
		Attrezzo attrezzo = creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
		assertEquals(attrezzo, this.borsa.getAttrezzo(NOME_ATTREZZO));
	}
	
	@Test
	public void testGetAttrezzoNonEsistente() {
		assertNull(this.borsa.getAttrezzo(NOME_ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoEsistente() {
		creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
		assertTrue(this.borsa.hasAttrezzo(NOME_ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoNonEsistente() {
		creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
		assertFalse(this.borsa.hasAttrezzo("nonPresente"));
	}
	
	@Test
	public void testRemoveAttrezzoEsistente() {
		creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
		assertTrue(this.borsa.hasAttrezzo(NOME_ATTREZZO));
		this.borsa.removeAttrezzo(NOME_ATTREZZO);
		assertFalse(this.borsa.hasAttrezzo(NOME_ATTREZZO));
	}
	
	@Test 
	public void testRemoveAttrezzoNonEsistente() {
		creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
		assertNull(this.borsa.removeAttrezzo("nonPresente"));
	}
	
	@Test
	public void testGetDescrizioneBorsa() {
		creaEdAggiungiAttrezzoBorsa(this.borsa, NOME_ATTREZZO, PESO_ATTREZZO);
		assertEquals("Contenuto borsa (2kg/10kg): attrezzoTest (2kg) ", this.borsa.toString());
	}
	
	
	/* Metodo che crea ed aggiunge un attrezzo
	 * nella borsa, prendendo come parametri la borsa, il nome
	 * ed il peso dell'attrezzo e restituise un riferimento 
	 * all'attrezo creato*/
	public Attrezzo creaEdAggiungiAttrezzoBorsa(Borsa borsa, String nomeAttrezzo, int pesoAtterezzo) {
		Attrezzo attrezzoDaAggiungere = new Attrezzo(nomeAttrezzo, pesoAtterezzo);
		borsa.addAttrezzo(attrezzoDaAggiungere);
		return attrezzoDaAggiungere;
	}
}
