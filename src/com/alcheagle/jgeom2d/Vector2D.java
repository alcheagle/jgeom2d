/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcheagle.jgeom2d;

/**
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public class Vector2D implements Cloneable {

	private double x, y;
	private Double magnitude;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D() {
		x = 0;
		y = 0;
	}

	public Vector2D(Vector2D position) {
		x = position.x;
		y = position.y;
	}

	public Vector2D sum(Vector2D v) {
		return new Vector2D(x + v.x, y + v.y);
	}

	public synchronized Vector2D selfSum(Vector2D v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public Vector2D sub(Vector2D v) {
		return sum(v.opposite());
	}

	public synchronized Vector2D selfSub(Vector2D v) {
		selfSum(v.opposite());

		return this;
	}

	public synchronized Vector2D selfTimes(double d) {
		x *= d;
		y *= d;
		return this;
	}

	public Vector2D times(double d) {
		return new Vector2D(x * d, y * d);
	}

	public Vector2D rotate(double theta) {
		double cs = Math.cos(theta);
		double sn = Math.sin(theta);

		double px = x * cs - y * sn;
		double py = x * sn + y * cs;

		return new Vector2D(px, py);
	}

	public synchronized Vector2D selfRotate(double theta) {
		double cs = Math.cos(theta);
		double sn = Math.sin(theta);

		double px = x * cs - y * sn;
		double py = x * sn + y * cs;

		x = px;
		y = py;

		return this;
	}

	public double dotProduct(Vector2D v) {
		return (x * v.x) + (y * v.y);
	}

	public Vector2D project(Vector2D v) {
		double mag = v.magnitude();
		double dotp = this.dotProduct(v);

		return v.times(dotp / (mag * mag));
	}

	public Vector2D opposite() {
		return new Vector2D(-x, -y);
	}

	public double getX() {
		return x;
	}

	public synchronized void setX(double x) {
		this.x = x;
		magnitude = null;
	}

	public double getY() {
		return y;
	}

	public synchronized void setY(double y) {
		this.y = y;
		magnitude = null;
	}

	private double calculate_magnitude() {
		return x * x + y * y;
	}

	public double magnitude() {
		return magnitude == null ? magnitude = calculate_magnitude() : magnitude;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ')';
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
		hash = 67 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
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
		final Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		return true;
	}
}
