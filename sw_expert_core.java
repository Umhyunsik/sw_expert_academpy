import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}

	}

	
	
	static int[][] maps;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[] check;
	static ArrayList<Pair> list;
	static int[] save;
	static int corecnt;
	static int corelen;

	static boolean can(int x, int y, int rotate) {

		int nx = x + dx[rotate];
		int ny = y + dy[rotate];

		while (true) {
			if (maps[nx][ny] == 0 && (nx == 0 || nx == maps.length - 1 || ny == 0 || ny == maps.length - 1))
				return true;
			if (maps[nx][ny] == 1 || maps[nx][ny] == 2)
				return false;

			nx = nx + dx[rotate];
			ny = ny + dy[rotate];

		}

	}

	static int draw(int x, int y, int rotate) {

		int nx = x + dx[rotate];
		int ny = y + dy[rotate];
		int len = 0;
		while (true) {
			if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps.length) {
				break;
			}
			maps[nx][ny] = 2;
			len += 1;
			nx = nx + dx[rotate];
			ny = ny + dy[rotate];

		}
		return len;

	}

	static void erase(int x, int y, int rotate) {

		int nx = x + dx[rotate];
		int ny = y + dy[rotate];
		while (true) {
			if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps.length) {
				break;
			}
			maps[nx][ny] = 0;

			nx = nx + dx[rotate];
			ny = ny + dy[rotate];

		}

	}

	static void dfs(int index, int cnt, int len) {
		if (index == save.length) {
			if (cnt > corecnt) {
				corecnt = cnt;
				corelen = len;
			} else if (cnt == corecnt) {
				corelen = Math.min(corelen, len);
			}

			return;
		}

		Pair temp = list.get(save[index]);
		int x = temp.x;
		int y = temp.y;

		for (int i = 0; i < dx.length; i++) {
			if (can(x, y, i)) {
				int drawtemp = draw(x, y, i);
				dfs(index + 1, cnt + 1, drawtemp + len);
				erase(x, y, i);
			}
		}
		dfs(index + 1, cnt, len);

	}

	static void combi(int target, int cnt, int K) {
		if (cnt == K) {
			int idx = 0;
			for (int i = 0; i < check.length; i++) {
				if (check[i]) {
					save[idx++] = i;
				}
			}
			dfs(0, 0, 0);
			return;
		}
		if (target == list.size()) {
			return;
		}
		check[target] = true;
		combi(target + 1, cnt + 1, K);
		check[target] = false;
		combi(target + 1, cnt, K);

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int Tc = sc.nextInt();
		for (int tc = 1; tc <= Tc; tc++) {
			int N = sc.nextInt();
			maps = new int[N][N];
			list = new ArrayList<>();
			corelen=0;
			corecnt=0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maps[i][j] = sc.nextInt();
					if (maps[i][j] == 1) {
						if (i != 0 && j != 0 && j != N - 1 && i != N - 1) {
							list.add(new Pair(i, j));
						}
					}
				}
			}
			
			check = new boolean[list.size()];
			for (int i = 1; i <= list.size(); i++) {
				save = new int[i];
				combi(0, 0, i);
			}
			System.out.printf("#%d %d",tc,corelen);
			System.out.println();
		}

	}
}
