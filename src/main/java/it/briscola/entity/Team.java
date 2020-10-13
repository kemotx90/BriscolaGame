package it.briscola.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "team")
public class Team implements Serializable{

	private static final long serialVersionUID = 1722535582693695408L;
	
	private Long id_team;
	private Giocatore giocatore1;
	private Giocatore giocatore2;

}
