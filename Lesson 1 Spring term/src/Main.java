import threads.CalcSquare;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    // task 1
    CalcSquare t1 = new CalcSquare(2);
    CalcSquare t2 = new CalcSquare(3);

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    // что если убрать .join()?
    System.out.println("task 1");
    System.out.printf("%d - %d", t1.getRes(), t2.getRes());

  }
}