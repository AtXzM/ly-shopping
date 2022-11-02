package com.atxin.glmall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class sha {
    static List<Map> map[]=new List[30];
    static int desert[][]=new int[30][30];
    static int []State=new int [30];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<30;i++)
            map[i]=new ArrayList();

        for(int i=1;i<=27;i++)
            State[i]=sc.nextInt();

       while(sc.hasNextInt()) {
           int start = sc.nextInt();
           int end = sc.nextInt();

          desert[start][end]=1;

       }





        int weight=1049; int ans=0;
        int lowWater=31,lowFood=29;
        for(int i=0;weight>0;i++){
            weight-=3*i;
            int tempWater=lowWater+i;
            int maxFood=weight/2;
            for(int j=maxFood;j>=lowFood;j--){

            }
        }
//        System.out.println(ans);
    }
//    static int MaxValue(int Vt,int state,int time,){
//       if(Vt==27)
//    }
    static class Map{
        int id;
        int state;

        public Map(int id, int state) {
            this.id = id;
            this.state = state;
        }
    }
}
