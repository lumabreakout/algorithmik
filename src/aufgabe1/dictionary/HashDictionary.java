package aufgabe1.dictionary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	
	private static final int INITIAL_SIZE = 3;
	private static final double MAX_FILL_RATIO = 0.8;
	
	@SuppressWarnings("unchecked")
	private Node<K, V>[] data = (Node<K, V>[]) new Node[INITIAL_SIZE];
	private Node<K, V>[] datanew = null;
	
	private int min = 0;		 // start index of elements in old table
	private int indexCount = 0;
	
	
	
	
	public static void main(String[] args) {
		Dictionary<String, String> dict = new HashDictionary<String, String>();
		
		dict.insert("a", "1");
		dict.insert("b", "2");
		dict.insert("ab", "3");
		dict.insert("c", "4");
		
		System.out.println(dict.remove("a"));
		System.out.println(dict.remove("b"));
		System.out.println(dict.remove("ab"));
		System.out.println(dict.remove("c"));
		
	}
	
	
	private void lazyCopyElements() {
		if (datanew != null && min < data.length) {
			Node<K, V> node = data[min];
			min++;
			Node<K, V> p = node;			
			while (p != null) {
				Node<K,V> nexNode = p.next;
				p.next = null;				
				insert(p);
				p = nexNode;
			}
			data[min - 1] = null;
		}
		// lazy copying finished -> delete new table
		if (min == data.length) {
			data = datanew;
			datanew = null;
			min = 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	private V insert(Node<K, V> node) {
		// default: search in old table
		int index = Math.abs(node.elem.key.hashCode() % data.length);		
		Node<K, V>[]  currentTable = data;		
		
		// decide whether to search in old or new table
		if (index < min) {
			index = Math.abs(node.elem.key.hashCode() % datanew.length);
			currentTable = datanew;
		}		
		
		Node<K, V> p = currentTable[index];
		
		// increase indexCount
		if (p == null) {  // new array index is going to be used now
			if (currentTable == datanew || 
					datanew == null) { 
				indexCount++;
			} 
		}
		
		// check if new new table shall be created
		if (datanew == null &&
			(((double) indexCount / (double) data.length) >= MAX_FILL_RATIO)) {
			indexCount = 0;
			datanew = (Node<K, V>[]) new Node[data.length * 2];
		}	
		
		// search for key in linkedList
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
		lazyCopyElements();
		return insert(new Node<K, V>(key, value));
	}
	
	

	@Override
	public V search(K key) {
		// default: search in old table
		int index = Math.abs(key.hashCode() % data.length);		
		Node<K, V>[]  currentTable = data;		
		
		// decide whether to search in old or new table
		if (index < min) {
			index = Math.abs(key.hashCode() % datanew.length);
			currentTable = datanew;
		}		
		
		Node<K, V> p = currentTable[index];		
		while(p != null && !p.elem.key.equals(key)) {
			p = p.next;
		}
		
		if (p != null) {
			return p.elem.value;
		}
		
		return null;
	}

	@Override
	public V remove(K key) {
		// default: search in old table
		int index = Math.abs(key.hashCode() % data.length);		
		Node<K, V>[]  currentTable = data;		
		
		// decide whether to search in old or new table
		if (index < min) {
			index = Math.abs(key.hashCode() % datanew.length);
			currentTable = datanew;
		}		
		
		Node<K, V> current = currentTable[index];
		
		if (current == null) {  // key does not exist
			return null;
			
		} else if (current.next == null) {   // only 1 node
			if (current.elem.key.equals(key)) {
				if (currentTable == datanew ||  
						datanew == null) {
					indexCount--;
				} 
				
				V retval = current.elem.value;
				currentTable[index] = null;
				return retval;
			}
			return null;
		}   
		
		// 
		Node<K, V> p = currentTable[index];		
		while(p.next != null && !p.next.elem.key.equals(key)) {
			p = p.next;
		}
		if (p.next != null) {
			V retval = p.next.elem.value;
			p.next = p.next.next;
			return retval;
		}
		return null;
	}

	@Override
	public List<Element<K, V>> getEntries() {
		List<Element<K, V>> retVal = new LinkedList<Dictionary.Element<K,V>>();
		
		for (int i=min; i < data.length; i++) {
			Node<K, V> p = data[i];
			while (p != null) {
				retVal.add(p.elem);
				p = p.next;
			}
		}
		if (datanew != null) {
			for (int i=0; i < datanew.length; i++) {
				Node<K, V> p = datanew[i];
				while (p != null) {
					retVal.add(p.elem);
					p = p.next;
				}
			}		
		}
		
		return retVal;
	}

}
