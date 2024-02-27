package unsafe;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class LockFreeQueue<T> {

  private static final Unsafe unsafe = getUnsafe();
  private Node<T> head;
  private volatile Node<T> tail;

  private static long tailOffset;

  static {
    try {
      tailOffset = unsafe.objectFieldOffset(LockFreeQueue.class.getDeclaredField("tail"));
    } catch (Exception ex) {
      throw new Error(ex);
    }
  }

  private static Unsafe getUnsafe() {
    try {
      Field f = Unsafe.class.getDeclaredField("theUnsafe");
      f.setAccessible(true);
      return (Unsafe) f.get(null);
    } catch (Exception e) {
      throw new RuntimeException("Could not obtain access to sun.misc.Unsafe", e);
    }
  }

  private static class Node<T> {

    volatile T item;
    volatile Node<T> next;

    Node(T item) {
      this.item = item;
    }
  }

  public LockFreeQueue() {
    head = new Node<>(null);
    tail = head;
  }

  public void enqueue(T item) {
    Node<T> newNode = new Node<>(item);
    Node<T> currentTail;
    while (true) {
      currentTail = tail;
      Node<T> tailNext = currentTail.next;
      if (currentTail == tail) {
        if (tailNext != null) {
          // Этот случай происходит, если в очереди имеется следующий узел после хвоста,
          // то есть если другой поток уже изменил хвост, но еще не успел вставить свой элемент.
          // Мы помогаем его операцию завершить, делая CAS на хвосте.
          unsafe.compareAndSwapObject(this, tailOffset, currentTail, tailNext);
        } else {
          // Пытаемся добавить новый узел как хвост списка
          if (unsafe.compareAndSwapObject(currentTail, tailOffset, null, newNode)) {
            // Делаем CAS, чтобы обновить хвост. Если CAS прошел успешно, значит мы обновили хвост.
            unsafe.compareAndSwapObject(this, tailOffset, currentTail, newNode);
            return;
          }
        }
      }
    }
  }
}
