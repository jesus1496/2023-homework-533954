package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comando.ComandoPrendi;

public class ComandoPrendiTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoPesante;
	private ComandoPrendi comandoPrendi;
	private IOConsole io;
	
	@Before
	public void setUp() throws Exception{
		partita = new Partita();
		attrezzo = new Attrezzo("martello", 2);
		attrezzoPesante = new Attrezzo("incudine", 11);
		comandoPrendi = new ComandoPrendi();
		io = new IOConsole();
		comandoPrendi.setIo(io);
	}
	
	public boolean attrezzoPresente(String s) {
		Collection<Attrezzo> array = partita.getStanzaCorrente().getAttrezzi();
		for(Attrezzo a: array) {
			if(a != null && s.equals(a.getNome()))
				return true;
		}
		return false;
	}
	
//	@Test
//	public void testAttrezzoPreso() {
//		partita.getStanzaCorrente().addAttrezzo(attrezzo);
//		comandoPrendi.setParametro("martello");
//		comandoPrendi.esegue(partita);
//		assertFalse(attrezzoPresente("martello"));
//	}
	
	@Test
	public void testAttrezzoNonPresente() {
		comandoPrendi.setParametro("martello");
		comandoPrendi.esegue(partita);
		assertFalse(attrezzoPresente("martello"));
	}
	
	@Test
	public void testAttrezzoPesante() {
		partita.getStanzaCorrente().addAttrezzo(attrezzoPesante);
		comandoPrendi.setParametro("incudine");
		comandoPrendi.esegue(partita);
		assertTrue(attrezzoPresente("incudine"));
	}
}
