package abst;

public abstract class Pet {

  private final String name;
  private int age;

  protected Pet(String name, int age){
    this.name = name;
    this.age = age;
  }

  public abstract void walk();

  public void birthday() {
    System.out.println("I have a birthday. I'm " + ++age);
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
