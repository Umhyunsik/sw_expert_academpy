package firstProject;

import java.util.Scanner;

public class sw_expert_사람네트워크2 {

		
public static void main(String[] args) {
	
	Scanner sc =new Scanner(System.in);
	int Tc=sc.nextInt();
	for(int tc=1;tc<=Tc;tc++) {
	int N=sc.nextInt();
	
	int [][] maps  =new int[N][N];
	//int [][] dp= new int [N][N];
	for(int i=0;i<maps.length;i++) {
		for(int j=0;j<maps.length;j++) {
			maps[i][j]=sc.nextInt();
			if(maps[i][j]==0&&i!=j)maps[i][j]=Integer.MAX_VALUE;
		}
	}
	
	for(int k=0;k<N;k++) {
		for(int i=0;i<N;i++) {
			if(i==k)continue;
			for(int j=0;j<N;j++) {
				if (i==j || j==k)continue;
				if(maps[i][k]!=Integer.MAX_VALUE && maps[k][j]!=Integer.MAX_VALUE && maps[i][k]+maps[k][j]<maps[i][j]) {
					maps[i][j]=maps[i][k]+ maps[k][j];
				}
			}
		}
	}
	int count=Integer.MAX_VALUE;
	for(int i=0;i<N;i++) {
		int sum=0;
		for(int j=0;j<N;j++) {
			if(i==j)continue;
			sum+=maps[i][j];
		}
		if(count>sum)count=sum;
	}
	System.out.printf("#%d %d",tc,count);
	System.out.println();
	}
	
}
}
