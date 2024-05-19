package org.example.model.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.transform.AffineTransform2D;
import org.example.model.math.Complex;
import org.example.model.transform.JuliaTransform;
import org.example.model.math.Matrix2x2;
import org.example.model.transform.Transform2D;
import org.example.model.math.Vector2D;

/**
 * <h1>ChaosGameDescriptionFactory</h1>
 * A factory class that creates ChaosGameDescription objects based on the given type.
 */
public class ChaosGameDescriptionFactory {


  /**
   * Creates a ChaosGameDescription object based on the given type.
   *
   * @param type is the type of description to create.
   * @return the ChaosGameDescription object.
   */
  public ChaosGameDescription createDescription(String type) {
    return switch (type) {
      case "Sierpinski" -> createSierpinskiDescription();
      case "Barnsley" -> createBarnsleyDescription();
      case "Julia" -> createJuliaDescription();
      case "Julia2" -> createJuliaDescription2();
      case "Julia3" -> createJuliaDescription3();
      case "Diamond" -> createDiamondDescription();
      case "Plant" -> createPlantDescription();
      case "Flower" -> createFlowerDescription();
      case "Snake" -> createSnakeDescription();
      case "Spine" -> createSpineDescription();
      case "JuliaRandom" -> createJuliaRandomDescription();
      case "AffineRandom" -> createAffineRandomDescription();
      case "EmptyAffine" -> createEmptyAffineDescription();
      case "EmptyJulia" -> createEmptyJuliaDescription();
      default -> throw new IllegalArgumentException("Invalid description type");
    };
  }

  /**
   * Creates a ChaosGameDescription object with the sierpinski values.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createSierpinskiDescription() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector1 = new Vector2D(0, 0);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector2 = new Vector2D(0.5, 0);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    Matrix2x2 matrix3 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector3 = new Vector2D(0.25, 0.5);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);

    transforms.add(transform1);
    transforms.add(transform2);
    transforms.add(transform3);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Creates a ChaosGameDescription object with the barnsley values.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createBarnsleyDescription() {
    Vector2D minCoords = new Vector2D(-2.65, 0);
    Vector2D maxCoords = new Vector2D(2.65, 10);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0, 0, 0, 0.16);
    Vector2D vector1 = new Vector2D(0, 0);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.85, 0.04, -0.04, 0.85);
    Vector2D vector2 = new Vector2D(0, 1.6);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    Matrix2x2 matrix3 = new Matrix2x2(0.2, -0.26, 0.23, 0.22);
    Vector2D vector3 = new Vector2D(0, 1.6);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);

    Matrix2x2 matrix4 = new Matrix2x2(-0.15, 0.28, 0.26, 0.24);
    Vector2D vector4 = new Vector2D(0, 0.44);
    AffineTransform2D transform4 = new AffineTransform2D(matrix4, vector4);

    transforms.add(transform1);
    transforms.add(transform2);
    transforms.add(transform3);
    transforms.add(transform4);
    List<Integer> weights = new ArrayList<>();
    weights.add(1);
    weights.add(85);
    weights.add(12);
    weights.add(12);


    return new ChaosGameDescription(minCoords, maxCoords, transforms, weights);
  }

  /**
   * Creates a ChaosGameDescription object with the julia transform for the complex -0.74543, 0.11301.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createJuliaDescription() {
    ChaosGameDescription description = generateJulia(new Complex(-0.74543, 0.11301));
    List<Integer> weights = new ArrayList<>();
    weights.add(1);
    weights.add(5);
    description.setWeights(weights);
    return description;
  }

  /**
   * Creates a ChaosGameDescription object with the julia transform for the complex 0.15546, -0.63690.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createJuliaDescription2() {
    return generateJulia(new Complex(0.15546, -0.63690));
  }

  /**
   * Creates a ChaosGameDescription object with the julia transform for the complex 0.42428198918101634, 0.14423276134020324.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createJuliaDescription3() {
    return generateJulia(new Complex(0.42428198918101634, 0.14423276134020324));
  }



  /**
   * Creates a ChaosGameDescription object with affine transforms. This description has been named "Diamond".
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createDiamondDescription() {
    Vector2D minCoords = new Vector2D(-0.25, -.75);
    Vector2D maxCoords = new Vector2D(1.25, .5);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(-0.035359, -0.45328, 0.61167, -0.30323);
    Vector2D vector1 = new Vector2D( 0.0765966, -0.740269 );
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.875270, -0.97410, 0.9323, -0.2067);
    Vector2D vector2 = new Vector2D( 0.067495, -0.54111 );
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);


    transforms.add(transform1);
    transforms.add(transform2);

    List<Integer> weights = new ArrayList<>();
    weights.add(1);
    weights.add(5);

    return new ChaosGameDescription(minCoords, maxCoords, transforms, weights);
  }

  /**
   * Creates a ChaosGameDescription object with affine transforms. This description has been named "Plant".
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createPlantDescription() {
    Vector2D minCoords = new Vector2D(-6, -2.5);
    Vector2D maxCoords = new Vector2D(2.5, 6);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.7735, 0.2473, -0.3505, 0.8680);
    Vector2D vector1 = new Vector2D(-1.5435, -0.0778);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(-0.1939, -0.6283, -0.5006, 0.0103);
    Vector2D vector2 = new Vector2D(0.4887, -1.0326);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    transforms.add(transform1);
    transforms.add(transform2);

    List<Integer> weights = new ArrayList<>();
    weights.add(8);
    weights.add(5);
    return new ChaosGameDescription(minCoords, maxCoords, transforms, weights);
  }

  /**
   * Creates a ChaosGameDescription object with affine transforms. This description has been named "Flower".
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createFlowerDescription() {
    Vector2D minCoords = new Vector2D(-2, -2);
    Vector2D maxCoords = new Vector2D(2, 2);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(-0.5639, -0.1856, -0.9115, 0.7954);
    Vector2D vector1 = new Vector2D(0.8410, 0.1493);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(-0.5211, -0.3527, -0.5343, 0.5359);
    Vector2D vector2 = new Vector2D(0.1484, 0.4851);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    transforms.add(transform1);
    transforms.add(transform2);

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Creates a ChaosGameDescription object with affine transforms. This description has been named "Snake".
   *
   * @return the ChaosGameDescription object.
   */
  public ChaosGameDescription createSnakeDescription() {
    Vector2D minCoords = new Vector2D(-1, -6);
    Vector2D maxCoords = new Vector2D(3, 0);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(-0.99, -0.04, 0.29, 0.71);
    Vector2D vector1 = new Vector2D(1.35, -1.90);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(-0.58, -0.51, 0.05, 0.5);
    Vector2D vector2 = new Vector2D(-0.09, -0.07);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    Matrix2x2 matrix3 = new Matrix2x2(0.79, 0.53, -0.41, 0.20);
    Vector2D vector3 = new Vector2D(1.78, -1.93);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);

    transforms.add(transform1);
    transforms.add(transform2);
    transforms.add(transform3);

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Creates a ChaosGameDescription object with affine transforms. This description has been named "Spine".
   *
   * @return the ChaosGameDescription object.
   */
  public ChaosGameDescription createSpineDescription() {
    Vector2D minCoords = new Vector2D(-3.5, -7.9);
    Vector2D maxCoords = new Vector2D(8.5, 3.8);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.94, 0.25, -0.17, 0.73);
    Vector2D vector1 = new Vector2D(1.9, -0.80);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(-0.13, 0.38, -0.66, -0.57);
    Vector2D vector2 = new Vector2D(0.37, 0.04);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    transforms.add(transform1);
    transforms.add(transform2);

    List<Integer> weights = new ArrayList<>();
    weights.add(3);
    weights.add(1);

    return new ChaosGameDescription(minCoords, maxCoords, transforms, weights);
  }

  /**
   * Generates a ChaosGameDescription with a julia transform for the given complex number.
   *
   * @param c is the complex number to use.
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription generateJulia(Complex c) {
    List<Transform2D> transforms = new ArrayList<>();

    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);

    transforms.add(transform1);
    transforms.add(transform2);
    //TODO find a way to calculate min and max coords
    Vector2D minCoords = new Vector2D(-1.6, -1.2);
    Vector2D maxCoords = new Vector2D(1.6, 1.2);

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Generates a ChaosGameDescription with julia transforms for the given complex numbers.
   *
   * @param complexes is the array of complex numbers to use.
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription generateJulia(Complex[] complexes) {
    List<Transform2D> transforms = new ArrayList<>();

    for (Complex c : complexes) {
      JuliaTransform transform1 = new JuliaTransform(c, 1);
      JuliaTransform transform2 = new JuliaTransform(c, -1);

      transforms.add(transform1);
      transforms.add(transform2);
    }

    //TODO find a way to calculate min and max coords
    Vector2D minCoords = new Vector2D(-1.6, -1.4);
    Vector2D maxCoords = new Vector2D(1.6, 1.4);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }
  /**
   * Creates a ChaosGameDescription object with randomly generated julia transforms. It can have up to 2 transforms.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createJuliaRandomDescription() {
    Random random = new Random();
    int transformCount = random.nextInt(2) + 1;
    Complex[] complexes = new Complex[transformCount];
    for (int i = 0; i < transformCount; i++) {
      double real = random.nextDouble() * 3 - 1.5;
      real = Math.round(real * 10000.0) / 10000.0;
      double imaginary = random.nextDouble() * 3 - 1.5;
      imaginary = Math.round(imaginary * 10000.0) / 10000.0;
      complexes[i] = new Complex(real, imaginary);
    }
    return generateJulia(complexes);
  }

  /**
   * Creates a ChaosGameDescription object with randomly generated affine transforms. It can have 2-4 transforms.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createAffineRandomDescription() {
    Vector2D minCoords = new Vector2D(-6, -6);
    Vector2D maxCoords = new Vector2D(6, 6);
    List<Transform2D> transforms = new ArrayList<>();
    List<Integer> weights = new ArrayList<>();

    Random random = new Random();
    int transformCount = random.nextInt(3) + 2;
    for (int i = 0; i < transformCount; i++) {
      double a = random.nextDouble() * 2 - 1;
      double b = random.nextDouble() * 2 - 1;
      double c = random.nextDouble() * 2 - 1;
      double d = random.nextDouble() * 2 - 1;
      double e = random.nextDouble() * 4 - 2;
      double f = random.nextDouble() * 4 - 2;

      a = Math.round(a * 10000.0) / 10000.0;
      b = Math.round(b * 10000.0) / 10000.0;
      c = Math.round(c * 10000.0) / 10000.0;
      d = Math.round(d * 10000.0) / 10000.0;
      e = Math.round(e * 10000.0) / 10000.0;
      f = Math.round(f * 10000.0) / 10000.0;

      Matrix2x2 matrix = new Matrix2x2(a, b, c, d);
      Vector2D vector = new Vector2D(e, f);
      AffineTransform2D transform = new AffineTransform2D(matrix, vector);
      transforms.add(transform);
      weights.add(random.nextInt(3)+1);
    }
    return new ChaosGameDescription(minCoords, maxCoords, transforms, weights);
  }

  /**
   * Creates an empty affine description, with default values.
   * @return the ChaosGameDescription object.
   */
  public ChaosGameDescription createEmptyAffineDescription() {
    Vector2D minCoords = new Vector2D(-1, -1);
    Vector2D maxCoords = new Vector2D(1, 1);
    Matrix2x2 matrix = new Matrix2x2(1, 0, 0, 1);
    Vector2D vector = new Vector2D(0, 0);
    AffineTransform2D transform = new AffineTransform2D(matrix, vector);
    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(transform);
    List<Integer> weights = new ArrayList<>();
    weights.add(1);
    return new ChaosGameDescription(minCoords, maxCoords, transforms, weights);
  }

  /**
   * Creates an empty julia description, with default values.
   * @return the ChaosGameDescription object.
   */
  public ChaosGameDescription createEmptyJuliaDescription() {
    Vector2D minCoords = new Vector2D(-1.6, -1.2);
    Vector2D maxCoords = new Vector2D(1.6, 1.2);
    Complex c = new Complex(0, 0);
    JuliaTransform transformPos = new JuliaTransform(c, 1);
    JuliaTransform transformNeg = new JuliaTransform(c, -1);
    List<Transform2D> transforms = new ArrayList<>(List.of(transformPos, transformNeg));
    List<Integer> weights = new ArrayList<>();
    weights.add(1);
    weights.add(1);
    return new ChaosGameDescription(minCoords, maxCoords, transforms, weights);
  }
}
