# Homework 
## Дедлайн - 6 ноября 15:30
Ниже приведены условия задач, сейчас у вас есть тесты для проверки работы вашего кода. В src/main/java есть две папки - hw_classes и hw_iterator - там классы, которые вам нужно реализовать. Код для задач 3 и 5 лежит в классе ListUtil в hw_classes. В src/test/java есть тестовые классы, которые нужно запускать (большой зеленый треугольничек возле имени тест класса) и тестить ваш код (после того, как вы его напишите). Тесты пройдены, если у вас они горят зеленым. Выглядеть это будет как-то так: <image src="/src/main/resources/тесты неуспех.png" alt="">
 (тут тесты не работают, так как код не написан).

 Чтобы все это запускать, вам нужен будет maven (можно кратко глянуть, что это такое, сильно вникать пока не обязательно), установите его себе, склонируйте эту ветку и можете тестить. Чтобы установить maven:
 - на linux:
   ```bash
   sudo apt update
   sudo apt install maven
   mvn --version
   ```
 - на mac:
   ```bash
   brew install maven
   mvn --version
   ```
   Если вдруг `brew` у вас нет, [вот так](https://www.youtube.com/watch?v=IWJKRmFLn-g) его можно установить.
 - на винде:
   примите соболезнования и ссылку на [официальный сайт для скачивания](https://maven.apache.org/download.cgi)
   
Команда `mvn --version` должна выдать что-то вроде Apache Maven 3.9.5 и еще сколько-то строчек инфы.

## Task 1
### 12 баллов
Напишите `MyLinkedList` - двусвязный список. Реализуйте интерфейс List из interfaces(метод `add(int index, E el)` бросает исключение, если индекс больше длины списка).
- Методы `getFirst(), getLast()` должны работать за O(1).

```java
public class MyLinkedList<E> implements List<E>, Iterable<E>{

    public void add(E el) {}

    // вставка в начало списка
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

    // очистка списка, после применения меода список должен быть пустой
    public void clear() {}


    @Override
    public MyIterator<E> iterator() {
        return null;
    }
}
```

Также вам нужно создать рядом (в отдельном файле) свой класс `MyIterator` - наследника стандартного `Iterator`.

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
Ваш класс `MyLinkedList` должен реализовывать интерфейс `Iterable`, но в методе `iterator()` возвращать `MyIterator`.

## Task 2
### 8 баллов
Напишите обобщенный класс `MyDeque`. Реализуйте интерфейс `Deque` из interfaces. Класс `MyDeque` должен быть реализован через два стека внутри(стек используйте стандартный из `java.util`). 

## Task 3
### 4 балла
Реализуйте статический метод `rangeList` - здесь в возвращаемом значение `List<>` также из `java.util`. Все указанные методы должны быть оптимально переопределены.
- `indexOf()` возвращает первый индекс элемента и -1, если такого нет
- `lastIndexOf()` возвращает последний индекс элемента и -1, если такого нет

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
Напишите класс `DoubleIterator`, который будет в конструкторе принимать два стандартных итератора и итерироваться по ним (сначала по одному до конца, затем по второму).

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
Реализуйте метод `medianSort`, который принимает список чисел и сортирует их в порядке удаления от медианы. (Чем число ближе к медиане, тем левее оно в итоговом списке) Внутри метода должен использоваться компаратор(Можно его сделать через `Comparator.comparingInt()`). Если элементы одинаково удалены от медианы, то левее будет меньшее число. Элементы в списке могут повторяться.
> Медиана набора чисел — число, которое находится в середине этого набора, если его упорядочить по возрастанию, то есть такое число, что половина из элементов набора не меньше него, а другая половина не больше. Например для [1, 3, 7] медиана = 3, для [1, 3, 5, 7] медиана = 4
```java
public static List<Integer> medianSort(List<Integer> list) {
  return null;
} 
```

## Task 6
### 4 балла
Дан класс `Employee`. В методе compareTo нужно реализовать сравнение через имя без учета регистра, а если имя совпало, то сравнение по возрасту.
Также нужно заполнить три статик переменные для кастомных компараторов:
- `AGE_SALARY_COMPARATOR` сортирует сначала по возрасту, затем по зарплате
- `PASSPORT_COMPARATOR` сортирует сначала по имени, затем по фамилии и затем по городу (тут регистр важен)
- `FULL_COMPARATOR` сортирует по всем полям класса по очереди начиная с имени и заканчивая зп.

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
