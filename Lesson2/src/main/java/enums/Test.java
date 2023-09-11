package enums;

import enums.EnumInheritanceTest.Operator;

public class Test {

  public static void main(String[] args) {
    DayOfWeek sunday = DayOfWeek.SUNDAY;
    String sundayStr = sunday.getText();

    for (DayOfWeek day: DayOfWeek.values()) {
      System.out.println(day.getText());
    }

    // Пример того, как можно "отнаследоваться от enum"
    System.out.println("Plus: " + Operator.PLUS.calculate(7, 3));
    System.out.println("Minus: " + Operator.MINUS.calculate(7, 3));
    System.out.println("Multiply: " + Operator.MULTIPLICATION.calculate(7, 3));

    System.out.println(Operator.PLUS instanceof Operator);
    System.out.println(Operator.PLUS.getClass());

  }
}
