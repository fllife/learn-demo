package com.mxm.java.learn_demo.java8.lambda;

public class Apple {
	
	private String color;
	
	private Integer weight;
	
	public Apple(String color, Integer weight) {
		this.color = color;
		this.weight = weight;
	}
	
	public Apple() {
		
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public String toString() {
		return "color : " + color + ", weight : " + weight;
	}
	
	
}
