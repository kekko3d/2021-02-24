package it.polito.tdp.PremierLeague.model;

public class Event implements Comparable<Event> {
	
	public enum EventType  {
		INIZIO, 
		GOAL,
		ESPULSIONE,
		INFORTUNIO
	}
	
	private Player player;
	private EventType type;
	private Integer Nsaliente;
	
	
	public Event(Player player, EventType type, Integer nsaliente) {
		super();
		this.player = player;
		this.type = type;
		Nsaliente = nsaliente;
	}



	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public EventType getType() {
		return type;
	}


	public void setType(EventType type) {
		this.type = type;
	}


	public Integer getNsaliente() {
		return Nsaliente;
	}


	public void setNsaliente(Integer nsaliente) {
		Nsaliente = nsaliente;
	}

	@Override
	public int compareTo(Event o) {
		return this.Nsaliente.compareTo(o.getNsaliente()) ;
	}



	@Override
	public String toString() {
		return "Event [player=" + player + ", type=" + type + ", Nsaliente=" + Nsaliente + "]";
	}
	
	
	
	
	
	
	

}
