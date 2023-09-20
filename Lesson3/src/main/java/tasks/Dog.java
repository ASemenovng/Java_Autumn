package tasks;

public class Dog extends Pet{

  private int love;

  public Dog(String name, int age, int love) {
    super(name, age);
    this.love = love;
  }

  @Override
  public void sayHello() {
    if (love > 10) {
      super.sayHello();
    }
  }
}
