package interfaces;

public interface List<E> {

  void add(int index, E el);

  E get(int index);

  void remove(int index);
}
