package aufgabe1.dictionary;

/**
 * SortedArrayDictionary implementiert ein Dictionary mit einem Feld, in dem die Datensätze
 * lückenlos und sortiert gespeichert werden. Für die Suche wird binäre Suche eingesetzt.
 */
public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
	
	private final static int INITIAL_CAPACITY = 10;
	@SuppressWarnings("unchecked")
	private Element<K,V>[] data = new Element[INITIAL_CAPACITY];
	private int length;
	
	private static class Element<K, V>  {
		K key;
		V value;
		public Element(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	@Override
	public V insert(K key, V value) {
		ensureCapacity();
		int index;
		
		// Check if the key already exists
		if ((index = getKeyIndex(key)) != -1) {
			V oldval = data[index].value;
			data[index].value = value;
			return oldval;
		}
		
		
		
		return null;
	}
	
	private void ensureCapacity() {
		
	}

	@Override
	public V search(K key) {
		int index = getKeyIndex(key);
		if (index != -1) {
			return data[index].value;
		} else {
			return null;
		}
	}
	
	/**
	 * Tries to retrieve the index of the internal element of the key. 
	 * @return Array index of the key or -1 in case the key does not exist. 
	 */
	private int getKeyIndex(K key) {
		int li = 0;
		int re = this.length - 1;
		while (li < re) {
			int m = (li + re) / 2;
			if (key.compareTo(data[m].key) < 0) {
				re = m - 1;
			} else if (key.compareTo(data[m].key) > 0) {
				li = m + 1;
			} else {
				return m;
			}
		}
		return -1;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

}
