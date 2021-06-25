package it.polito.tdp.PremierLeague.model;

public class PlayerAndPeso {

	private Player player;
	private double peso;
	
	
	
	public PlayerAndPeso(Player player, double peso) {
		super();
		this.player = player;
		this.peso = peso;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "PlayerAndPeso [player=" + player + ", peso=" + peso + "]";
	}
	
	
	
	
}
