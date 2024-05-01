package org.example.model;

import java.util.ArrayList;
import java.util.List;

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
      case "Grain" -> createGrainDescription();
      case "Snowflake" -> createSnowflakeDescription();
      case "Wave" -> createWaveDescription();
      case "Diamond" -> createDiamondDescription();
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
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Creates a ChaosGameDescription object with the julia values.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createJuliaDescription() {
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    List<Transform2D> transforms = new ArrayList<>();

    Complex complex1 = new Complex(-0.74543, 0.11301);
    JuliaTransform transform1 = new JuliaTransform(complex1, 1);
    JuliaTransform transform2 = new JuliaTransform(complex1, -1);

    transforms.add(transform1);
    transforms.add(transform2);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  private ChaosGameDescription createJuliaDescription2() {
    Vector2D minCoords = new Vector2D(-1.4, -1.2);
    Vector2D maxCoords = new Vector2D(1.4, 1.2);
    List<Transform2D> transforms = new ArrayList<>();

    Complex complex1 = new Complex(0.15545970506376272, -0.6369025653907516);
    JuliaTransform transform1 = new JuliaTransform(complex1, 1);
    JuliaTransform transform2 = new JuliaTransform(complex1, -1);

    transforms.add(transform1);
    transforms.add(transform2);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }
  private ChaosGameDescription createJuliaDescription3() {
    Vector2D minCoords = new Vector2D(-1.4, -1.4);
    Vector2D maxCoords = new Vector2D(1.4, 1.4);
    List<Transform2D> transforms = new ArrayList<>();

    Complex complex1 = new Complex(0.42428198918101634, 0.14423276134020324);
    JuliaTransform transform1 = new JuliaTransform(complex1, 1);
    JuliaTransform transform2 = new JuliaTransform(complex1, -1);

    transforms.add(transform1);
    transforms.add(transform2);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  private ChaosGameDescription createGrainDescription() {
    Vector2D minCoords = new Vector2D(-2, -2);
    Vector2D maxCoords = new Vector2D(2, 2);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.8526, -0.6319, -0.9674, -0.4780);
    Vector2D vector1 = new Vector2D(-0.8476, 0.7119);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(-0.4179, 0.3461, -0.4627, -0.3773);
    Vector2D vector2 = new Vector2D(0.6458, 0.1437);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);


    transforms.add(transform1);
    transforms.add(transform2);

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }
  private ChaosGameDescription createSnowflakeDescription() {
    Vector2D minCoords = new Vector2D(-2.5, -2);
    Vector2D maxCoords = new Vector2D(-.5, .5);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.4616, 0.7805, -0.4300, 0.2053);
    Vector2D vector1 = new Vector2D(-0.1491, -0.6422);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.7374, 0.2385, 0.4507, -0.7032);
    Vector2D vector2 = new Vector2D(-0.2748, -0.9047);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);


    transforms.add(transform1);
    transforms.add(transform2);

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }
  private ChaosGameDescription createWaveDescription() {
    Vector2D minCoords = new Vector2D(-2.5, -2);
    Vector2D maxCoords = new Vector2D(-.5, .5);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.4591390135618698, 0.8832052017098566, -0.7462426250152951, 0.833017031294506);
    Vector2D vector1 = new Vector2D( -0.11902225565217517, -0.9110847611752506);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.2583303066519558, -0.5680200732142788, 0.6128344748728818, -0.08576953593379777);
    Vector2D vector2 = new Vector2D(-0.7718854713164023, -0.8008603013926072);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);


    transforms.add(transform1);
    transforms.add(transform2);

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }
  private ChaosGameDescription createDiamondDescription() {
    Vector2D minCoords = new Vector2D(-0.25, -.75);
    Vector2D maxCoords = new Vector2D(1.25, .5);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(-0.03535949538401151, -0.4532831417408121, 0.6116778800475406, -0.3032324309903214);
    Vector2D vector1 = new Vector2D( 0.07659661687866781, -0.7402694095766407 );
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.8752702951432678, -0.9741038761368701, 0.9323165704394973, -0.2067485177080317);
    Vector2D vector2 = new Vector2D( 0.0674954419906022, -0.5411159639984362 );
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);


    transforms.add(transform1);
    transforms.add(transform2);

    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }


}
