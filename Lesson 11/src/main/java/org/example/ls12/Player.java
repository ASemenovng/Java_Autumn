package org.example.ls12;

public class Player extends Entity {

  public Player(int hp, String name) {
    super(hp, name);
  }

  @Override
  public void damage (int damage) {
    hp -= damage;
    System.out.println("Я получил урон, мое здоровье: " + hp);
  }

  @Override
  public void doSmth (Entity smbd) {
    System.out.println("Hello, " + smbd.name + "!");
  }
}
