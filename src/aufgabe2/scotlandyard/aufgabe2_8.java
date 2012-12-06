package aufgabe2.scotlandyard;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import SYSimulation.src.sim.SYSimulation;
import aufgabe2.graph.AdjacencyListUndirectedGraph;
import aufgabe2.graph.DijkstraShortestPath;

public class aufgabe2_8 {
	
	private static final double BUS = 2;
	private static final double TAXI = 3;
	private static final double UBAHN = 5;

	public static void main(String[] args) {
		AdjacencyListUndirectedGraph<Integer> graph = new AdjacencyListUndirectedGraph<Integer>();
		
		for (int i = 1; i <= 199; ++i) {
			graph.addVertex(Integer.valueOf(i));
		}
		
		File f = new File("ScotlandYard.txt");
		Scanner s = null;
		
		try {
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String teile[] = line.split(" ");
				if (teile.length == 3) {
					Integer from = Integer.valueOf(teile[0]);
					Integer to = Integer.valueOf(teile[1]);
					
					Double weight = 0.0;
					if (teile[2].equals("Taxi")) {
						weight = TAXI;
					} else if (teile[2].equals("UBahn")) {
						weight = UBAHN;
					} else {
						weight = BUS;
					}					
					
					graph.addEdge(from, to, weight);
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			s.close();
		}	
		
		
		DijkstraShortestPath<Integer> navi = new DijkstraShortestPath<Integer>(graph);
		
		if (navi.searchAllShortestPaths(1))  {			
			
			SYSimulation sim = null;
			try {
				 sim  = new SYSimulation();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
						
			sim.startSequence("Kuerzester weg von 1 nach 188");
			List<Integer> visitOrder = new ArrayList<Integer>();
			visitOrder = navi.getShortestPathTo(7);
			
			for (int i = 1; i < visitOrder.size(); ++i) {
				Integer start = visitOrder.get(i - 1);
				Integer end = visitOrder.get(i);
				sim.visitStation(start);
				sim.visitStation(end);
				if(graph.getWeight(start, end) == UBAHN) {
					sim.drive(start, end, Color.RED);
				} else if (graph.getWeight(start, end) == BUS) {
					sim.drive(start, end, Color.GREEN);
				} else  if (graph.getWeight(start, end) == TAXI) {
					sim.drive(start, end, Color.YELLOW);
				}
			}
			sim.stopSequence();
		}
		
	}
	
}
