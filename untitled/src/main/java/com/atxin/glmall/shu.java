package com.atxin.glmall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class shu {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<first> l1=new ArrayList<first>();
//        List
for(int i=1;i<59;i++){
    if(i!=15&&i!=17) {
        String id = sc.next();
        String wu1 = sc.next();
        String lexing = sc.next();
        if (i != 19 && i != 40 && i != 58 && i != 48) sc.next();
        String state = sc.next();
        l1.add(new first(id, lexing, state));
    }
}
//for(int i=0;i<)
    }
    static class first{
        String id;
        String leixing;

        public first(String id, String leixing, String state) {
            this.id = id;
            this.leixing = leixing;
            this.state = state;
        }

        String state;
    }

    static class sec{
        List<String> l1;

        public sec(List<String> l1) {
            this.l1 = l1;
        }
    }
}
