# Homework 
## Дедлайн - 6 ноября 15:30
Ниже приведены задачи, функциональность каждой нужно проверить в методе main.

## Task 1
### 9 баллов
Напишите MyLinkedList - двусвязный список. Реализуйте интерфейс List из interfaces(метод add(int index, E el) бросает исключение, если индекс больше длины списка). Методы getFirst(), getLast() должны работать за O(1).
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
  public MyIterator<E> iterator() {
    return null;
  }
}
```

Также вам нужно создать рядом (в отдельном файле) свой класс MyIterator - наследника стандартного Iterator.
```java
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
```
Ваш класс MyLinkedList должен реализовывать интерфейс Iterable, но в методе iterator() возвращать MyIterator.

## Task 2
### 8 баллов
Напишите обобщенный класс MyDeque, Реализуйте интерфейс Deque из interfaces. Класс MyDeque должен быть реализован через два стека внутри(стек используйте стандартный из java.util). 

## Task 3
### 4 балла
Реализуйте статический метод rangeList - здесь в возвращаемом значение List<> также из java.util. Все указанные методы должны быть оптимально переопределены.
- indexOf() возвращает первый индекс элемента и -1, если такого нет
- lastIndexOf() возвращает последний индекс элемента и -1, если такого нет
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
### 4 балла
Реализуйте метод sort, который принимает список чисел и сортирует их в порядке удаления от медианы. (Чем число ближе к медиане, тем левее оно в итоговом списке) Внутри метода должен использоваться компаратор(Можно его сделать через Comparator.comparingInt()). Элементы в списке могут повторяться.
> Медиана набора чисел — число, которое находится в середине этого набора, если его упорядочить по возрастанию, то есть такое число, что половина из элементов набора не меньше него, а другая половина не больше. Например для [1, 3, 7] медиана = 3, для [1, 3, 5, 7] медиана = 4
```java
public static List<Integer> medianSort(List<Integer> list) {
  return null;
} 
```

## Task 6
### 4 балла
Дан класс Employee. В методе compareTo нужно реализовать сравнение через имя без учета регистра, а если имя совпало, то сравнение по возрасту.
Также нужно заполнить три статик переменные для кастомных компараторов:
- AGE_SALARY_COMPARATOR сортирует сначала по возрасту, затем по зарплате
- PASSPORT_COMPARATOR сортирует сначала по имени, затем по фамилии и затем по городу (тут регистр важен)
- FULL_COMPARATOR сортирует по всем полям класса по очереди начиная с имени и заканчивая зп.
```java
public class Employee implements Comparable<Employee> {

  private String name;
  private String surname;
  private String city;
  private int age;
  private int salary;

  public static final Comparator<Employee> AGE_SALARY_COMPARATOR;
  public static final Comparator<Employee> PASSPORT_COMPARATOR;
  public static final Comparator<Employee> FULL_COMPARATOR;
}
```
