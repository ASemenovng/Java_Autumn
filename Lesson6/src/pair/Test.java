package pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Test {

  public static void main(String[] args) {
    Pair<Integer, Integer> p = new Pair<>(1, 2);
    Pair<Integer, String> pair = new Pair<>(100, "Name");
    String name = pair.getRight();
    print(Arrays.asList(1, 2, 3));
  }

  public static <T> void print(List<T> list) {
    T param;
  }

}
