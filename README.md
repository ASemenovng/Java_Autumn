# Homework 
## Дедлайн - 6 ноября 15:30
Ниже приведены задачи, функциональность каждой нужно проверить в методе main.

## Task 1
### 7 балла
Напишите MyLinkedList - двусвязный список. Реализуйте интерфейс List из interfaces(метод add(int index, E el) бросает исключение, если индекс больше длины списка).
```java
public class LinkedList<E> implements List<E>, Iterable<E>{

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

  public E getFirst() {
    return null;
  }

  public E getLast() {
    return null;
  }

  @Override
  public MyIterator<E> iterator() {}
}
```

Также вам нужно создать рядом (в отдельном файле) свой класс MyIterator - наследника стандартного Iterator.
```java
public class MyIterator<E> implements Iterator<E> {

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public E next() {
    return null;
  }

  public boolean hasPrevious() {
    return false;
  }

  public E previous() {
    return null;
  }
}
```
Ваш класс должен реализовывать интерфейс Iterable, но в методе iterator() возвращать MyIterator.

## Task 2
### 5 балла
Напишите обобщенный класс MyDeque, Реализуйте интерфейс Deque из interfaces. Класс MyDeque должен быть реализован через два стека внутри(стек используйте стандартный из java.util). 

## Task 3
### 3 балла
Реализуйте статический метод rangeList - здесь в возвращаемом значение List<> также из java.util. Все указанные методы должны быть оптимально переопределены.
```java
static List<Integer> rangeList(int from, int to) {
    return new AbstractList<>() {
      @Override
      public Integer get(int index) {
        return null;
      }

      @Override
      public int size() {
        return 0;
      }

      @Override
      public int indexOf(Object o) {
        return super.indexOf(o);
      }

      @Override
      public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
      }

      @Override
      public boolean contains(Object o) {
        return super.contains(o);
      }
    };
  }
```

## Task 4
### 3 балла
Напишите класс DoubleIterator, который будет в конструкторе принимать два стандартных итератора и итерироваться по ним (сначала по одному до конца, затем по второму).
```java
public class DoubleIterator<T> implements Iterator<T> {

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public T next() {
    return null;
  }
}
```

## Task 5
### 2 балла

