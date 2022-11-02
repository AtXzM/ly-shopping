package com.atxin.glmall.华为0921;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//兵马寻路 0921第二题
public class sold_horse_rose {
    static char map[][];
    //每个点只走一次
    static int vis[][];
    static int dx[]={0,1,0,-1};
    static int dy[]={1,0,-1,0};

    static int dx_horse[]={-2,-1,1,2,-2,-1,1,2};
    static int dy_horse[]={-1,-2,-2,-1,1,2,2,1};
    //坐标队列
    static Queue<int []> corrdinate=new LinkedList<>();
    //步数队列
    static Queue<Integer> step=new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        vis=new int[m][n];
        map=new char[m][n];
        for(int i=0;i<m;i++){
            map[i]=sc.next().toCharArray();
        }

        //数组下标 0代表Y轴 1 代表X轴 2代表此时是兵还是马（0兵 1马）
        corrdinate.add(new int[]{0,0,0});
        step.add(0);
        while(corrdinate.size()>0){
            int [] cor=corrdinate.poll();

            int stp=step.poll();
            //到达终点
            if(cor[0]==map.length-1&&cor[1]==map[0].length-1){
                System.out.println(stp);
                break;
            }
            //此时是驿站 有两种情况 转变或者不转变
            if(map[cor[0]][cor[1]]=='S'){
                map[cor[0]][cor[1]]='X';

                //不转变
                corrdinate.add(cor);
                step.add(stp);
                //转变 消耗步数
                corrdinate.add(new int[]{cor[0],cor[1],cor[2]==0?1:0});
                step.add(stp+1);
            }
            else if(cor[2]==0) {
                //如果是兵 有四个方向可走
                for (int i = 0; i < 4; i++) {
                    int y = cor[0] + dy[i];
                    int x = cor[1] + dx[i];

                    if (y >= 0 && y < m && x >= 0
                            && x < n && vis[y][x] == 0 &&map[y][x]!='X'){
                        vis[y][x]=1;
                        corrdinate.add(new int[]{y,x,0});
                        step.add(stp+1);
                    }
                }
            }else{
                //如果是马 有八个方向可走
                for(int i=0;i<8;i++){
                    int y = cor[0] + dy_horse[i];
                    int x = cor[1] + dx_horse[i];
                    if (y >= 0 && y < m && x >= 0
                            && x < n && vis[y][x] == 0 &&map[y][x]!='X'){
                        vis[y][x]=1;
                        corrdinate.add(new int[]{y,x,1});
                        step.add(stp+1);
                    }
                }
            }
        }
    }
}
