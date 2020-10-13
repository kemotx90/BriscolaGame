package it.briscola.classi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import it.briscola.entity.Giocatore;
import it.briscola.utility.GameMechanism;
import it.briscola.utility.GenerateGame;

@Data
public class TavoloDaGioco {
	
	private static final Integer MAX_GIOCATORI = 4;
	private static final Integer MAX_CARTE_IN_MANO = 3;
	private List<Giocatore> giocatori;
	private List<Carta> mazzoDaGioco;
	
	@Autowired
	private GenerateGame generaGame;
	@Autowired
	private GameMechanism gameMech;
	@Autowired 
	private Logger log;
	
	public TavoloDaGioco() {
		giocatori = new ArrayList<Giocatore>();
		mazzoDaGioco = new ArrayList<Carta>();
		setMazzoCarteAlTavolo();
	}
	
	private void setMazzoCarteAlTavolo(){	
		List<Carta> mazzoCarte = generaGame.generaMazzoPerGioco();
		this.setMazzoDaGioco(mazzoCarte);
	}
	
	public void setGiocatori(Giocatore giocatore) throws Exception{
		if(giocatore == null) {
			log.error("Nessun giocatore inseribile nel tavolo");
			throw new Exception("Errore. Impossibile inserire il giocatore al tavolo");
		}
		
		if(giocatori.size() <= MAX_GIOCATORI) {
			giocatori.add(giocatore);
		}else {
			log.warn("Massimo numero di giocatori inseriti impossibile inserire ancora");
			throw new Exception("Hai inserito il numero massimo di giocatori consentiti");
		}
	}
	
	
	public Carta pescaCartaDalMazzo() throws Exception {
		Carta cartaPescata = gameMech.pescaCarta(this.mazzoDaGioco);
		
		if(cartaPescata == null) {
			log.error("CartaPescata e' null");
			throw new Exception("Nessuna carta pescata");
		}	
		
		if(!rimuoviCartaDalMazzo(cartaPescata)) {
			log.error("impossibile rimuovere la carta dal mazzo");
			throw new Exception("Carta non rimossa dal mazzo dopo la pesca");
		}
		
		return cartaPescata;
	}
	
	
	private boolean rimuoviCartaDalMazzo(Carta cartaDaRimuovere) throws Exception {
		if(this.getMazzoDaGioco() == null) {
			log.error("Impossibile pescare da questo Mazzo");
			throw new Exception("Errore. Impossibile pescare dal Mazzo");
		}
		
		if(this.getMazzoDaGioco().size() == 0) {
			throw new Exception("Carte Esaurite");
		}
		
		return this.getMazzoDaGioco().remove(cartaDaRimuovere);
	}
	
	
	public boolean daiCarteAiGiocatori() throws Exception{
		if(this.giocatori.size() < 2) {
			log.warn("Impossibile iniziare la partita in meno di 2 giocatori");
			throw new Exception("Impossibile iniziare la partita");
		}
		
		for(Giocatore giocatore : this.giocatori) {
			List<Carta> carteInMano = new ArrayList<Carta>();
			for(int i=0; i<MAX_CARTE_IN_MANO; i++) {
				carteInMano.add(pescaCartaDalMazzo());
			}
			giocatore.setCarteInMano(carteInMano);
			if(contaCarteInManoAlGiocatore(giocatore) < MAX_CARTE_IN_MANO) {
				return false;
			}
		}
		return true;
	}
	
	private int contaCarteInManoAlGiocatore(Giocatore giocatore) throws Exception{
		if(giocatore == null) {
			log.warn("Attenzione nessun giocatore Selezionato per la conta delle carte in mano");
			throw new Exception("impossibile contare le carte del giocatore");
		}
		
		int carteInManoAlGiocatore = giocatore.getCarteInMano().size();
		return carteInManoAlGiocatore;
	}

}
