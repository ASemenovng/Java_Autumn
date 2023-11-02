package hw_classes;

import interfaces.List;
import hw_iterator.MyIterator;

public class MyLinkedList<E> implements List<E>, Iterable<E>{

    public void add(E el) {}

    public void insertHead(E el) {}

    public void printList() {}

    @Override
    public void add(int index, E el) {}

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void remove(int index) {}

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public E getFirst() {
        return null;
    }

    public E getLast() {
        return null;
    }

    public void clear() {}


    @Override
    public MyIterator<E> iterator() {
        return null;
    }
}