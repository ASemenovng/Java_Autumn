# Homework 
## Дедлайн - 20 ноября 15:30
Во всех задачах кроме последней нужно использовать стандартные колеекции из `java.util`.

## Task 1
### 3 балла
Напишите алгоритм [решето Эратосфена](https://ru.wikipedia.org/wiki/Решето_Эратосфена) для нахождения всех простых чисел меньше или равных n. В решении используйте `HashSet<>` - для начала заполните set всеми числами от 2 до n, а затем организуйте повторяющийся поиск.
```java
public static List<Integer> findPrimes(int n) {
  // your code
}
```

## Task 2
### 4 балла
Реализуйте метод, который будет менять местами ключи и значения в мапе. То есть значения из старой мапы становятся ключами в новой, а ключи из старой становятся значениями в новой. Учтите момент, что значения могут повториться, а значит в новой мапе несколько ключей могут соответствовать одному значению.
```java
public static <K, V> Map<V, Collection<K>> inverse(Map<? extends K, ? extends V> map){
  // your code
}
```

## Task 3
### 4 балла
Реализуйте методы, вычисляющие объединение, разность, симметрическую разность и пересечение двух множеств, не используя циклы. Вам пригодятся методы Set<> `addAll()`, `retainAll()` и `removeAll()`.
```java
public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
  // your code
}

public static <E> Set<E> intersection(Set<? extends E> s1, Set<? extends E> s2) {
  // your code
}

public static <E> Set<E> difference(Set<? extends E> s1, Set<? extends E> s2) {
  // your code
}

public static <E> Set<E> symmetricDifference(Set<? extends E> s1, Set<? extends E> s2) {
  // your code
}

```

## Task 4
### 15/20 баллов
Вам нужно реализовать свой хеш-сет `MyHashSet<>` на основе интерфейса `BaseSet<>` - интерфейс лежит в src/set
Если сделаете реализацию без `Iterable<>` - 15 баллов, если с ним - 20. Если будете делать с `Iterable<>`, то вам удобнее будет сделать свой итератор (рекомендация, а не требование), а не просто реализовывать анонимный класс внутри метода.

Ps. Писать сет на основе мапы внутри нельзя, нужна честная реализация. 

То есть на 15 баллов нужно имплементить интерфейс:
```java
public interface BaseSet<E> {
    // BaseSet methods
}
```

На 20 баллов нужно имплементить интерфейс:
```java
public interface BaseSet<E> extends Iterable<E> {
    // BaseSet methods
}
```
Помните, что идейно `HashSet<>` устроен также, как и `HashMap<>`, поэтому реализация внутри должна быть соответствующая, вам нужно сделать примерно все то, что мы делали с мапой на паре (должен быть указан `LOAD_FACTOR`, должна быть своя хеш-функция, индекс в элементе считается от хеша и тд), поэтому в итоге у вас должен быть какой-то такой класс(возьмите этот код за основу):
```java
public class MyHashSet<E> implements BaseSet<E> {

  private static final int DEFAULT_CAPACITY = 1 << 4;
  private static final int MAX_CAPACITY = 1 << 30;
  private static final float LOAD_FACTOR = 0.75f;

  private Node<E>[] table;
  private int size;


  private static int hash(Object key) {
    // your code
  }

  @SuppressWarnings("unchecked")
  private void resize() {
    // your code
  }



  @Override
  public void add(E e) {
    // your code
  }

  
  @Override
  public void remove(Object o) {
    // your code
  }


  @Override
  public void clear() {
    // your code
  }

  @Override
  public boolean contains(Object o) {
    // your code
  }

  @Override
  public boolean isEmpty() {
    // your code
  }


  @Override
  public int size() {
    // your code
  }



  @Override
  public boolean equals(Object o) {
    // your code
  }



  @Override
  public Iterator<E> iterator() {
    // your code
    // опционально, можно не делать
  }

  @Override
  public String toString() {
    // your code
  }


  static class Node<T> {

    final int hash;
    final T key;
    Node<T> next;

    public Node(int hash, T key, Node<T> next) {
      this.hash = hash;
      this.key = key;
      this.next = next;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return hash == node.hash && Objects.equals(key, node.key) && Objects.equals(
          next, node.next);
    }

    @Override
    public int hashCode() {
      return Objects.hash(hash, key, next);
    }

    @Override
    public String toString() {
      // your code
    }
  }
}
```
