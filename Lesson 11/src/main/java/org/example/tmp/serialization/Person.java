package org.example.tmp.serialization;

import java.io.Serializable;

public class Person implements Serializable {

  private String name;
  private int age;
  private transient String password;

  public Person(String name, int age, String password) {
    this.name = name;
    this.age = age;
    this.password = password;
  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", password='" + password + '\'' +
        '}';
  }
}
