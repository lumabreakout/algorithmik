package aufgabe3;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import aufgabe2.graph.AdjacencyListUndirectedGraph;
import aufgabe2.graph.Edge;
import aufgabe2.graph.UndirectedGraph;

public class TelNet {

	private final int lbg;
	private UndirectedGraph<TelKnoten> graph;
	private Map<TelKnoten, TelKnoten> vorgaenger = new HashMap<TelKnoten, TelKnoten>();
	private Map<TelKnoten, Double> distance = new HashMap<TelKnoten, Double>();

	/**
	 *   Legt ein neues Telefonnetz mit dem Leitungsbegrenzungswert lbg an.
	 */
	public TelNet(int lbg) {
		this.lbg = lbg;
		this.graph = new AdjacencyListUndirectedGraph<TelKnoten>();
	}

	/*
	 * Fuegt einen neuen Telefonknoten mit Koordinate (x,y) dazu.
	 * und erstellt Verbindungen zu im Radius liegenden anderen Knoten.
	 */
	public void addTelKnoten(int x, int y) {
		TelKnoten newnode = new TelKnoten(x, y);
		this.graph.addVertex(newnode);
	}

	/**
	 * Berechnet ein optimales Telefonnetz als minimal 
	 * aufspannenden Baum mit dem Algorithmus von Prim.
	 */
	public void computeOptTelNet() {
		primAlgo(this.graph, graph.getVertexList().get(0));


	}

	private void primAlgo(UndirectedGraph<TelKnoten> graph, TelKnoten startNode) {
		List<TelKnoten> nodes = graph.getVertexList();
		List<TelKnoten> kandidaten = new LinkedList<TelKnoten>();
		List<TelKnoten> baum = new LinkedList<TelKnoten>();
		vorgaenger.clear();
		distance.clear();

		for (TelKnoten v : nodes) {
			vorgaenger.put(v, null);
			distance.put(v, Double.POSITIVE_INFINITY);
		}

		distance.put(startNode, 0.0);
		kandidaten.add(startNode);

		while (!kandidaten.isEmpty()) {
			TelKnoten v = removeNodeMinimalDistance(kandidaten, distance);
			if (!baum.contains(v)) {
				baum.add(v);
			}

			for (TelKnoten w : graph.getAdjacentVertexList(v)) {
				if (!baum.contains(w)) {
					if (Double.isInfinite(distance.get(w).doubleValue())) {
						kandidaten.add(w);
					}
					Double cost = getCost(v, w);
					if (cost.compareTo(distance.get(w)) < 0) {
						vorgaenger.put(w, v);
						distance.put(w, cost);
					}
				}
			}
		}

		if (baum.size() != nodes.size()) {
			System.out.println("Min-aufspannender Baum existiert nicht!");
		}
	}

	private TelKnoten removeNodeMinimalDistance(List<TelKnoten> kandidaten, Map<TelKnoten, Double> distance) {

		int minIndex = 0;
		TelKnoten minNode = kandidaten.get(0);

		for (int i = 0; i < kandidaten.size(); ++i) {
			TelKnoten v = kandidaten.get(i);
			if (distance.get(v).compareTo(distance.get(minNode)) < 0) {
				minNode = v;
				minIndex = i;
			}
		}

		kandidaten.remove(minIndex);
		return minNode;
	}


	private double getCost(TelKnoten u, TelKnoten v) {
		int result = Math.abs(u.x - v.x) + Math.abs(u.y - v.y);
		return result;
	}

	/**
	 * Zeichnet das gefundene optimale 
	 * Telefonnetz mit der Groesse xMax*yMax in ein Fenster.
	 */
	public void drawOptTelNet(int xMax, int yMax, boolean showAllEdges, double nodeSize) {
		StdDraw.setCanvasSize(1000, 1000);
		StdDraw.setXscale(0, xMax);
		StdDraw.setYscale(0, yMax);
		StdDraw.show(0);

		StdDraw.clear(Color.WHITE);

		// alle moelichen verbindungen einzeichnen
		if (showAllEdges) {
			StdDraw.setPenColor(Color.GRAY);
			for (Edge<TelKnoten> edge : this.graph.getEdgeList()) {
				TelKnoten u = edge.getSource();
				TelKnoten v = edge.getTarget();
				StdDraw.line(u.x, u.y, u.x, v.y);
				StdDraw.line(v.x, v.y, u.x, v.y);
			}
		}

		// alle verbindungen des min-aufspannenden graph zeichnen
		StdDraw.setPenColor(Color.RED);
		for (Edge<TelKnoten> edge : this.graph.getEdgeList()) {
			TelKnoten u = edge.getTarget();
			TelKnoten v = edge.getSource();
			if (vorgaenger.get(u) == v || vorgaenger.get(v) == u) {
				StdDraw.line(u.x, u.y, u.x, v.y);
				StdDraw.line(v.x, v.y, u.x, v.y);
			}
		}

		// alle telefonknoten einzeichnen
		StdDraw.setPenColor(Color.BLACK);
		for (TelKnoten k : this.graph.getVertexList()) {
			StdDraw.filledCircle(k.x, k.y, nodeSize);
		}

		StdDraw.show(0);
	}

	/**
	 * Fügt n zufällige Telefonknoten zum Netz dazu mit 
	 * x-Koordinaten aus [0,xMax] und y-Koordinaten aus [0,yMax].
	 */
	public void generateRandomTelNet(int n, int xMax, int yMax) {
		int gridsize = 10;
		for (int i = 0; i < n; ++i) {
			int x = (int) (Math.random() * xMax);
			int y = (int) (Math.random() * yMax);
			x = x - (x % gridsize);
			y = y - (y % gridsize);
			if (!nodeExists(x, y)) {
				addTelKnoten(x, y);
			} else {
				i--;  // try again
				continue;
			}
		}

		generateEdges();
	}

	public void generateEdges() {
		this.graph.clearEdges();

		for (TelKnoten source : this.graph.getVertexList()) {
			for (TelKnoten target : this.graph.getVertexList()) {
				if (source == target) {
					continue;
				}

				double weight = getCost(source, target);
				if (weight <= this.lbg) {
					this.graph.addEdge(source, target, weight);
				}
			}
		}
	}

	private boolean nodeExists(int x, int y) {
		for (TelKnoten k : this.graph.getVertexList()) {
			if (k.x == x && k.y == y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Liefert ein optimales Telefonnetz als Liste von Telefonverbindungen zurueck.
	 */
	public List<TelVerbindung> 	getOptTelNet() {
		List<TelVerbindung> list = new LinkedList<TelVerbindung>();
		
		List<TelKnoten> visited = new LinkedList<TelKnoten>();
		
		for (Map.Entry<TelKnoten, TelKnoten> entry : vorgaenger.entrySet()) {
			TelKnoten v =  entry.getKey();
			TelKnoten prev = entry.getValue();	
			
			if (prev == null) {
				visited.add(v);
				continue;
			}
			
			while (prev != null && !visited.contains(v)) {
				visited.add(v);
				list.add(new TelVerbindung(v, prev));
				
				v = prev;
				prev = vorgaenger.get(v);
			}			
			
		}
		
		return list;
	}

	/**
	 *  Liefert die Gesamtkosten eines optimalen Telefonnetzes zurueck.
	 */
	public int 	getOptTelNetKosten() {
		int sum = 0;
		for (TelVerbindung v : getOptTelNet())  {
			sum += getCost(v.u, v.v);
		}
		return sum;
	}

	public static void 	main(java.lang.String[] args) {

//		aufgabe2();
		aufgabe3();
	}
	
	private static void aufgabe2() {
		int xmax = 7;
		int ymax = 7;
		TelNet net = new TelNet(7);
		net.addTelKnoten(1,1);
		net.addTelKnoten(3, 1);
		net.addTelKnoten(4,2);
		net.addTelKnoten(3,4);
		net.addTelKnoten(2,6);
		net.addTelKnoten(4,7);
		net.addTelKnoten(7,5);
		net.generateEdges();
		
		net.computeOptTelNet();

		net.drawOptTelNet(xmax, ymax, false, 0.1);
		System.out.println("Kosten " + net.getOptTelNetKosten());
	}
	
	private static void aufgabe3() {
		int xmax = 1000;
		int ymax = 1000;
		TelNet net = new TelNet(100);
		net.generateRandomTelNet(1000, xmax, ymax);
		net.computeOptTelNet();

		net.drawOptTelNet(xmax, ymax, false, 5);
		System.out.println("Kosten " + net.getOptTelNetKosten());
	}
	
}
