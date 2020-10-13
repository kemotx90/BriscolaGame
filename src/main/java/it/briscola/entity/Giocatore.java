package it.briscola.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import it.briscola.classi.Carta;
import lombok.Data;

@Data
@Entity
@Table(name = "giocatore")
public class Giocatore implements Serializable{
	
	private static final long serialVersionUID = -8110655105222192259L;
	
	private Long id_giocatore;
	private String nome;
	private List<Carta> carteInMano;
	private List<Carta> mazzoPreso;
	private Integer punteggio;
	
}
