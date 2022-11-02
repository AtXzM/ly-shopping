package com.atxin.glmall.华为0921;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
/*
6 6
SBBBBB
BXXXXB
BBXBBB
XBBXXB
BXBBXB
BBXBEB
 */
public class soldier_分层 {
    static int m,n;
    static char map[][]=new char[1001][1001];
    static int vis[][][]=new int[1001][1001][2];
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
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
        System.out.println(start[0]+" "+start[1]+" "+end[0]+" "+end[1]);
        Queue<int []> nextV=new PriorityQueue<>((a,b)->a[2]-b[2]);
        nextV.add(new int[]{start[0],start[1],0,0});

        while(nextV.size()>0) {
            int[] currV = nextV.poll();
            System.out.println(currV[0]+" "+currV[1]+" "+currV[2]+" "+currV[3]);
            if (currV[0] == end[0] && currV[1] == end[1]) {
                System.out.println(currV[2]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int y = currV[0] + dy[i];
                int x = currV[1] + dx[i];
                if (y >= 0 && y < m && x >= 0 && x < n&&map[y][x]!='X') {
                    //第一个点到附近的点都只需要一步
                   if(currV[2]==0){
                           vis[y][x][0]=1;
                           nextV.add(new int[]{y,x,1,0});
                           vis[y][x][1]=1;
                           nextV.add(new int[]{y,x,1,1});
                   }else{
                       if(currV[3]==0){

                           if(i==1||i==3){
                               if(currV[0]==1&&currV[1]==0&&i==1)System.out.println("这里放了吗"+y+" "+x);
                               if(vis[y][x][0]==0) {
                                   if(currV[0]==1&&currV[1]==0)System.out.println("这里放了吗");
                                   vis[y][x][0] = 1;
                                   nextV.add(new int[]{y, x, currV[2] + 1, 0});
                               }
                           }else if(i==2||i==0){
                               if(vis[y][x][1]==0) {
                                   vis[y][x][1] = 1;
                                   nextV.add(new int[]{y, x, currV[2] + 2, 1});
                               }
                           }
                       }else{
                           if(i==1||i==3){
                               if(vis[y][x][0]==0) {
                                   vis[y][x][0] = 1;
                                   nextV.add(new int[]{y, x, currV[2] + 2, 0});
                               }
                           }else if(i==2||i==0){
                               if(vis[y][x][1]==0) {
                                   vis[y][x][1] = 1;
                                   nextV.add(new int[]{y, x, currV[2] + 1, 1});
                               }
                           }
                       }
                   }

                }
            }
        }

    }
}

