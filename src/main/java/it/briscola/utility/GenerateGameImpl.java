package it.briscola.utility;

import java.util.ArrayList;
import java.util.List;

import it.briscola.Enum.Seme;
import it.briscola.Enum.TipoCarta;
import it.briscola.classi.Carta;

public class GenerateGameImpl implements GenerateGame{

	@Override
	public List<Carta> generaMazzoPerGioco() {
		List<Carta> mazzoCarte = new ArrayList<Carta>();
		
		for(Seme semeGioco : Seme.values()) {
			for(TipoCarta tipoCarta : TipoCarta.values()) {
				Carta carta = new Carta(semeGioco, tipoCarta);
				mazzoCarte.add(carta);
			}
		}
		
		return mazzoCarte;
	}

}
