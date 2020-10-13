package it.briscola.classi;

import it.briscola.Enum.Seme;
import it.briscola.Enum.TipoCarta;
import lombok.Data;

@Data
public class Carta{

	private Seme seme;
	private TipoCarta tipoCarta;
	
	public Carta(Seme seme, TipoCarta tipoCarta) {
		this.seme = seme;
		this.tipoCarta = tipoCarta;
	}

}
