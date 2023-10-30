import classes.ArrayDeque;
import interfaces.Deque;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    Comparator<String> comp = (s, t) -> s.charAt(1) - t.charAt(0);


    Deque<Integer> aq = new ArrayDeque<>();
    for (int i = 0; i < 50; i++) {
      aq.addLast(i);
    }
    aq.printDeque();


    for (int i = 0; i < 48; i++) {
      aq.removeFirst();
    }
    aq.printDeque();

    aq.addFirst(1);
    aq.addLast(2);
    aq.removeFirst();
    aq.printDeque();
  }

  static Iterable<String> remove(Iterable<String> iterable) {
    Iterator<String> iterator = iterable.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().isEmpty()) {
        iterator.remove();
      }
    }
    return iterable;
  }

  void printAll(Iterable<?> iterable) {
    Iterator<?> iterator = iterable.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  static Set<Integer> rangeSet(int from, int to) {
    return new AbstractSet<Integer>() {
      @Override
      public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
          int next = from;
          @Override
          public boolean hasNext() {
            return next != to;
          }

          @Override
          public Integer next() {
            if (next == to) {
              throw new NoSuchElementException();
            }
            return next++;
          }
        };
      }

      @Override
      public int size() {
        return to - from;
      }
    };
  }
}