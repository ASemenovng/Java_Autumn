package org.example.tmp;

public class A {

  private String field;

  public A(String field) {
    this.field = field;
  }

  public void foo() {
    field = "mod".concat(field);
  }

  @Override
  public String toString() {
    return "A{" +
        "field='" + field + '\'' +
        '}';
  }
}
