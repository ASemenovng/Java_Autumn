package interfaces;

import java.util.NoSuchElementException;

public interface Deque<E> {

  void addFirst(E e);

  void addLast(E e);

  E removeFirst();

  E removeLast();

  E peekFirst();

  E peekLast();

  boolean isEmpty();

  int size();
}
