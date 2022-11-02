package com.atxin.glmall.华为0921;

import java.util.HashMap;
import java.util.Scanner;
//养猪场防疫
public class raisPigs {
    static int n,m;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        sc.nextLine();
        HashMap<Integer,Integer> family=new HashMap<>();
        HashMap<Integer,Integer> path=new HashMap<>();
        while(m-->0){
            String mother_son[]=sc.nextLine().split("\\s+");
            for(int i=1;i<mother_son.length;i++){
                family.put(Integer.parseInt(mother_son[i]),Integer.parseInt(mother_son[0]));
            }
        }
        int flag=0;
        int kid_1=sc.nextInt(); int kid_2=sc.nextInt();
        path.put(kid_1,0);
        path.put(kid_2,0);
        while(family.containsKey(kid_1)||family.containsKey(kid_2)){
         if(family.containsKey(kid_1)) {
             int level_1=path.get(kid_1);
             kid_1=family.get(kid_1);
             path.put(kid_1,level_1+1);
             if(path.containsKey(kid_1)){
                 flag=1;
                 System.out.println(Math.abs(level_1-path.get(kid_1)));
                 break;
             }
         }
         if(family.containsKey(kid_2)) {
             int level_2=path.get(kid_2);
             kid_2=family.get(kid_2);
             path.put(kid_2,level_2+1);
             if(path.containsKey(kid_2)){
                 flag=1;
                 System.out.println(Math.abs(level_2-path.get(kid_2)));
                 break;
             }
         }

        }
    }
}
