package it.polito.tdp.baseball.model;

public class Grado implements Comparable<Grado>{

	private People p;
	private Integer g;
	public Grado(People p, Integer g) {
		super();
		this.p = p;
		this.g = g;
	}
	public People getP() {
		return p;
	}
	public void setP(People p) {
		this.p = p;
	}
	public Integer getG() {
		return g;
	}
	public void setG(Integer g) {
		this.g = g;
	}
	@Override
	public int compareTo(Grado o) {
		// TODO Auto-generated method stub
		return o.getG()-this.g;
	}
	
	
}
