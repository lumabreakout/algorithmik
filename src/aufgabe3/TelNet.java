package aufgabe3;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import aufgabe2.graph.AdjacencyListUndirectedGraph;
import aufgabe2.graph.Edge;
import aufgabe2.graph.UndirectedGraph;

public class TelNet {

	private final int lbg;
	private UndirectedGraph<TelKnoten> graph;

	/**
	 *   Legt ein neues Telefonnetz mit dem Leitungsbegrenzungswert lbg an.
	 */
	public TelNet(int lbg) {
		this.lbg = lbg;
		this.graph = new AdjacencyListUndirectedGraph<TelKnoten>();
	}

	/**
	 * Fügt einen neuen Telefonknoten mit Koordinate (x,y) dazu.
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
		
		
		
	}
	
	private void primAlgo(UndirectedGraph<TelKnoten> graph, TelKnoten startNode) {
		List<TelKnoten> nodes = graph.getVertexList();
		List<TelKnoten> kandidaten = new LinkedList<TelKnoten>();
		List<TelKnoten> baum = new LinkedList<TelKnoten>();
		Map<TelKnoten, TelKnoten> vorgaenger = new HashMap<TelKnoten, TelKnoten>();
		
		kandidaten.add(nodes.remove(0));
		
		while (!kandidaten.isEmpty()) {
			// TODO:
		}
	}
	
	


	private double getCost(TelKnoten u, TelKnoten v) {
		int result = Math.abs(u.x - v.x) + Math.abs(u.y - v.y);
		return result;
	}

	/**
	 * Zeichnet das gefundene optimale 
	 * Telefonnetz mit der Größe xMax*yMax in ein Fenster.
	 */
	public void drawOptTelNet(int xMax, int yMax) {
		StdDraw.setCanvasSize(xMax, yMax);
		StdDraw.setXscale(0, xMax);
		StdDraw.setYscale(0, yMax);
		StdDraw.show(0);
		
		StdDraw.clear(Color.WHITE);
		
		// alle möglichen verbindungen einzeichnen
		StdDraw.setPenColor(Color.GRAY);
		for (Edge<TelKnoten> edge : this.graph.getEdgeList()) {
			TelKnoten u = edge.getSource();
			TelKnoten v = edge.getTarget();
			StdDraw.line(u.x, u.y, u.x, v.y);
			StdDraw.line(v.x, v.y, u.x, v.y);
		}
		
		// TODO: alle nötigen verbindungen einzeichnen
		
		
		// alle telefonknoten einzeichnen
		StdDraw.setPenColor(Color.BLACK);
		for (TelKnoten k : this.graph.getVertexList()) {
			StdDraw.filledCircle(k.x, k.y, 5);
		}
	
		StdDraw.show(0);
	}

	/**
	 * Fügt n zufällige Telefonknoten zum Netz dazu mit 
	 * x-Koordinaten aus [0,xMax] und y-Koordinaten aus [0,yMax].
	 */
	public void generateRandomTelNet(int n, int xMax, int yMax) {
		int gridsize = 20;
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
	 * Liefert ein optimales Telefonnetz als Liste von Telefonverbindungen zurück.
	 */
	public List<TelVerbindung> 	getOptTelNet() {
		// TODO:
		return null;
	}

	/**
	 *  Liefert die Gesamtkosten eines optimalen Telefonnetzes zurück.
	 */
	public int 	getOptTelNetKosten() {
		int sum = 0;
		for (TelVerbindung v : getOptTelNet())  {
			sum += getCost(v.u, v.v);
		}
		return sum;
	}

	public static void 	main(java.lang.String[] args) {
		int xmax = 500;
		int ymax = 500;
		TelNet net = new TelNet(100);
		net.generateRandomTelNet(100, xmax, ymax);
		net.drawOptTelNet(xmax, ymax);
	
	}
}
