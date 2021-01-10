package configuracion;

import java.util.Stack;

public class DFS {
	Stack<Integer> stack;
	int first;
	int[][] adjMatrix;
	int[] visited = new int[7];

	public DFS(int[][] Matrix) {

		this.adjMatrix = Matrix;
		stack = new Stack<Integer>();
		int[] node = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int firstNode = node[0];
		depthFirstSearch(firstNode, 7);
	}

	public void depthFirstSearch(int first, int n) {
		int v, i;

		stack.push(first);

		while (!stack.isEmpty()) {
			v = stack.pop();
			if (visited[v] == 0) {
				System.out.print("\n" + (v + 1));
				visited[v] = 1;
			}
			for (i = 0; i < n; i++) {
				if ((adjMatrix[v][i] == 1) && (visited[i] == 0)) {
					stack.push(v);
					visited[i] = 1;
					System.out.print(" " + (i + 1));
					v = i;
				}
			}
		}
	}
}
