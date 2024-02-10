package org.example.in_out;

import java.util.List;

public class JSONObj {

  private int num;
  private String name;
  private List<String> list;

  public JSONObj(int num, String name, List<String> list) {
    this.num = num;
    this.name = name;
    this.list = list;
  }

  public JSONObj() {
  }

  @Override
  public String toString() {
    return "JSONObj{" +
        "num=" + num +
        ", name='" + name + '\'' +
        ", list=" + list +
        '}';
  }
}
