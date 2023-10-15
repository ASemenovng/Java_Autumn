package interfaces;

import java.util.Stack;

public interface Queue<E> {

  E add(E el);

  E peek();

  E remove();

  boolean isEmpty();

  int size();
}
