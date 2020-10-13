package it.briscola.utility;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.briscola.Enum.Seme;
import it.briscola.Enum.TipoCarta;
import it.briscola.classi.Carta;

public class GameMechanismImpl implements GameMechanism{
	
	@Autowired 
	private Logger log;

	@Override
	public Integer valoreCarta(Carta carta, Seme briscola) throws Exception{
		if(carta == null) {
			log.error("Nessun valore per una carta Nulla");
			throw new Exception("Errore. Controllare La carta Selezionata");
		}
		
		if(briscola == null) {
			log.error("Nessun valore per una briscola Nulla");
			throw new Exception("Errore. Controllare La briscola Selezionata");
		}
		
		Integer valoreCarta = 0;
		TipoCarta tipoCarta = carta.getTipoCarta();
		
		switch(tipoCarta) {
		case Asso:
			valoreCarta = 12;
		case Due:
			valoreCarta = 3;
		case Tre:
			valoreCarta = 11;
		case Quattro:
			valoreCarta = 4;
		case Cinque:
			valoreCarta = 5;
		case Sei:
			valoreCarta = 6;
		case Sette:
			valoreCarta = 7;
		case Fante:
			valoreCarta = 8;
		case Cavallo:
			valoreCarta = 9;
		case Re:
			valoreCarta = 10;
		}

		
		if(briscola.compareTo(carta.getSeme()) == 0) {
			valoreCarta = valoreCarta + 10;
		}
		return valoreCarta;
	}

	@Override
	public Integer puntiCarta(Carta carta) throws Exception {
		if(carta == null) {
			log.error("Nessun punteggio per una carta Nulla");
			throw new Exception("Errore. Controllare La carta Selezionata");
		}
		
		Integer punteggioCarta = 0;
		
		TipoCarta tipoCarta = carta.getTipoCarta();
		switch(tipoCarta) {
		case Asso:
			punteggioCarta = 11;
		case Tre:
			punteggioCarta = 10;
		case Re:
			punteggioCarta = 4;
		case Cavallo:
			punteggioCarta = 3;
		case Fante:
			punteggioCarta = 2;
		default:
			punteggioCarta = 0;
		}
		
		return punteggioCarta;
	}

	@Override
	public Carta pescaCarta(List<Carta> mazzoCarteTavolo) throws Exception{		
		Carta cartaPescata = generaCartaRandom(mazzoCarteTavolo);
		if(!mazzoCarteTavolo.contains(cartaPescata)) {
			pescaCarta(mazzoCarteTavolo);
		}
		
		return cartaPescata;
	}

	
	private int randomInteger(int maxRandomNumber) {
		Random randomNum = new Random();
		return randomNum.nextInt(maxRandomNumber);
	}

	
	private Carta generaCartaRandom(List<Carta> mazzoCarte) throws Exception{
		if(mazzoCarte == null) {
			log.error("Impossibile generare un valore random da un mazzo di carte null");
			throw new Exception("Impossibile generare una carta random");
		}
		
		if(mazzoCarte.size() == 0) {
			log.error("Impossibile generare un numero random da un mazzo di carte vuoto");
			throw new Exception("Carte terminate");
		}
		
		int randomNumber = randomInteger(mazzoCarte.size());
		Seme seme = mazzoCarte.get(randomNumber).getSeme();
		TipoCarta tipoCarta = mazzoCarte.get(randomNumber).getTipoCarta();
		return new Carta(seme, tipoCarta);
	}

}
