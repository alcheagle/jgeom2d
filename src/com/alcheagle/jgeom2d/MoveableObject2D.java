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
public abstract class MoveableObject2D extends Object2D implements Moveable {

	protected Vector2D speed;
	protected Vector2D acceleration;

	public MoveableObject2D() {
		super();
		speed = new Vector2D();
		acceleration = new Vector2D();
	}

	public Vector2D getSpeed() {
		return speed;
	}

	public void setSpeed(Vector2D speed) {
		this.speed = speed;
	}

	public Vector2D getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}

	@Override
	public void move() {
		speed.selfSum(acceleration);
		position.selfSum(speed);
		if (boundary != null) {
			boundary.translate(speed);
		}
	}
}
