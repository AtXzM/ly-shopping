package com.atxin.glmall.dp;

import java.util.Scanner;

public class Tocharge {
    static int charge[] = new int[100000];
    static int[] wait = new int[100000];
    static int N, dis;
    class Solution {
        int dp[];
        public int change(int amount, int[] coins) {
            dp=new int[amount+2];

            for(int i=0;i<coins.length;i++){
                for(int j=coins[i];j<=amount;j++){
                    dp[j]+=dp[j-coins[i]];
                }
            }
            return dp[amount];
        }

    }
    public static void main(String[] args) {
        int dp[][] = new int[10001][1001];
        Scanner sc = new Scanner(System.in);
        dis = sc.nextInt();
        dis = dis / 100;
        N = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            charge[i] = sc.nextInt();
            charge[i]=charge[i]/100;
            wait[i] = sc.nextInt();
        }
        for (int j = 0; j <= 10; j++) {
            if (dis - charge[N] > j) {
                dp[N][j] = wait[N] + dis - charge[N]+1;
            } else dp[N][j] = dis - charge[N];
        }
        for(int i=N-1;i>=0;i--){
            for(int j=0;j<=10;j++){
                if(charge[i+1]-charge[i]>j){
                    dp[i][j]=dp[i+1][10-(charge[i+1]-charge[i])]+wait[i]+1+(charge[i+1]-charge[i]);
                }else{
                    dp[i][j]=Math.min(dp[i+1][10-(charge[i+1]-charge[i])]+wait[i]+1,
                            dp[i+1][j-(charge[i+1]-charge[i])])+(charge[i+1]-charge[i]);
                }
            }
        }
        System.out.println(dp[0][10]);
    }
}