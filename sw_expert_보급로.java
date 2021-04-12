import java.util.PriorityQueue;
import java.util.Scanner;

public class sw_expert_º¸±Þ·Î {

	static int N;
	static int maps[][];
	static boolean visited[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Pair implements Comparable<Pair> {

		int x;
		int y;
		int foot;

		public Pair(int x, int y, int foot) {
			super();
			this.x = x;
			this.y = y;
			this.foot = foot;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.foot - o.foot;
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int Tc = sc.nextInt();
		for (int tc = 1; tc <= Tc; tc++) {
			N = sc.nextInt();
			maps = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String temp = sc.next();
				for (int j = 0; j < temp.length(); j++) {
					maps[i][j] = Character.getNumericValue(temp.charAt(j));
				}
			}

			System.out.println("#"+tc+" "+bfs());
		}

	}

	private static int bfs() {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(0, 0, 0));
		visited[0][0] = true;
		int ans=0;
		while (!pq.isEmpty()) {

			Pair temp = pq.poll();

			if (temp.x == maps.length - 1 && temp.y == maps.length - 1) {
				ans=temp.foot;
				break;
				
			}
			for (int i = 0; i < dx.length; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps.length)
					continue;

				if (!visited[nx][ny]) {
					pq.add(new Pair(nx, ny, temp.foot + maps[nx][ny]));
					visited[nx][ny] = true;
				}
			}

		}
		return ans;

	}
}
