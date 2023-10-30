package classes;

import interfaces.List;

public class LinkedList<E> implements List<E> {

  private Node<E> head;


  public void add(E el) {

    Node<E> newNode = new Node<>(el);

    if (head == null) {
      head = newNode;
    } else {
      Node<E> current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
  }

  public void insertHead(E el) {
    Node<E> node = new Node<>(el);
    node.next = head;
    head = node;
  }

  public void printList() {
    Node<E> current = head;
    System.out.print("[");
    while (current.next != null) {
      System.out.print(current.value + ", ");
      current = current.next;
    }
    System.out.print(current.value + "]");
  }

  @Override
  public void add(int index, E el) {
    Node<E> newNode = new Node<>(el);

    if (index < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }
    if (head == null) {
      if (index != 0) {
        throw new ArrayIndexOutOfBoundsException();
      }
      head = newNode;
    } else {
      Node<E> current = head;
      if (index == 0) {
        insertHead(el);
      } else {
        for (int i = 0; i < index - 1; ++i) {
          current = current.next;
          if (current == null) {
            throw new ArrayIndexOutOfBoundsException();
          }
        }
        newNode.next = current.next;
        current.next = newNode;
      }
    }
  }

  @Override
  public E get(int index) {
    return null;
  }

  @Override
  public void remove(int index) {

  }


  private static class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
      this.value = value;
    }

    public T getValue() {
      return value;
    }

    public void setValue(T value) {
      this.value = value;
    }

    public Node<T> getNext() {
      return next;
    }

    public void setNext(Node<T> next) {
      this.next = next;
    }
  }
}
