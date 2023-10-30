package interfaces;

public interface Stack<E> {

  void push(E el);

  E peek();

  E pop();

  boolean isEmpty();

  int size();
}
