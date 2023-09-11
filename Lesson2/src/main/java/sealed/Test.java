package sealed;

public class Test {

  public static void main(String[] args) {
    process();
  }

  static void process() {
    if (new Test() instanceof Runnable) {
      System.out.println("yes");
    }

    // тут будет ошибка
    /*
    if (new Student() instanceof Runnable) {
      System.out.println("yes");
    }
     */


    if (new Curator() instanceof Runnable) {
      System.out.println("yes");
    }
  }

}
