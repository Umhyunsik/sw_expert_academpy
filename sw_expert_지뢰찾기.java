import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_expert_Áö·ÚÃ£±â {

	static char maps[][];

	static boolean [][] visited;
 	static int[] dx = { 0, 1, 0, -1, -1,  1,-1, 1 };
	static int[] dy = { 1, 0, -1, 0, -1, -1, 1, 1 };

	static class Point {

		int x, y;
		int count;

		public Point(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int Tc=sc.nextInt();
		for(int t=1;t<=Tc;t++) {
		int N = sc.nextInt();
		maps = new char[N][N];
		visited=new boolean [N][N];
		for (int i = 0; i < N; i++) {
			String temp = sc.next();
			for (int j = 0; j < temp.length(); j++) {
				maps[i][j] = temp.charAt(j);
			}
		}
		int ans=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maps[i][j] == '.'&&!visited[i][j]) {
					
					int count = 0;
					for (int n = 0; n < dx.length; n++) {
						int nx = i + dx[n];
						int ny = j + dy[n];
						if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps.length)
							continue;
						if (maps[nx][ny] == '*') {
							count += 1;
						}

					}
					if (count == 0) {
						ans+=1;
						bfs(i, j);
						
					}
					

				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(maps[i][j]=='.')ans+=1;
			}
		}
		System.out.println("#"+t+" "+ans);

		}
	}
	private static void bfs(int i, int j) {
		maps[i][j] = (char) ('0');
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j, 1));
		visited[i][j] = true;
		while (!q.isEmpty()) {

			Point temp = q.poll();
			int count = 0;
			for (int n = 0; n < dx.length; n++) {
				int nx = temp.x + dx[n];
				int ny = temp.y + dy[n];
				if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps.length)
					continue;
				if (maps[nx][ny] == '*') {
					count += 1;
				}

			}
			maps[temp.x][temp.y] = (char) (count + '0');     
			
			if (count == 0) {
				for (int n = 0; n < dx.length; n++) {
					int nx = temp.x + dx[n];
					int ny = temp.y + dy[n];
					if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps.length)
						continue;
					if (maps[nx][ny] == '.' && !visited[nx][ny]) {
						count += 1;
						visited[nx][ny] = true;

						q.add(new Point(nx, ny, temp.count + 1));
					}
				}
				

			}
		}
		//printmaps(maps);
	

	}


	private static void printmaps(char[][] copymaps) {
		for(int i=0;i<copymaps.length;i++) {
			for(int j=0;j<copymaps.length;j++) {
				System.out.print(copymaps[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	

	
}
