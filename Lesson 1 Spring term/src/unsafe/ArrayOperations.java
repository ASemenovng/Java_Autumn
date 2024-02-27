package unsafe;

import static unsafe.UnsafeAccess.getUnsafeInstance;

import sun.misc.Unsafe;

// Unsafe предоставляет ряд методов для работы с массивами, включая создание массивов,
// чтение и запись элементов массива, а также выделение и освобождение памяти для массивов
public class ArrayOperations {
  public static void main(String[] args) {
    Unsafe unsafe = getUnsafeInstance();
    int length = 5;

    // Выделяем память для массива целых чисел
    long arrayAddress = unsafe.allocateMemory(length * Integer.BYTES);

    // Записываем значения в массив
    for (int i = 0; i < length; i++) {
      unsafe.putInt(arrayAddress + i * Integer.BYTES, i * 10);
    }

    // Читаем значения из массива
    for (int i = 0; i < length; i++) {
      int value = unsafe.getInt(arrayAddress + i * Integer.BYTES);
      System.out.println("Значение в ячейке " + i + ": " + value);
    }

    // Освобождаем память для массива
    unsafe.freeMemory(arrayAddress);
  }
}
