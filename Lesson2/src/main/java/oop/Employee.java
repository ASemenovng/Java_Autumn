package oop;

public class Employee extends Person {

  public static long generalId;
  private long id;
  private String company;
  private int salary;

  static {
    generalId = 1;
  }

  public Employee(String name, int age, String city, String company, int salary) {
    super(name, age, city);
    this.company = company;
    this.salary = salary;
  }

  public void hired() {
    company = "None";
  }
  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public long getId() {
    return id;
  }
  public void setId() {
    id = generalId++;
  }

  @Override
  public void sayHi() {
    System.out.println("Hi, " + /*super.*/getName() + ". I'm employee");
  }

  public static void foo() {
    System.out.println("static foo");
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", company='" + company + '\'' +
        ", salary=" + salary +
        '}';
  }
}
