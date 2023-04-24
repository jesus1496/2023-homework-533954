package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	public static final String DESCRIZIONE_STANZA_BLOCCATA = "La stanza è bloccata";
	
	private String direzioneBloccata;
	private String attrezzoSblocante;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSblocante) {
		super(nome);
		this.attrezzoSblocante = attrezzoSblocante;
		this.direzioneBloccata = direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		//posso andare alla stanza adiacente se la direzione non è bloccata e se ho l'attrezzo nella stanza che mi serve per sbloccarla
		if(direzione.equals(direzioneBloccata) && !super.hasAttrezzo(attrezzoSblocante))
			return this;
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		return DESCRIZIONE_STANZA_BLOCCATA;
	}
}
