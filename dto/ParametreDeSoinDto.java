package com.integrys.backend.dto;

import java.util.Date;

import com.integrys.backend.entities.ParametreSoin;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ParametreDeSoinDto {
	private String poids;
	private String temperature;
	private String imc;
	private String taille;
	private Date dateDePriseDeParametre;

	
	public ParametreSoin toParametreSoin() {
		return new ParametreSoin(null, this.poids, this.temperature, this.imc, this.taille, this.dateDePriseDeParametre, null, null);
	}
}
