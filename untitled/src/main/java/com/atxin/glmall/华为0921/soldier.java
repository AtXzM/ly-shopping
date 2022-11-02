package com.atxin.glmall.华为0921;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class soldier {
    static int m,n;
    static char map[][]=new char[1001][1001];
    static int vis[][]=new int[1001][1001];
    static int dx[]={0,1,0,-1};
    static int dy[]={1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        m=sc.nextInt();
        n=sc.nextInt();
        int start[]=new int[2];
        int end[]=new int[2];
        for(int i=0;i<m;i++) {
            String str=sc.next();
            if(str.indexOf("S")!=-1){
                start[0]=i;start[1]=str.indexOf("S");
            }
            if(str.indexOf("E")!=-1){
                end[0]=i;end[1]=str.indexOf("E");
            }
            map[i]=str.toCharArray();
        }
        int dist[][]=new int[1001][1001];
        for(int i=0;i<1001;i++) Arrays.fill(dist[i],Integer.MAX_VALUE);
        dist[start[0]][start[1]]=0;
        Queue<int []> nextV=new PriorityQueue<>((a, b)->a[2]-b[2]);
        nextV.add(new int[]{start[0],start[1],0,0});

        while(nextV.size()>0){
            int curV[]=nextV.poll();

            for (int i = 0; i < 4; i++) {
                int y = curV[0] + dy[i];
                int x = curV[1] + dx[i];
                if (y >= 0 && y < m && x >= 0 && x < n && map[y][x] != 'X'&&vis[y][x]==0) {
                    if(curV[2]==0){
                            vis[y][x] = 1;
                            dist[y][x] = 1;
                            nextV.add(new int[]{y,x,1,0});
                            nextV.add(new int[]{y,x,1,1});
                    }else if(vis[y][x]==0){
                        vis[y][x]=1;
                    int time=(i%2)==(curV[3]%2)?1:2;
                    int dis=curV[2]+time;
                    int dir=(i%2)==(curV[3]%2)?curV[3]:curV[3]==0?1:0;
                    if(dist[y][x]>dis){
                        dist[y][x]=dis;
                        nextV.add(new int[]{y,x,dis,dir});
                    }
                    }
                }
            }
        }
         System.out.println(dist[end[0]][end[1]]);
    }
}
