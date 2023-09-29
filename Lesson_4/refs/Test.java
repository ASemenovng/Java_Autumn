package refs;

import java.util.Arrays;
import refs.constructors.ObjectGenerator;
import refs.constructors.SimpleObject;

public class Test {

  public static void main(String[] args) {

    /*
    // 1
    SimpleGen gen = new SimpleGen();

    FunctionalGenerator generator = gen::getNumber;

    System.out.println(generator.next());
    System.out.println(generator.next());
    System.out.println(generator.next());

    // 2
    generator = SimpleGen::getRandomNumber;

    System.out.println(generator.next());
     */

    /*
    // 3
    FunctionalGenerator generator = ISimpleGen::getNumber;

    System.out.println(generator.next(new SimpleGen()));
     */

    // 4
    // ObjectGenerator generator = SimpleObject::new;

    // System.out.println(generator.generate());

    // ObjectGenerator generator = int[]::new;
    // System.out.println(Arrays.toString((int[])generator.generate(5)));

  }

}
