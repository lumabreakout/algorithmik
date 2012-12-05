package aufgabe2.scotlandyard;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import SYSimulation.src.sim.SYSimulation;
import aufgabe2.graph.AdjacencyListUndirectedGraph;
import aufgabe2.graph.GraphTraversion;

public class aufgabe2_6 {
	

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
				if (teile.length == 3 && teile[2].equals("Taxi")) {
					Integer from = Integer.valueOf(teile[0]);
					Integer to = Integer.valueOf(teile[1]);
					graph.addEdge(from, to);
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			s.close();
		}	
		
		
		List<Integer> visitOrder = GraphTraversion.depthFirstSearch(graph, 1);
		SYSimulation sim = null;
		try {
			 sim  = new SYSimulation();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sim.startSequence("Tiefensuche");
		for (int i = 1; i < visitOrder.size(); ++i) {
			sim.drive(visitOrder.get(i - 1), visitOrder.get(i));
		}
		sim.stopSequence();
		
		
		
	    visitOrder = GraphTraversion.breadthFirstSearch(graph, 1);
		
		sim.startSequence("Breitensuche");
		for (int i = 1; i < visitOrder.size(); ++i) {
			sim.drive(visitOrder.get(i - 1), visitOrder.get(i), Color.RED);
		}
		sim.stopSequence();
		
		 
		
		
	}

}
