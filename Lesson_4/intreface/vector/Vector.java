package intreface.vector;

/**
 * interface of immutable 3-D vector
 * */
public interface Vector {

  double component(int n);

  double length();

  Vector add(Vector other);

}
