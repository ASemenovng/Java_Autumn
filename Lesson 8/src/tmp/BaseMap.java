package tmp;

public interface BaseMap<K, V> {

  V get(Object key);

  void put(K key, V value);

  V remove(Object key);

  boolean containsKey(Object key);


  boolean containsValue(Object value);

  int size();
}
