package com.atxin.glmall.dp;

import java.util.Scanner;

public class caveExplore {
    static int n,m,ans=Integer.MAX_VALUE;
   static int cave_first[];
   static int cave_sec[];
   //在第几层山洞 走第几扇门（两扇门）的最小通关时间
   static int dp[][]=new int[1000001][2];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

         n= sc.nextInt();
         m=sc.nextInt();

         cave_first=new int[n+1];
         cave_sec=new int[n+1];
        for(int i=1;i<=n;i++){
            cave_first[i]=sc.nextInt();
            cave_sec[i]=sc.nextInt();
        }
        //分别以1-m作为山门颜色枚举
        for(int i=1;i<=m;i++){
            //数组山门颜色赋值
            cave_first[0]=i;cave_sec[0]=i;
            int crack_Time_1=0,crack_Time_2=0;

        for(int j=1;j<=n;j++){
            //前一层走第一扇门，本层走第一扇门的破解时间
             crack_Time_1=(cave_first[j-1]==cave_first[j]
                    ?0:cave_first[j-1]*cave_first[j]);
            //前一层走第二扇门，本层走第一扇门的破解时间
             crack_Time_2=(cave_sec[j-1]==cave_first[j]
                     ?0:cave_sec[j-1]*cave_first[j]);
            dp[j][0]=Math.min(dp[j-1][0]+crack_Time_1,dp[j-1][1]+crack_Time_2);
            //前一层走第一扇门，本层走第二扇门的破解时间
             crack_Time_1=(cave_first[j-1]==cave_sec[j]
                    ?0:cave_first[j-1]*cave_sec[j]);
            //前一层走第二扇门，本层走第二扇门的破解时间
             crack_Time_2=(cave_sec[j-1]==cave_sec[j]
                    ?0:cave_sec[j-1]*cave_sec[j]);
            dp[j][1]=Math.min(dp[j-1][0]+crack_Time_1,dp[j-1][1]+crack_Time_2);
        }
        ans=Math.min(Math.min(dp[n][0],dp[n][1]),ans);


        }
        System.out.println(ans);
    }


}
