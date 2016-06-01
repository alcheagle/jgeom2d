/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcheagle.jgeom2d.boundary;

import com.alcheagle.jgeom2d.boundary.TriangleBoundary;
import com.alcheagle.jgeom2d.Vector2D;
import com.alcheagle.jgeom2d.Vector2D;

/**
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public interface Boundary {
	
	public boolean isInside(Vector2D point);
	
	public boolean isColliding(Boundary b);
	
	public TriangleBoundary[] getTriangulation();
	
	public void translate(Vector2D offset);
}
