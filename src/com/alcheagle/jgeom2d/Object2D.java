/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcheagle.jgeom2d;

import com.alcheagle.jgeom2d.shape.Shape;

/**
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public abstract class Object2D {
	protected Vector2D position;
	protected double mass;
	protected Shape boundary;
	
	public final static double DEFAULT_MASS = 0.0;
	
	public Object2D() {
		position = new Vector2D();
		mass = DEFAULT_MASS;
	}
	
	public Object2D(Shape boundary) {
		this.boundary = boundary;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public Shape getBoundary() {
		return boundary;
	}

	public void setBoundary(Shape boundary) {
		this.boundary = boundary;
	}
	
	public boolean isColliding(Object2D o) {
		if(boundary == null) {
			return false; //TODO is better to throw an exception or to simply return false when there is no boudary?
		}
		return boundary.isColliding(o.boundary); //fire collision event
	}
}
