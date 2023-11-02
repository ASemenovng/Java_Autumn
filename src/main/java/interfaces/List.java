package interfaces;

public interface List<E> {

  void add(E el);

  void add(int index, E el);

  E get(int index);

  void remove(int index);

  int size();

  boolean isEmpty();
}
