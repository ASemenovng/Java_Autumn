import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> implements IMap<K, V> {

  private static final int DEFAULT_CAPACITY = 16;
  private static final int MAX_CAPACITY = 1 << 30;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  private final float loadFactor;

  private int size;

  private int threshHold;
  private Entry<K, V>[] table;

  @SuppressWarnings("unchecked")
  public MyHashMap(int capacity, float loadFactor) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Illegal capacity: " + capacity);
    }
    if (loadFactor < 0 || Float.isNaN(loadFactor)) {
      throw new IllegalArgumentException("Illegal loadFactor: " + loadFactor);
    }
    if (capacity > MAX_CAPACITY) {
      capacity = MAX_CAPACITY;
    }

    int cap = 1;
    while (cap < capacity) {
      cap <<= 1;
    }
    this.threshHold = (int) (cap * loadFactor);
    this.loadFactor = loadFactor;
    this.table = (Entry<K, V>[]) new Entry[cap];
  }

  public MyHashMap(int capacity) {
    this(capacity, DEFAULT_LOAD_FACTOR);
  }

  public MyHashMap() {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  static int hash(int h, int length) {
    h ^= (h >>> 20) ^ (h >>> 12);
    h ^= (h >>> 7) ^ (h >>> 4);
    return h & (length - 1);
  }

  @Override
  public V get(Object key) {
    if (key == null) {
      if (table[0] == null) {
        return null;
      }
      Entry<K, V> tE = table[0];
      while (tE.key != null) {
        if (tE.next == null) {
          return null;
        }
        tE = tE.next;
      }
      return tE.value;
    } else {
      int hashKey = hash(key.hashCode(), table.length);
      if (table[hashKey] == null) {
        return null;
      } else {
        Entry<K, V> tE = table[hashKey];
        while (tE.key == null || !(tE.key.equals(key))) {
          if (tE.next == null) {
            return null;
          } else {
            tE = tE.next;
          }
        }
        return tE.value;
      }
    }
  }

  @Override
  public void put(K key, V value) {
    if (size > threshHold) {
      resize(2 * table.length);
    }

    if (key == null) {
      if (table[0] == null) {
        Entry<K, V> newE = new Entry<>(null, value, null);
        table[0] = newE;
        size++;
      } else {
        Entry<K, V> e = table[0];
        while (e.key != null) {
          if (e.next == null) {
            Entry<K, V> newE = new Entry<>(null, value, table[0]);
            table[0] = newE;
            size++;
            return;
          } else {
            e = e.next;
          }
        }
        e.value = value;
      }
    } else {
      int hashKey = hash(key.hashCode(), table.length);
      if (table[hashKey] == null) {
        Entry<K, V> newE = new Entry<>(null, value, null);
        table[hashKey] = newE;
        size++;
      } else {
        Entry<K, V> e = table[hashKey];
        while (e.key != null || !(e.key.equals(key))) {
          if (e.next == null) {
            Entry<K, V> newE = new Entry<>(key, value, table[hashKey]);
            table[hashKey] = newE;
            size++;
          } else {
            e = e.next;
          }
        }
        e.value = value;
      }
    }
  }

  @Override
  public V remove(Object key) {
    if (key == null) {
      if (table[0] == null) {
        return null;
      } else {
        Entry<K, V> tE = table[0];
        if (table[0].key == null) {
          Entry<K, V> keyToRemove = table[0];
          V vR = keyToRemove.value;
          table[0] = keyToRemove.next;
          size--;
          return vR;
        } else {
          Entry<K, V> prev = null;
          while (tE.key != null) {
            if (tE.next == null) {
              return null;
            } else {
              prev = tE;
              tE = tE.next;
            }
          }
          V vR = tE.value;
          prev.next = tE.next;
          size--;
          return vR;
        }
      }
    } else {
      int hashKey = hash(key.hashCode(), table.length);
      if (table[hashKey] == null) {
        return null;
      } else {
        Entry<K, V> tE = table[hashKey];
        if (table[hashKey].key.equals(key)) {
          Entry<K, V> keyToRemove = table[hashKey];
          V vR = keyToRemove.value;
          table[hashKey] = keyToRemove.next;
          size--;
          return vR;
        } else {
          Entry<K, V> prev = null;
          while (tE.key == null || !(tE.key.equals(key))) {
            if (tE.next == null) {
              return null;
            } else {
              prev = tE;
              tE = tE.next;
            }
          }
          V vR = tE.value;
          Objects.requireNonNull(prev).next = tE.next;
          size--;
          return vR;
        }
      }
    }
  }

  @Override
  public boolean containsKey(Object key) {
    if (key == null) {
      if (table[0] == null) {
        return false;
      } else {
        Entry<K, V> tE = table[0];
        while (tE.key != null) {
          if (tE.next == null) {
            return false;
          } else {
            tE = tE.next;
          }
        }
        return true;
      }
    } else {
      Entry<K, V> tE = table[hash(key.hashCode(), table.length)];
      if (tE == null) {
        return false;
      }
      while (tE.key == null || !(tE.key.equals(key))) {
        if (tE.next == null) {
          return false;
        } else {
          tE = tE.next;
        }
      }
      return true;
    }
  }

  @Override
  public boolean containsValue(Object value) {
    if (value == null) {
      for (var e : table) {
        while (e != null) {
          if (e.value == null) {
            return true;
          } else {
            e = e.next;
          }
        }
      }
    } else {
      for (var e : table) {
        while (e != null) {
          if (e.value == null) {
            e = e.next;
          } else if (e.value.equals(value)) {
            return true;
          } else {
            e = e.next;
          }
        }
      }
    }
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  private void resize(int newCapacity) {
    if (table.length == MAX_CAPACITY) {
      threshHold = Integer.MAX_VALUE;
    } else {
      Entry<K, V>[] copyOfTable = Arrays.copyOf(table, table.length);
      @SuppressWarnings("unchecked")
      Entry<K, V>[] newHashTable = (Entry<K, V>[]) new Entry[newCapacity];
      table = newHashTable;
      for (int i = 0; i < copyOfTable.length; ++i) {
        if (copyOfTable[i] != null) {
          Entry<K, V> e = copyOfTable[i];
          while (e != null) {
            put(e.key, e.value);
            e = e.next;
          }
        }
      }
      threshHold = (int) (newCapacity * loadFactor);
    }
  }

  @Override
  public String toString() {
    return "MyHashMap{" +
        "table=" + Arrays.toString(table) +
        '}';
  }

  static class Entry<K, V> {
    private final K key;
    private V value;
    Entry<K, V> next;


    Entry(K key, V value, Entry<K, V> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }

    public Entry<K, V> getNext() {
      return next;
    }

    public void setNext(Entry<K, V> next) {
      this.next = next;
    }

    @Override
    public String toString() {
      return "Entry{" +
          "key=" + key +
          ", value=" + value +
          ", next=" + next +
          '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Entry<?, ?> entry = (Entry<?, ?>) o;
      return Objects.equals(key, entry.key) && Objects.equals(value, entry.value)
          && Objects.equals(next, entry.next);
    }

    @Override
    public int hashCode() {
      return Objects.hash(key, value, next);
    }
  }
}
