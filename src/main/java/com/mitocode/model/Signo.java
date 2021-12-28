/**
 * 
 */
package com.mitocode.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author gustavoefrainparcosanchez
 *
 */

@Entity
@Table(name = "signo")
public class Signo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSigno;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "temperatura", nullable = false, length = 10)
	private String temperatura;
	
	@Column(name = "pulso", nullable = false, length = 20)
	private String pulso;
	
	@Column(name = "ritmoRespitatorio", nullable = false, length = 20)
	private String ritmoRespitatorio;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPaciente", nullable = false)
	private Paciente paciente;
	
	
	public Integer getIdSigno() {
		return idSigno;
	}
	public void setIdSigno(Integer idSigno) {
		this.idSigno = idSigno;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public String getPulso() {
		return pulso;
	}
	public void setPulso(String pulso) {
		this.pulso = pulso;
	}
	public String getRitmoRespitatorio() {
		return ritmoRespitatorio;
	}
	public void setRitmoRespitatorio(String ritmoRespitatorio) {
		this.ritmoRespitatorio = ritmoRespitatorio;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
 

}
