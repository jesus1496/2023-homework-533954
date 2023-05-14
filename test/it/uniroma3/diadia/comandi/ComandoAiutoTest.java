package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.comando.ComandoAiuto;
import it.uniroma3.diadia.comando.ComandoFine;
import it.uniroma3.diadia.fixture.Fixture;

public class ComandoAiutoTest {
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testPartitaConComandoAiuto() {
		String[] righeDaLeggere = {"aiuto", "fine"};
		IOSimulator io = Fixture.creaSimulazionePartitaEGioca(righeDaLeggere);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		for(int i = 0; i < ComandoAiuto.elencoComandi.length; i++) {
			assertTrue(io.hasNextMessaggio());
			assertEquals(ComandoAiuto.elencoComandi[i]+" ", io.nextMessaggio());
		}
		assertTrue(io.hasNextMessaggio());
		io.nextMessaggio();
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}

}
