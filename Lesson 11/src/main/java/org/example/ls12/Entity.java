package org.example.ls12;

import java.lang.ref.SoftReference;

public abstract class Entity {
  int hp;
  String name;

  public Entity(int hp, String name) {
    this.hp = hp;
    this.name = name;
  }

  public abstract void damage(int damage);

  public abstract void doSmth(Entity smbd);
}
