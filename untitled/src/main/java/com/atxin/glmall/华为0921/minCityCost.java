package com.atxin.glmall.华为0921;

import java.util.*;

//图二分
public class minCityCost {
    static List<city>[] ve=new List[100001];
    static  int n,m,k;
    static int start[]=new int[100001];
    static int end[]=new int[100001];
    static int cost[]=new int[100001];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<100001;i++)ve[i]=new ArrayList<>();
         n=sc.nextInt();
         m=sc.nextInt();
         k=sc.nextInt();
         for(int i=0;i<m;i++)start[i]=sc.nextInt();
         for(int i=0;i<m;i++)end[i]=sc.nextInt();
         for(int i=0;i<m;i++)cost[i]=sc.nextInt();
         for(int i=0;i<m;i++){
             ve[start[i]].add(new city(end[i],cost[i]));
             ve[end[i]].add(new city(start[i],cost[i]));
         }

        int low=1,high=10;
        while(low<high){
            int mid=low+high>>1;

            if(check(mid))high=mid;
            else low=mid+1;
        }
        System.out.println(low);


    }
    static boolean check(int x){
        Queue<int []> nextV=new LinkedList<>();
        int vis[]=new int[100001];
        nextV.add(new int[]{1,0});
        while(nextV.size()>0){
            int currV[]=nextV.poll();
            if(currV[1]>k)return false;
            if(currV[0]==n)return true;

            for(city cit:ve[currV[0]]){
                if(cit.code>x)continue;
                if(vis[cit.end]==0) {
                    nextV.add(new int[]{cit.end, currV[1] + 1});
                    vis[cit.end]=1;
                }
            }
        }
        return false;
    }
    static class city{
        int end,code;
        public city(int end, int code) {
            this.end = end;
            this.code = code;
        }
    }


}
