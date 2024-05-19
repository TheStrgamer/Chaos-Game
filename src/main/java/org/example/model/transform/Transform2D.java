package org.example.model.transform;

import org.example.model.math.Vector2D;

/**
 * <h1>Transform2D</h1>
 * An interface representing a 2D transformation. The goal of this interface is to transform a given
 * 2D vector.
 */
public interface Transform2D {

  /**
   * Transforms the given vector.
   *
   * @param vector the vector to transform
   * @return the transformed vector
   */
  Vector2D transform(Vector2D vector);

  /**
   * Checks if the given transform is equal to this transform.
   *
   * @param transform the transform to check
   * @return true if the given transform is equal to this transform, false otherwise
   */
  boolean equals(Transform2D transform);
}
