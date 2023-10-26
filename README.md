# Homework 
## Дедлайн - 6 ноября 15:30
Ниже приведены задачи, функциональность каждой нужно проверить в методе main.

## Task 1
### 7 балла
Напишите MyLinkedList - двусвязный список. Реализуйте интерфейс List из interfaces(метод add(int index, E el) бросает исключение, если индекс больше длины списка). Также вам нужно создать рядом (в отдельном файле) свой класс MyIterator - наследника стандартного Iterator.
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
### 4 балла

## Task 3
### 2 балла

## Task 4
### 2 балла

## Task 5
### 2 балла

