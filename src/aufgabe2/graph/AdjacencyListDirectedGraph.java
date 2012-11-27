package aufgabe2.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V> {
	
	private HashMap<V, HashMap<V, Double>> adjacencyList;
	
	public AdjacencyListDirectedGraph() {
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
		if (adjacencyList.get(v).containsKey(w)) {
			return false;
		}
		
		
		
		adjacencyList.get(v).put(w, weight);
		
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
		if (!containsEdge(v, w)) {
			throw new IllegalArgumentException("Edge does not exist");
		}
		
		return adjacencyList.get(v).get(w).doubleValue();
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
		
		for (Entry<V, HashMap<V, Double>> vertexEntry : adjacencyList.entrySet()) {			
			for (Entry<V, Double> edgeEntry : vertexEntry.getValue().entrySet()) {			
				edgeList.add(new Edge<V>(vertexEntry.getKey(), edgeEntry.getKey()));				
			}			
		}		 
		
		return edgeList;
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		List<V> res = new ArrayList<V>();
		for (Edge<V> edge : getEdgeList()) {
			
			if (edge.getSource().equals(v) &&
				!res.contains(edge.getTarget())) {
				res.add(edge.getTarget());
			
//			TODO: alle knoten oder nur zielknoten w von v->w
//			} else if(edge.getTarget().equals(v) && 
//					!res.contains(edge.getSource())) {			
//				res.add(edge.getSource());
			}
		}
		return res;
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
	public int getInDegree(V v) {
		return getIncomingEdgeList(v).size();
	}

	@Override
	public int getOutDegree(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		
		return adjacencyList.get(v).keySet().size();
	}

	@Override
	public List<V> getPredecessorVertexList(V v) {			
		List<V> res = new ArrayList<V>();
		for (Edge<V> edge : getIncomingEdgeList(v)) {
			res.add(edge.getSource());
		}
		return res;
	}

	@Override
	public List<V> getSuccessorVertexList(V v) {
		List<V> res = new ArrayList<V>();
		for (Edge<V> edge : getOutgoingEdgeList(v)) {
			res.add(edge.getTarget());
		}
		return res;
	}

	@Override
	public List<Edge<V>> getOutgoingEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		List<Edge<V>> res = new ArrayList<Edge<V>>();
		for (V key : adjacencyList.get(v).keySet()) {
			res.add(new Edge<V>(v, key));
		}
		
		return res;
	}

	@Override
	public List<Edge<V>> getIncomingEdgeList(V v) {
		if (!containsVertex(v)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		
		List<Edge<V>> res = new ArrayList<Edge<V>>();
		for (Edge<V> edge : getEdgeList()) {
			if (edge.getTarget().equals(v)) {
				res.add(edge);
			}
		}
	
		return res;
	}

}
