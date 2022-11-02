package com.atxin.glmall.dp;

import java.util.Scanner;

public class legalSeque {
    static int dp[][];
    static int mod=(int)1e9+7;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        char seq[]=str.toCharArray();
        dp=new int[str.length()+1][10];


        for(int i=1;i<=str.length();i++){
            int st=(seq[i-1]-'0')%9;
            dp[i][st]=1;
            for(int j=0;j<=9;j++){
                dp[i][j]+=(dp[i-1][j]+dp[i-1][(j-st+9)%9])%mod;
            }
        }
        System.out.println(dp[str.length()][0]);
    }
}
