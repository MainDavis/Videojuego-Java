package configuracion;

import java.util.Iterator;
import java.util.LinkedList;

public class DFS {
	private LinkedList<Integer> adjLists[];
	private boolean visited[];

	// Graph creation
	public DFS(int vertices) {
		adjLists = new LinkedList[vertices];
		visited = new boolean[vertices];

		for (int i = 0; i < vertices; i++)
			adjLists[i] = new LinkedList<Integer>();
	}

	// Add edges
	public void addEdge(int src, int dest) {
		adjLists[src].add(dest);
	}

	// DFS algorithm
	public void algoritmo(int vertex) {
		visited[vertex] = true;
		System.out.print(vertex + " ");

		Iterator<Integer> ite = adjLists[vertex].listIterator();
		while (ite.hasNext()) {
			int adj = ite.next();
			if (!visited[adj])
				algoritmo(adj);
		}
	}
}
