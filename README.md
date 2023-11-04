# Homework 
## Дедлайн - 20 ноября 15:30

## Task 1
### 3 балла
Напишите алгоритм **решето Эратосфена** для нахождения всех простых чисел меньше или равных n. В решении используйте `HashSet<>` - для начала заполните set всеми числами от 2 до n, а затем организуйте повторяющийся поиск.
```java
public static List<Integer> findPrimes(int n) {
  // your code
}
```

## Task 2
### 4 балла
Реализуйте метод, который будет менять местами ключи и значения в мапе. Учтите момент, что значения могут повториться, а значит в новой мапе несколько ключей могут соответствовать одному значению.
```java
public static <K, V> Map<V, Collection<K>> inverse(Map<? extends K, ? extends V> map){
  // your code
}
```

## Task 3
### 4 балла
Реализуйте методы, вычисляющие объединение, разность и пересечение двух множеств, не используя циклы. Вам пригодятся методы Set<> `addAll`, `retainAll` и `removeAll`.
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

```
