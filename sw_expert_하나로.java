package firstProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class sw_expert_하나로 {

	static int[] parents;
	static int[] x;
	static int[] y;
	static int N;

	static class Pair implements Comparable<Pair> {
		int num_x;
		int num_y;
		double dist;

		public Pair(int num_x, int num_y, double dist) {
			this.num_x = num_x;
			this.num_y = num_y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.dist > o.dist) {
				return 1;
			} else if (this.dist == o.dist) {
				return 0;
			} else
				return -1;

		}
	}

	static void make() {// 크기가 1인 단위집합을 만든다.

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		// return findSet(parents[a]);//path compression 전 (값만 리턴해주기 )
		return parents[a] = findSet(parents[a]); // 리턴해주면서 부모 값으로 변경

	}

	static boolean union(int a, int b) {
		int aparent = findSet(a);
		int bparent = findSet(b);

		if (aparent == bparent) {// 이미 같은 그룹
			return false;
		}
		if (aparent > bparent) {
			parents[aparent] = bparent;
		} else {
			parents[bparent] = aparent;
		}

		return true;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int Tc = sc.nextInt();
		for (int tc = 1; tc <= Tc; tc++) {
			N = sc.nextInt();
			x = new int[N];
			y = new int[N];

			parents = new int[N];
			make();
			for (int i = 0; i < N; i++)
				x[i] = sc.nextInt();
			for (int i = 0; i < N; i++)
				y[i] = sc.nextInt();
			double E = sc.nextDouble();

			PriorityQueue<Pair> q = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double temp = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
					q.add(new Pair(i, j, temp * temp));
				}

			}

			double result = 0.0;
			int count = 0;
			while (!q.isEmpty()) {

				Pair temp = q.poll();
				if (union(temp.num_x, temp.num_y)) {
					result += temp.dist * E;
					if (++count == N - 1)
						break;
				}

			}

			System.out.printf("%d %d",tc,Math.round(result));
			System.out.println();

		}

	}
}
