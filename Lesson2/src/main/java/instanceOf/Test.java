package instanceOf;

public class Test {

  public static void main(String[] args) {
    Object string = "this is string!";

    // было давно
    if (string instanceof String) {
      String realString = (String) string;
      System.out.println(realString);
    }

    // было недавно
    if (string instanceof String) {
      var realString = (String) string;
      System.out.println(realString);
    }

    // стало сейчас
    if (string instanceof String realString) {
      System.out.println(realString);
    }

    // так тоже можно
    if (!(string instanceof String s) || s.isEmpty()) {
      return;
    }
    System.out.println("String: " + s.trim());
  }

}
