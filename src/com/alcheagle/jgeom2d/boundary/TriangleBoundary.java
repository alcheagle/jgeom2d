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
public class TriangleBoundary implements Boundary {

	private final Vector2D[] vertexes;
	public static final int SIDES_NUMBER = 3;

	public TriangleBoundary() {
		vertexes = new Vector2D[SIDES_NUMBER];
	}

	public void setVertex1(Vector2D vertex) throws IllegalArgumentException {
		this.setVertex(0, vertex);
	}

	public void setVertex2(Vector2D vertex) throws IllegalArgumentException {
		this.setVertex(1, vertex);
	}

	public void setVertex3(Vector2D vertex) throws IllegalArgumentException {
		this.setVertex(2, vertex);
	}

	public void setVertex(int number, Vector2D vertex) throws IllegalArgumentException {
		if (number < 0 && number > SIDES_NUMBER) {
			throw new IllegalArgumentException("Atrinagle has only 3 vertexes, refer to them from 0 to 2");
		}

		vertexes[number] = vertex;
	}

	public Vector2D[] getVertexes() {
		return vertexes;
	}

	private static double sign(Vector2D p1, Vector2D p2, Vector2D p3) {
		return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getX());
	}

	@Override
	public boolean isInside(Vector2D point) {
		boolean b1, b2, b3;

		b1 = sign(point, vertexes[0], vertexes[1]) < 0.0f;
		b2 = sign(point, vertexes[1], vertexes[2]) < 0.0f;
		b3 = sign(point, vertexes[2], vertexes[0]) < 0.0f;

		return ((b1 == b2) && (b2 == b3));
	}

	@Override
	public boolean isColliding(Boundary b) {
		boolean res = false;

		if (b instanceof CircleBoundary) {
			CircleBoundary cb = (CircleBoundary) b;
			res = cb.isColliding(this);
		} else {
			//FIXME check if the actual triangle is inside the other triangles
			
			TriangleBoundary[] t = b.getTriangulation();
			
			for (int i = 0; i < t.length && !res; i++) {
				Vector2D[] v = t[i].getVertexes();
				for (int j = 0; j < v.length && !res; j++) {
					res = res || this.isInside(v[j]);
				}
			}
		}

		return res;
	}

	@Override
	public TriangleBoundary[] getTriangulation() {
		TriangleBoundary[] t = new TriangleBoundary[1];
		t[0] = this;
		return t;
	}

	@Override
	public void translate(Vector2D offset) {
		for (Vector2D vertex : vertexes) {
			vertex.sum(offset);
		}
	}

}
