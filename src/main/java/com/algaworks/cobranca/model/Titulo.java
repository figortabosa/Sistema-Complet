 package com.algaworks.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Titulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String nomeDoCulto;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double dizimos;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double ofertas;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double doacoes;
	
	@NumberFormat(pattern = "#,##0.00")
	private Double outros;
	
	@ManyToOne
	@ForeignKey(name = "usuario_fk")
	private Usuario usuario;
	
	public void soma() {
		this.total = this.dizimos + this.ofertas + 
				this.doacoes + this.outros;
	}
	@NumberFormat(pattern = "#,##0.00")
	private Double total ;
	
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNomeDoCulto() {
		return nomeDoCulto;
	}
	public void setNomeDoCulto(String nomeDoCulto) {
		this.nomeDoCulto = nomeDoCulto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public StatusTitulo getStatus() {
		return status;
	}
	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	public Double getDizimos() {
		return dizimos;
	}
	public void setDizimos(Double dizimos) {
		this.dizimos = dizimos;
	}
	public Double getOfertas() {
		return ofertas;
	}
	public void setOfertas(Double ofertas) {
		this.ofertas = ofertas;
	}
	public Double getDoacoes() {
		return doacoes;
	}
	public void setDoacoes(Double doacoes) {
		this.doacoes = doacoes;
	}
	public Double getOutros() {
		return outros;
	}
	public void setOutros(Double outros) {
		this.outros = outros;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
