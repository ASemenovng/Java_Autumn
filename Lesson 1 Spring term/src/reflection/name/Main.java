package reflection.name;

public class Main {

  public static void main(String[] args) {

    Employee e = new Employee();
    Employee m = new Manager();

    Class<? extends Employee> ce = e.getClass();
    Class<? extends Employee> cm = m.getClass();

    System.out.printf("%s, %s%n", ce.getName(), cm.getName());

    System.out.printf("%s, %s%n", ce.getSimpleName(), cm.getSimpleName());
  }

}
