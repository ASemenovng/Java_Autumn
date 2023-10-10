package bridge;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Person implements Comparable<Person>{

  @Override
  public int compareTo(Person o) {
    return 0;
  }

  public static void main(String[] args) throws NoSuchMethodException {
    Arrays.stream(Person.class.getDeclaredMethods()).forEach(System.out::println);

    Method m1 = Person.class.getMethod("compareTo", Person.class);
    Method m2 = Person.class.getMethod("compareTo", Object.class);

    System.out.println(m1.isBridge());
    System.out.println(m2.isBridge());

    System.out.println(m1.isSynthetic());
    System.out.println(m2.isSynthetic());
  }
}
