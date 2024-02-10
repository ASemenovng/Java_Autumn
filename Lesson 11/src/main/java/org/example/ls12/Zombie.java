package org.example.ls12;

public class Zombie extends Entity {

  public Zombie(int hp, String name) {
    super(hp, name);
  }

  @Override
  public void damage(int damage) {
    hp -= damage / 2;
    System.out.println("Я получил урон, мое здоровье: " + hp);
  }

  @Override
  public void doSmth(Entity smbd) {
    smbd.damage(57);
  }
}
