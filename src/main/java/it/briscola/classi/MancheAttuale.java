package it.briscola.classi;

import it.briscola.entity.Giocatore;
import lombok.Data;

@Data
public class MancheAttuale {
	
	private Giocatore giocatore;
	private Carta cartaGiocata;

}
