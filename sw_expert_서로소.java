package firstProject;

import java.util.Scanner;

public class sw_expert_서로소 {
	static int N, M;
	static int parent[];

	public static void set() {

		for (int i = 0; i < N; i++)
			parent[i] = i;
	}

	public static int findParent(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = findParent(parent[a]);
	}

	public static boolean check(int a, int b) {
		int aParent = findParent(a);
		int bParent = findParent(b);
		if (aParent == bParent)
			return true;
		else return false;
	}

	public static boolean union(int a, int b) {
		int aParent = findParent(a);
		int bParent = findParent(b);
		if (aParent == bParent)
			return true;

		if (aParent < bParent) {
			parent[bParent] = aParent;
		} else {
			parent[aParent] = bParent;
		}
		return false;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int Tc = sc.nextInt();
		for (int tc = 1; tc <= Tc; tc++) {
			N = sc.nextInt();
			parent = new int[N];
			M = sc.nextInt();
			set();
			System.out.printf("#%d ", tc);
			for (int i = 0; i < M; i++) {
				int check = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();
				a -= 1;
				b -= 1;
				if (check == 1) {
					if (check(a, b))
						System.out.print(1);
					else
						System.out.print(0);
				}
				if (check == 0) {
					union(a, b);
				}

			}
			System.out.println();
		}

	}
}
