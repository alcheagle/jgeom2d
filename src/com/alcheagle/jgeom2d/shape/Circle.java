/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcheagle.jgeom2d.shape;

import com.alcheagle.jgeom2d.Vector2D;
import java.util.Objects;

/**
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public class Circle implements Shape {

	private Vector2D center;
	private double radius;

	public Circle() {
		this(0);
	}

	public Circle(double radius) {
		this(new Vector2D(), radius);
	}

	public Circle(Vector2D position, double radius) {
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
	public boolean isColliding(Shape b) {
		boolean res = false;

		if(b instanceof Circle) {
			Circle cb = (Circle) b;
			Vector2D dcenter = center.sub(cb.center);
			
			return dcenter.magnitude() <=  (radius + cb.radius);
		} else {
			Triangle[] t = b.getTriangulation();

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
	public Triangle[] getTriangulation() {
		return null;
	}

	@Override
	public void translate(Vector2D offset) {
		center.sum(offset);
	}

	@Override
	public String toString() {
		return "CircleBoundary{" + center + ", r=" + radius + '}';
	}

	@Override
	public int hashCode() {
		int hash = 5;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Circle other = (Circle) obj;
		if (Double.doubleToLongBits(this.radius) != Double.doubleToLongBits(other.radius)) {
			return false;
		}
		if (!Objects.equals(this.center, other.center)) {
			return false;
		}
		return true;
	}
	
	
}
