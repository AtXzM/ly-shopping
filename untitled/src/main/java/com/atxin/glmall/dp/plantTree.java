package com.atxin.glmall.dp;

import java.util.*;
//记搜写法
public class plantTree {

    static  int n;
    static  int position[];
    static  int radis[];
    static  int value[];
    static int dp[][]=new int[10001][2];//表示在第i棵树，
    static List<String> list=new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();

        position=new int[n+1];
        radis=new int[n+1];
        value=new int[n+1];
        for(int i=0;i<dp.length;i++) Arrays.fill(dp[i],-1);
        for(int i=1;i<=n;i++)position[i]=sc.nextInt();
        for(int i=1;i<=n;i++)radis[i]=sc.nextInt();
        for(int i=1;i<=n;i++)value[i]=sc.nextInt();

        System.out.println(Math.max(dfs(1,1),dfs(1,0)));
    }

        static int dfs(int idx,int state){
        if(idx==n+1){
            return 0;
        }
        if(dp[idx][state]!=-1)return dp[idx][state];
        int flag=0;
        //对之前已经种过的树进行判断，如果不符合条件那么当前位置不能种树
        for(String radius:list){
            String [] tree_ra=radius.split(",");
            if(position[idx]>position[Integer.parseInt(tree_ra[2])]){
                if(position[idx]-radis[idx]<Integer.parseInt(tree_ra[1])){
                    flag=1;break;
                }



            }else{
                if(position[idx]+radis[idx]>Integer.parseInt(tree_ra[0])){
                    flag=1;break;
                }
            }
        }
        if(flag==1)return dp[idx][state]= dfs(idx+1,0);
        else {
            int upper=position[idx]+radis[idx];
            int lower=position[idx]-radis[idx];

            list.add(lower+","+upper+","+idx);
            //种树
            int value_1=dfs(idx+1,1)+value[idx];
            list.remove(list.size()-1);
            //不种
            int value_0=dfs(idx+1,0);
            return dp[idx][state]=Math.max(value_0,value_1);
        }
        }
}
