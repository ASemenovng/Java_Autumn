package org.example.serr;

import java.io.*;

public class Person implements Serializable {
  private String name;
  private int age;
  private transient String socialSecurityNumber;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Person(String name, int age, String socialSecurityNumber) {
    this.name = name;
    this.age = age;
    this.socialSecurityNumber = socialSecurityNumber;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
        '}';
  }
}