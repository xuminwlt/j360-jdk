package me.j360.jdk.common.order;

import java.util.Arrays;

/**
 * Created with j360-jdk -> me.j360.jdk.common.order.
 * User: min_xu
 * Date: 2015/10/21
 * Time: 9:34
 * 说明：冒泡排序
 */
public class BubbleSort {

    public static  void main(String[] args){
        int[] ints = {3,2,4,6,7,1};
        bubble_0(ints, 6);
        for(int i:ints){
            System.out.println(i);
        }

    }
    public static void bubble_0(int[] a,int n){
        for(int i =0 ; i< n-1; ++i) {
            for(int j = 0; j < n-i-1; ++j) {
                if(a[j] > a[j+1])
                {
                    int tmp = a[j] ; a[j] = a[j+1] ;  a[j+1] = tmp;
                }
            }
        }
    }


    /**
     * 冒泡排序算法的改进
     对冒泡排序常见的改进方法是加入一标志性变量exchange，用于标志某一趟排序过程中是否有数据交换，如果进行某一趟排序时并没有进行数据交换，
     则说明数据已经按要求排列好，可立即结束排序，避免不必要的比较过程。本文再提供以下两种改进算法：
     1．设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可。
     改进后算法如下:
     * */
    public static void bubble_1 ( int r[], int n) {
        int i= n -1;  //初始时,最后位置保持不变
        while ( i> 0) {
            int pos= 0; //每趟开始时,无记录交换
            for (int j= 0; j< i; j++)
                if (r[j]> r[j+1]) {
                    pos= j; //记录交换的位置
                    int tmp = r[j]; r[j]=r[j+1];r[j+1]=tmp;
                }
            i= pos; //为下一趟排序作准备
        }
    }

    /**
     * 传统冒泡排序中每一趟排序操作只能找到一个最大值或最小值,我们考虑利用在每趟排序中进行正向和反向两遍冒泡的方法一次可以得到两个最终值(最大者和最小者) , 从而使排序趟数几乎减少了一半。
     改进后的算法实现为:
     * */
    public static void bubble_2 ( int r[], int n){
        int low = 0;
        int high= n -1; //设置变量的初始值
        int tmp,j;
        while (low < high) {
            for (j= low; j< high; ++j) //正向冒泡,找到最大者
                if (r[j]> r[j+1]) {
                    tmp = r[j]; r[j]=r[j+1];r[j+1]=tmp;
                }
            --high;                 //修改high值, 前移一位
            for ( j=high; j>low; --j) //反向冒泡,找到最小者
                if (r[j]<r[j-1]) {
                    tmp = r[j]; r[j]=r[j-1];r[j-1]=tmp;
                }
            ++low;                  //修改low值,后移一位
        }
    }

    /**
     * 快速排序
     * */


}
