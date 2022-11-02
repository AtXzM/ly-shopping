package com.atxin.glmall.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class minCost {
    static List<map> edge[] = new List[100001];
    static int start[]=new int[100001];
    static int end[]=new int[100001];
    static int spend[]=new int[100001];
    static int minSpend,k;
    static int vis[]=new int[100001];
    public static void main(String[] args) throws IOException {
        StreamTokenizer s =new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        s.nextToken();
        int n =(int)s.nval;
        for (int i = 1; i <= n; i++)
            edge[i] = new ArrayList<>();

        s.nextToken();
        int m =(int)s.nval;
        s.nextToken();
         k =(int)s.nval;
        for(int i=0;i<m;i++){
            s.nextToken();
            start[i] =(int)s.nval;
        }
        for(int i=0;i<m;i++){
            s.nextToken();
            end[i] =(int)s.nval;
        }
        for(int i=0;i<m;i++){
            s.nextToken();
            spend[i] =(int)s.nval;
        }
        for(int i=0;i<m;i++){
            edge[start[i]].add(new map(end[i],spend[i]));
            edge[end[i]].add(new map(start[i],spend[i]));
        }
        PriorityQueue<vMinCost> spot=new PriorityQueue<>();
        spot.add(new vMinCost(1,-1,0));

        while(spot.size()>0){
            vMinCost drop=spot.poll();
            if(drop.a==n)
                break;
            if(vis[drop.a]==1)continue;
            vis[drop.a]=1;
            if(!isLegalStep(drop.a,n, drop.step))continue;
            minSpend=Math.max(minSpend, drop.b);
            for(map dot:edge[drop.a])
                spot.add(new vMinCost(dot.end, dot.code, drop.step+1));

        }
      System.out.println(minSpend);
    }
    static boolean isLegalStep(int start,int end,int step){
        int step_e=0;
        PriorityQueue<vMinCost> spot=new PriorityQueue<>();
        spot.add(new vMinCost(start,-1,step));
        int vis[]=new int[100001];
        while(spot.size()>0){
            vMinCost drop=spot.poll();
            if(drop.a==end){
                step_e=drop.step;
                break;
            }
            if(vis[drop.a]==1)continue;
            vis[drop.a]=1;
            for(map dot:edge[drop.a])
                spot.add(new vMinCost(dot.end, dot.code, drop.step+1));

        }
       return step_e>k?false:true;
    }
    static class map {
        int end, code;
        public map(int end, int code) {
            super();
            this.end = end;
            this.code = code;
        }

    }
    //优先队列排序
    static class vMinCost implements Comparable<vMinCost>{
        int a,b,step;
        public vMinCost(int a, int b,int step) {
            super();
            this.a = a;
            this.b = b;
            this.step=step;
        }
        @Override
        public int compareTo(vMinCost o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.b, o.b);
        }
    }
}
