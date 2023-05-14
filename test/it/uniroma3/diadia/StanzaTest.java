package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class StanzaTest {
	
	private static final String ATTREZZO = "attrezzoSemplice";
	private static final String SECONDO_ATTREZZO = "secondoAttrezzo";
	private Borsa borsa;
	private static final int PESO_MAX_BORSA = 20;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa(PESO_MAX_BORSA);
	}
	
	//aggiunge l'attrezzo alla stanza e va a testare che quel attrezzo sia presente nella stanza
	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzo = this.creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO, 1);
		assertEquals(attrezzo, this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testAttrezzoTroppoPesante() {
		Attrezzo attrezzoPesante = new Attrezzo("ttrezzoPesante", PESO_MAX_BORSA+1);
		assertFalse(this.borsa.addAttrezzo(attrezzoPesante));
	}
	
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testGetAttrezzoInesistente() {
		creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO, 1);
		assertNull(this.borsa.getAttrezzo("inesistente"));
	}

	@Test
	public void testGetPesoMax() {
		assertEquals(PESO_MAX_BORSA, this.borsa.getPesoMax());
	}

	@Test
	public void testGetPesoIniziale() {
		assertEquals(0, this.borsa.getPeso());
	}

	@Test
	public void testGetPesoDopoAggiuntaAttrezzo() {
		creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO, 1);
		assertEquals(1, this.borsa.getPeso());
	}
	
	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo(ATTREZZO));
	}
	
	//
	@Test
	public void testHasAttrezzoEsistente() {
		this.creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO, 1);
		assertTrue(this.borsa.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoInesistente() {
		creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO, 1);
		assertFalse(this.borsa.hasAttrezzo("inesistente"));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		creaAttrezzoEAggiungiInBorsa(this.borsa, SECONDO_ATTREZZO,2);
		creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO,1);
		List<Attrezzo> expected = Arrays.asList(new Attrezzo(ATTREZZO, 1), new Attrezzo(SECONDO_ATTREZZO, 2));
		assertEquals(expected, this.borsa.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		creaAttrezzoEAggiungiInBorsa(this.borsa, SECONDO_ATTREZZO,2);
		creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO,1);
		Set<Attrezzo> expected = new TreeSet<>(Arrays.asList(new Attrezzo(ATTREZZO, 1),
				new Attrezzo(SECONDO_ATTREZZO, 2)));
		assertEquals(expected, this.borsa.getContenutoOrdinatoPerNome());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		creaAttrezzoEAggiungiInBorsa(this.borsa, SECONDO_ATTREZZO,2);
		creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO,1);
		Map<Integer, Set<Attrezzo>> expected = new HashMap<>();
		expected.put(1, Collections.singleton(new Attrezzo(ATTREZZO, 1)));
		expected.put(2, Collections.singleton(new Attrezzo(SECONDO_ATTREZZO, 2)));
		assertEquals(expected, this.borsa.getContenutoRaggrupatoPerPeso());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		creaAttrezzoEAggiungiInBorsa(this.borsa, SECONDO_ATTREZZO,1);
		creaAttrezzoEAggiungiInBorsa(this.borsa, ATTREZZO,1);
		Set<Attrezzo> expected = new TreeSet<>(Arrays.asList(new Attrezzo(ATTREZZO, 1),
				new Attrezzo(SECONDO_ATTREZZO, 1)));
		assertEquals(expected, this.borsa.getSortedSetOrdinatoPerPeso());
	}
	
	private Attrezzo creaAttrezzoEAggiungiInBorsa(Borsa borsa, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		borsa.addAttrezzo(attrezzo);
		return attrezzo;
	}

}








