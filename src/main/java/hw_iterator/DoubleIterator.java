package hw_iterator;

import java.util.Iterator;

public class DoubleIterator<T> implements Iterator<T> {

    public DoubleIterator(Iterator<T> first, Iterator<T> second) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}