package oop;

import java.util.Arrays;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    Person defaultPerson = new Person();
    Person newPerson = new Person(21, "Moscow");

    System.out.println("person.name: " + newPerson.getName());
    newPerson.sayHi();
    newPerson.sayHi("John");
    newPerson.birthday();
    newPerson.birthday();

    defaultPerson = Person.init(26);
    System.out.println(defaultPerson);
    defaultPerson.birthday();


    Person person = new Person("Alex", 21, "Moscow");
    Person employee = new Employee("Bob", 21, "London", "Google", 10_000);
    Person lead = new Lead("Lui", 21, "NY", "ARM", 100_000, "main team");
    List<Person> people = Arrays.asList(person, employee, lead);
    for(Person p: people) {
      p.sayHi();
    }
  }
}
