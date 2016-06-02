/*
 * Copyright (C) 2016 Andrea Gilardoni <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alcheagle.jgeom2d.boundary;

import com.alcheagle.jgeom2d.shape.Circle;
import com.alcheagle.jgeom2d.Vector2D;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.alcheagle.jgeom2d.shape.Shape;

/**
 *
 * @author Andrea Gilardoni
 * <a href="mailto:andrea.gilardoni96@gmail.com">andrea.gilardoni96@gmail.com</a>
 */
public class CircleTest {

	public static class Pair<X, Y> {

		public final X x;
		public final Y y;

		public Pair(X x, Y y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Triplet<X, Y, Z> extends Pair<X, Y> {

		public final Z z;

		public Triplet(X x, Y y, Z z) {
			super(x, y);
			this.z = z;
		}
	}

	public CircleTest() {

	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of isInside method, of class CircleBoundary.
	 */
	@Test
	public void testIsInside() {
		Circle sample;
		List<Pair<Vector2D, Boolean>> samples;

		//creating CircleBoundary sample
		Vector2D center = new Vector2D(10, 10);
		sample = new Circle(center, 10);

		//creating Vector samples
		samples = new ArrayList<>();

		samples.add(
				new Pair<>(
						center,
						true
				));
		samples.add(
				new Pair<>(
						new Vector2D(20, 20),
						false
				));
		samples.add(
				new Pair<>(
						new Vector2D(),
						false
				));
		samples.add(
				new Pair<>(
						new Vector2D(0, 20),
						false
				));
		samples.add(
				new Pair<>(
						new Vector2D(20, 0),
						false
				));
		samples.add(
				new Pair<>(
						new Vector2D(200, 20),
						false
				));
		samples.add(
				new Pair<>(
						new Vector2D(20, 100),
						false
				));
		samples.add(
				new Pair<>(
						new Vector2D(10, 20),
						true
				));

		System.out.println(
				"isInside Testing");
		//testing
		for (Pair<Vector2D, Boolean> s : samples) {
			assertEquals("failing with: " + s.x, sample.isInside(s.x), s.y);
		}
	}

	/**
	 * tests the collisions method
	 */
	@Test
	public void testCollisions() {
		List<Triplet<Shape, Shape, Boolean>> samples;
		samples = new ArrayList<>();

		samples.add(new Triplet<Shape, Shape, Boolean>(
				new Circle(new Vector2D(10, 10), 10), //this method will be called on the second arg
				new Circle(new Vector2D(10, 10), 10),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 100), //this method will be called on the second arg
				new Circle(new Vector2D(20, 20), 10),
				true
		));

		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 100), //this method will be called on the second arg
				new Circle(new Vector2D(), 10),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 0), //this method will be called on the second arg
				new Circle(new Vector2D(), 0),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(100, 100), 10), //this method will be called on the second arg
				new Circle(new Vector2D(), 100),
				false
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(10, 10), 10), //this method will be called on the second arg
				new Circle(new Vector2D(22, 22), 10),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 10), //this method will be called on the second arg
				new Circle(new Vector2D(100, 100), 10),
				false
		));
		
		for (CircleTest.Triplet<Shape, Shape, Boolean> sample : samples) {
			assertEquals("Testing: " + sample.x + " with " + sample.y, sample.z, sample.x.isColliding(sample.y));
		}
	}
	
	@Test
	public void testTranslations() {
		List<Triplet<Shape, Shape, Boolean>> samples;
		samples = new ArrayList<>();

		samples.add(new Triplet<Shape, Shape, Boolean>(
				new Circle(new Vector2D(10, 10), 10), //this method will be called on the second arg
				new Circle(new Vector2D(10, 10), 10),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 100), //this method will be called on the second arg
				new Circle(new Vector2D(20, 20), 10),
				true
		));

		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 100), //this method will be called on the second arg
				new Circle(new Vector2D(), 10),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 0), //this method will be called on the second arg
				new Circle(new Vector2D(), 0),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(100, 100), 10), //this method will be called on the second arg
				new Circle(new Vector2D(), 100),
				false
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(10, 10), 10), //this method will be called on the second arg
				new Circle(new Vector2D(22, 22), 10),
				true
		));
		
		samples.add(new Triplet<>(
				new Circle(new Vector2D(), 10), //this method will be called on the second arg
				new Circle(new Vector2D(100, 100), 10),
				false
		));
		
		for (Triplet<Shape, Shape, Boolean> sample : samples) {
			Vector2D translation = new Vector2D(Math.random()*10000d, Math.random()*10000d);
			
			sample.x.translate(translation);
			sample.y.translate(translation);
		}
		
		for (CircleTest.Triplet<Shape, Shape, Boolean> sample : samples) {
			assertEquals("Testing: " + sample.x + " with " + sample.y, sample.z, sample.x.isColliding(sample.y));
		}
	}
}
