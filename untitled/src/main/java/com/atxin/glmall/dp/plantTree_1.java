package com.atxin.glmall.dp;

import java.util.Arrays;

public class plantTree_1 {
    public static void main(String[] args) {
        int n = 4;
        int[] pos = new int[]{2,3,4,5};
        int[] r= new int[]{1,1,1,2};
        int[] val = new int[]{50,10,40,70};
        System.out.println(getMaxValue(n,pos,val,r));

        n = 5;
        pos = new int[]{2,3,6,5,7};
        r= new int[]{1,1,3,1,1};
        val = new int[]{20,20,100,70,60};
        System.out.println(getMaxValue(n,pos,val,r));
    }

    public static int getMaxValue(int n,int[] pos,int[] val,int[] r){
        int res = 0;
        int[][] lance = new int[n][3];

        for(int i = 0; i < n;i++){
            lance[i][0] = pos[i] - r[i];
            lance[i][1] = pos[i] + r[i];
            lance[i][2] = val[i];
        }

        Arrays.sort(lance,(a, b)-> a[1] - b[1]);//注意数组是按照右边界进行排序的，只有保证右边界有序，dp方程才成立

        int[][] dp = new int[n][2];
        dp[0][0] = lance[0][2];//代表种树带来的价值
        dp[0][1] = 0;//代表不种树的价值

        for(int i = 1; i< n;i++){
            int tempValue = 0;
            for(int j = 0;j < i;j++){
                if(lance[j][1] <= lance[i][0])
                    tempValue = Math.max(tempValue,dp[j][0]);
//                else tempValue=Math.max(tempValue,dp[j][1]);
            }
            dp[i][0] = tempValue + lance[i][2]; //加上自己的价值，表示 种了这棵树能达到的最大价值
            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][0]);//表示不种这棵树的价值
            System.out.println(i+" "+dp[i][0]+" "+dp[i][1]);
            res = Math.max(Math.max(res,dp[i][0]),dp[i][1]);
        }
        return res;
    }
}

