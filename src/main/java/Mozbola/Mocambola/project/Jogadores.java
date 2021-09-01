package Mozbola.Mocambola.project;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Jogadores implements Serializable {

	@Id
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	@NotBlank
	@Column(unique = true)
	private String nome;
	@NotBlank
	private String posicao;
	@NotBlank
	private String datanatalicia;
	
	private float altura;
	@Column(unique = true)
	private String alcunha;
	@Column(name = "picByte", length = 100000000)
	private byte[] picByte;
	
	
	
	public Jogadores() {
	}

	public Jogadores(@NotBlank String nome, @NotBlank String posicao, @NotBlank String datanatalicia,
			@NotBlank float altura, String alcunha, byte[] picByte) {
		
		this.nome = nome;
		this.posicao = posicao;
		this.datanatalicia = datanatalicia;
		this.altura = altura;
		this.alcunha = alcunha;
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

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getDatanatalicia() {
		return datanatalicia;
	}

	public void setDatanatalicia(String datanatalicia) {
		this.datanatalicia = datanatalicia;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public String getAlcunha() {
		return alcunha;
	}

	public void setAlcunha(String alcunha) {
		this.alcunha = alcunha;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}


	@Override
	public String toString() {
		return "Jogadores [codigo=" + codigo + ", nome=" + nome + ", posicao=" + posicao + ", datanatalicia="
				+ datanatalicia + ", altura=" + altura + ", alcunha=" + alcunha + ", picByte="
				+ Arrays.toString(picByte) + "]";
	}
	
	
}
