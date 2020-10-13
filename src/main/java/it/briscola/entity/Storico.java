package it.briscola.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "storico")
public class Storico implements Serializable{

	private static final long serialVersionUID = -5391834464598874024L;
	
	private Long id_storico;
	private Team teamBlu;
	private Team teamRosso;
	private Team teamVincente;
	private Integer scoreTeamBlu;
	private Integer scoreTeamRosso;

}
