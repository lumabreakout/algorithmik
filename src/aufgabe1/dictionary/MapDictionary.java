package aufgabe1.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * MapDictionary benutzt ein Map-Objekt aus den Java-Collections. Das Map-Objekt wird dem
 * Konstruktor als Parameter übergeben. In der Anwendung ist sowohl ein HashMap- als auch
 * ein TreeMap-Objekt zu übergeben.
 */
public class MapDictionary<K, V> implements Dictionary<K, V> {
	
	private final Map<K, V> baseContainer;
	
	public MapDictionary(Map<K, V> baseContainer) {
		this.baseContainer = baseContainer;
	}

	@Override
	public V insert(K key, V value) {
		return this.baseContainer.put(key, value);
	}

	@Override
	public V search(K key) {
		return this.baseContainer.get(key);
	}

	@Override
	public V remove(K key) {
		return this.baseContainer.remove(key);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("MapDictionary[%s-based] (len:%d)\n{\n", this.baseContainer.getClass().getSimpleName(), this.baseContainer.size()));
		for (Entry<K, V> e : this.baseContainer.entrySet()) {
			sb.append(String.format("\t'%s' => '%s'\n", e.getKey().toString(), e.getValue().toString()));
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		MapDictionary<String, Integer> dict = new MapDictionary<String, Integer>(new HashMap<String, Integer>());
		dict.insert("a", 123);
		dict.insert("c", 456);
		dict.insert("b", 789);
		System.out.println(dict.search("c"));
		System.out.println(dict.toString());
	}

}
