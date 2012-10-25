package aufgabe1.dictionary;

import java.util.ArrayList;
import java.util.List;

import aufgabe1.dictionary.Dictionary.Element;

/**
 * HashDictionary enthält als Implementierung eine wie in der Vorlesung besprochene
 * Hashtabelle mit linear verketteten Listen.
 */
public class HashDictionary<K, V> implements Dictionary<K, V> {

	private static class Node<K, V>{
		public Element<K, V> elem;
		public Node<K, V> next;
		
		public Node(K key, V value, Node<K, V> next) {
			super();
			this.elem = new Element<K, V>(key, value);
			this.next = next;
		}
		
		public Node(K key, V value) {
			super();
			this.elem = new Element<K, V>(key, value);
		}
	}
	
	private static final int INITIAL_SIZE = 7;
	private static final double MAX_FILL_RATIO = 0.8;
	
	@SuppressWarnings("unchecked")
	private Node<K, V>[] data = (Node<K, V>[]) new Node[INITIAL_SIZE];
	private Node<K, V>[] datanew = null;
	
	private int min = 0;		 // start index of elements in old table
	private int indexCount = 0;  // num of used indexes
	
	private void lazyCopyElements() {
		if (datanew != null && min < data.length) {
			Node<K, V> node = data[min];
			min++;
			Node<K, V> p = node;
			while (p != null) {
				insert(p);
				p = p.next;
			}
			data[min - 1] = null;
		}		
	}
	
	@SuppressWarnings("unchecked")
	private V insert(Node<K, V> node) {
		
		lazyCopyElements();
		
		// default: search in old table
		int index = Math.abs(node.elem.key.hashCode() % data.length);		
		Node<K, V>[]  currentTable = data;		
		
		// decide whether to search in old or new table
		if (index < min) {
			index = Math.abs(node.elem.key.hashCode() % datanew.length);
			currentTable = datanew;
		}		
		
		Node<K, V> p = currentTable[index];
		if (p == null) {  // new array index is going to be used now
			indexCount++;
			
			// check if new new table shall be created
			if ((((double) indexCount / data.length) > MAX_FILL_RATIO) 
					&& datanew == null) {
				
				datanew = (Node<K, V>[]) new Node[data.length * 2];
			}
		}
		
		while(p != null && !p.elem.key.equals(node.elem.key)) {
			p = p.next;
		}
		
		// index is free or index found but no eqal key
		if (p == null) {
			node.next = currentTable[index];
			currentTable[index] = node;
			return null;
		}
		
		// key already exists -> replace value and return old value
		V oldVal = p.elem.value;
		p.elem.value = node.elem.value;
		return oldVal;
	}
		
	@Override
	public V insert(K key, V value) {
		return insert(new Node<K, V>(key, value));
	}

	@Override
	public V search(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Element<K, V>> getEntries() {
		// TODO Auto-generated method stub
		return new ArrayList<Element<K, V>>(0);
	}

}
