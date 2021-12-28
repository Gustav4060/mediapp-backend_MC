/**
 * 
 */
package com.mitocode.dto;

import java.time.LocalDate;

import com.mitocode.model.Paciente;

/**
 * @author gustavoefrainparcosanchez
 *
 */
public class SignoDTO {
	
	private Integer idSigno;
	private LocalDate fecha;	
	private String temperatura;
	private String pulso;
	private String ritmoRespitatorio;
	private Integer idPaciente;
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
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	
	
	
}
