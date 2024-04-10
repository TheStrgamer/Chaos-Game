package org.example.model;

/**
 * <h1>Transform2D</h1>
 * An interface representing a 2D transformation. The goal of this interface is to transform a given
 * 2D vector.
 */
public interface Transform2D {

  Vector2D transform(Vector2D vector);
}
