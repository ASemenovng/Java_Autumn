package tasks;

public class Main {

  public static void main(String[] args) {
    Vector a = new Vector(2, 8);
    Vector b = new Vector(1, 5);
    Cat myCat = new Cat("1", 18, 9);
    Dog myDog = new Dog("2", 1, 11);
    myCat.sayHello();

    Pet[] animals = {myCat, myDog};

    // class Vector, Pet, поля/методы/конструктор

    // static, final

    // OOP

    // фабричная инициализация (static)

    // private, protected...

    // extends

    // overload, override

    // instanceof

    // singleton

    // record

    // sealed

    // полиморфизм

    // upcast, downcast

    // enum
  }


  // Напишите метод, который находит сумму всех чисел от 1 до n.
  int sum(int n) {
    int sum = 0;
    for (int i = 1; i < n; i++) {
      sum += i;
    }
    return sum;
  }


  // Напишите проверку, является ли заданное число простым.
  static boolean isSimple(int n) {
    for (int i = 2; i < Math.sqrt(n) + 1; ++i) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  // binarySearch recursion
  static int binary(int[] n, int value, int l, int r) {
    int mid = (l + r) / 2;
    if (l == r) {
      return value == n[mid] ? l : -1;
    }

    if (value < n[mid]) {
      return binary(n, value, l, mid - 1);
    } else if (value > n[mid]) {
      return binary(n, value, mid + 1, r);
    }
    return mid;

  }

  // binarySearch not recursion
  static int binary(int[] arr, int goal) {
    int l = 0;
    int r = arr.length - 1;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (arr[mid] == goal) {
        return mid;
      }
      if (arr[mid] > goal) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    if (arr[l] == goal) {
      return l;
    }
    return -1;
  }

  // напишите калькулятор
  static double calc(double x, double y, String operand) {
    return switch (operand) {
      case "+" -> x + y;
      case "-" -> x - y;
      case "*" -> x * y;
      case "/" -> x / y;
      case "^" -> Math.pow(x, y);
      case "sqrt" -> Math.pow(x, 1 / y);
      default -> throw new IllegalStateException();
    };
  }

  // проверить число на четность

  // написать сложение матриц

  // написать транспонирование матриц (квадратных/любых)

  // написать умножение матриц


}