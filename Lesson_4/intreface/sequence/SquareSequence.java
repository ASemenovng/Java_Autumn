package intreface.sequence;

public class SquareSequence implements IntSequence {

  int value;

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public int next() {
    value++;
    return value * value;
  }
}
