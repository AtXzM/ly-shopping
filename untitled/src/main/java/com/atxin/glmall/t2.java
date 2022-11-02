package com.atxin.glmall;

import java.util.Arrays;
import java.util.Scanner;

public class t2 {
    static char s[];
    static String str;
    static int dp[][][];

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        str=sc.next();
        s=str.toCharArray();

        int value=1,max=1;
        for(int i=0;i<str.length();i++){
          if(s[i]=='0')max++;
          if(s[i]=='1')value++;
        }
        max=Math.max(value,max);
            System.out.println(max);
        dp=new int[1000][1000][2];
for(int i=0;i<str.length();i++)
    for(int j=0;j<max+1;j++)
        Arrays.fill(dp[i][j],-1);
        System.out.println(dfs(0,0,0));
    }
    static int dfs(int idx,int preCost,int preValue){
        if(idx==str.length())return 0;
//        System.out.println(preCost);
  if(dp[idx][preCost][preValue]!=-1)return dp[idx][preCost][preValue];
       if((char)(preValue+'0')==s[idx]&&idx!=0){
           return dp[idx][preCost][preValue]= Math.max(dfs(idx+1,preCost+1,s[idx]-'0')+preCost+1,
                   dfs(idx+1,preCost,preValue));

       }
       else{
           return
                   dp[idx][preCost][preValue]= Math.max(dfs(idx+1,1,s[idx]-'0')+1,
                   dfs(idx+1,preCost,preValue));
       }
    }
}
