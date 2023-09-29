package abst;

public class Dog extends Pet {

  protected Dog(String name, int age) {
    super(name, age);
  }

  @Override
  public void walk() {
    System.out.println("I,m " + getName() + " and i'm gonna walk");
  }
}
