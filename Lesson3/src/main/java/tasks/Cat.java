package tasks;

public class Cat extends Pet {
  private int lifes;

  public Cat(String name, int age, int lifes) {
    super(name, age);
    this.lifes = lifes;
  }

  @Override
  public void sayHello() {
    System.out.println("Hello, I am cat" + getName());
  }

  public void death() {
    lifes -= 1;
  }

  public void printLifes() {
    System.out.println(lifes);
  }
}
