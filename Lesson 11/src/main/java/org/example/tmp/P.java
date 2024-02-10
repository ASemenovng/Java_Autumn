package org.example.tmp;

public class P {

  int x = 2;

  public int getX() {
    return x;
  }

  public static void main(String[] args) {
    P p = new C();
    System.out.println(p.x + " - " + p.getX());
  }
}
