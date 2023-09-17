package it.polito.tdp.baseball.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.baseball.db.BaseballDAO;

public class Model {
	
	private BaseballDAO dao;
	private Graph<People,DefaultEdge> graph;
	List<People> allNodes;
	
	public Model() {
		this.dao=new BaseballDAO();
		this.allNodes=new ArrayList<>();
	}
	
	
	public void buildGraph(Integer year,Integer salary) {
		this.allNodes.clear();
		this.loadNodes(year,salary);
		this.graph=new SimpleGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(this.graph, this.allNodes);
		for(People p1:this.graph.vertexSet()) {
			for(People p2:this.graph.vertexSet()) {
				if(p1.getPlayerID()!=p2.getPlayerID() && this.graph.getEdge(p1,p2)==null && this.getEdge(p1.getPlayerID(), p2.getPlayerID(), year)) {
					this.graph.addEdge(p1, p2);
				}
			}
		}
		
	}
	
	public Grado getGradoMassimo(){
		List<Grado> gradi=new ArrayList<>();
		List<People> incidenti=new ArrayList<>();
		for(People p:this.graph.vertexSet()) {
			incidenti.addAll(Graphs.neighborListOf(this.graph,p));
			gradi.add(new Grado(p,incidenti.size()));
			incidenti.clear();
		}
		Collections.sort(gradi);
		return gradi.get(0);
	}
	
	public List<Set<People>> getNumTotConnesse(){
		ConnectivityInspector<People, DefaultEdge> inspect = new ConnectivityInspector<>(this.graph);
		return inspect.connectedSets();
	}
	
	private Boolean getEdge(String n1,String n2,Integer y) {
		for(String s1:this.dao.getTeamsOfIn(n1, y)) {
			for(String s2:this.dao.getTeamsOfIn(n2, y)) {
				if(s1.equals(s2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void loadNodes(Integer y,Integer s) {
		if(this.allNodes.isEmpty()) {
			this.allNodes.addAll(this.dao.getPlayersInWith(y,s));
		}
	}
	
	public List<Integer> getAllYears() {
		return this.dao.readAllYears();
	}


	public Integer getVsize() {
		// TODO Auto-generated method stub
		return this.graph.vertexSet().size();
	}
	
	public Integer getEsize() {
		// TODO Auto-generated method stub
		return this.graph.edgeSet().size();
	}
	}