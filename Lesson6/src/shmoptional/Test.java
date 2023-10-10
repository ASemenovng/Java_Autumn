package shmoptional;

public class Test {

  public static void main(String[] args) {
    Shmoptional<? extends Number> present = new Shmoptional<>(10);




    System.out.println(present.get());
  }




}
