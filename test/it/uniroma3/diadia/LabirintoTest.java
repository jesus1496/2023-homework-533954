package it.uniroma3.diadia;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {
	
	private Labirinto labirinto;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto = Labirinto.newBuilder("labirinto2.txt").getLabirinto();	
	}
	
	@Test
	public void testGetStanzaIniziale() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}

}
