package it.briscola.utility;

import java.util.List;

import it.briscola.Enum.Seme;
import it.briscola.Enum.TipoCarta;
import it.briscola.classi.Carta;

public interface GameMechanism {

	public Integer valoreCarta(Carta carta, Seme briscola) throws Exception;
	public Integer puntiCarta(Carta carta) throws Exception;
	public Carta pescaCarta(List<Carta> mazzoCarteTavolo) throws Exception;
	
}
