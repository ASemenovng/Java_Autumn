package hw_iterator;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }

    public boolean hasPrevious() {
        return false;
    }

    public T previous() {
        return null;
    }
}
