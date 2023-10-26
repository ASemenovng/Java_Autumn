package interfaces;

import java.util.Stack;

public interface Queue<E> {

  void add(E el);

  E peek();

  E remove();

  boolean isEmpty();

  int size();
}
