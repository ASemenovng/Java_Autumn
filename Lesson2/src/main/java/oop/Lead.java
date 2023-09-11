package oop;

public class Lead extends Employee{

  private String team;


  public Lead(String name, int age, String city, String company, int salary, String team) {
    super(name, age, city, company, salary);
    this.team = team;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  @Override
  public void sayHi() {
    System.out.println("Hi, " + /*super.*/getName() + ". I'm lead");
  }
}
