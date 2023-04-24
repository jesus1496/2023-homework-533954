package it.uniroma3.diadia;

 import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//serve per inportare vari metodi statici del framework che permettono di fare asserzioni

import org.junit.Before;
import org.junit.Test; //serve per importare l'annotazione del framework
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private Partita partita;
	
	@Before
	public void setUp() {
		this.partita = new Partita();
	}

	//testa che la stanza vincente di una partita non deve essere null
	@Test  //utile a marcare i metodi i test-case
	public void testGetStanzaVincenteNotNull() {
		assertNotNull(this.partita.getStanzaVincente());
	}
	
	//testa se la mia stanzaCorrente è una stanzaVincente 
	@Test
	public void testVintaSeStanzaCorrenteEVincente() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testVintaSeStanzaCorrenteENonVincente() {
		this.partita.setStanzaCorrente(new Stanza("Non vincente"));
		assertFalse(this.partita.vinta());
	}
	
	//test fa vedere che la partita non sia già vinta quando viene istanziata
	@Test
	public void testNonVintaInizioPartita() {
		assertFalse(this.partita.vinta());
	}
	
	@Test 
	public void testFinitaSeVinta() {
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente());
		assertFalse(this.partita.isFinita());
	}
	
	//testo se le imposto io che la partita è finita
	@Test 
	public void testFinitaEsplicitamenteSettato() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	//se cfu è 0 la partita è finita
	@Test
	public void testFinitaSeCFUfiniti() {
		this.partita.setCfu(0);
		assertFalse(this.partita.isFinita());
	}
	
	//quando inizia la partita non deve essere già finita
	@Test
	public void testNonFinitaInizioPartita() {
		assertFalse(this.partita.isFinita());
	}
}
