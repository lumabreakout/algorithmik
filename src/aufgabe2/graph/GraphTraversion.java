package aufgabe2.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class GraphTraversion {
	/**
	 * liefert eine vom Knoten s gestartete Tiefensuche als Liste zurück.
	 * @param <V>
	 * @param g
	 * @param s
	 * @return
	 */
	public static <V> List<V> depthFirstSearch(Graph<V> g, V v) {
		List<V> visitedVertices = new LinkedList<V>();
		depthFirstSearch(v, g, visitedVertices);
		return new ArrayList<V>(visitedVertices);
	}

	private static <V> void depthFirstSearch(V v, Graph<V> g, List<V> visitedVertices) {
		Deque<V> stk = new LinkedList<V>();
		stk.push(v);
		while( ! stk.isEmpty() ) {
			v = stk.pop();
			if ( visitedVertices.contains(v)) {
				continue;
			}
			visitedVertices.add(v);
			
			for (V w : g.getAdjacentVertexList(v)) {
				if ( ! visitedVertices.contains(w) ) {
					stk.push(w);
				}
			}
		}
	}

	/**
	 * liefert eine vom Knoten s gestartete Breitensuche als Liste zurück.
	 * @param g
	 * @param s
	 * @return
	 */
	public static <V> List<V> breadthFirstSearch(Graph<V> g, V v) {
		List<V> visitedVertices = new LinkedList<V>();
		breadthFirstSearch(v, g, visitedVertices);
		return new ArrayList<V>(visitedVertices);
	}
	
	private static <V> void breadthFirstSearch(V v, Graph<V> g, List<V> visitedVertices) {
		Deque<V> queue = new LinkedList<V>();
		queue.add(v);
		while( ! queue.isEmpty() ) {
			v = queue.remove();
			if ( visitedVertices.contains(v)) {
				continue;
			}
			visitedVertices.add(v);
			
			for (V w : g.getAdjacentVertexList(v)) {
				if ( ! visitedVertices.contains(w) ) {
					queue.add(w);
				}
			}
		}
	}

	/**
	 * liefert eine topologische Sortierung des gerichteten Graphen g als Liste zurück.
	 * @param g
	 * @return
	 */
	public static <V> List<V> topologicalSort(DirectedGraph<V> g) {
		List<V> ts = new LinkedList<V>(); // topologisch sortierte Folge
		HashMap<V, Integer> inDegree = new HashMap<V, Integer>(); // Anz. noch nicht besuchter Vorgänger
		Deque<V> q = new LinkedList<V>();
				
		for (V vert : g.getVertexList()) {			
			inDegree.put(vert, g.getInDegree(vert));
			if (inDegree.get(vert).equals(0)) {
				q.add(vert);
			}			
		}
				
		while (! q.isEmpty() ) {
			V vert = q.remove();
			ts.add(vert);			
			
			for (V w : g.getSuccessorVertexList(vert)) {
				inDegree.put(w, inDegree.get(w) -1);
				if (inDegree.get(w).equals(0)) {
					q.add(w);
				}				
			}
		}
		if (ts.size() != g.getNumberOfVertexes()) {
			return null; // Graph zyklisch;
		} else {
			return ts;
		}
	}

}
