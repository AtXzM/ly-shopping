package com.atxin.glmall.华为0921;

import java.util.Arrays;
import java.util.Scanner;

public class logLimit {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int sum=0;
        int records[]=new int[n];
        for(int i=0;i<n;i++){
            records[i]=sc.nextInt();
            sum+=records[i];
        }
        //前缀和数组
        int preSum[]=new int[n];

        int total=sc.nextInt();

        Arrays.sort(records);
        preSum[0]=records[0];
        for(int i=1;i<n;i++)
            preSum[i]=preSum[i-1]+records[i];


        if(sum<=total)System.out.println(-1);
        else{
            //从1到records数组最大值开始二分 限流值如果比records最大值还大就没有意义了
            for(int i=1;i<=records[n-1];i++){
                int low=0,high=n-1;
                //寻找>=i的后继
                while (low < high)
                {
                    int mid = (low + high) >>1;
                    if (records[mid] >= i)
                        high = mid;

                    else
                        low = mid + 1;
                }
//               S ystem.out.println(low);
                if(low==0){
                    if(n*i>total){
                        System.out.println(i-1);
                        break;
                    }
                }
                else {
                    //每次二分判断一下超没超过total 如果超过直接输出上一个i
//                    System.out.println(preSum[low-1]+"  "+(n-low)*i+" "+i);
                    if(preSum[low-1]+(n-low)*i>total){
                        System.out.println(i-1);
                        break;
                    }

               }
            }
        }

    }
}
