package aufgabe2.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V> {
	
	public static final int PREDECESSOR_LIST = 0;
	public static final int SUCCESSOR_LIST = 1;
	
	private HashMap<V, List<HashMap<V, Double>>> adjacencyList;
	
	public AdjacencyListDirectedGraph() {
		adjacencyList = new HashMap<V, List<HashMap<V, Double>>>();
	}

	@Override
	public boolean addVertex(V v) {
		if (adjacencyList.containsKey(v)) {
			return false;
		}
		
		List<HashMap<V, Double>> vertexLists = new LinkedList<HashMap<V, Double>>();
		vertexLists.add(new HashMap<V, Double>());  // Predecessor vertices (Knoten die auf mich zeigen)
		vertexLists.add(new HashMap<V, Double>());  // Successor vertices (Knoten auf die ich zeige)
		adjacencyList.put(v, vertexLists);
		
		return true;
	}

	@Override
	public boolean addEdge(V v, V w) {
		return addEdge(v, w, 1.0);
	}
	
	private boolean verticesExist(V v, V w) {
		return containsVertex(v) && containsVertex(w);
	}

	@Override
	public boolean addEdge(V v, V w, double weight) {

		// start or end vertex does not exist
		if (!verticesExist(v,w)) {
			throw new IllegalArgumentException("start and endvertex must exist");
		}
		
		// target and source is the same vertex
		if (v.equals(w)) {
			throw new IllegalArgumentException("target and source can not be the same");
		}
		
		
		// edge does already exist
		if (adjacencyList.get(v).get(SUCCESSOR_LIST).containsKey(w) ||
			adjacencyList.get(w).get(PREDECESSOR_LIST).containsKey(v)) {
			return false;			
		}
		// outgoing Successor		
		adjacencyList.get(v).get(SUCCESSOR_LIST).put(w, weight); 
		
		// incoming Predecessor
		adjacencyList.get(w).get(PREDECESSOR_LIST).put(v, weight);		
		
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
				
		return adjacencyList.get(v).get(SUCCESSOR_LIST).containsKey(w);
	}

	@Override
	public double getWeight(V v, V w) {
		if (!containsEdge(v, w)) {
			throw new IllegalArgumentException("Edge does not exist");
		}
		
		return adjacencyList.get(v).get(SUCCESSOR_LIST).get(w).doubleValue();
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
		
		for (Entry<V, List<HashMap<V, Double>>> vertexEntry : adjacencyList.entrySet()) {			
			for (Entry<V, Double> edgeEntry : vertexEntry.getValue().get(SUCCESSOR_LIST).entrySet()) {			
				edgeList.add(new Edge<V>(vertexEntry.getKey(), edgeEntry.getKey()));				
			}			
		}		 
		
		return edgeList;
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		List<V> res = new ArrayList<V>();
		
		res.addAll(adjacencyList.get(v).get(PREDECESSOR_LIST).keySet());
		res.addAll(adjacencyList.get(v).get(SUCCESSOR_LIST).keySet());
		
		return res;
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		return getOutgoingEdgeList(v);
	}

	@Override
	public int getInDegree(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		
		return adjacencyList.get(v).get(PREDECESSOR_LIST).size();
	}

	@Override
	public int getOutDegree(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		
		return adjacencyList.get(v).get(SUCCESSOR_LIST).size();
	}

	@Override
	public List<V> getPredecessorVertexList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		
		List<V> res = new ArrayList<V>();
		res.addAll(adjacencyList.get(v).get(PREDECESSOR_LIST).keySet());
		return res;
	}

	@Override
	public List<V> getSuccessorVertexList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		
		List<V> res = new ArrayList<V>();
		res.addAll(adjacencyList.get(v).get(SUCCESSOR_LIST).keySet());
		return res;
	}

	@Override
	public List<Edge<V>> getOutgoingEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		List<Edge<V>> res = new ArrayList<Edge<V>>();
		for (Entry<V, Double> edgeEntry : adjacencyList.get(v).get(SUCCESSOR_LIST).entrySet()) {		
			res.add(new Edge<V>(v, edgeEntry.getKey()));
		}
		
		return res;
	}

	@Override
	public List<Edge<V>> getIncomingEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		
		List<Edge<V>> res = new ArrayList<Edge<V>>();
		for (Entry<V, Double> edgeEntry : adjacencyList.get(v).get(PREDECESSOR_LIST).entrySet()) {		
			res.add(new Edge<V>(edgeEntry.getKey(), v));
		}
	
		return res;
	}

}
