package shmoptional;

import java.util.NoSuchElementException;

public class Shmoptional<T> {

  private T value;

  public Shmoptional(T value) {
    this.value = value;
  }

  public T orElse(T other) {
    return value == null ? other : value;
  }

  public void set(T value) {
    this.value = value;
  }

  public T get() {
    if (value == null) {
      throw new NoSuchElementException();
    }
    return value;
  }
}
