package aufgabe1.dictionary;

import java.util.ArrayList;
import java.util.List;

import aufgabe1.dictionary.Dictionary.Element;

/**
 * TreeDictionary setzt die in der Vorlesung besprochenen AVL-Bäume ein.
 */
public class TreeDictionary<K extends Comparable<K>, V> implements Dictionary<K, V> {
	
	private static class Node<K, V> {
		public Node<K, V> left;
		public Node<K, V> right;
		public Dictionary.Element<K, V> element;
		public int height;
		
		public Node(K key, V value) {
			this.element = new Element<K, V>(key, value);
			this.height = 0;
			this.left = null;
			this.right = null;
		}
	}
	
	private static class InsertRetVal<V> {		
		public V oldValue;
	}
	
	private Node<K, V> root = null;

	@Override
	public V insert(K key, V value) {
		InsertRetVal<V> retVal = new InsertRetVal<V>();
		root = insertR(root, retVal, key, value);
		return retVal.oldValue;
	}
	
	private Node<K, V> insertR(Node<K, V> p, InsertRetVal<V> retVal, K key, V value) {		
		// key not found insert new Node
		if (p == null) { 
			p = new Node<K, V>(key, value);
			retVal.oldValue = null;
			return p;
			
		// insert left
		} else if (p.element.key.compareTo(key) > 0) {
			p.left = insertR(p.left, retVal, key, value);
			
		// insert right
		} else if (p.element.key.compareTo(key) < 0) {
			p.right = insertR(p.right, retVal, key, value);
		
		// key found -> replace
		} else {			
			retVal.oldValue = p.element.value; // store old Value
			p.element = new Element<K, V>(key, value);			
		}
		
		p = balance(p);
		
		return p;
	}
	
	private Node<K, V> balance(Node<K, V> p) {
		if (p == null) {
			return null;
		}
		p.height = Math.max(getHeight(p.left), getHeight(p.right)) +1;
		
		// Case A left side is heavier
		if (getBalance(p) == -2) {			
			// Case A1 
			if (getBalance(p.left) <= 0) {
				return rotateRight(p);
			
			// Case A2
			} else {
				return rotateLeftRight(p);
			}
		
		// Case B right side is heavier
		} else if (getBalance(p) == 2) {
			// Case B1 
			if (getBalance(p.right) >= 0) {
				return rotateLeft(p);
			
			// Case B2
			} else {
				return rotateRightLeft(p);
			}
		}
		return p;		
	}

	@Override
	public V search(K key) {
		Node<K, V> p = root;
		
		while (p != null) {
			if (key.compareTo(p.element.key) == 0) {  // element found
				return p.element.value;
			} else if (key.compareTo(p.element.key) < 0) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		
		return null;
	}
	
	private Node<K, V> rotateRight(Node<K, V> p) {		
		Node<K, V> b = p.left.right;
		Node<K, V> q = p.left;
		
		//@see documentation
		q.right = p;
		p.left = b;
		
		p.height = Math.max(getHeight(p.left), getHeight(p.right)) +1;
		q.height = Math.max(getHeight(q.left), getHeight(q.right)) +1;
			
		return q;		
	}
	
	private Node<K, V>  rotateLeftRight(Node<K, V> p) {
		p = rotateLeft(p.left);
		p = rotateRight(p);
		
		return p;
	}
	
	private Node<K, V>  rotateLeft(Node<K, V> p) {
		Node<K, V> b = p.right.left;
		Node<K, V> q = p.right;
		
		//@see documentation
		q.left = p;
		p.right = b;
		
		p.height = Math.max(getHeight(p.left), getHeight(p.right)) +1;
		q.height = Math.max(getHeight(q.left), getHeight(q.right)) +1;
		
		return q;		
	}
	
	private Node<K, V>  rotateRightLeft(Node<K, V> p) {
		p = rotateRight(p.right);
		p = rotateLeft(p);
		
		return p;
	}
	
	private int getHeight(Node<K, V> p) {
		if (p == null) {
			return -1;
		} 
		return p.height;
	}
	
	private int getBalance(Node<K, V> p) {
		if (p == null) {
			return 0;
		}
		return getHeight(p.right) - getHeight(p.left);
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Element<K, V>> getEntries() {
		List<Element<K, V>> retVal = new ArrayList<Dictionary.Element<K,V>>();
		getEntriesR(root, retVal);
		return retVal;
	}
	
	private void getEntriesR(Node<K, V> p, List<Element<K, V>> list) {
		if (p == null) {
			return;
		}
		getEntriesR(p.left, list);
		list.add(p.element);
		getEntriesR(p.right, list);
	}

}
