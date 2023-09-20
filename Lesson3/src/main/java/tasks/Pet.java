package tasks;

public class Pet {
  private final String name;
  private int age;


  public Pet (String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void sayHello() {
    System.out.println("Hello, I am " + name);
  }

}
