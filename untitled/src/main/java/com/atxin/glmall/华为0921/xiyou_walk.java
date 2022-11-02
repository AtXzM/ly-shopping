package com.atxin.glmall.华为0921;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
4 4 10
10 20 30 40
100 120 140 160
200 230 260 290
300 400 500 600
 */
public class xiyou_walk {
    static int n,m,t;
    static int map[][]=new int[500][500];
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
    //代表每个点之前爆发次数走没走过
    static int vis[][][]=new int[500][500][10];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        m=sc.nextInt();
        n=sc.nextInt();
        t=sc.nextInt();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++)map[i][j]=sc.nextInt();
        }
        //数组元素代表 y轴 x轴 步数 剩余爆发次数
        Queue<int []> coordinate=new LinkedList<>();
        coordinate.add(new int[]{0,0,0,3});
        while(coordinate.size()>0){
            int nextV[]=coordinate.poll();
            if(nextV[0]==m-1&&nextV[1]==n-1){
                System.out.println(nextV[2]);
                break;
            }
            for(int i=0;i<4;i++){
                int y=nextV[0]+dy[i];
                int x=nextV[1]+dx[i];
                if(y>=0&&y<m&&x>=0&&x<n){
                         //高度差小于t不需要爆发
                        if(map[y][x]-map[nextV[0]][nextV[1]]<=t){
                            if(vis[y][x][nextV[3]]==0) {
                                vis[y][x][nextV[3]] = 1;
                                coordinate.add(new int[]{y, x, nextV[2] + 1, nextV[3]});
                            }
                        }else{
                            //需要爆发 将爆发次数减一
                            if(nextV[3]>0&&  vis[y][x][nextV[3]-1]==0){
                                vis[y][x][nextV[3]-1] = 1;
                                coordinate.add(new int[]{y, x, nextV[2] + 1, nextV[3]-1});
                            }
                        }

                }
            }
        }
    }
}
