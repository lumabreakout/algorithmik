package aufgabe2.graph;

public class TestGraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UndirectedGraph<Integer> graf1 = new AdjacencyListUndirectedGraph<Integer>();
		graf1.addVertex(1);
		graf1.addVertex(2);
		graf1.addVertex(3);
		graf1.addVertex(4);
		graf1.addEdge(1, 2);
		graf1.addEdge(1, 3);
		graf1.addEdge(2, 4);
		graf1.addEdge(3, 4);
		graf1.addEdge(1, 4);
		
		System.out.println("Number of Edges: " + graf1.getNumberOfEdges());
		System.out.println("Number of Vertexes: " + graf1.getNumberOfVertexes());
		
		Integer vertex1 = graf1.getVertexList().get(0);
		
		System.out.println("Vertex1 Degrees: " + graf1.getDegree(vertex1));
		System.out.println("Vertex1 getAdjacentVertexList: " + graf1.getAdjacentVertexList(vertex1));
		System.out.println("Vertex1 getIncidentEdgeList: " + graf1.getIncidentEdgeList(vertex1));
		
		
		DirectedGraph<Integer> graf2 = new AdjacencyListDirectedGraph<Integer>();
		graf2.addVertex(1);
		graf2.addVertex(2);
		graf2.addVertex(3);
		graf2.addVertex(4);
		graf2.addEdge(1, 2);
		graf2.addEdge(1, 3);
		graf2.addEdge(1, 4);
		graf2.addEdge(4, 2);
		graf2.addEdge(3, 4);		
		
		System.out.println("Number of Edges: " + graf2.getNumberOfEdges());
		System.out.println("Number of Vertexes: " + graf2.getNumberOfVertexes());
		
		Integer vertex2 = graf2.getVertexList().get(3);
		
		System.out.println("Vertex2 In Degree: " + graf2.getInDegree(vertex2));
		System.out.println("Vertex2 Out Degree: " + graf2.getOutDegree(vertex2));
		System.out.println("Vertex2 Predecessors: " + graf2.getPredecessorVertexList(vertex2));
		System.out.println("Vertex2 Successors: " + graf2.getSuccessorVertexList(vertex2));
		System.out.println("Vertex2 IncomingEdgeList: " + graf2.getIncomingEdgeList(vertex2));
		System.out.println("Vertex2 OutgoingEdgeList: " + graf2.getOutgoingEdgeList(vertex2));
		System.out.println("Vertex2 getAdjacentVertexList: " + graf2.getAdjacentVertexList(vertex2));
		System.out.println("Vertex2 getIncidentEdgeList: " + graf2.getIncidentEdgeList(vertex2));
		
		
		DirectedGraph<Integer> graf3 = new AdjacencyListDirectedGraph<Integer>();
		for (int i = 1; i <= 7; ++i) {
			graf3.addVertex(i);
		}
		
		graf3.addEdge(1, 2);
		graf3.addEdge(2, 3);
		graf3.addEdge(2, 4);
		graf3.addEdge(1, 5);
		graf3.addEdge(5, 7);
		graf3.addEdge(5, 6);
		
		System.out.println("depthFirstSearch " + GraphTraversion.depthFirstSearch(graf3, 
				graf3.getVertexList().get(0)));
		System.out.println("breadthFristSearch " + GraphTraversion.breadthFirstSearch(graf3, 
				graf3.getVertexList().get(0)));		
		
		// topological without cycle
		System.out.println("topologicalSort " + GraphTraversion.topologicalSort(graf3));
	
		// topological with cycle
		graf3.addEdge(6, 7);
		graf3.addEdge(7, 1);
		System.out.println("topologicalSort " + GraphTraversion.topologicalSort(graf3));

		
		// 5. morgendliche Anziehen
		DirectedGraph<String> graf4 = new AdjacencyListDirectedGraph<String>();
		graf4.addVertex("Strümpfe");
		graf4.addVertex("Schuhe");
		graf4.addVertex("Hose");
		graf4.addVertex("Unterhose");
		graf4.addVertex("Unterhemd");
		graf4.addVertex("Hemd");
		graf4.addVertex("Gürtel");
		graf4.addVertex("Pullover");
		graf4.addVertex("Mantel");
		graf4.addVertex("Schal");
		graf4.addVertex("Handschuhe");
		graf4.addVertex("Mütze");
		
		graf4.addEdge("Strümpfe", "Schuhe");
		graf4.addEdge("Unterhose", "Hose");
		graf4.addEdge("Hose", "Schuhe");
		graf4.addEdge("Hose", "Gürtel");
		graf4.addEdge("Unterhemd", "Hemd");
		graf4.addEdge("Hemd", "Pullover");
		graf4.addEdge("Pullover", "Mantel");
		graf4.addEdge("Mantel", "Schal");
		graf4.addEdge("Schal", "Handschuhe");
		graf4.addEdge("Schuhe", "Handschuhe");
		graf4.addEdge("Hose", "Handschuhe");
		
		System.out.println("topologicalSort " + GraphTraversion.topologicalSort(graf4));

	}

}
