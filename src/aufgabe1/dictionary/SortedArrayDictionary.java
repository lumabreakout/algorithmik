package aufgabe1.dictionary;

/**
 * SortedArrayDictionary implementiert ein Dictionary mit einem Feld, in dem die Datensätze
 * lückenlos und sortiert gespeichert werden. Für die Suche wird binäre Suche eingesetzt.
 */
public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

	private final static int INITIAL_CAPACITY = 10;
	private final static int INCREASE_FACTOR = 2;
	
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
	
	
	public static void main(String[] args) {
		SortedArrayDictionary<String, Integer> dict = new SortedArrayDictionary<String, Integer>();
		dict.insert("a", 123);
		dict.insert("c", 456);
		dict.insert("b", 789);
		System.out.println(dict.getKeyIndex("c"));
		System.out.println(dict.toString());
	}
	

	@Override
	public V insert(K key, V value) {
		
		// Check if the key already exists
		int index;
		if ((index = getKeyIndex(key)) != -1) {
			V oldval = data[index].value;
			data[index].value = value;
			return oldval;
		}
		
		if (this.length == data.length) {
			ensureCapacity(INCREASE_FACTOR * data.length);
		}

		Element<K, V> newElement = new Element<K, V>(key, value);
		
		// Create an empty place in the data array where the new element
		// will be stored.
		int i = this.length - 1;
		while (i >= 0 && key.compareTo(data[i].key) < 0) {
			data[i + 1] = data[i];
			i--;
		}
		data[i+1] = newElement;
		
		this.length++;
		
		return null;
	}

	private void ensureCapacity(int newCapacity) {
		@SuppressWarnings("unchecked")
		Element<K, V>[] newData = new Element[newCapacity];
		System.arraycopy(data, 0, newData, 0, this.length);
		this.data = newData;
	}

	@Override
	public V search(K key) {
		int index = getKeyIndex(key);
		if (index != - 1) {
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
		// binary search as usual...
		int li = 0;
		int re = this.length - 1;
		int m = -1;
		while (li <= re) {
			m = (li + re) / 2;
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
		// search for key
		int index = getKeyIndex(key);
		V retval = data[index].value;
		
		if (index == -1) 
			return null;
		
		// delete key and fill the gap
		int i;
		for (i = index; i < length - 1; i++) {
			data[i] = data[i +1];
		}
		
		data[i + 1] = null;
		this.length--;
		
		return retval;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("SortedArrayDictionary (len:%d)\n{\n", this.length));
		for (int i = 0; i < this.length; i++) {
			sb.append(String.format("\t'%s' => '%s'\n", data[i].key.toString(), data[i].value.toString()));
		}
		sb.append("}");
		return sb.toString();
	}

}
