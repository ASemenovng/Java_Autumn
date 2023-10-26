package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

  private T[] array;
  private int next = 0;

  public ArrayIterator(T[] array) {
    this.array = array;
  }

  @Override
  public boolean hasNext() {
    return next + 1 < array.length;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return array[next++];
  }
}
