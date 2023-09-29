package intreface;

import intreface.sequence.IntSequence;
import intreface.sequence.SquareSequence;

public class Test {

  public static void main(String[] args) {
    // vector

    // sequence
  }

  public static double average(IntSequence sequence, int n) {
    int count = 0;
    double sum = 0;

    while (sequence.hasNext() && count < n) {
      ++count;
      sum += sequence.next();
    }
    return sum;
  }

}
