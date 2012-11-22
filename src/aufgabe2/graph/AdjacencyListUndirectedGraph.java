package aufgabe2.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

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
		// TODO Auto-generated method stub
		return null;
	}

	private Comparator<Edge<V>> getUndirectedEdgeComparator() {
		return new Comparator<Edge<V>>() {

			@Override
			public int compare(Edge<V> edge1, Edge<V> edge2) {
				if ((edge1.source == edge2.target) && (edge1.target == edge2.source) &&
						(edge1.weight == edge2.weight)) {
					return 0;
				}
				return 1;
			}
			
		};
	}
	
	@Override
	public List<Edge<V>> getEdgeList() {
		Set<Edge<V>> retList = new TreeSet<Edge<V>>(getUndirectedEdgeComparator());
		
		for (Entry<V, HashMap<V, Double>> vertexEntry : adjacencyList.entrySet()) {			
			for (Entry<V, Double> edgeEntry : vertexEntry.getValue().entrySet()) {
				retList.add(new Edge<V>(vertexEntry.getKey(), edgeEntry.getKey(), edgeEntry.getValue()));
			}
		}		 
		
		List<Edge<V>> edgeList = new ArrayList<Edge<V>>();
		edgeList.addAll(retList);
		return edgeList;
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDegree(V v) {
		// TODO Auto-generated method stub
		return 0;
	}

}
