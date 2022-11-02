package com.atxin.glmall;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class maxMultiple {
    static long num[];
    static int n,k;
  static  long dp[][];
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        k=sc.nextInt();
        dp=new long[k][n];
        num=new long[n];
        for(int i=0;i<n;i++)num[i]=sc.nextInt();
        for(int i=0;i<k;i++)
            Arrays.fill(dp[i],-1);

        System.out.println(dfs(0,0));
    }
  static long dfs(int kMultiple,int idx){
        if(idx==n&&kMultiple%k==0)return 0;
        if(idx==n)return Integer.MIN_VALUE;
        if(dp[kMultiple][idx]!=-1)return dp[kMultiple][idx];


            return dp[kMultiple][idx] =
                    Math.max(dfs((int) (kMultiple + num[idx]) % k, idx + 1
                    ) + num[idx], dfs(kMultiple, idx + 1));


    }
}
