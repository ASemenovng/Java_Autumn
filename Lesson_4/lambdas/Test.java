package lambdas;

import lambdas.summator.Summator;

public class Test {

  public static void main(String[] args) {

    //
    MyInterface my = () -> 10;

    MyInterface newMy = () -> {
      String result = "";
      for (int i = 0; i < 10 ; ++i)
        result += i;
      return Integer.parseInt(result); // здесь return - выход из лямбда функции, а не из функции main
    };
    System.out.println(newMy.doSmth());

    StringModificator modificator = text -> text.substring(0, 3);
    System.out.println(modificator.getString("1234567"));





    // анонимный класс
    SumAndMultiply sumAndMultiply = new SumAndMultiply() {
      @Override
      public int apply(int x, int y, int z) {
        return (x + y) * z;
      }
    };

    // лямбда
    SumAndMultiply lambdaSumAndMultiply = (x, y, z) -> (x + y) * z;

    // итог один и тот же
    System.out.println(sumAndMultiply.apply(1, 2, 3));
    System.out.println(lambdaSumAndMultiply.apply(1, 2, 3));
  }

  public static Summator getSummator(final int[] arr) {
    return () -> {
      int res = 0;
      for (int i : arr) {
        res += i;
      }
      return res;
    };
  }
}
