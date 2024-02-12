import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class ClassLoaderDL {

  static {
    int sum = IntStream.range(0, 10_000).parallel()
        .map(operand -> operand * 2)
        .sum();
  }
  public static void main(String[] args) {

  }

}
