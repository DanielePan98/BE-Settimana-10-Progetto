package it.gestionefilm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name = "trovaByRegista", query = "SELECT f FROM Film f where f.regista =?1")
@NamedQuery(name = "TrovaTutti", query = "from Film")
public class Film {

	private int id;
	private String titolo;
	private String incasso;
	private String regista;
	private int anno;
	private String tipo;




	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	public int getId() {
		return id;
	}
	@Column
	public String getTitolo() {
		return titolo;
	}
	@Column
	public String getIncasso() {
		return incasso;
	}
	@Column
	public String getRegista() {
		return regista;
	}
	@Column
	public int getAnno() {
		return anno;
	}
	@Column (name="genere")
	public String getTipo() {
		return tipo;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setIncasso(String incasso) {
		this.incasso = incasso;
	}
	public void setRegista(String regista) {
		this.regista = regista;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

