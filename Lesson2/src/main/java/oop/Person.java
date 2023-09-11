package oop;

public class Person {

  private final String name;
  private int age;
  private String city;

  public Person() {
    this.name = "Default name";
  }


  public Person(String name, int age, String city) {
    this.name = name;
    this.age = age;
    this.city = city;
  }

  public Person(int age, String city) {
    this("Alex", age, city);
  }


  public static Person init(int age) {
    return age < 18 ? new Person("Young", age, "smth") : new Person("Old", age, "smth");
  }


  public void sayHi() {
    System.out.println("Hi, " + name);
  }

  public void sayHi(String name) {
    System.out.println("Hi, " + name);
  }

  public void birthday() {
    makeOlder();
    System.out.println("Oh, I've gotten older! I'm " + age + " years.");
  }

  private void makeOlder() {
    age += 1;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age > 0) {
      this.age = age;
    }
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", city='" + city + '\'' +
        '}';
  }
}