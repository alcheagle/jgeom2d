/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcheagle.jgeom2d.boundary;

import com.alcheagle.jgeom2d.Vector2D;

/**
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public class CircleBoundary implements Boundary {

	private Vector2D center;
	private double radius;

	public CircleBoundary() {
		this(0);
	}

	public CircleBoundary(double radius) {
		this(new Vector2D(), radius);
	}

	public CircleBoundary(Vector2D position, double radius) {
		this.center = position;
		this.radius = radius;
	}

	public Vector2D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public boolean isInside(Vector2D point) {
		Vector2D deltapos = point.sub(center);
		
		return deltapos.magnitude() <= radius;
	}

	@Override
	public boolean isColliding(Boundary b) {
		boolean res = false;

		if(b instanceof CircleBoundary) {
			CircleBoundary cb = (CircleBoundary) b;
			Vector2D dcenter = center.sub(cb.center);
			
			return dcenter.magnitude() <=  (radius + cb.radius);
		} else {
			TriangleBoundary[] t = b.getTriangulation();

			for (int i = 0; i < t.length && !res; i++) {
				Vector2D[] v = t[i].getVertexes();
				for (int j = 0; j < v.length && !res; j++) {
					res = this.isInside(v[j]);
				}
			}
		}

		return res;
	}

	@Override
	public TriangleBoundary[] getTriangulation() {
		return null;
	}

	@Override
	public void translate(Vector2D offset) {
		center.sum(offset);
	}
}
