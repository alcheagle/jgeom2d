/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcheagle.jgeom2d.shape;

import com.alcheagle.jgeom2d.Vector2D;

/**
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public class Rectangle extends Polygon {

	public Rectangle(Vector2D position, Vector2D lengths) {
		Vector2D oppvert = position.sum(lengths);

		super.addVertexes(
				position,
				new Vector2D(position.getX(), oppvert.getY()),
				new Vector2D(oppvert.getX(), position.getY()),
				oppvert);
	}

	private Rectangle(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4) {
		super();//the user could generate a shape that is not a rectangle
		super.addVertexes(p1, p2, p3, p4);
	}

	@Override
	public synchronized void addVertexes(Vector2D... v) {
		throw new UnsupportedOperationException("Unable to add vertexes to a Rectangle");
	}

	@Override
	protected Triangle[] triangulate() {
		Triangle[] triangulation = new Triangle[2];

		triangulation[0] = new Triangle();
		triangulation[0].setVertex1(getVertex(0));
		triangulation[0].setVertex2(getVertex(1));
		triangulation[0].setVertex3(getVertex(2));
		
		triangulation[1] = new Triangle();
		triangulation[1].setVertex1(getVertex(0));
		triangulation[1].setVertex2(getVertex(2));
		triangulation[1].setVertex3(getVertex(3));

		return triangulation;
	}
}
