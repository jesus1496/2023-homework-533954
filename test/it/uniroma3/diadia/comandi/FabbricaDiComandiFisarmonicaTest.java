package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comando.Comando;
import it.uniroma3.diadia.comando.ComandoFine;
import it.uniroma3.diadia.comando.ComandoNonValido;
import it.uniroma3.diadia.comando.ComandoVai;
import it.uniroma3.diadia.comando.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	private Comando expected;

	@Before
	public void setUp() throws Exception{
		io = new IOConsole();
		fabbrica = new FabbricaDiComandiFisarmonica(io);
	}
	
	@Test
	public void testComandoNonValido() {
		expected = new ComandoNonValido();
		assertEquals(expected.getNome(), fabbrica.costruisciComando("pippo").getNome());
	}
	
	@Test
	public void testComandoConParametro() {
		expected = new ComandoVai();
		expected.setParametro("nord");
		Comando current = fabbrica.costruisciComando("vai nord");
		assertEquals(expected.getNome(), current.getNome());
		assertEquals(expected.getParametro(), current.getParametro());
	}
	
	@Test
	public void testComandoSenzaParametro() {
		expected = new ComandoFine();
		assertEquals(expected.getNome(), fabbrica.costruisciComando("fine").getNome());
	}

}
