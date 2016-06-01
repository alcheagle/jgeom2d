/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcheagle.jgeom2d.boundary;

import com.alcheagle.jgeom2d.Vector2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represent a polygon with n number of vertexes, they could have
 * concavity or not. the last vertex is always the first
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public class PolygonBoundary implements Boundary {

	private final List<Vector2D> vertexes;
	private TriangleBoundary[] triangles;

	public PolygonBoundary() {
		vertexes = new ArrayList<>();
		triangles = null;
	}

	public synchronized void addVertexes(Vector2D... v) {
		vertexes.addAll(Arrays.asList(v));//TODO add the first vertex at the end of the list
		triangles = null;
	}
	
	public Vector2D getVertex(int pos) {
		return vertexes.get(pos);
	}

	@Override
	public boolean isInside(Vector2D point) {
		boolean res = false;

		TriangleBoundary[] t = getTriangulation();

		for (int i = 0; i < t.length && !res; i++) {
			t[i].isInside(point);
		}

		return res;
	}

	@Override
	public boolean isColliding(Boundary b) {
		boolean res = false;
		TriangleBoundary[] t = getTriangulation();

		for (int i = 0; i < t.length && !res; i++) {
			res = t[i].isColliding(b);
		}
		
		return res;
	}
	
	@Override
	public TriangleBoundary[] getTriangulation() {
		if (triangles == null) {
			triangles = triangulate();
		}

		return triangles;
	}

	protected synchronized TriangleBoundary[] triangulate() {
		throw new UnsupportedOperationException("Triangulation Algorithm not yet implemented");
	}

	@Override
	public String toString() {
		return "PolygonBoundary{" + "vertexes=" + vertexes + ", triangles=" + triangles + '}';
	}

	@Override
	public void translate(Vector2D offset) {
		for (Vector2D vertex : vertexes) {
			vertex.sum(offset);
		}
	}
}
