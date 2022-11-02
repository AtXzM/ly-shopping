package com.atxin.glmall.华为0921;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class maliya {
    static int m,n,k;
    static int defect[]=new int[100];
    static int dp[][]=new int[100][100];
    public static void main(String[] args) {
        Set<Integer> set=new HashSet<>();
        Scanner sc=new Scanner(System.in);
        m=sc.nextInt();
        n=sc.nextInt();
        k=sc.nextInt();
        for(int i=0;i<k;i++)
            set.add(sc.nextInt());


        for(int i=1;i<=m;i++)
            dp[0][i]=1;



        for(int i=1;i<=n+1;i++){
            for(int j=1;j<=m;j++){
                for(int z=1;z<=3;z++){
                    if(i-z>=0){
                        if(set.contains(i))dp[i][j]+=dp[i-z][j-1];
                        else dp[i][j]+=dp[i-z][j];
                    }
                }
            }
        }
        System.out.println(dp[n+1][m]);

    }
}
