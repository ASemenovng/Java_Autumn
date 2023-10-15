package interfaces;

public interface Stack<E> {

  E push(E el);

  E peek();

  E pop();

  boolean isEmpty();

  int size();
}
