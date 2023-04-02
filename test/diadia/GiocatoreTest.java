package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {

	private static final String MESSAGGIO_BORSA_VUOTA = "CFU correnti: 10\nBorsa vuota";

	private static final int CFU = 10;
	
	private Giocatore giocatore;
	
	@Before
	public void setUp() {
		this.giocatore = new Giocatore();
		this.giocatore.setCfu(CFU);
	}

	@Test
	public void testGetCfuGiocatore() {
		assertEquals(CFU, this.giocatore.getCfu());
	}
	
	@Test
	public void testGetCfuInizioPartita() {
		assertNotEquals(0, this.giocatore.getCfu());
	}
	
	@Test
	public void testSetCFu() {
		this.giocatore.setCfu(4);
		assertEquals(4, this.giocatore.getCfu());
	}
	
	@Test
	public void testGetDescrizioneGiocatoreBorsaVuota() {
		assertEquals(MESSAGGIO_BORSA_VUOTA, this.giocatore.toString());
	}

}
