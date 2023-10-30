package classes;

import interfaces.Deque;
import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E>, Iterable<E> {

  private Object[] items;
  private int size;
  private int nextFirst;
  private int nextLast;
  private static final int CAPACITY = 8;

  public ArrayDeque() {
    items = new Object[CAPACITY];
    size = 0;
    nextFirst = 0;
    nextLast = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  public int minusOne(int index) {
    return Math.floorMod(index - 1, items.length);
  }


  public int plusOne(int index) {
    return Math.floorMod(index + 1, items.length);
  }

  public int plusOne(int index, int length) {
    return Math.floorMod(index + 1, length);
  }

  private void resize() {
    if (size == items.length) {
      expand();
    }
    if (size < items.length / 4 && items.length > CAPACITY) {
      reduce();
    }
  }

  private void expand() {
    resizeHelper(items.length * 2);
  }

  private void reduce() {
    resizeHelper(items.length / 2);
  }

  private void resizeHelper(int capacity) {
    Object[] temp = items;
    int begin = plusOne(nextFirst);
    int end = minusOne(nextLast);
    items = new Object[capacity];
    nextFirst = 0;
    nextLast = 1;
    for (int i = begin; i != end; i = plusOne(i, temp.length)) {
      items[nextLast] = temp[i];
      nextLast = plusOne(nextLast);
    }
    items[nextLast] = temp[end];
    nextLast = plusOne(nextLast);
  }

  @Override
  public void addFirst(E item) {
    resize();
    items[nextFirst] = item;
    nextFirst = minusOne(nextFirst);
    size++;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E peekFirst() {
    return (E) items[plusOne(nextFirst)];
  }

  @Override
  public E removeFirst() {
    resize();
    E res = peekFirst();
    nextFirst = plusOne(nextFirst);
    items[nextFirst] = null;
    size--;
    return res;
  }

  @Override
  public void addLast(E item) {
    resize();
    items[nextLast] = item;
    nextLast = plusOne(nextLast);
    size++;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E peekLast() {
    return (E) items[minusOne(nextLast)];
  }

  @Override
  public E removeLast() {
    resize();
    E res = peekLast();
    nextLast = minusOne(nextLast);
    items[nextLast] = null;
    size--;
    return res;
  }

  @Override
  public void printDeque() {
    for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
      System.out.print(items[i] + " ");
    }
    System.out.println();
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }
}