
import java.util.Scanner;

public class sw_expert_Å°¼ø¼­ {

	static int N, M;
	static Edge[] edge;
	static Edge[] revEdge;
	static int[] visited;
	static int save[];
	static int revSave[];

	static class Edge {

		int vertex;
		Edge nextEdge;

		public Edge(int vertex, Edge nextEdge) {
			super();
			this.vertex = vertex;
			this.nextEdge = nextEdge;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int Tc = sc.nextInt();
		for (int tc = 1; tc <= Tc; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			edge = new Edge[N + 1];
			revEdge = new Edge[N + 1];
			for (int i = 0; i < M; i++) {
				int small = sc.nextInt();
				int big = sc.nextInt();
				edge[small] = new Edge(big, edge[small]);
				revEdge[big] = new Edge(small, revEdge[big]);
			}
			save = new int[N + 1];
			revSave = new int[N + 1];
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				visited = new int[N + 1];
				visited[i] = 1;

				save[i] = dfs(i);

				visited = new int[N + 1];
				visited[i] = 1;
				revSave[i] = revdfs(i);

				if (save[i] + revSave[i] == N + 1)
					ans += 1;
			}
			System.out.println("#"+tc+" "+ans);
		}

	}

	private static int revdfs(int i) {
		int sum = 1;
		for (Edge now = revEdge[i]; now != null; now = now.nextEdge) {

			if (visited[now.vertex] == 0) {
				visited[now.vertex] = 1;
				sum += revdfs(now.vertex);

			}

		}

		return sum;
	}

	private static int dfs(int i) {
		int sum = 1;
		for (Edge now = edge[i]; now != null; now = now.nextEdge) {

			if (visited[now.vertex] == 0) {
				visited[now.vertex] = 1;
				sum += dfs(now.vertex);

			}

		}

		return sum;
	}
}
