package tasks;

public class Vector {
  private final float x;
  private final float y;

  public Vector(float x, float y){
    this.x = x;
    this.y = y;
  }


  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  @Override
  public String toString() {
    return "Vector{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}
