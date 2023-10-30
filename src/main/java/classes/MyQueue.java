package classes;

import interfaces.Queue;
import java.util.Arrays;

public class MyQueue<E> implements Queue<E> {

  private E[] arr;
  private int t;
  private int h;
  private static final int CAPACITY = 8;

  @SuppressWarnings("unchecked")
  public MyQueue() {
    arr = (E[]) new Object[CAPACITY];
  }

  @Override
  public void add(E el) {
    if (t == arr.length) {
      arr = Arrays.copyOf(arr, t * 2);
    }
    arr[t++] = el;
  }

  @Override
  public E peek() {
    return arr[h];
  }

  @Override
  public E remove() {
    E res = arr[h];
    arr[h++] = null;
    checkH();
    return res;
  }

  @Override
  public boolean isEmpty() {
    return t == h;
  }

  @Override
  public int size() {
    return arr.length;
  }

  private void checkH(){
    if (h >= 10){
      E[] newArr = (E[]) new Object[arr.length - h];
      for (int i = h; i < t; i++) {
        newArr[i - h] = arr[i];
      }
      arr = newArr;
      t -= h;
      h = 0;
    }
  }
}
