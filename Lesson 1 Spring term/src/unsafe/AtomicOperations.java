package unsafe;

import static unsafe.UnsafeAccess.getUnsafeInstance;

import sun.misc.Unsafe;

// Unsafe предоставляет методы для выполнения атомарных операций
public class AtomicOperations {
  public static void main(String[] args) {
    Unsafe unsafe = getUnsafeInstance();

    long memoryAddress = unsafe.allocateMemory(4);
    unsafe.putInt(memoryAddress, 42);

    // Атомарная замена значения, если оно равно 42
    boolean swapped = unsafe.compareAndSwapInt(null, memoryAddress, 42, 100);

    int value = unsafe.getInt(memoryAddress);

    System.out.println("Значение после операции: " + value);
    System.out.println("Замена выполнена: " + swapped);

    unsafe.freeMemory(memoryAddress);
  }
}

