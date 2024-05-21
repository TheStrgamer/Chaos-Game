package org.example.model.transform;

import org.example.model.math.Vector2D;

/**
 * <h2>Transform2D.</h2>
 * <p>
 * An interface representing a 2D transformation. The goal of this interface is to transform a given
 * 2D vector.
 * </p>
 * <p>
 * The interface has a method for transforming a 2D vector and a method for checking if the given
 * transform has the same values as this transform.
 * </p>
 *
 * @version 0.4.0
 * @since 0.2.0
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

  /**
   * Returns a deep copy of this transform.
   *
   * @return a deep copy of this transform
   */
  Transform2D deepCopy();
}
