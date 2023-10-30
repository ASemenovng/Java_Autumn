package classes;

import interfaces.List;
import java.util.Arrays;

public class ArrayList<E> implements List<E> {
  private Object[] array;
  int size;
  int last = 0;
  public ArrayList(int len){
    array = new Object[len];
  }

  @Override
  public void add(E el){
    if (last >= array.length) {
      array = Arrays.copyOf(array, array.length * 2);
    }
    array[last] = el;
    last ++;
  }

  @Override
  public void add(int index, E el) {

  }

  @Override
  public E get(int index) {
    return null;
  }

  @Override
  public void remove(int index) {
    array[last] = null;
    last--;
    if (last <= array.length / 4){
      array = Arrays.copyOf(array, array.length / 2);
    }
  }
}
