package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private StanzaBloccata sb;
	private Stanza s;
	private Attrezzo a;
	
	@Before
	public void setUp() throws Exception{
		sb = new StanzaBloccata("La stanza è bloccata", Direzione.ovest, "grimaldello");
		s = new Stanza("Stanzetta");
		a = new Attrezzo("grimaldello", 1);
		sb.impostaStanzaAdiacente(Direzione.ovest, s);
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		assertEquals(sb, sb.getStanzaAdiacente(Direzione.ovest));
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		sb.addAttrezzo(a);
		assertEquals(s, sb.getStanzaAdiacente(Direzione.ovest));
	}

	@Test
	public void testGetDescrizioneDirezioneSbloccata() {
		sb.addAttrezzo(a);
		assertEquals(sb.toString(), sb.getDescrizione());
	}
}
