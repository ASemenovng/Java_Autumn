public interface IMap<K, V> {

  V get(Object k);
  void put(K k, V v);

  V remove(Object key);

  boolean containsKey(Object key);
  boolean containsValue(Object value);

  int size();
}
