package aufgabe2.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {
	
	private HashMap<V, HashMap<V, Double>> adjacencyList;
	
	public AdjacencyListUndirectedGraph() {
		adjacencyList = new HashMap<V, HashMap<V, Double>>();
	}

	@Override
	public boolean addVertex(V v) {
		if (adjacencyList.containsKey(v)) {
			return false;
		}
		
		adjacencyList.put(v, new HashMap<V, Double>());
		return true;
	}

	@Override
	public boolean addEdge(V v, V w) {
		return addEdge(v, w, 1.0);
	}

	@Override
	public boolean addEdge(V v, V w, double weight) {
		
		// start or end vertex does not exist
		if (!verticesExist(v,w)) {
			return false;
		}
		
		// edge does already exist
		if (adjacencyList.get(v).containsKey(w)) {
			return false;
		}
		
		adjacencyList.get(v).put(w, weight);
		adjacencyList.get(w).put(v, weight);
		
		return true;
	}

	@Override
	public boolean containsVertex(V v) {
		return adjacencyList.containsKey(v);
	}

	@Override
	public boolean containsEdge(V v, V w) {
		// start or end vertex does not exist
		if (!verticesExist(v,w)) {
			return false;
		}
		
		return adjacencyList.get(v).containsKey(w);
	}

	@Override
	public double getWeight(V v, V w) {
		// start or end vertex does not exist
		if (!verticesExist(v,w)) {
			throw new IllegalArgumentException("Start or end vertex does not exist.");
		}
		
		return adjacencyList.get(v).get(w).doubleValue();
	}
	
	private boolean verticesExist(V v, V w) {
		return containsVertex(v) && containsVertex(w);
	}

	@Override
	public int getNumberOfVertexes() {
		return adjacencyList.size();
	}

	@Override
	public int getNumberOfEdges() {		
		return getEdgeList().size();
	}

	@Override
	public List<V> getVertexList() {
		return new ArrayList<V>(adjacencyList.keySet());
	}

	@Override
	public List<Edge<V>> getEdgeList() {

		List<Edge<V>> edgeList = new ArrayList<Edge<V>>();
		List<V> visitedNodes = new LinkedList<V>();
		
		for (Entry<V, HashMap<V, Double>> vertexEntry : adjacencyList.entrySet()) {			
			for (Entry<V, Double> edgeEntry : vertexEntry.getValue().entrySet()) {
				if (!visitedNodes.contains(edgeEntry.getKey())) {
					edgeList.add(new Edge<V>(vertexEntry.getKey(), edgeEntry.getKey()));
				}
				
			}
			visitedNodes.add(vertexEntry.getKey());
		}		 
		
		return edgeList;

	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		return new ArrayList<V>(adjacencyList.get(v).keySet());
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		List<Edge<V>> res = new LinkedList<Edge<V>>();
		for (Entry<V, Double> edgeEntry : adjacencyList.get(v).entrySet()) {		
			res.add(new Edge<V>(v, edgeEntry.getKey()));
		}
		return res;
	}

	@Override
	public int getDegree(V v) {
		return adjacencyList.get(v).size();
	}

}
