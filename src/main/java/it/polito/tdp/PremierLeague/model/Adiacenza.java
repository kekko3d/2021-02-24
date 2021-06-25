package it.polito.tdp.PremierLeague.model;

public class Adiacenza {

	private Integer id1;
	private Integer id2;
	private double peso;
	
	public Adiacenza(Integer id1, Integer id2, double peso) {
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
	}

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Adiacenza [id1=" + id1 + ", id2=" + id2 + ", peso=" + peso + "]";
	}
	
	
	
	
	

}
