package com.atxin.glmall;

import java.util.Arrays;
import java.util.Scanner;

public class t3 {
   // 充电站距离
    static int chargeDis[];
   // 排队时间
    static int queue[];
    static int dis,N;
    //表示 在走了多少公里,到第几个充电站，还有多少电力的情况下到终点的最短时间
    static int dp[][][];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
       dis= sc.nextInt();
       //因为距离是一百的整数倍 直接除100
        //时间复杂度为 10000*10000*10 肯定TLE
        dis=dis/100;
        chargeDis=new int[dis+1];
        queue=new int[dis+1];
         N=sc.nextInt();
         dp=new int[dis][N][11];
        for(int i=0;i<N;i++){
            chargeDis[i]=sc.nextInt()/100;
            queue[i]=sc.nextInt();
        }
     for(int i=0;i<dis;i++)
         for(int j=0;j<N;j++)
             Arrays.fill(dp[i][j],-1);
        System.out.println(dfs(0,0,10));
    }
    //走了多少距离,到底几个充电桩，还剩多少电力
    static int dfs(int endDis,int n,int eletric) {
        //到终点
        if (endDis == dis) return 0;
        if(dp[endDis][n][eletric]!=-1)return dp[endDis][n][eletric];
        //已经到最后一个充电桩 只有两种情况 如果电力能走到最后那么肯定不用充电了
        if(n==N-1){
            if(eletric>=dis-endDis)
                return dp[endDis][n][eletric]= dfs(endDis+1,n,eletric-1)+1;
            //如果走不到 充电
            else return dp[endDis][n][eletric]= dfs(endDis,n,10)+1+queue[n];
        }
        //如果当前距离有充电桩
        if (endDis == chargeDis[n]) {
            //如果能走到下一个充电桩 那么当前充电桩可以充，也可以不充，到下一个充
            if (chargeDis[n + 1] - endDis <= eletric)
                return dp[endDis][n][eletric]= Math.min(dfs(endDis, n + 1, 10) + 1 + queue[n],
                        dfs(endDis + 1, n + 1, eletric - 1) + 1);

            else {
          // 走不到 必须充
                return dp[endDis][n][eletric]=dfs(endDis, n + 1, 10) + 1 + queue[n];
            }

        }//当前距离无充电桩 只能继续往下走
        else{
            return dp[endDis][n][eletric]=dfs(endDis+1,n,eletric-1)+1;
        }
    }
}
