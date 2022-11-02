package com.atxin.glmall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class walkSteps {
    static int mod = (int) 1e9 + 7;
    static int dp[][][];
    static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        dp = new int[n][m + 10][m + 10];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m + 10; j++)
                for (int z = 0; z < m + 10; z++) Arrays.fill(dp[i][j], -1);
List<String> l4=new ArrayList<String>();
      System.out.println(dfs(0, 0, 0, l4));
    }

    //preStep_1 前一步  preStep_2 前两步
    static int dfs(int currStep, int preStep_1, int preStep_2, List<String> list) {
        if (currStep == n) {
//            for(int i=0;i<list.size();i++)System.out.println(list.get(i));
//            System.out.println("已到达");
            return 1;
        }
        if (currStep > n) return 0;

        if (dp[currStep][preStep_1][preStep_2] != -1) return dp[currStep][preStep_1][preStep_2];
        int sum = 0;
        for (int i = m; i > 0; i--) {

            if (i != preStep_1 && i != preStep_2) {
                list.add(i + " " + preStep_1 + " " + preStep_2);
                sum = (sum + dfs(currStep + i, i, preStep_1, list)) % mod;
                list.remove(list.size() - 1);
//                    System.out.println(i);
            }


        }

        dp[currStep][preStep_1][preStep_2] = sum;
        return sum;
    }
}
