package Mozbola.Mocambola.project;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Clube implements Serializable{

	@Id
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	@NotBlank
	@Column(unique = true)
	private String nome;
	@NotBlank
	private String localizacao;
	@Column(unique = true)
	private String treinador;
	@NotBlank
	private String fundacao;
	private String dono;
	@Column(name = "picByte", length = 100000000)
	private byte[] picByte;
	


	
	
	public Clube() {
		
	}

	public Clube(@NotBlank String nome, @NotBlank String localizacao, String treinador, @NotBlank String fundacao,
			String dono, byte[] picByte) {
		
		this.nome = nome;
		this.localizacao = localizacao;
		this.treinador = treinador;
		this.fundacao = fundacao;
		this.dono = dono;
		this.picByte = picByte;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getTreinador() {
		return treinador;
	}

	public void setTreinador(String treinador) {
		this.treinador = treinador;
	}

	public String getFundacao() {
		return fundacao;
	}

	public void setFundacao(String fundacao) {
		this.fundacao = fundacao;
	}

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	
	@Override
	public String toString() {
		return "Clube [codigo=" + codigo + ", nome=" + nome + ", localizacao=" + localizacao + ", treinador="
				+ treinador + ", fundacao=" + fundacao + ", dono=" + dono + ", picByte=" + Arrays.toString(picByte)
				+  "]";
	}
	
	
	
	
}
