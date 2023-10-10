import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import shmoptional.IntegerShmoptional;
import shmoptional.Shmoptional;

public class Main {

  static Object obj = "str";

  public static void main(String[] args) {
    Shmoptional<Number> s = new Shmoptional<>(10);
    Main.<Number>setNotNull(s, 15.0);
    System.out.println(s.get());
    //s.set(new Y());
    test();
    //print(null);
    //Integer i = 2023;
    //Class<Integer> integerClass = i.getClass();
    // String smth = newList();

    String randS = getRandom("a", "b", "c");
    Integer randI = getRandom(10, 13, 56);
    getDouble(new IntegerShmoptional(123));
  }
  static double getDouble(Shmoptional<? extends Number> shmoptional) {
    return shmoptional.get().doubleValue();
  }

  public static <T> T getRandom(T... values) {
    return values[ThreadLocalRandom.current().nextInt(values.length)];
  }

  private static <T extends List<Integer>> T newList() {
    return (T) new ArrayList<Integer>();
  }

  static void print(Object... objects) {
    for (Object o : objects) {
      System.out.println(o);
    }
  }

  void foo(String[] strings, int x) {

  }


  //@SuppressWarnings("unchecked")
  static <T> T getT() {
    return (T) obj;
  }

  static void test() {
    String s = getT();
    Integer i = getT();
  }


  static <T> void setNotNull(Shmoptional<? super T> shmoptional, T val) {
    if (val == null) {
      throw new IllegalArgumentException();
    }
    shmoptional.set(val);
  }
}