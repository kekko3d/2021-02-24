package it.polito.tdp.PremierLeague.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private SimpleDirectedWeightedGraph <Player, DefaultWeightedEdge> grafo;
	private Map<Integer,Player> idMap;
	
	private Integer espulsioneHome;
	private Integer espulsioneAway;
	private Integer PunteggioHome;
	private Integer PunteggioAway;
	PriorityQueue<Event> queue;

	
	public Model() {
		dao = new PremierLeagueDAO();
		PunteggioHome = 0;
		PunteggioAway = 0;
		espulsioneHome = 0;
		espulsioneAway = 0;
	}
	
	public void creaGrafo(Integer idMatch) {
		this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

		idMap = new HashMap <Integer, Player> () ;

		for(Player a : dao.listPlayerMatch(idMatch)) {
			idMap.put(a.getPlayerID(), a);
		}

		Graphs.addAllVertices(grafo, idMap.values());
		for(Adiacenza e : dao.Adiacenze(idMatch)) {
			
			if(idMap.containsKey(e.getId1())
					&& idMap.containsKey(e.getId2())){
				
				//questo if e il seguente servono a non aggiungere lo stesso arco più volte
				if(this.grafo.containsVertex(idMap.get(e.getId1())) ||
						this.grafo.containsVertex(idMap.get(e.getId2()))) {
					
					if(e.getPeso() > 0) {
						Graphs.addEdgeWithVertices(this.grafo, idMap.get(e.getId1()), idMap.get(e.getId2()), e.getPeso());
					
					} else {
						Graphs.addEdgeWithVertices(this.grafo, idMap.get(e.getId2()), idMap.get(e.getId1()), e.getPeso() * (-1));
					}
					
				}
			}
		}
	}
	
	public PlayerAndPeso getBestPlayer() {
				PlayerAndPeso result = null;
		double peso = 0.0;
		double bestPeso = 0.0;

		for(Player p : grafo.vertexSet()) {
			peso = 0.0;
			for(DefaultWeightedEdge  e : grafo.edgesOf(p)) {
				peso += this.grafo.getEdgeWeight(e);
			}
			if(bestPeso < peso) {
				bestPeso = peso;
				result = new PlayerAndPeso(p, bestPeso);
			}
		}
		return result;
	}
	
	public void sim(Integer N, Integer idMatch) {
		queue = new PriorityQueue <Event> ();
		Match m = null;
		for(Match m1 : dao.listAllMatches()) {
			if(m1.matchID == idMatch) {
				m = m1;
			}
		}
		
		System.out.println(m);
		for(int i =0; i< N; i++) {
			double prob = Math.random();
			
			if(prob < 0.5) {
				if((m.getTeamHomeFormation() - espulsioneHome) < (m.getTeamAwayFormation() - espulsioneAway)) {
					PunteggioAway++;
				} else if((m.getTeamHomeFormation() - espulsioneHome) > (m.getTeamAwayFormation() - espulsioneAway)) {
					PunteggioHome++;
				} else {
					PlayerAndPeso best = getBestPlayer();
					if(dao.getId(m, best.getPlayer()) == m.getTeamAwayID()) 
						PunteggioAway++;
					
					if(dao.getId(m, best.getPlayer()) == m.getTeamHomeID()) 
						PunteggioHome++;
				}
			}
			if(0.5 <= prob && prob < 0.8) {
				
				PlayerAndPeso best = getBestPlayer();
				
				if(dao.getId(m, best.getPlayer()) == m.getTeamAwayID()) 
					if(Math.random() < 0.6 )
						espulsioneAway++;
					else
						espulsioneHome++;
				
				if(dao.getId(m, best.getPlayer()) == m.getTeamHomeID()) 
					if(Math.random() < 0.6 )
						espulsioneHome++;
					else
						espulsioneAway++;
			}
			
			if(0.8 <= prob) {
				if(Math.random() < 0.5)
					N = N + 3;
				else
					N = N + 2;
			}
		}
		
		System.out.println("PunteggioAway: " + PunteggioAway);
		System.out.println("PunteggioHome: " + PunteggioHome);
		System.out.println("espulsioneAway: " + espulsioneAway);
		System.out.println("espulsioneHome: " + espulsioneHome);


	}

	public Collection<Match> getMatch() {
		LinkedList<Match>  temp = new LinkedList<Match>(dao.listAllMatches());
		Collections.sort(temp, new Comparator<Match>() {
			public int compare(Match o1, Match o2) {
				return o1.getMatchID() - o2.getMatchID();
			}
		});
		return temp;
	}

	public Integer getNVertici() {
		// TODO Auto-generated method stub
		return this.grafo.vertexSet().size();
	}

	public Integer getNAdiacenze() {
		// TODO Auto-generated method stub
		return this.grafo.edgeSet().size();
	}

	public Integer getPunteggioAway() {
		// TODO Auto-generated method stub
		return PunteggioAway;
	}

	public Integer getPunteggioHome() {
		// TODO Auto-generated method stub
		return PunteggioHome;
	}

	public Integer getEspulsioneAway() {
		// TODO Auto-generated method stub
		return espulsioneAway;
	}

	public Integer getEspulsioneHome() {
		// TODO Auto-generated method stub
		return espulsioneHome;
	}

	public void azzeraContatore() {
		PunteggioAway = 0;
		PunteggioHome = 0;
		espulsioneAway = 0;
		espulsioneHome = 0;
	}
	
	

	


}

	

