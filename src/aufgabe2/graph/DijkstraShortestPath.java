package aufgabe2.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DijkstraShortestPath<V> {
	
	private Graph<V> graph;
	
	private HashMap<V, Double> distance;
	private HashMap<V, V> prevVertex;
	private V startVertex;
	private List<V> visitOrder = new LinkedList<V>();
	private double wayDistance = Double.POSITIVE_INFINITY;
	
	public static void main(String[] args) {
		Graph<Integer> graf1 = new AdjacencyListUndirectedGraph<Integer>();
		graf1.addVertex(1);
		graf1.addVertex(2);
		graf1.addVertex(3);
		graf1.addVertex(4);
		graf1.addVertex(5);
		graf1.addEdge(1, 2, 2);
		graf1.addEdge(1, 3, 1);
		graf1.addEdge(3, 4, 1);
		graf1.addEdge(3, 5, 4);
		graf1.addEdge(2, 4, 2);
		graf1.addEdge(4, 5, 1);
		
		DijkstraShortestPath<Integer> path1 = new DijkstraShortestPath<Integer>(graf1);
		path1.searchShortestPath(1, 5);
		System.out.println(path1.getDistance());
		System.out.println(path1.getShortestPath());
	}
	
	
	public DijkstraShortestPath(Graph<V> g) {
		this.graph = g;		
	}
	
	public boolean searchShortestPath(V s, V g) {
		if (distance == null) {
			if (!dijkstra(s, g)) {
				return false;
			}
		}
		
		return getShortestPathTo(s, g);				
		
	}
	
	private boolean getShortestPathTo(V s, V g) {
		visitOrder.clear();
		wayDistance = 0;
		
		if (prevVertex.get(g) == null) {
			wayDistance = Double.POSITIVE_INFINITY;
			return false;
		}
		visitOrder.add(g);
		V p = prevVertex.get(g);
		while (p != s) {
			wayDistance += distance.get(p);
			visitOrder.add(0, p);
			p = prevVertex.get(p);
		}
		visitOrder.add(0, s);
		
		return true;
	}
	
	
	public List<V> getShortestPath() {
		return visitOrder;
	}
	
	public double getDistance() {
		return wayDistance;
	}
		
	private void initTable() { 
		distance = new HashMap<V, Double>();
		prevVertex = new HashMap<V, V>();
		for (V v : graph.getVertexList()) {
			distance.put(v, Double.POSITIVE_INFINITY);
			prevVertex.put(v, null);
		}
	}
	
	public boolean searchAllShortestPaths(V s) {
		return dijkstra(s, null);
	}
	
	private boolean dijkstra(V s, V g) {
		if (s == null) {
			return false;
		}
		
		initTable();
		
		List<V> candidates = new ArrayList<V>();
		distance.put(s, 0.0); // distance for start vertex
		
		candidates.add(s);
		
		while (!candidates.isEmpty()) {
			// remove Vertex with lowest distance
			V v = getMinVertex(candidates);
			candidates.remove(v);
			if (v == g) { /* cancel if target vertex was specified and already visited */
				return true;
			}			
			
			for (V w : graph.getAdjacentVertexList(v)) {
				if (Double.isInfinite(distance.get(w))) {
					candidates.add(w);
				}
				if ((distance.get(v) + graph.getWeight(v, w)) < distance.get(w).doubleValue()) {
					prevVertex.put(w, v);
					distance.put(w, distance.get(v) + graph.getWeight(v, w)); 

				}
			}
		}
		
		startVertex = s;
		
		return true;
	}
	
	private V getMinVertex(List<V> list) {
		V retVal = list.get(0); 
		for (V listEntry : list) {
			if (distance.get(retVal) > distance.get(listEntry)) {
				retVal = listEntry;
			}
		}
		return retVal;
	}
	
	public List<V> getShortestPathTo(V g) {
		getShortestPathTo(startVertex, g);
		return visitOrder;
	}
	
	public double getDistanceTo(V g) {
		getShortestPathTo(startVertex, g);
		return wayDistance;
	}

}
